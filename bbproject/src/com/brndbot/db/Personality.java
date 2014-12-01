package com.brndbot.db;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** The table which defines brand personalities */
public class Personality implements TableModel {

	final static Logger logger = LoggerFactory.getLogger(Personality.class);
	final static String tableName = "personality";
	
	private Integer id;
	private Integer orgId;
	private String name;
	
	public String getTableName () {
		return tableName;
	}
	
	/** Returns the ID of the personality */
	public int getId () {
		return id;
	}
	
	/** Returns the name of the personality */
	public String getName () {
		return name;
	}
	
	/** Returns the ID of the organization to which the personality belongs */
	public int getOrgId () {
		return orgId;
	}
}
