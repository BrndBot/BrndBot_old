package com.brndbot.db;

import java.io.FilterInputStream;
import java.io.InputStream;

/** This is simply a FilterInputStream with some extra metadata, including
 *  MIME type, width, and height.
 */
public class MimeTypedInputStream extends FilterInputStream {

	private String mimeType;
	private int width;
	private int height;
	
	
	public MimeTypedInputStream(InputStream in, String mimeType) {
		super(in);
		this.mimeType = mimeType;
	}

	public String getMimeType () {
		return mimeType;
	}
	
	public int getWidth () {
		return width;
	}
	
	public void setWidth (int w) {
		width = w;
	}
	
	public int getHeight () {
		return height;
	}
	
	public void setHeight (int h) {
		height = h;
	}
}
