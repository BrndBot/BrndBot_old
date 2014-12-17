// block.js -- Javascript class stored in the blockStack array for each row in the editor.  Although
// this class is structured, you can actually use any of the fields for any purpose.  Just make sure the
// HTML in the template where the data is to be displayed is synched correctly (the name field goes in the "name" 
// div in the HTML, for example).  Also, sync the content.js HTML so that the values can be edited correctly.

function Block (
	channel_type, 
	block_type,
	block_type_desc,
	database_id,
	name,
	full_name,
	starting_date,
	schedule_reference,
	description,
	short_description,
	imgURL)
{
	this.channel_type = channel_type;
	this.block_type = block_type;
	this.block_type_desc = block_type_desc;
	this.database_id = database_id;
	this.name = name;
	this.full_name = full_name;
	this.starting_date = starting_date;
	this.schedule_reference = schedule_reference;
	this.description = description;
	this.short_description = short_description;
	this.imgURL = imgURL;

	this.blockID = '';
	this.buttonID = '';
	this.nameID = '';
	this.fullNameID = '';
	this.descriptionID = '';
}

// Calculate the IDs for a given template number "i"
Block.prototype.getBlockElementIDs = function(i) 
{
	var base = '';
	if (this.block_type == 1)
		base = 'class';
	else if (this.block_type == 2)
		base = 'workshop';
	else if (this.block_type == 3)
		base = 'staff';
	else if (this.block_type == 4)
		base = 'client';
	else if (this.block_type == 5)
		base = 'finder';
	else if (this.block_type == 6)
		base = 'sale';
	else if (this.block_type == 7)
		base = 'site';
	else if (this.block_type == 8)
		base = 'appointment';
	else if (this.block_type == 9)
		base = 'schedule';
	else if (this.block_type == 10)
		base = 'text';
	else if (this.block_type == 11)
		base = 'footer';
	else if (this.block_type == 12)
		base = 'social';
	else if (this.block_type == 13)
		base = 'graphic';
	else if (this.block_type == 14)
		base = 'nonclass';
	else if (this.block_type == 15)
		base = 'nonworkshop';
	else
	{
		alert('Unknown block_type, error: ' + this.block_type);
	}

	// If the template is not enumerated (meaning it's a singleton and cannot be moved
	//  up or down), then the IDs don't have the "i" suffix.
	if (i > 0)
	{
		this.blockID = base + 'Block-' + i;
		this.buttonID = base + 'Button-' + i;
		this.nameID = base + 'Name-' + i;
		this.fullNameID = base + 'FullName-' + i;
		this.descriptionID = base + 'Description-' + i;
		this.imgUrlID = base + 'ImgURL-' + i;
	}
	else
	{
		// Probably shouldn't get here
		alert('missed one Gary- I: ' + i);
		this.blockID = base + 'Block';
		this.buttonID = base + 'Button';
		this.nameID = base + 'Name';
		this.fullNameID = base + 'FullName';
		this.descriptionID = base + 'Description';
		this.imgUrlID = base + 'ImgURL';
	}
};

// These are getters
Block.prototype.getBlockID = function()
{
	return this.blockID;
};

Block.prototype.getBlockType = function()
{
	return this.block_type;
};

Block.prototype.setBlockType = function(type)
{
	this.block_type = type;
};

Block.prototype.getBlockTypeDesc = function()
{
	return this.block_type_desc;
};

// Fill in the values for in this object into the template.  If the IDs are not
//  defined in the template, simply skip over it.
Block.prototype.fillMyValues = function(useShortDescription, displayEditType)
{
	fillValue(this.nameID, this.name);
	fillValue(this.fullNameID, this.full_name);
	fillValue(this.startingDateID, this.starting_date);
	if (useShortDescription)
	{
		fillValue(this.descriptionID, this.short_description);
	}
	else
	{
		fillValue(this.descriptionID, this.description);
	}
	if (displayEditType)
	{
		$('#editType').html(this.block_type_desc);
	}
	fillValue(this.imgUrlID, this.imgURL);

	if (this.blockID != '') $('#' + this.blockID).show();
	else $('#' + this.blockID).hide();
};

function fillValue(id, val)
{
	var obj = $('#' + id);
	if (obj)
	{
		$('#' + id).html(val);
	}
}
