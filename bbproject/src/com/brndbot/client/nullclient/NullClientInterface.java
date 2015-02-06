package com.brndbot.client.nullclient;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.client.ClientInterface;
import com.brndbot.client.ModelCollection;
import com.brndbot.client.Promotion;

public class NullClientInterface implements ClientInterface {

	private static final String CLIENT_INTERFACE_NAME = "NullClient";
	
	private static final long serialVersionUID = 1L;

	final static Logger logger = LoggerFactory.getLogger(NullClientInterface.class);

	private ModelCollection mCollection = new ModelCollection ();
	private Map<String, Promotion> promotionPrototypes = new HashMap<>();
	
	public NullClientInterface() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getName() {
		return CLIENT_INTERFACE_NAME;
	}

	@Override
	public Map<String, Promotion> getPromotionPrototypes(String modelName) {
		return promotionPrototypes;
	}

}
