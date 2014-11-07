package com.brndbot.util;

import static org.junit.Assert.*;

import org.junit.Test;

public class PWHashTest {

	@Test
	public void testHash() throws Exception {
		String hash = PWHash.getHash("LittleBobbyTables");
		System.out.println ("Length of hash is " + hash.length());
		assertTrue (PWHash.validatePassword("LittleBobbyTables", hash));
		assertFalse (PWHash.validatePassword("Little Bobby Tables", hash));
	}


}
