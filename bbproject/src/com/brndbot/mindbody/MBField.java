/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.mindbody;

/* Class to define the request and response values for a MB API field */
public class MBField
{
	private String _requestField;
	private String _responseField;
	
	public MBField(String field)
	{
		_requestField = field;
		_responseField = field;
	}
	
	public String getRequestField() { return _requestField; }
	public String getResponseField() { return _responseField; }
}
