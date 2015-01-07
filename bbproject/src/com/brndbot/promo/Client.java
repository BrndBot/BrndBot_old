package com.brndbot.promo;

import java.io.Serializable;
import java.lang.reflect.Constructor;
//import java.util.HashMap;
import java.util.List;
//import java.util.Map;

import java.util.Map;

import javax.servlet.http.HttpSession;

import com.brndbot.client.BrandIdentity;
import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.Promotion;
import com.brndbot.client.StyleSet;

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
	private String name;
	
	/** The hierarchy of Models available to us. */
	private ModelCollection modelCollection;
	
	/** The StyleSets available to us. */
	private List<StyleSet> styleSets;
	
	/** FIXME ****HACK*** doesn't work with more than one user  */
	private static transient Client client;
	
	public static Client getClient (HttpSession session) {
		// **** TOTALLY TEMPORARY CODE ******
		// Just create a small set of Models here. Can use the 
		// parser to build them from XML.

		logger.debug ("Entering getClient");
		// FIXME temporary hack
		if (client != null) {
			logger.debug ("returning existing client");
			return client;
		}
		try {
			client = new Client ("com.brndbot.dummyclient.DummyClientInterface");
			logger.debug ("Got client");
			return client;
		} catch (Exception e) {
			logger.error ("Error creating client: {}", e.getClass().getName());
			logger.error ("Message: {}", e.getMessage());
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
	
	/** Specify the active brand identity */
	public void setBrandIdentity (BrandIdentity bi) {
		brandIdentity = bi;
	}
	
	public Map<String,Promotion> getPromotionPrototypes (String modelName) {
		return ClientDataHolder.getPromotionPrototypes(userId, modelName, clientInterface);
	}
	
	public ClientInterface getClientInterface () throws ClientException {
		if (clientInterface == null)
			createClientInterface();
		return clientInterface;
	}
	


}
