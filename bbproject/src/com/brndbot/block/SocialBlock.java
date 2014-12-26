/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

/** A SocialBlock seems to be the one that holds
 *  social media links in the editor.
 */
public class SocialBlock extends BlockBase
{
	public SocialBlock(
			ChannelEnum channel_type,
			//BlockType block_type,
			int database_id,
			String name,
			String description,
			String short_description,
			String img_url)
	{
		super(channel_type,
		//block_type,
		database_id,
		name,
		description,
		short_description,
		img_url);
	}

	public SocialBlock(SocialBlock b)
	{
		super(b);
	}
}
