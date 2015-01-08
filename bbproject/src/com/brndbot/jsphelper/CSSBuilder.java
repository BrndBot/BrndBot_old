package com.brndbot.jsphelper;

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
	
	/** Adds a color style expression. This can be any kind of
	 *  valid CSS color expression but isn't checked. 
	 */
	public void setColor (String c) {
		cssBuf.append("color:");
		cssBuf.append(c);
		cssBuf.append(";");
	}
	
	/** Set bottom left anchor position */
	public void setBottomLeftAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("left:" + x);
		cssBuf.append (";");
		cssBuf.append ("bottom:" + y);
		cssBuf.append (";");
	}

	/** Set top left anchor position */
	public void setTopLeftAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("left:" + x);
		cssBuf.append (";");
		cssBuf.append ("top:" + y);
		cssBuf.append (";");
	}
	/** Set bottom right anchor position */
	public void setBottomRightAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("right:" + x);
		cssBuf.append (";");
		cssBuf.append ("bottom:" + y);
		cssBuf.append (";");
	}
	/** Set bottom left anchor position */
	public void setTopRightAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("right:" + x);
		cssBuf.append (";");
		cssBuf.append ("top:" + y);
		cssBuf.append (";");
	}

	/** Adds a font-family style for a given font name. Wraps it in
	 *  double quotes to be safe. Not suitable as it stands for specifying
	 *  multiple typefaces. 
	 */
	public void setFont (String font) {
		cssBuf.append("font-family:");
		cssBuf.append(font);
		cssBuf.append(";");
	}
	
	public void setPointSize (int size) {
		cssBuf.append ("font-size:");
		cssBuf.append(Integer.toString(size));
		cssBuf.append("pt;");
	}
	
	public void setItalic () {
		cssBuf.append ("font-style:italic;");
	}
	
	public void setBold () {
		cssBuf.append ("font-weight:bold;");
	}
	
	/** Set the Z-Index. Lower values go behind higher values. */
	public void setZIndex (int n) {
		cssBuf.append ("z-index:" + n);
	}
}
