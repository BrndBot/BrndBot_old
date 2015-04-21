package com.brndbot.promo;

/** Interface for a singleton class providing an implementation for caching Client objects.
 *  There can be different implementations for a single-JVM in-memory cache, a database
 *  cache, a file-based cache, etc.
 *  
 *  Ground rules:
 *  
 *  When a Client is created, it is added to the ClientCache object.
 *  
 *  When the user logs out, its Client is removed from the cache.
 *  
 *  All calls to a ClientCache should "touch" the cache, updating its most
 *  recent touch time.
 *  
 *  A Client is eligible for removal if it hasn't been touched in some
 *  period of time. Removal doesn't destroy the cache object, but it will
 *  mark it as invalid.
 */
public interface ClientCache {
	
	/** Add a client, assigning it a unique key and returning the key */
	public int add (Client c);
	
	/** Remove a client 
	 */
	public void remove (Client c);

	
	/** Retrieve a client by key. Returns null if no valid
	 *  client is found.
	 */
	public Client getClient (int key);
	
	/** Purge old clients from the cache. */
	public void purge ();
}
