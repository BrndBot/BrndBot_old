// This source file contains Javascript that is specific to the FB version of edit.jsp

// Called once to initialize Kendo widgets and controls specific to this version of the editor.
function benchSpecificInits()
{
	$('#newGraphicLink').on('click', function(e)
	{
		unimplemented();
	});

	$('#newSocialLink').on('click', function(e)
	{
		unimplemented();
	});

	$('#newScheduleLink').on('click', function(e)
	{
		unimplemented();
	});

	$('#newFooterLink').on('click', function(e)
	{
		unimplemented();
	});

	function unimplemented()
	{
		alert('This feature is not yet installed on this server.');
	}
}

// Map the edit field with the corresponding display field.  There is a similar function for this for each
//  editor/channel type.
function initFieldMap()
{
	// FieldMap constructor takes the following values:
	// display_id = ID of the div in the template
	// edit_id = ID of the control in the editor HTML used to modify value
	// uses_enumeration = Is the template enumerated?  Used to calculate ID in template correctly
	// field_type = what type of control (button, text field, textarea, etc.)

	// TEXT BLOCK
	var arr = new Array();
	arr.push(new FieldMap('textName', 'textEditName', true, INPUT_TEXT_FLD));
	arr.push(new FieldMap('textDescription', 'textEditDescription', true, TEXT_AREA_FLD));
	masterFields[TEXT_OBJ - 1] = arr;

	// CLASS BLOCK
	var class_arr = new Array();
	class_arr.push(new FieldMap('classHeader', 'classEditHeader', true, INPUT_TEXT_FLD));
	class_arr.push(new FieldMap('className', 'classEditName', true, INPUT_TEXT_FLD));
	class_arr.push(new FieldMap('classDescription', 'classEditDescription', true, TEXT_AREA_FLD));
	class_arr.push(new FieldMap('classImgURL', 'classEditImgURL', true, IMAGE_FLD));
	masterFields[CLASS_OBJ - 1] = class_arr;

	// WORKSHOP BLOCK
	var wrk_arr = new Array();
	wrk_arr.push(new FieldMap('workshopHeader', 'workshopEditHeader', true, INPUT_TEXT_FLD));
	wrk_arr.push(new FieldMap('workshopName', 'workshopEditName', true, INPUT_TEXT_FLD));
	wrk_arr.push(new FieldMap('workshopDescription', 'workshopEditDescription', true, TEXT_AREA_FLD));
	wrk_arr.push(new FieldMap('workshopImgURL', 'workshopEditImgURL', true, IMAGE_FLD));
	masterFields[WORKSHOP_OBJ - 1] = wrk_arr;

	// STAFF BLOCK
	var st_arr = new Array();
	st_arr.push(new FieldMap('staffName', 'staffEditName', true, INPUT_TEXT_FLD));
	st_arr.push(new FieldMap('staffDescription', 'staffEditDescription', true, TEXT_AREA_FLD));
	masterFields[STAFF_OBJ - 1] = st_arr;
}

// This function is called once when the editor first loads.  It displays the default set of templates for this editor.
function initialBlockDisplay(the_starting_block)
{
	// save the orig block type for use blow
	var saveType = the_starting_block.block_type;

	// First block is text for the chosen object
	the_starting_block.block_type = TEXT_OBJ;
	var count = blockStack.push(the_starting_block);
	var block = blockStack[count - 1];
	bench.fillBlock(count, block, false);

	// Second block is original type, mostly graphic
	the_starting_block.block_type = saveType;
	count = blockStack.push(the_starting_block);
	block = blockStack[count - 1];
	bench.fillBlock(count, block, false);

	// display the panes (suppress selection)
//	addTextBlock(true);
//	addGraphicBlock(true);
}

// Adds a simple text object and template to the bottom of the stack
function addTextBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			TEXT_OBJ,
			' Text',
			0,
			'Change to your post title',
			'FullName',
			'17-sep-2014',
			'SchedRef',
			'Change to your message',
			'Change to your message',
			'');
	blockStack.push(block);
	bench.fillBlock(blockStack.length, block, display_edit_type);
	$(block.getBlockID()).show();
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
	}
}

function editorSpecificDynamicPush(block)
{
	// Get rid of the current stuff
	for (var i = 0; i < blockStack.length; i++)
	{
		$('#' + blockStack[i].blockID).hide();
	}
	blockStack.length = 0;

	initialBlockDisplay(block);
//	blockStack.push(block);
//	bench.fillBlock(blockStack.length, block, true);
}
