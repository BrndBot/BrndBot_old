package com.brndbot.promo;

import java.io.File;
import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.List;
//import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.brndbot.client.BrandIdentity;
import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.Model;
import com.brndbot.client.Promotion;
import com.brndbot.client.StyleSet;
import com.brndbot.client.parser.StyleSetParser;

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
	
	private String clientInterfaceClass;
	
	private int userId;
	
	/** The name of the organization */
	private String organizationName;
	
	/** FIXME ****HACK*** doesn't work with more than one user  */
	private static transient Client client;
	
	public static Client getClient (HttpSession session) {
		// **** TOTALLY TEMPORARY CODE ******
		// Just create a small set of Models here. Can use the 
		// parser to build them from XML.

		
		
		// FIXME temporary hack
		if (client != null) {
			return client;
		}
		try {
			client = new Client ("com.brndbot.dummyclient.DummyClientInterface");
			client.organizationName = "LevelOne";		// TODO hackhackhack
			client.brandIdentity = new BrandIdentity ("default");
			client.loadStyleSets();
			client.applyBrandStyles();
			return client;
		} catch (Exception e) {
			logger.error ("Error creating client: {}", e.getClass().getName());
			logger.error ("Message: {}", e.getMessage());
			e.printStackTrace();
			return null;
		}
		
		// TODO save it in the session and get it back from there
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
	
	public Map<String,Promotion> getPromotionPrototypes (String modelName) {
		//return ClientDataHolder.getPromotionPrototypes(userId, modelName, clientInterface);
		return clientInterface.getPromotionPrototypes(modelName);
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
		pathb.append (organizationName);
		pathb.append ("/");
		pathb.append (brandIdentity.getName());
		pathb.append ("/");
		String path = pathb.toString();
		logger.debug ("Path = {}", path);
		Map<String, Model> models = clientInterface.getModels ().getAllModels();
		for (String modelName : models.keySet()) {
			logger.debug ("Model name = {}", modelName);
			File modelDir = new File (path + modelName);
			if (!modelDir.exists() || !modelDir.isDirectory()) {
				logger.warn ("No model directory {}", modelDir.getPath());
				continue;
			}
			File [] styleSetFiles = modelDir.listFiles();
			
			// parse all the style set files for this model
			for (File ssfile : styleSetFiles) {
				StyleSetParser ssp = new StyleSetParser (ssfile);
				try {
					logger.debug ("Starting parser on {}", ssfile.getPath());
					StyleSet ss = ssp.parse();
					logger.debug ("Adding style set {}", ss.getName());
					brandIdentity.addStyleSet(modelName, ss);
				} catch (Exception e) {
					logger.error ("Exception parsing styleset {}", ssfile.getPath());
					logger.error (e.getClass().getName());
					continue;
				}
			}
		}
	}
	
	/* Apply the BrandIdentity's styles to the promotion prototypes. */
	private void applyBrandStyles () {
		Map<String, Model> models = clientInterface.getModels ().getAllModels();
		for (String modelName : models.keySet()) {
			Map<String,Promotion> promoProtos = getPromotionPrototypes(modelName);
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
