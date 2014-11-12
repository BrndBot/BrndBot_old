package com.brndbot.system;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

public class SystemProp
{
	static private SystemProp _sys_props = null;

	static final private String PROPERTY_FILENAME_BASE = "\\webapps\\brndbot\\WEB-INF\\system.properties";
	static private String PROPERTY_FILENAME;

	// The property names for the system.properties configuration file in main folder
	static final public String TOMCAT_BASE = "tomcat.base";
	static final public String WEB_NAME = "web.name";
	static final public String DB_USER = "db.user";
	static final public String DB_PW = "db.pw";
	static final public String ASSETS = "assets";
	static final public String MINDBODY_NAME = "mindbody.name";
	static final public String MINDBODY_KEY = "mindbody.key";
	static final public String MINDBODY_STUDIOID = "mindbody.studioid";
	static final public String PHP_SERVER_PAGE = "php.server.page";

	private Properties _properties;

	private SystemProp()
	{
        Map<String, String> env = System.getenv();
        String catalina_home = env.get("CATALINA_HOME");
        PROPERTY_FILENAME = Utils.Slashies(catalina_home + PROPERTY_FILENAME_BASE);
        _properties = new Properties();
	}

	static public SystemProp create()
	{
		if (_sys_props == null)
		{
			_sys_props = new SystemProp();
			_sys_props.loadProperties();
		}
		return _sys_props;
	}

	public void loadProperties()
	{
		FileInputStream in = null;
		try 
		{
			in = new FileInputStream(PROPERTY_FILENAME);
			_properties.load(in);
		} 
		catch (FileNotFoundException e) 
		{
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
		SystemProp props = SystemProp.create();
		props._properties.setProperty(property_name, val);
	}

	static public String get(String property_name)
	{
		SystemProp props = SystemProp.create();
		String val = (String)props._properties.getProperty(property_name); 
		return val;
	}
}
