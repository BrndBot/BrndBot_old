package com.brndbot.promo;

//import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.Promotion;

/** This class holds maps from user IDs to client data.
 *  If necessary, it can be turned into a cache; mappings should be
 *  purged when we implement logout.
 *  
 *  To maintain compatibility with multiple servers, callers will
 *  always check if the data needs to be reloaded.
 */
public class ClientDataHolder {

	/**
	 *  A very simple class, just to make the nesting less confusing.
	 */
	private static class UserProtoMap extends HashMap<String, List<Promotion>> {

		private static final long serialVersionUID = 1L;		
	}
	
	/**
	 *  This is a map, keyed by user ID, to:
	 *    a list of:
	 *      maps keyed by model name to promotion prototypes.
	 */
	private static Map<Integer, UserProtoMap> protoMap;

	synchronized public static List<Promotion>
			getPromotionPrototypes (int userId, String modelName, ClientInterface ci) {
		// Create the map if necessary
		if (protoMap == null) {
			protoMap = new HashMap<>();
			
			// create the user list if necessary.
			UserProtoMap userProtos = protoMap.get(userId);
			if (userProtos == null) {
				userProtos = new UserProtoMap();
				protoMap.put (userId, userProtos);
			}
			// obtain the promotion prototypes for this model if necessary.
			List<Promotion> protoList = userProtos.get (modelName);
			if (protoList == null) {
				ci.getPromotionPrototypes(modelName);
			}
			
		}
		return null;		// TODO stub
	}
}
