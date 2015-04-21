package com.brndbot.promo;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** A singleton class that provides an implementation for caching Client objects.
 *  This version assumes a single server with a single JVM. If this changes,
 *  it should be necessary only to re-implement ClientCache.
 *  
 */
public class SingleJVMClientCache implements ClientCache {

	/** Expiration time in minutes */
	private final static long EXPIRE_MINUTES = 60 * 6;
	
	/** Unique key. We just increase them monotonically, since uniqueness is
	 *  required only within a session. */
	private static int lastKey = 500;

	private final static Logger logger = LoggerFactory.getLogger(SingleJVMClientCache.class);
	
	private Map<Integer, Client> clientMap;
	
	
	/* The singleton instance */
	private static SingleJVMClientCache clientCache = null;
	
	/* Return the singleton instance, creating it if necessary. */
	public static synchronized SingleJVMClientCache getClientCache () {
		if (clientCache == null)
			clientCache = new SingleJVMClientCache();
		return clientCache;
	}
	
	/* Private constructor */
	private SingleJVMClientCache() {
		clientMap = new HashMap<>();
	}
	
	/** Retrieve a client by key */
	public Client getClient (int key) {
		return clientMap.get(key);
	}
	
	/** Add a client to the cache */
	public synchronized int add (Client c) {
		c.touch();
		// To avoid race conditions, this should be the only place that
		// changes lastKey
		lastKey++;	
		c.setCacheKey(lastKey);
		clientMap.put(lastKey, c);
		return lastKey;
	}
	
	/** Remove a client */
	public synchronized void remove (Client c) {
		int key = c.getCacheKey();
		clientMap.remove(key);
		c.setValid (false);
		c.setCacheKey (-1);
	}
	

	
	/** Purge the cache. */
	public synchronized void purge () {
		// We clone the key set to avoid a ConcurrentModificationException
		Set<Integer> keys = new HashSet<Integer>(clientMap.keySet());
		long now = new Date ().getTime();		// current time in milliseconds
		for (Integer key : keys) {
			Client c = clientMap.get(key);
			long touched = c.getTouchStamp().getTime();
			if (!c.isValid() ||  now - touched > 60L * 1000L * EXPIRE_MINUTES) {
				// We duplicate the code from remove to avoid gridlock issues from two syncs
				logger.info ("Removing client with key {}", key);
				clientMap.remove(key);
				c.setValid (false);
				c.setCacheKey (-1);
			}
		}
	}
}
