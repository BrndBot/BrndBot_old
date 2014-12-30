package com.brndbot.jsphelper;

import java.io.StringWriter;
import java.io.Writer;

import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

/** This is a superclass for classes that generate HTML fragments
 *  using JDOM.
 */
public abstract class Renderer {

	Writer writer;
	XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
	
	public Renderer () {
		writer = new StringWriter();		
	}
	
	/** Returns the constructed block as an HTML fragment */
	public String getFragment () {
		return writer.toString ();
	}
}
