package com.brndbot.promo;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
//import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.brndbot.client.BrandPersonality;
import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.Promotion;
import com.brndbot.client.style.StyleSet;
import com.brndbot.client.parser.StyleSetParser;
import com.brndbot.db.DbConnection;
import com.brndbot.db.Organization;
import com.brndbot.db.Personality;
import com.brndbot.db.User;
import com.brndbot.system.BrndbotException;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.SystemProp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.xhtmlrenderer.extend.UserInterface;

/**
 *  Client is a class that holds the
 *  models and styles referring to an organization.
 *  This information drives everything.
 *  
 *  A Client object needs to be stored as a session attribute.
 *  However, we don't want this to become a huge amount of data,
 *  since in a clustering environment this can result in a lot
 *  of disk activity. Reference the promotion prototypes and
 *  models through a cache, not through the Client.
 *  
 *  Classes we should NOT incorporate into the Client:
 *  
 *  
 */
public class Client implements Serializable {

	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(Client.class);
	
	/** Key into the ClientCache */
	private int cacheKey;
	
	/** Time of last touch */
	private Date touchStamp;
	
	/** The ClientCache instance. The implementation should ideally be in the
	 *  configuration data, but that can be done later. */
	private static ClientCache clientCache = SingleJVMClientCache.getClientCache();
	
	/** Validity flag. When a Client is removed from the cache, it is marked
	 *  as no longer valid. */
	private boolean valid;
	
	/** An instance of the ClientInterface subclass which feeds 
	 *  us data 
	 */
	private transient ClientInterface clientInterface;
	private BrandPersonality brandPersonality;
	private ModelCollection modelCollection;
	
	private String clientInterfaceClass;
	
	private int userId;
	
	/* The human-readable name of the organization */
	private String organizationName;
	
	/* The name used for the organization's directory */
	private String organizationDirName;
	
	/* We accumulate default promotion prototypes here as they're 
	 * created, so we don't keep building new copies.
	 * The map is from model name to prototype. */
	Map<String,Promotion> defaultPromotionPrototypes = new HashMap<>();
	
