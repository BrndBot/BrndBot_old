/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.system;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/** I don't know what this is for. It puts passwords into cookies,
 *  which is insane.
 */
public class LoginCookie 
{
	HttpServletRequest _request;
	HttpServletResponse _response;

	public static String USE_COOKIE = "brndbot.usecookie";
	public static String COOKIE_EMAIL_ADDRESS = "brndbot.cookie.email";
	public static String COOKIE_PASSWORD = "brndb11otcookiepassword";

	// IDs and names for login html
	public static String USE_COOKIE_CB = "brndbotusecookie";
	public static String EMAIL_ADDRESS = "brndbotemailcookie";
	public static String PASSWORD = "brndbotpassword";
	//public static String ERROR = "brndboterror";

	public LoginCookie(HttpServletRequest request, HttpServletResponse response)
	{
		_request = request;
		_response = response;
	}

	public boolean useCookie()
	{
		return (findCookie(USE_COOKIE) != null);
	}

	public void deleteCookie(String key)
	{
		Cookie killCookie = new Cookie(key, null);
	    killCookie.setMaxAge(0);
	    killCookie.setPath("/");
	    _response.addCookie(killCookie);
	}

	public void addCookie(String key, String val)
	{
		Cookie cookie = new Cookie (key, val);
		cookie.setMaxAge(365 * 24 * 60 * 60);
		_response.addCookie(cookie);
	}

	public void saveToCookie(boolean use_cookie, String email, String pw)
	{
		if (!use_cookie)
		{
			// delete the cookies
			deleteCookie(USE_COOKIE);
			deleteCookie(COOKIE_EMAIL_ADDRESS);
			deleteCookie(COOKIE_PASSWORD);
		}
		else
		{
			addCookie(USE_COOKIE, "yes");
			addCookie(COOKIE_EMAIL_ADDRESS, email);
			//addCookie(COOKIE_PASSWORD, pw);
		}
	}

	private String findCookie(String key)
	{
		Cookie cookies [] = _request.getCookies ();
		String cookieVal = null;
		if (cookies != null)
		{
			for (int i = 0; i < cookies.length; i++) 
	    	{
		    	if (cookies[i].getName().equals(key))
		    	{
		    		cookieVal = cookies[i].getValue();
		        	break;
		    	}
		   	}
		}
		return cookieVal;
	}

	public String getEmailFromCookie()
	{
		String email = "";
		if (useCookie())
		{
			email = findCookie(COOKIE_EMAIL_ADDRESS);
			if (email == null)
			{
				email = "";
			}
		}
		return email;
	}

	public String getPasswordFromCookie()
	{
		String pw = "";
		if (useCookie())
		{
			//pw = findCookie(COOKIE_PASSWORD);
			if (pw == null)
			{
				pw = "";
			}
		}
		return pw;
	}
}
