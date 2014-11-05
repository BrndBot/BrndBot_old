var INPUT_TEXT_FLD = 1;
var TEXT_AREA_FLD = 2;
var CHECK_BOX_FLD = 3;
var BUTTON_TEXT_FLD = 4;
var IMAGE_FLD = 5;

function FieldMap (
	display_id, 
	edit_id,
	uses_enumeration,
	field_type)
{
	this.display_id = display_id;
	this.edit_id = edit_id;
	this.use_enum = uses_enumeration;
	if (field_type < INPUT_TEXT_FLD || field_type > IMAGE_FLD)
	{	
		alert('Invalid field type: ' + field_type);
		this.field_type = 0;
	}
	else
		this.field_type = field_type;
}

FieldMap.prototype.getDisplayID = function(stack_enum)
{
	var id = '#' + this.display_id;
	if (this.use_enum)
	{
		id += '-' + stack_enum;
	}
	return id;
};

FieldMap.prototype.getEditID = function(stack_enum)
{
	return '#' + this.edit_id;
};

FieldMap.prototype.setEditValue = function(text, stack_idx)
{
	if (this.field_type == INPUT_TEXT_FLD)
	{
		$(this.getEditID()).val(text);
	}
	else if (this.field_type == TEXT_AREA_FLD)
	{
		$(this.getEditID()).html(text);
	}
	else if (this.field_type == BUTTON_TEXT_FLD)
	{
		$(this.getEditID()).val(text);
	}
	else if (this.field_type == IMAGE_FLD)
	{
		$(this.getEditID()).html(text);
	}
	else
		alert('Not implemented yet.');
};

FieldMap.prototype.getEditValue = function()
{
	var text = null;
	if (this.field_type == INPUT_TEXT_FLD)
	{
		text = $(this.getEditID()).val();
	}
	else if (this.field_type == TEXT_AREA_FLD)
	{
		text = $(this.getEditID()).val();
	}
	else if (this.field_type == BUTTON_TEXT_FLD)
	{
		text = $(this.getEditID()).val();
	}
	else if (this.field_type == IMAGE_FLD)
	{
		text = $(this.getEditID()).html();
	}
	else
		alert('Not implemented yet.');
	return text;
};

FieldMap.prototype.setDisplayValue = function(text, stack_idx)
{
	if (this.field_type == INPUT_TEXT_FLD || this.field_type == TEXT_AREA_FLD
			|| this.field_type == BUTTON_TEXT_FLD || this.field_type == IMAGE_FLD)
	{
		$(this.getDisplayID(stack_idx)).html(text);
	}
	else
		alert('Not implemented yet.');
};
