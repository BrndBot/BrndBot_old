package com.brndbot.promo;

import java.lang.reflect.Constructor;
import java.util.List;

import com.brndbot.client.ClientException;
import com.brndbot.client.ClientInterface;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.StyleSet;

/**
 *  The OrganizationDescriptor is a class that holds the
 *  models and styles referring to an organization.
 *  This information drives everything.
 *  
 */
public class Client {

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
	
	public static Client getOrganizationDescriptor () {
		// **** TOTALLY TEMPORARY CODE ******
		// Just create a small set of Models here. Can use the 
		// parser to build them from XML.

		return null;
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
			throw new ClientException (e);
		}
	}

}
