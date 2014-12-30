/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.brndbot.db.DbConnection;

public class SystemProp
{
	final static Logger logger = LoggerFactory.getLogger(SystemProp.class);

	static final private String PROPERTY_FILENAME_BASE = "\\webapps\\brndbot\\WEB-INF\\system.properties";
	static private String PROPERTY_FILENAME;

	// The property names for the system.properties configuration file in main folder
	static final public String TOMCAT_BASE = "tomcat.base";
	static final public String WEB_NAME = "web.name";
	static final public String DB_USER = "db.user";
	static final public String DB_PW = "db.pw";
	static final public String DB_URL = "db.url";
	static final public String ASSETS = "assets";
	static final public String MINDBODY_NAME = "mindbody.name";
	static final public String MINDBODY_KEY = "mindbody.key";
	static final public String MINDBODY_STUDIOID = "mindbody.studioid";
	static final public String PHP_SERVER_PAGE = "php.server.page";

	private static Properties _properties;


//	static public SystemProp create()
//	{
//		if (_sys_props == null)
//		{
//			_sys_props = new SystemProp();
//			_sys_props.loadProperties();
//		}
//		return _sys_props;
//	}

	public static void loadProperties()
	{
		System.out.println ("in loadProperties");
        Map<String, String> env = System.getenv();
        String catalina_home = env.get("CATALINA_HOME");
        if (catalina_home == null) {
        	catalina_home = System.getProperty("catalina.home");
        }
        if (catalina_home == null) {
        	System.out.println("***FATAL*** CATALINA_HOME is not defined in environment variables.");
        }
        PROPERTY_FILENAME = Utils.Slashies(catalina_home + PROPERTY_FILENAME_BASE);
        _properties = new Properties();
		FileInputStream in = null;
		try 
		{
			in = new FileInputStream(PROPERTY_FILENAME);
			_properties.load(in);
		} 
		catch (FileNotFoundException e) 
		{
			System.out.println("***FATAL No properties file");
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			e.printStackTrace();
		}
		finally
		{
			try {
				in.close();
			} 
			catch (IOException e) 
			{
				e.printStackTrace();
			}
			in = null;
		}
	}

	static public void put(String property_name, String val)
	{
		if (_properties == null)
			loadProperties();
		if (_properties == null)
			logger.error("FATAL: Could not load system properties");
		//SystemProp props = SystemProp.create();
		SystemProp._properties.setProperty(property_name, val);
	}

	static public String get(String property_name)
	{
		logger.debug ("SystemProp.get, property = {}", property_name);
		if (_properties == null)
			loadProperties();
		if (_properties == null)
			logger.error("FATAL: Could not load system properties");
		String val = (String)SystemProp._properties.getProperty(property_name); 
		return val;
	}
}
