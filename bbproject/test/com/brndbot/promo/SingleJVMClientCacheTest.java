package com.brndbot.promo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SingleJVMClientCacheTest {

	private ClientCache cc;
	@Before
	public void setUp() throws Exception {
		cc = SingleJVMClientCache.getClientCache();
	}



	@Test
	public void testSingleton() {		
		assertNotNull(cc);
		ClientCache cc2 = SingleJVMClientCache.getClientCache();
		assertEquals (cc, cc2);
	}
	
	@Test
	public void testAdd () {
		Client c = Client.getDummyClient ();
		int k = cc.add(c);
		Client c1 = cc.getClient (k);
		assertEquals (c, c1);
		assertTrue (c.isValid());
		assertEquals (k, c.getCacheKey());
	}
	
	@Test
	public void testTwoClients () {
		Client c = Client.getDummyClient ();
		int k = cc.add(c);
		Client c1 = Client.getDummyClient ();
		int k1 = cc.add(c1);
		assertNotEquals(k,  k1);
		assertEquals (c, cc.getClient(k));
		assertEquals (c1, cc.getClient(k1));
	}
	
	@Test
	public void testRemove () {
		Client c = Client.getDummyClient ();
		int k = cc.add(c);
		Client c1 = Client.getDummyClient ();
		int k1 = cc.add(c1);
		cc.remove(c);
		assertNull(cc.getClient (k));
		assertNotNull(cc.getClient (k1));
	}
	
	@Test
	public void testPurge () {
		// We assume it won't take an hour for the processor to run this test.
		Client c = Client.getDummyClient ();
		int k = cc.add(c);
		Client c1 = Client.getDummyClient ();
		int k1 = cc.add(c1);
		cc.purge();
		assertNotNull (cc.getClient(k));
		assertNotNull (cc.getClient(k1));
		
		c.setValid(false);
		cc.purge();
		assertNull (cc.getClient (k));
	}
}
