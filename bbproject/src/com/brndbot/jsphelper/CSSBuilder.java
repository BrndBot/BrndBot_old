package com.brndbot.jsphelper;

import com.brndbot.client.style.TextStyle.Alignment;

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
	
	/** Adds a text color style expression. This can be any kind of
	 *  valid CSS color expression but isn't checked. 
	 */
	public void setColor (String c) {
		cssBuf.append("color:");
		cssBuf.append(c);
		cssBuf.append(";");
	}

	/** Adds a background color style expression. This can be any kind of
	 *  valid CSS color expression but isn't checked. 
	 */
	public void setBackgroundColor (String c) {
		cssBuf.append("background-color:");
		cssBuf.append(c);
		cssBuf.append(";");
	}

	/** Sets the alignment.  */
	public void setTextAlign (Alignment a) {
		String astr = null;
		switch (a) {
		case LEFT:
			astr = "left";
			break;
		case RIGHT:
			astr = "right";
			break;
		case CENTER:
			astr = "center";
			break;
		case JUSTIFIED:
			astr = "justify";
			break;
		}
		if (astr != null) {
			cssBuf.append ("text-align:");
			cssBuf.append (astr);
			cssBuf.append (";");
		}
	}
	
	public void setWidth (int w) {
		cssBuf.append ("width:");
		cssBuf.append (Integer.toString(w));
		cssBuf.append ("px;max-width:");
		cssBuf.append (Integer.toString(w));
		cssBuf.append("px;");
	}
	
	public void setHeight (int h) {
		cssBuf.append ("height:");
		cssBuf.append (Integer.toString(h));
		cssBuf.append ("px;max-height:");
		cssBuf.append (Integer.toString(h));
		cssBuf.append("px;");
	}
	
	/** Set opacity. We use 0-100 internally, but CSS3 expects 0.0-1.0. */
	public void setOpacity (int op) {
		cssBuf.append("opacity:");
		double fop = op / 100.0;
		cssBuf.append(Double.toString (fop));
		cssBuf.append(";");
	}
	
	/** Insert a fixed piece of CSS to scale the image down to its container */
	public void limitScaling() {
		cssBuf.append("max-width:100%;max-height:100%;");
	}
	
	/** Set bottom left anchor position */
	public void setBottomLeftAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("left:" + x);
		cssBuf.append ("px;");
		cssBuf.append ("bottom:" + y);
		cssBuf.append ("px;");
	}

	/** Set top left anchor position */
	public void setTopLeftAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("left:" + x);
		cssBuf.append ("px;");
		cssBuf.append ("top:" + y);
		cssBuf.append ("px;");
	}
	/** Set bottom right anchor position */
	public void setBottomRightAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("right:" + x);
		cssBuf.append ("px;");
		cssBuf.append ("bottom:" + y);
		cssBuf.append ("px;");
	}
	/** Set bottom left anchor position */
	public void setTopRightAnchor (int x, int y) {
		cssBuf.append ("position:absolute;");
		cssBuf.append ("right:" + x);
		cssBuf.append ("px;");
		cssBuf.append ("top:" + y);
		cssBuf.append ("px;");
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
