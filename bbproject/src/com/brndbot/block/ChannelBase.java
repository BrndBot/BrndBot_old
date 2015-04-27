/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ChannelBase
{
	private Integer _channel_type_id;
	private String _channel_description;
	private String _source_file_name;
	private Integer _block_limit;
	private Boolean _is_social_media;

	public ChannelBase()
	{
		_channel_type_id = new Integer(0);
		_channel_description = "";
		_source_file_name = "";
		_block_limit = new Integer(1);
		_is_social_media = new Boolean(false);
	}

	public ChannelBase(ChannelBase m)
	{
		_channel_type_id = new Integer(m._channel_type_id.intValue());
		_channel_description = m._channel_description;
		_source_file_name = m._source_file_name;
		_block_limit = new Integer(m._block_limit.intValue());
		_is_social_media = new Boolean(m._is_social_media.booleanValue());
	}

	public Integer getContentTypeID() { return _channel_type_id; }
	public void setContentTypeID(int arg) { _channel_type_id = new Integer(arg); } 

	public String getContentDescription() { return _channel_description; }
	public void setContentDescription(String arg) { _channel_description = arg; }

	public String getSourceFileName() { return _source_file_name; }
	public void setSourceFileName(String arg) { _source_file_name = arg; }

	public Integer getBlockLimit() { return _block_limit; }
	public void setBlockLimit(Integer arg) { _block_limit = new Integer(arg.intValue()); }

	public Boolean getIsSocialMedia() { return _is_social_media; }
	public void setIsSocialMedia(Boolean arg) { _is_social_media = new Boolean(arg.booleanValue()); }

	public ChannelBase(ResultSet rs)
	{
		try
		{
			_channel_type_id = new Integer(rs.getInt("ContentTypeID"));
			_channel_description = rs.getString("ContentDescription");
			_source_file_name = rs.getString("SourceFileName");
			_block_limit = rs.getInt("BlockLimit");
			_is_social_media = rs.getBoolean("IsSocialMedia");
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally
		{
//			System.out.println("att name: " + _channel_description);
		}
	}


}
