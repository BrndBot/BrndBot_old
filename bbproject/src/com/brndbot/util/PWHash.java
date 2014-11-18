/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/** Class to handle hashing of passwords and possibly other data. */
public class PWHash {

	final static Logger logger = LoggerFactory.getLogger(PWHash.class);
	
	private static SecureRandom sr;
	
	static {
		try {
			if (sr == null) {
				sr = SecureRandom.getInstance("SHA1PRNG");
			}
		} catch (Exception e) {
			logger.error("Could not initialize SecureRandom");
		}
	}
	
		
	public static String getHash (String pw) {
		try {
			String salt = getSalt();
			String hash = getSHA256Hash(pw, salt);
			return salt + ":" + hash;
		} catch (Exception e) {
			logger.error("Could not hash password");
			return null;
		}
	}

	public static boolean validatePassword(String originalPassword, String storedHash) 
			throws NoSuchAlgorithmException, InvalidKeySpecException
	{
		String[] parts = storedHash.split(":");
		String salt = parts[0];
		String hash = parts[1];
		
		String testHash = getSHA256Hash(originalPassword, salt);
		return (testHash.equals(hash));
	}
	
	
	/* Hash a password */
	private static String getSHA256Hash(String passwordToHash, String salt)
	{
		String generatedHash = null;
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for(int i=0; i< bytes.length ;i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedHash = sb.toString();
		} 
		catch (NoSuchAlgorithmException e) 
		{
			e.printStackTrace();
		}
		return generatedHash;
	}
	
	/* Generate a salt */
	private static String getSalt() throws NoSuchAlgorithmException
	{
		//Always use a SecureRandom generator
		//Create array for salt
		byte[] salt = new byte[16];
		//Get a random salt
		sr.nextBytes(salt);
		//return salt
		return salt.toString();
	}
}
