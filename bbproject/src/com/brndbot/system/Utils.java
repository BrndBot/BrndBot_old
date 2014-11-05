package com.brndbot.system;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import java.text.DecimalFormat;

public class Utils
{
	public Utils() { }

	static public boolean isInt(String word)
	{
		try
    	{
			Integer.parseInt(word);
    	}
    	catch (NumberFormatException e)
    	{
    		return false;
    	}
    	return true;
	}

	static public String numWithCommas(int num)
	{
		String txt = "";
		try
    	{
        	DecimalFormat myFormatter = new DecimalFormat("###,###,###");
        	txt = myFormatter.format(num);
    	}
    	catch (NumberFormatException e)
    	{
    	}
    	return txt;
	}

	static public String numWithCommas(long num)
	{
		String txt = "";
		try
    	{
        	DecimalFormat myFormatter = new DecimalFormat("###,###,###,###");
        	if (num > 0)
        	{
	        	txt = myFormatter.format(num);
        	}
    	}
    	catch (NumberFormatException e)
    	{
    	}
    	return txt;
	}

	static public Integer safeIntFromString(String num)
	{
		Integer intV = null;
		if (num == null || num.equals(""))
		{
			return new Integer(0);
		}
		try
    	{
        	intV = new Integer(Integer.parseInt(num));
    	}
    	catch (NumberFormatException e)
    	{
    		System.out.println("NumberFormatException on " + num);
    		intV = new Integer(0);
    	}
    	return intV;
	}

	static public String limitLength(String arg, int len)
	{
		if (arg == null)
		{
			arg = "";
		}
		if (arg.length() < len+1)
		{
			return arg;
		}
		return arg.substring(0, len-1);
	}

	static public String Slashies(String arg)
	{
		if (arg != null && arg.contains("\\"))
        {
        	return arg.replace("\\", "/");
        }
		return arg;
	}

	static public int SafeInt(Integer arg)
	{
		if (arg == null)
		{
			return 0;
		}
		return arg.intValue();
	}
	
	static public boolean SafeBoolean(Boolean arg)
	{
		if (arg == null)
		{
			return false;
		}
		return arg.booleanValue();
	}
	
	static public String quoteNN(String arg)
	{
		String ret = "\"";
		if (arg == null)
		{
			ret = "";
		}
		return ret;
	}

	static public long SafeLong(Long arg)
	{
		if (arg == null)
		{
			return 0L;
		}
		return arg.longValue();
	}
	
	static public String SafeString(String arg)
	{
		if (arg == null)
        {
        	arg = "";
        }
		return arg.trim();
	}

/*	static private boolean outputParam()
	{
		 return (Utils.SafeString(EmpProp.get(EmpProp.PARAM_OUTPUT)).length() != 0);
	}
*/
	static public String getStringParameter(
			HttpServletRequest request,
			String parameter)
	{
		String str = SafeString((String)request.getParameter(parameter)); 
//		if (outputParam())
//			System.out.println("Request Param " + parameter + ": " + 
//					(str.length() != 0 ? str : "{empty}"));
		return str;
	}

	static public int getIntParameter(
			HttpServletRequest request,
			String parameter)
	{
		String temp = SafeString((String)request.getParameter(parameter));
//		if (outputParam())
//			System.out.println("Int Param " + parameter + ": " + 
//				(temp == null ? "{empty}" :
//					(temp.length() == 0 ? "{empty}" : temp)));
		System.out.println("TEMP for parameter: " + parameter + " is: " + temp);
		int ret = 0;
		try
		{
			ret = Integer.parseInt(temp);
		}
		catch (NumberFormatException e)
		{ }
		return ret;
	}

	static public long getLongParameter(
			HttpServletRequest request,
			String parameter)
	{
		String temp = SafeString((String)request.getParameter(parameter));
//		if (outputParam())
//			System.out.println("Int Param " + parameter + ": " + 
//				(temp == null ? "{empty}" :
//					(temp.length() == 0 ? "{empty}" : temp)));
		long ret = 0;
		try
		{
			ret = Long.parseLong(temp);
		}
		catch (NumberFormatException e)
		{ }
		return ret;
	}

	static public String getUnsafeStringParameter(
			HttpServletRequest request,
			String parameter)
	{
		String str = SafeString((String)request.getParameter(parameter)); 
//		if (outputParam())
//			System.out.println("Request Param " + parameter + ": " + 
//					(str.length() != 0 ? str : "{empty}"));
		return (str.length() == 0 ? null : str);
	}

	static public Integer getUnsafeIntParameter(
			HttpServletRequest request,
			String parameter)
	{
		int temp = Utils.getIntParameter(request, parameter);
//		if (outputParam())
//			System.out.println("Int Param " + parameter + ": " + temp); 
		return (temp == 0 ? null : new Integer(temp));
	}

	static public String getStringSession(
			HttpSession session,
			String parameter)
	{
		String str = SafeString((String)session.getAttribute(parameter)); 
		return str;
	}

	static public int getIntSession(
			HttpSession session,
			String attribute) throws NumberFormatException
	{
		String temp = SafeString((String)session.getAttribute(attribute));
		int ret = 0;
//		if (outputParam())
//			System.out.println("Session Int Param " + attribute + ": " + 
//				(temp == null ? "{empty}" :
//					(temp.length() == 0 ? "{empty}" : temp)));
		try
		{
			ret = Integer.parseInt(temp);
		}
		catch (NumberFormatException e)
		{ }

		return ret;
	}

	static public String slashed(String str)
	{
		if (str.length() != 0 && !str.startsWith("/"))
		{
			str = "/" + str; 
		}
		return str;
	}

	static public Boolean getBooleanSession(
			HttpSession session,
			String attribute) throws NumberFormatException
	{
		Boolean temp = (Boolean)session.getAttribute(attribute);
		if (temp == null)
		{
			temp = new Boolean(false);
		}
		return temp;
	}

	static public String trimRelative(String str)
	{
		if (str == null)
		{
			return "";
		}

		if (str.startsWith("../"))
		{
			return str.substring(2);
		}
		return str;
	}

	static public Integer unSafeInteger(Integer arg)
	{
		Integer new_arg = arg;
		if (new_arg != null)
		{
			new_arg = new Integer(arg.intValue());
		}
		return new_arg;
	}

	static public String jsSafeStr(String s)
	{
		return s.replace("\'", "\\\'");
	}
}
