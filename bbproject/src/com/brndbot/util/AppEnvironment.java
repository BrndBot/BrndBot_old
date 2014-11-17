package com.brndbot.util;

import com.brndbot.system.SystemProp;
import com.brndbot.system.Utils;

/** Static functions relating to the application environment. */
public class AppEnvironment {

	/** This takes a file path and puts it into a path which is relative to
	 *  the application base. Path separators can be either the system file
	 *  separator or the backslash. However, backslashes that aren't intended
	 *  as file separators will be turned into file separators, so be careful. */
	public final static String baseInAppDirectory (String path) {
		// TODO replace all calls that get TOMCAT_BASE to fix up a path
		// with calls to here.
		String tomcat_base = SystemProp.get(SystemProp.TOMCAT_BASE);
		return Utils.Slashies(tomcat_base + "\\" + path);
	}
}
