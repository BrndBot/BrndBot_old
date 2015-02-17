/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.system;

/** A clumsy way of wrapping a check that throws a RuntimeException
 *  in what looks like an assertion system while just being lazy and
 *  not doing proper exception handling. Can we get rid of it?
 */
public class Assert 
{
	private Assert() {}
	
	static public void that(boolean arg, String error)
	{
		if (!arg)
		{
			throw new RuntimeException("Error: " + error);
		}
	}
}
