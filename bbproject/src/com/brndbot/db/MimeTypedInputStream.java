package com.brndbot.db;

import java.io.FilterInputStream;
import java.io.InputStream;

/** This is simply a FilterInputStream that reports
 *  its MIME type.
 */
public class MimeTypedInputStream extends FilterInputStream {

	String mimeType;
	
	public MimeTypedInputStream(InputStream in, String mimeType) {
		super(in);
		this.mimeType = mimeType;
	}

	public String getMimeType () {
		return mimeType;
	}
}
