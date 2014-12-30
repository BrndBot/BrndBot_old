package com.brndbot.dummyclient;

public class DummyClientInterfaceFactory {

	private static DummyClientInterface dci;
	
	public static DummyClientInterface getClientInterface () {
		if (dci == null) {
			dci = new DummyClientInterface();
		}
		return dci;
	}

}
