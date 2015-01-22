package com.brndbot.client.dummy;

public class DummyClientInterfaceFactory {

	private static DummyClientInterface dci;
	
	public static DummyClientInterface getClientInterface () {
		if (dci == null) {
			dci = new DummyClientInterface();
		}
		return dci;
	}

}
