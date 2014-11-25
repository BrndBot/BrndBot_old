/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.style;

import java.io.File;

import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class Style {

	final static Logger logger = LoggerFactory.getLogger(Style.class);

	public Style(String filepath) {
		SAXBuilder builder = new SAXBuilder ();
		File file = new File (filepath);
		try {
			Document doc = builder.build (file);
		} catch (Exception e) {
			
		}
	}
}
