/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import com.brndbot.client.Promotion;

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
			String short_description)
	{
		super(channel_type,
		//block_type,
		database_id,
		name,
		description,
		short_description);
		
		blockTypeName = block_type_name;
	}

	/** We need a zero-argument constructor to permit useBean in JSP.
	 *  Since we're using it that way, the heavily-loaded constructor
	 *  may become obsolete.
	 */
	public Block () {
		super (ChannelEnum.UNDEFINED,
				-1,
				"",
				"",
				"");
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
