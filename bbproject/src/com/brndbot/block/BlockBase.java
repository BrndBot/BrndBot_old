/**
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2014
 */
package com.brndbot.block;

import com.brndbot.mindbody.MBPoly;
import com.brndbot.mindbody.MBPolyException;
import com.brndbot.mindbody.MBPolyFactory;
import com.brndbot.system.Assert;

public class BlockBase
{
	private ChannelEnum _channel_type;
	private BlockType _block_type;
	private String _block_type_name;
	private Integer _database_id;
	private String _name; // main name of the content
	private String _full_name; // Teacher first & last name
	private String _starting_date; // formatted starting date
	private String _schedule_reference;  // formatted schedule reference
	private String _description;  // content description
	private String _short_description;  // no html short version of the description
	private String _img_url;  // img tag for workshops and classes (for now)

	public BlockBase(
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
		_channel_type = ChannelEnum.create(channel_type.getValue().intValue());
		_block_type = BlockType.create(block_type.getValue().intValue());
		_block_type_name = block_type_name;
		Assert.that(database_id > 0, "Database ID is zero for the block.");
		_database_id = new Integer(database_id);
		_name = name;
		_full_name = full_name;
		_starting_date = starting_date;
		_schedule_reference = schedule_reference;
		_description = description;
		_short_description = short_description;
		_img_url = img_url;
	}

	public BlockBase(BlockBase b)
	{
		_channel_type = ChannelEnum.create(b._channel_type.getValue().intValue());
		_block_type = BlockType.create(b._block_type.getValue().intValue());
		_block_type_name = b._block_type_name;
		Assert.that(b._database_id.intValue() > 0, "Database ID is zero for the block.");
		_database_id = new Integer(b._database_id.intValue());
		_name = b._name;
		_full_name = b._full_name;
		_starting_date = b._starting_date;
		_schedule_reference = b._schedule_reference;
		_description = b._description;
		_short_description = b._short_description;
		_img_url = b._img_url;
	}

	public boolean equals(Object object)
	{
	    boolean isEqual= false;

	    if (object != null && object instanceof BlockBase)
	    {
	    	BlockBase b = ((BlockBase)object);
	        isEqual = (this._channel_type.getValue().intValue() == 
	        		b._channel_type.getValue().intValue()) &&
	        	(this._block_type.getValue().intValue() == 
	        		b._block_type.getValue().intValue()) &&
	        	(this._database_id.intValue() == b._database_id.intValue());
	    }
	    return isEqual;
	}

	public int hashCode() 
	{
		return ((this._database_id.intValue() * 100) + 
				(this._channel_type.getValue().intValue() * 10) +
				(this._block_type.getValue().intValue()));
	}

	public ChannelEnum get_channel_type() {
		return _channel_type;
	}

	public void set_channel_type(ChannelEnum _channel_type) {
		this._channel_type = _channel_type;
	}

	public BlockType get_block_type() {
		return _block_type;
	}

	public void set_block_type(BlockType _block_type) {
		this._block_type = _block_type;
	}

	public String get_block_type_name() {
		return _block_type_name;
	}

	public void set_block_type_name(String _block_type_name) {
		this._block_type_name = _block_type_name;
	}

	public Integer get_database_id() {
		return _database_id;
	}

	public void set_database_id(Integer _database_id) {
		this._database_id = _database_id;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_full_name() {
		return _full_name;
	}

	public void set_full_name(String _full_name) {
		this._full_name = _full_name;
	}

	public String get_starting_date() {
		return _starting_date;
	}

	public void set_starting_date(String _starting_date) {
		this._starting_date = _starting_date;
	}

	public String get_schedule_reference() {
		return _schedule_reference;
	}

	public void set_schedule_reference(String _schedule_reference) {
		this._schedule_reference = _schedule_reference;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public String get_short_description() {
		return _short_description;
	}

	public void set_short_description(String _short_description) {
		this._short_description = _short_description;
	}

	public String get_img_url() {
		return _img_url;
	}

	public void set_img_url(String _img_url) {
		this._img_url = _img_url;
	}

	static public BlockBase getAsBlock(
		ChannelEnum channelType, int user_id, int btype, int id, int max_width)
	{
		Block block = null;
		BlockType block_type = BlockType.create(btype);

		MBPolyFactory mb_factory = null;
		try 
		{
			mb_factory = new MBPolyFactory();
			MBPoly mb_client = mb_factory.createMBPoly(block_type, user_id);
			block =(Block)mb_client.retrieveAsBlock(channelType, user_id, id, 450, max_width);
		} 
		catch (MBPolyException e) 
		{
			e.printStackTrace();
		}
		return block;
	}
}
