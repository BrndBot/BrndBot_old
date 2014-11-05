package com.brndbot.block;

public class Block extends BlockBase
{
	public Block(
			ChannelEnum channel_type,
			BlockType block_type,
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
		block_type,
		block_type_name,
		database_id,
		name,
		full_name,
		starting_date,
		schedule_reference,
		description,
		short_description,
		img_url);
	}

	public Block(Block b)
	{
		super(b);
	}
}
