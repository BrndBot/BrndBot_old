package com.brndbot.client.nullclient;

public class NullClientInterfaceFactory {

	private static NullClientInterface nci;
	
	public static NullClientInterface getClientInterface() {
		if (nci == null) {
			nci = new NullClientInterface();
		}
		return nci;
	}

}
