/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

//import com.brndbot.system.Assert;

/**
 *  In this revised version of BlockBase, we do away with all 
 *  organization-specific block types. We may want to inject a 
 *  Model here in its place.  
 *  
 *  What exactly IS a Block, anyway?
 *  
 *  TODO integrate with the new Model world.
 */
public class BlockBase
{
	private ChannelEnum channelType;
	//private BlockType _block_type;
	private Integer _database_id;
	private String name; // main name of the content
	private String description;  // content description
	private String shortDescription;  // no html short version of the description

	public BlockBase(
			ChannelEnum channel_type,
			//BlockType block_type,
			int database_id,
			String nam,
			String desc,
			String short_description)
	{
		channelType = ChannelEnum.create(channel_type.getValue());
//		_block_type = BlockType.create(block_type.getValue().intValue());
		_database_id = new Integer(database_id);
		name = nam;
		description = desc;
		shortDescription = short_description;
	}

	public BlockBase(BlockBase b)
	{
		channelType = ChannelEnum.create(b.channelType.getValue());
//		_block_type = BlockType.create(b._block_type.getValue().intValue());
//		Assert.that(b._database_id.intValue() > 0, "Database ID is zero for the block.");
		_database_id = new Integer(b._database_id.intValue());
		name = b.name;
		description = b.description;
		shortDescription = b.shortDescription;
	}

	public boolean equals(Object object)
	{
	    boolean isEqual= false;

	    if (object != null && object instanceof BlockBase)
	    {
	    	BlockBase b = ((BlockBase)object);
	        isEqual = (this.channelType.getValue() == 
	        		b.channelType.getValue());
	    }
	    return isEqual;
	}

	public int hashCode() 
	{
		return ((this._database_id * 100) + 
				(this.channelType.getValue() * 10)); 
	}

	public int getChannelType() {
		return channelType.getValue();
	}
	
	public void setChannelType (int chType) {
		channelType = ChannelEnum.getByValue(chType);
	}

	public void setChannelType(ChannelEnum _channel_type) {
		this.channelType = _channel_type;
	}

	/** What is a database ID, anyway? */
	public Integer getDatabaseId() {
		return _database_id;
	}

	public void setDatabaseId(Integer _database_id) {
		this._database_id = _database_id;
	}

	/** Get the name of the Promotion being used */
	public String getName() {
		return name;
	}

	public void setName(String _name) {
		this.name = _name;
	}

	/* There is no sane reason why a block should have a "full name"
	 * or "starting date." Or a lot of the other client-dependent things which
	 * are in here! */
//	public String getFullName() {
//		return _full_name;
//	}
//
//	public void setFullName(String _full_name) {
//		this._full_name = _full_name;
//	}
//
//	public String get_starting_date() {
//		return _starting_date;
//	}
//
//	public void set_starting_date(String _starting_date) {
//		this._starting_date = _starting_date;
//	}

//	public String get_schedule_reference() {
//		return _schedule_reference;
//	}
//
//	public void set_schedule_reference(String _schedule_reference) {
//		this._schedule_reference = _schedule_reference;
//	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String _description) {
		this.description = _description;
	}

	public String getShortDescription() {
		return shortDescription;
	}

	public void setShortDescription(String _short_description) {
		this.shortDescription = _short_description;
	}

	static public BlockBase getAsBlock(
		ChannelEnum channelType, int user_id, int btype, int id, int max_width)
	{
		Block block = null;
		// TODO build a block somehow, probably based on a Prototype Promotion.
		// The old code used MindBody-specific methods to create a block.
		// It seems to do this based just on the channel type, user ID,
		// and a mysterious constant of 450.
		//
		// May not be needed. Use bean-type operations instead.
		return block;
	}
}