	public static Client getClient (HttpSession session) {
		logger.debug ("starting getClient");
		Integer clientKey = (Integer) session.getAttribute (SessionUtils.CLIENT);
		int uid = SessionUtils.getUserId(session);
		if (uid == 0) {
			logger.error ("getClient: User ID in session is 0");
			return null;		// fail early 
		}
		Client client = null;
		client = getByKey (clientKey);

		if (client != null && client.isValid()) {
			return client;
		}

		DbConnection con = null;
		try {
			con = DbConnection.getDb();
			User user = new User(uid);
			user.loadClientInfo(con);
			int orgId = user.getOrganizationID();
			Organization org = Organization.getById(orgId);
			String moduleClass = org.getModuleClass();
			client = new Client (moduleClass);
			client.userId = uid;
			client.organizationName = org.getName();
			client.organizationDirName = org.getDirectoryName();
			int persId = user.getPersonalityID();
			client.brandPersonality = new BrandPersonality(persId);
			Personality pers = Personality.getById(persId);
			if (pers == null || pers.getOrgId() != orgId) {
				logger.warn ("Setting up dummy BrandPersonality");
				client.brandPersonality = new BrandPersonality();		// Can only use own organization's brand personality
			}
			else {
				client.brandPersonality.setName (pers.getName());
			}
			client.loadModels ();
			client.loadStyleSets();
			client.applyBrandStyles();
			clientCache.add (client);
			return client;
		} catch (Exception e) {
			logger.error ("Error creating client: {}", e.getClass().getName());
			logger.error ("Message: {}", e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			if (con != null)
				con.close();
		}
	}
	
	/** Update the user information */
	public void updateUserInfo (int userId) {
		User usr = new User(userId);
		DbConnection con = null;
		try {
			con = DbConnection.getDb();
			usr.loadClientInfo(con);
			if (brandPersonality == null || brandPersonality.isDummy()) {
				int persId = usr.getPersonalityID();
				brandPersonality = new BrandPersonality (persId);
				Personality pers = Personality.getById(persId);
				if (pers == null || pers.getOrgId() != usr.getOrganizationID()) {
					logger.warn ("Setting up dummy BrandPersonality");
					brandPersonality = new BrandPersonality();		// Can only use own organization's brand personality
				}
				else {
					brandPersonality.setName (pers.getName());
				}
			}
		} catch (Exception e) {
			
		} finally {
			if (con != null)
				con.close();
		}
	}
	
	
	/** Return a non-working CLient object for unit testing purposes. */
	protected static Client getDummyClient () {
		return new Client();
	}
	
	/** Get the ClientCache from here. Then we just have to change this if
	 *  we change implementation. */
	public static ClientCache getClientCache() {
		return SingleJVMClientCache.getClientCache();
	}
	
	/** Return the Client matching the key. Will return null
	 *  if there's no such object. */
	public static Client getByKey (Integer key) {
		if (key == null)
			return null;
		Client client = clientCache.getClient(key);
		if (client == null) {
			logger.error ("No client for key {}", key);
		}
		return client;
	}
	
	/** Constructor. This creates an instance of the ClientInterface implementation
	 *  specified in the argument.
	 *  
	 *  @throws ClientException  if ClientInterface can't be instantiated
	 */
	private Client(String clientInterfaceClass) throws ClientException {
		this.clientInterfaceClass = clientInterfaceClass;
		createClientInterface();
		touch();
		valid = true;
	}
	
	/** Make no-argument Constructor private to prevent accidental calls.
	 *  This can be used to create a limited Client object for unit tests, through
	 *  getDummyClient. */
	private Client () {
		logger.warn ("Private Client constructor called, valid only in test");
		touch();
		valid = true;
	}
	
	public ModelCollection getModels () {
		touch();
		try {
			if (modelCollection == null)
				loadModels ();
			return modelCollection;
		}
		catch (Exception e) {
			return null;
		}
	}
	
	/** Load up the models. */
	private void loadModels () throws BrndbotException {
		touch();
		String modelBase = SystemProp.get(SystemProp.LOCAL_ASSETS);
		ModelLoader loader = new ModelLoader(modelBase + "/models/" + organizationDirName);
		modelCollection = loader.readModelFiles();
	}
	


	private void createClientInterface () throws ClientException {
		try {
			@SuppressWarnings("unchecked")
			Class<ClientInterface> clazz = (Class<ClientInterface>) Class.forName(clientInterfaceClass);
			Constructor<ClientInterface> ctor = clazz.getConstructor ();
			clientInterface = ctor.newInstance ();
		} catch (Exception e) {
			logger.error("Could not load client interface {}", clientInterfaceClass);
			logger.error(e.getClass().getName());
			logger.error("Message: {}", e.getMessage());
			throw new ClientException (e);
		}
	}
	
	public int getCacheKey () {
		touch();
		return cacheKey;
	}
	
	public void setCacheKey (int k) {
		touch();
		cacheKey = k;
	}
	
	public boolean isValid () {
		return valid;
	}
	
	public void setValid (boolean b) {
		touch();
		valid = b;
	}
	
	/** Update the touch stamp. If it's been a while since this was last touched,
	 *  run a purge. */
	public void touch () {
		Date oldStamp = touchStamp;
		touchStamp = new Date();
		if (oldStamp != null && touchStamp.getTime() - oldStamp.getTime() > 5000) {
			clientCache.purge();
		}
	}
	
	/** Get the touch stamp */
	public Date getTouchStamp () {
		return touchStamp;
	}
	
	public String getOrganizationName () {
		touch();
		return organizationName;
	}
	
	public BrandPersonality getBrandPersonality () {
		touch();
		return brandPersonality;
	}
	
	/** Specify the active brand personality and save to the database. */
	public void setBrandPersonality (int id) {
		logger.debug ("setBrandPersonality, id = {}", id);
		touch();
		Personality pers = Personality.getById(id);
		if (pers == null ) {
			// TODO should check organization ID
			logger.warn ("Setting up dummy BrandPersonality");
			brandPersonality = new BrandPersonality();		// Can only use own organization's brand personality
		}
		else {
			User user = new User (userId);
			user.setPersonalityID(id);
			DbConnection con = null;

			try {
				con = DbConnection.getDb();
				user.savePersonality(con);
			}
			catch (Exception e) {
				logger.error ("Error in setBrandPersonality: {}  {}", e.getClass().getName(), e.getMessage());
			}
			finally {
				if (con != null)
					con.close();
			}
		}
		brandPersonality.setName (pers.getName());
		
	}
	
	public ModelCollection getModelCollection () {
		touch();
		return modelCollection;
	}
	
	public Map<String,Promotion> getPromotionPrototypes (String modelName) {
		touch();
		Model model = modelCollection.getModelByName(modelName);
		return getPromotionPrototypes (model);
	}
	
	
	public Map<String,Promotion> getPromotionPrototypes (Model m) {
		touch();
		Map<String, Promotion> pmap = 
				clientInterface.getPromotionPrototypes(m.getName());
		if (!pmap.isEmpty())
			return pmap;

		// No promotion prototypes for the model. Create a default and put it into
		// the list.
		// If we already created one, use it.
		String modelName = m.getName();
		Promotion promo = defaultPromotionPrototypes.get(modelName);
		if (promo == null) {
			promo = new Promotion ("Default", m, null);
			promo.populateFromModel ();
			defaultPromotionPrototypes.put (modelName, promo);
		}
		pmap = new HashMap<>();
		pmap.put (promo.getName(), promo);
		return pmap;
	}
	
	public ClientInterface getClientInterface () throws ClientException {
		touch();
		if (clientInterface == null)
			createClientInterface();
		return clientInterface;
	}

	/* Read the style set files and load them into BrandPersonality */
	private void loadStyleSets() {
		logger.debug ("loadStyleSets");
		StringBuilder pathb = new StringBuilder("/var/brndbot/styles/");		// FIXME more hack
		pathb.append (organizationDirName);
		pathb.append ("/");
		pathb.append (brandPersonality.getName());
		pathb.append ("/");
		String path = pathb.toString();
		logger.debug ("loadStyleSets: path = {}", path);
		File styleSetDir = new File (path);
		if (!styleSetDir.exists() || !styleSetDir.isDirectory()) {
			logger.error ("No style set directory {}", path);
			return;
		}
		File [] styleSetFiles = styleSetDir.listFiles();
		
		// parse all the style set files for all models
		for (File ssfile : styleSetFiles) {
			logger.debug ("Style set file {}", ssfile.getPath());
			if (ssfile.isDirectory())
				continue;
			if (!ssfile.getName().endsWith(".xml"))
				continue;
			logger.debug ("Calling StyleSetParser on {}", ssfile.getPath ());
			StyleSetParser ssp = new StyleSetParser (ssfile);
			try {
				StyleSet ss = ssp.parse();
				logger.debug ("Adding style set {}", ss.getName());
				if (ss.isValid ()) 
					brandPersonality.addStyleSet(ss.getModel(), ss);
				else 
					logger.warn ("Invalid style set: {}", ss.getName());
			} catch (Exception e) {
				logger.error ("Exception parsing styleset {}", ssfile.getPath());
				logger.error (e.getClass().getName());
				continue;
			}
		}
	}
	
	/** Return the stylesets for the current brand identity */
	public Map<String,StyleSet> getStyleSets (String modelName) {
		return brandPersonality.getStyleSetsForModel(modelName);
	}
	
	/* Apply the BrandPersonality's styles to the promotion prototypes.
	 * TODO is this really useful for anything? */
	private void applyBrandStyles () {
		Map<String, Model> models = modelCollection.getAllModels();
		logger.debug ("applyBrandStyles");
		for (String modelName : models.keySet()) {
			Model m = models.get (modelName);
			Map<String,Promotion> promoProtos = getPromotionPrototypes(m);
			if (promoProtos == null) {
				logger.error ("promoProtos is null for {}", modelName);
				continue;
			}
			Map<String,StyleSet> styleSets = brandPersonality.getStyleSetsForModel(modelName);
			if (styleSets == null) {
				logger.warn ("styleSets is null for {}", modelName);
				continue;
			}
			for (Promotion proto : promoProtos.values()) {
				// Hmm ... TODO which StyleSet is the default?? For now just pick one.
				List<StyleSet> tempList = new ArrayList<>(styleSets.values());
				StyleSet ss = tempList.get(0);
				proto.setStyleSet(ss);
			}
		}
	}

}
