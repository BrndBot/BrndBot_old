package com.brndbot.promo;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
//import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.brndbot.client.BrandIdentity;
import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.Promotion;
import com.brndbot.client.style.StyleSet;
import com.brndbot.client.parser.StyleSetParser;
import com.brndbot.db.DbConnection;
import com.brndbot.db.Organization;
import com.brndbot.db.User;
import com.brndbot.system.SessionUtils;
import com.brndbot.system.SystemProp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	/** An instance of the ClientInterface subclass which feeds 
	 *  us data 
	 */
	
	private transient ClientInterface clientInterface;
	private BrandIdentity brandIdentity;
	private ModelCollection modelCollection;
	
	private String clientInterfaceClass;
	
	private int userId;
	
	/* The human-readable name of the organization */
	private String organizationName;
	
	/* The name used for the organization's directory */
	private String organizationDirName;

	
	/* This allows multiple clients. We need to figure out a way to
	 * remove stale clients who don't explicitly log out.
	 */
	private static Map<Integer, Client> clients = new HashMap<>();
	
	/* We accumulate default promotion prototypes here as they're 
	 * created, so we don't keep building new copies.
	 * The map is from model name to prototype. */
	Map<String,Promotion> defaultPromotionPrototypes = new HashMap<>();
	
	public static Client getClient (HttpSession session) {
		logger.debug ("starting getClient");
		int uid = SessionUtils.getUserId(session);
		Client client = clients.get (uid);

		if (client != null) {
			logger.debug ("We already have a client");
			return client;
		}
		try {
			DbConnection con = DbConnection.GetDb();
			User user = new User(uid);
			logger.debug ("Loading client info");
			user.loadClientInfo(con);
			int orgId = user.getOrganizationID();
			logger.debug ("org ID is {}", orgId);
			Organization org = Organization.getById(orgId);
			String moduleClass = org.getModuleClass();
			logger.debug ("Module class is {}", moduleClass);
			client = new Client (moduleClass);
			client.organizationName = org.getName();
			client.organizationDirName = org.getDirectoryName();
			client.brandIdentity = new BrandIdentity ("default");	// TODO Is this a required value?
			client.loadModels ();
			client.loadStyleSets();
			client.applyBrandStyles();
			clients.put (uid, client);
			return client;
		} catch (Exception e) {
			logger.error ("Error creating client: {}", e.getClass().getName());
			logger.error ("Message: {}", e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		// TODO save it in the session and get it back from there
	}
	
	/** Return the Client matching the user ID. Will return null
	 *  if there's no such user. */
	public static Client getByUserId (int id) {
		Client client = clients.get(id);
		if (client == null) {
			logger.error ("No client for ID {}", id);
		}
		return client;
	}
	
	/** TODO a worse hack. Call this on logout/login to clear the session. */
	public static void reset () {
		clients.clear();
	}
	
	public ModelCollection getModels () {
		if (modelCollection == null)
			loadModels ();
		return modelCollection;
	}
	
	/** Load up the models. */
	private void loadModels () {
		String modelBase = SystemProp.get(SystemProp.LOCAL_ASSETS);
		ModelLoader loader = new ModelLoader(modelBase + "/models/" + organizationDirName);
		modelCollection = loader.readModelFiles();
	}
	
	/** Constructor. This creates an instance of the ClientInterface implementation
	 *  specified in the argument.
	 *  
	 *  @throws ClientException  if ClientInterface can't be instantiated
	 */
	private Client(String clientInterfaceClass) throws ClientException {
		this.clientInterfaceClass = clientInterfaceClass;
		createClientInterface();
	}
	
	/** Make no-argument Constructor private to prevent accidental calls. */
	private Client () {
		logger.error ("Private Client constructor somehow called");
	}
	
	/** Since ClientInterface is transient, we may have to re-create it
	 *  at any time. */
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
	
	public BrandIdentity getBrandIdentity () {
		return brandIdentity;
	}
	
	/** Specify the active brand identity */
	public void setBrandIdentity (BrandIdentity bi) {
		brandIdentity = bi;
	}
	
	public ModelCollection getModelCollection () {
		return modelCollection;
	}
	
	public Map<String,Promotion> getPromotionPrototypes (String modelName) {
		Model model = modelCollection.getModelByName(modelName);
		return getPromotionPrototypes (model);
	}
	
	
	public Map<String,Promotion> getPromotionPrototypes (Model m) {
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
			logger.debug ("No promotion prototypes for model, creating default");
			promo = new Promotion ("Default", m, null);
			promo.populateFromModel ();
			logger.debug ("Created prototype {} and populated it from model", promo.getName());
			defaultPromotionPrototypes.put (modelName, promo);
		}
		pmap = new HashMap<>();
		pmap.put (promo.getName(), promo);
		return pmap;
	}
	
	public ClientInterface getClientInterface () throws ClientException {
		if (clientInterface == null)
			createClientInterface();
		return clientInterface;
	}

	/* Read the style set files and load them into brandIdentity */
	private void loadStyleSets() {
		logger.debug ("loadStyleSets");
		StringBuilder pathb = new StringBuilder("/var/brndbot/styles/");		// FIXME more hack
		pathb.append (organizationDirName);
		pathb.append ("/");
		pathb.append (brandIdentity.getName());
		pathb.append ("/");
		String path = pathb.toString();
		logger.debug ("Path = {}", path);
		Map<String, Model> models = modelCollection.getAllModels();
		for (String modelName : models.keySet()) {
			logger.debug ("Model name = {}", modelName);
//			File modelDir = new File (path + modelName);
//			if (!modelDir.exists() || !modelDir.isDirectory()) {
//				logger.warn ("No styleset directory {}", modelDir.getPath());
//				continue;
//			}
			File styleSetDir = new File (path);
			File [] styleSetFiles = styleSetDir.listFiles();
			
			// parse all the style set files for all models
			for (File ssfile : styleSetFiles) {
				if (ssfile.isDirectory())
					continue;
				logger.debug ("Calling StyleSetParser on {}", ssfile.getPath ());
				StyleSetParser ssp = new StyleSetParser (ssfile);
				try {
					StyleSet ss = ssp.parse();
					logger.debug ("Adding style set {}", ss.getName());
					brandIdentity.addStyleSet(ss.getModel(), ss);
				} catch (Exception e) {
					logger.error ("Exception parsing styleset {}", ssfile.getPath());
					logger.error (e.getClass().getName());
					continue;
				}
			}
		}
	}
	
	/** Return the stylesets for the current brand identity */
	public Map<String,StyleSet> getStyleSets (String modelName) {
		return brandIdentity.getStyleSetsForModel(modelName);
	}
	
	/* Apply the BrandIdentity's styles to the promotion prototypes.
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
			Map<String,StyleSet> styleSets = brandIdentity.getStyleSetsForModel(modelName);
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
