package com.brndbot.promo;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.PromotionPrototype;
import com.brndbot.client.StyleSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  Client is a class that holds the
 *  models and styles referring to an organization.
 *  This information drives everything.
 *  
 *  A Client object needs to be stored as a session attribute.
 *  
 */
public class Client implements Serializable {


	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(Client.class);
	
	/** An instance of the ClientInterface subclass which feeds 
	 *  us data 
	 */
	private ClientInterface clientInterface;
	
	/** The name of the organization */
	private String name;
	
	/** The hierarchy of Models available to us. */
	private ModelCollection modelCollection;
	
	/** The StyleSets available to us. */
	private List<StyleSet> styleSets;
	
	/** The PromotionPrototypes available to us. */
	/** TODO this should really be a map. */
	private Map<String,PromotionPrototype> promotionPrototypes;
	
	/** FIXME ****HACK*** doesn't work with more than one user  */
	private static transient Client client;
	
	public static Client getClient (HttpSession session) {
		// **** TOTALLY TEMPORARY CODE ******
		// Just create a small set of Models here. Can use the 
		// parser to build them from XML.

		// FIXME temporary hack
		if (client != null)
			return client;
		try {
			client = new Client ("com.brndbot.dummymodule.DummyClient");
			return client;
		} catch (Exception e) {
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
	
	public PromotionPrototype getPromotionPrototype (String name) {
		return promotionPrototypes.get(name);
	}
	
	/** Pull in the data from the module */
	public void acquireData () {
		modelCollection = clientInterface.getModels ();
		List<PromotionPrototype> promos = clientInterface.getPromotionPrototypes ();
		promotionPrototypes = new HashMap<String,PromotionPrototype>();
		for (PromotionPrototype promo : promos) {
			promotionPrototypes.put (promo.getName(), promo);
		}
	}

}
