package com.brndbot.client.dummy;

import com.brndbot.dummyclient.DummyClientInterface;

public class DummyClientInterfaceFactory {

	private static DummyClientInterface dci;
	
	public static DummyClientInterface getClientInterface () {
		if (dci == null) {
			dci = new DummyClientInterface();
		}
		return dci;
	}

}
