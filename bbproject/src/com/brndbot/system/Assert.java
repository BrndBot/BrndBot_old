package com.brndbot.system;

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
