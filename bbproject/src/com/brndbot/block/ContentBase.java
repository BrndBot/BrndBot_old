/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.sql.ResultSet;
import java.sql.SQLException;


public class ContentBase
{
	private Integer _content_type_id;
	private String _content_description;

	public ContentBase()
	{
		_content_type_id = new Integer(0);
		_content_description = "";
	}

	public ContentBase(ContentBase m)
	{
		_content_type_id = new Integer(m._content_type_id.intValue());
		_content_description = m._content_description;
	}

	public Integer getContentTypeID() { return _content_type_id; }
	public void setContentTypeID(int arg) { _content_type_id = new Integer(arg); } 

	public String getContentDescription() { return _content_description; }
	public void setContentDescription(String arg) { _content_description = arg; }

	public ContentBase(ResultSet rs)
	{
		try
		{
			_content_type_id = new Integer(rs.getInt("ContentTypeID"));
			_content_description = rs.getString("ContentDescription");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
//			System.out.println("att name: " + _content_description);
		}
	}


}
