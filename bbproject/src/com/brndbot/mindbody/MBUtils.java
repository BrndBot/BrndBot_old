/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.mindbody;

import javax.xml.datatype.XMLGregorianCalendar;

import com.brndbot.system.Assert;

/* Handy utility functions for the MindBody API */
public class MBUtils 
{
	static public String return3LetterMonth(int month)
	{
		Assert.that(month > 0 && month < 13, "Invalid month: " + month);
		final String[] months = 
		{
			"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"
		};
		return months[month - 1];
	}
	
	static public String formatXMLDate(XMLGregorianCalendar d)
	{
		return String.format("%d-%s-%d",
				d.getDay(),
				MBUtils.return3LetterMonth(d.getMonth()),
				d.getEonAndYear());
	}
}
