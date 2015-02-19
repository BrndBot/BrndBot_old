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
	description,
	short_description,
	imgURL)
{
	this.channel_type = channel_type;
	this.block_type = block_type;
	this.block_type_desc = block_type_desc;
	this.database_id = database_id;
	this.name = name;
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
	// *** In this new world, we use promo_proto instead of the
	// *** client-specific block_type. This will be the JSON
	//     rendition of the promotion prototype, and its fields
	//     will be used to create an array of elements.
	for (i = 0; i < this.promo_proto.fields.length; i++) {
		var field = this.promo_proto.fields[i];
		// So what exactly does this JavaScript do with a block?
		// fillValue does ... something.
	}
	
	// If the template is not enumerated (meaning it's a singleton and cannot be moved
	//  up or down), then the IDs don't have the "i" suffix.
	if (i > 0)
	{
		//*** Replace this with an array of objects based on the
		//*** promotion prototype instead of these specific items.
		this.blockID = base + 'Block-' + i;
		this.buttonID = base + 'Button-' + i;
		this.nameID = base + 'Name-' + i;
		this.imgUrlID = base + 'ImgURL-' + i;
	}
	else
	{
		// Probably shouldn't get here
		alert('missed one Gary- I: ' + i);
		this.blockID = base + 'Block';
		this.buttonID = base + 'Button';
		this.nameID = base + 'Name';
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

	if (this.blockID !== '') $('#' + this.blockID).show();
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
