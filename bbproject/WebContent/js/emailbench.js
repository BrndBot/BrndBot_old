// This source file contains Javascript that is specific to the email version of edit.jsp

// Called once to initialize Kendo widgets and controls specific to this version of the editor.
function benchSpecificInits()
{
	$('#newGraphicLink').on('click', function(e)
	{
		addGraphicBlock(false, true);
	});

	$('#newSocialLink').on('click', function(e)
	{
		addSocialBlock(false, true);
	});
	
	$('#newScheduleLink').on('click', function(e)
	{
		addScheduleLink(false, true);
	});
	
	$('#newFooterLink').on('click', function(e)
	{
		addFooterBlock(false, true);
	});
	
	// Event handler when someone clicks the "up" button on a template
	$('div[class^="sortUp"]').each(function () 
	{
		$(this).on('click', function(e)
		{
			sortUpClicked(e, this.id);
			return false;
		});
	});
	
	// The event handler for toggling from the "big" version of a
	// template to the "small" version (Feature, non-featured).  There
	// is a naming convention dependency for switching to/from these
	// template versions.
	$("#toNonClassButton").kendoButton({
		click: function()
		{
			// Get the block data
			var stackIdx = bench.isolateEnum(bench.lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(NON_CLASS_OBJ);

			// Replace the entry in the array that houses all rows' data
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			bench.fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});

	// This is the complementary event handler for switching back from small to big 
	//  version of a template.
	$("#toClassButton").kendoButton({
		click: function()
		{
			// Get the block data
			var stackIdx = bench.isolateEnum(bench.lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(CLASS_OBJ);

			// Replace the entry in the array that houses all rows' data
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			bench.fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	// Same comments as above regarding class toggling, only for workshops
	$("#toNonWorkshopButton").kendoButton({
		click: function()
		{
			var stackIdx = bench.isolateEnum(bench.lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(NON_WORKSHOP_OBJ);
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			bench.fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	// See class version above
	$("#toWorkshopButton").kendoButton({
		click: function()
		{
			var stackIdx = bench.isolateEnum(bench.lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(WORKSHOP_OBJ);
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			bench.fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	// This is the function that actually does the sort up operation
	function sortUpClicked(e, id)
	{
		// Stop event percolating up the DOM
		e.preventDefault();
		
		// Take the perimeter boundry down and lose the idea of a currently selected block
		bench.unselectLastSelectedBlock();
	
		// Assumption is that the template has an enumerated value
		var stackIdx = bench.isolateEnum(id) - 1;
	//			$('#' + id).hide();
	
		// Switch the order in the data array first
		var block = blockStack[stackIdx];
		var other_block = blockStack[stackIdx - 1];
		blockStack.splice(stackIdx - 1, 2, block, other_block); // switch the order

		// Hide the previous templates since they are no longer accurate
		$('#' + block.getBlockID()).hide();
		$('#' + other_block.getBlockID()).hide();
	
		// recalculate the ids and redisplay the new order
		bench.fillBlock(stackIdx, blockStack[stackIdx - 1], true);
		bench.fillBlock(stackIdx + 1, blockStack[stackIdx], true);
		var theBlock = '#' + blockStack[stackIdx - 1].getBlockID();
		document.getElementById(blockStack[stackIdx - 1].getBlockID()).scrollIntoView();
		$(theBlock).click(); // select the template we moved
	}
	
	// Same as sortUp, only down
	$('div[class^="sortDown"]').each(function () 
	{
		$(this).on('click', function(e)
		{
			sortDownClicked(e, this.id);
			return false;
		});
	});
	
	function sortDownClicked(e, id)
	{
		e.preventDefault();
		bench.unselectLastSelectedBlock();
	
		var stackIdx = bench.isolateEnum(id) - 1;
	//			$('#' + id).hide();
		var block = blockStack[stackIdx];
		var other_block = blockStack[stackIdx + 1];
		blockStack.splice(stackIdx, 2, other_block, block); // switch the order
		$('#' + other_block.getBlockID()).hide();
		$('#' + block.getBlockID()).hide();
		
		// recalculate the ids and redisplay
		bench.fillBlock(stackIdx + 1, blockStack[stackIdx], true);
		bench.fillBlock(stackIdx + 2, blockStack[stackIdx + 1], true);
		document.getElementById(blockStack[stackIdx + 1].getBlockID()).scrollIntoView();
		$('#' + blockStack[stackIdx + 1].getBlockID()).click();
	}
}

// Adds a footer object and template to the bottom of the stack
function addFooterBlock(suppress_click, display_edit_type)
{
	// define the default values.  Needs to extract data from the database, not kludged/hardcoded.
	//  I would create a Java class or function from a class, call it in edit.jsp in a scriptlet,
	//  assign the results to a javascript variable and reference it globally.
	var block = new Block(
			EMAIL_CHANNEL,
			null,
			' Company Footer',
			0,
			'Yoga Sakti &nbsp;29 Bridge St, Salem, MA 01970 &nbsp;&nbsp;(978) 744-9642',
			'FullName',
			'17-sep-2014',
			'SchedRef',
			'',
			'',
			'imgurl');
	blockStack.push(block);
	bench.fillBlock(blockStack.length, block, display_edit_type);
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
	}
}

// Adds a schedule object and template to the bottom of the stack
function addScheduleLink(suppress_click, display_edit_type)
{
	var id = '#' + idPrefix[SCHEDULE_OBJ - 1] + 'Block-' + (blockStack.length + 1);
	var block = new Block(
			EMAIL_CHANNEL,
			null,
			' Upcoming Schedule',
			0,
			'Schedule for September and October',
			'Check it out',
			'17-sep-2014',
			'SchedRef',
			'New Yoga, Fitness, and Barre Classes.',
			'New Yoga, Fitness, and Barre Classes.',
			'imgurl');
	blockStack.push(block);
	bench.fillBlock(blockStack.length, block, display_edit_type);
//	$(id).show();
	document.getElementById(block.getBlockID()).scrollIntoView();
	$(id).click();
}

// Adds a social media object and template to the bottom of the stack
function addSocialBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			null,
			' Social Buttons',
			0,
			'Join Us on Social for Exclusive Updates!',
			'FullName',
			'17-sep-2014',
			'SchedRef',
			'Links to your social networks',
			'Links to your social networks',
			'');
	blockStack.push(block);
	bench.fillBlock(blockStack.length, block, display_edit_type);
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
	}
}

// Adds a graphic/image object and template to the bottom of the stack
function addGraphicBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			null,
			' Image or Graphic',
			0,
			'name',
			'FullName',
			'17-sep-2014',
			'SchedRef',
			'My corporate logo',
			'No description',
			'imgurl'
			);
	blockStack.push(block);
	bench.fillBlock(blockStack.length, block, display_edit_type);
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
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

	// GRAPHIC BLOCK
	var hdr_arr = new Array();

	// TEXT BLOCK
	var arr = new Array();
	arr.push(new FieldMap('textName', 'textEditName', true, INPUT_TEXT_FLD));
	arr.push(new FieldMap('textDescription', 'textEditDescription', true, TEXT_AREA_FLD));
	masterFields[TEXT_OBJ - 1] = arr;

	// SCHEDULE BLOCK
	var sch_arr = new Array();
	sch_arr.push(new FieldMap('scheduleName', 'scheduleEditName', true, INPUT_TEXT_FLD));
	sch_arr.push(new FieldMap('scheduleDescription', 'scheduleEditDescription', true, INPUT_TEXT_FLD));
	sch_arr.push(new FieldMap('scheduleFullName', 'scheduleEditFullName', true, BUTTON_TEXT_FLD));
	masterFields[SCHEDULE_OBJ - 1] = sch_arr;

	// SOCIAL BLOCK
	var soc_arr = new Array();
	soc_arr.push(new FieldMap('socialName', 'socialEditName', true, INPUT_TEXT_FLD));
	masterFields[SOCIAL_OBJ - 1] = soc_arr;

	// FOOTER BLOCK
	var foot_arr = new Array();
	foot_arr.push(new FieldMap('footerName', 'footerEditName', true, INPUT_TEXT_FLD));
	foot_arr.push(new FieldMap('footerDescription', 'footerEditDescription', true, INPUT_TEXT_FLD));
	masterFields[FOOTER_OBJ - 1] = foot_arr;
}

// This function is called once when the editor first loads.  It displays the default set of templates for this editor.
function initialBlockDisplay(the_starting_block)
{
	// display the panes (suppress selection)
	addGraphicBlock(true, false);
	addTextBlock(true, false);
	var count = blockStack.push(the_starting_block);
	var block = blockStack[count - 1];
	bench.fillBlock(count, block, false);
	addSocialBlock(true, false);
	addFooterBlock(true, false);
}

// Adds a simple text object and template to the bottom of the stack
function addTextBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			TEXT_OBJ,
			' Intro/Text Header',
			0,
			'Hey {Customer Name},',
			'FullName',
			'17-sep-2014',
			'SchedRef',
			'Come enjoy our new classes and check out our new Fall schedule.  Sign up today!',
			'Come enjoy our new classes and check out our new Fall schedule.  Sign up today!',
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
	blockStack.push(block);
	bench.fillBlock(blockStack.length, block, true);
}
