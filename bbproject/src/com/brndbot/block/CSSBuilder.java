package com.brndbot.block;

/* This class creates a CSS string from a bunch of settings */
public class CSSBuilder {

	StringBuilder cssBuf;
	
	public CSSBuilder() {
		cssBuf = new StringBuilder();
	}

	/** Returns the CSS string. It may contain double quotes.
	 */
	@Override
	public String toString () {
		return cssBuf.toString ();
	}
	
	/** Adds a font-family style for a given font name. Wraps it in
	 *  double quotes to be safe. Not suitable as it stands for specifying
	 *  multiple typefaces. 
	 */
	public void setFont (String font) {
		cssBuf.append("font-family:\"" + font + "\";");
	}
	
	public void setPointSize (int size) {
		cssBuf.append ("font-size:" + size + ";");
	}
	
	public void setItalic () {
		cssBuf.append ("font-style:italic;");
	}
	
	public void setBold () {
		cssBuf.append ("font-weight:bold;");
	}
}
