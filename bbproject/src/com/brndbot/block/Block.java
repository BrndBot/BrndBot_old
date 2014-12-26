/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import com.brndbot.promo.Promotion;

/** Block should probably have been called PromotionBlock to
 *  distinguish it from blocks not generated from the promotion.
 *  It gets a reference to a Prototype Promotion and constructs
 *  its fields from that. Other subclasses of BlockBase (e.g.,
 *  SocialBlock) are constructed in a different way. */
public class Block extends BlockBase
{
	private String blockTypeName;
	Promotion promo;
	
	public Block(
			ChannelEnum channel_type,
			//BlockType block_type,
			String block_type_name,
			int database_id,
			String name,
			String full_name,
			String starting_date,
			String schedule_reference,
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
		
		blockTypeName = block_type_name;
	}

	public Block(Block b)
	{
		super(b);
	}
	
	/** The name of the block type (promotion prototype) 
	 */
	public String getBlockTypeName () {
		return blockTypeName;
	}
}
