// This source file contains Javascript that is specific to the email version of bench.jsp

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
	
	$('div[class^="sortUp"]').each(function () 
	{
		$(this).on('click', function(e)
		{
			sortUpClicked(e, this.id);
			return false;
		});
	});
	
	$("#toNonClassButton").kendoButton({
		click: function()
		{
			var stackIdx = isolateEnum(lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(NON_CLASS_OBJ);
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	$("#toClassButton").kendoButton({
		click: function()
		{
			var stackIdx = isolateEnum(lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(CLASS_OBJ);
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	$("#toNonWorkshopButton").kendoButton({
		click: function()
		{
			var stackIdx = isolateEnum(lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(NON_WORKSHOP_OBJ);
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	$("#toWorkshopButton").kendoButton({
		click: function()
		{
			var stackIdx = isolateEnum(lastSelectedBlock) - 1;
			var block = blockStack[stackIdx];
			block.setBlockType(WORKSHOP_OBJ);
			blockStack.splice(stackIdx, 1, block);
			$('#' + block.getBlockID()).hide();
			
			// recalculate the ids and redisplay
			fillBlock(stackIdx + 1, blockStack[stackIdx], true);
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
			$('#' + blockStack[stackIdx].getBlockID()).click();
		}
	});
	
	function sortUpClicked(e, id)
	{
		e.preventDefault();
		unselectLastSelectedBlock();
	
		var stackIdx = isolateEnum(id) - 1;
	//			$('#' + id).hide();
		var block = blockStack[stackIdx];
		var other_block = blockStack[stackIdx - 1];
		blockStack.splice(stackIdx - 1, 2, block, other_block); // switch the order
		$('#' + block.getBlockID()).hide();
		$('#' + other_block.getBlockID()).hide();
	
		// recalculate the ids and redisplay
		fillBlock(stackIdx, blockStack[stackIdx - 1], true);
		fillBlock(stackIdx + 1, blockStack[stackIdx], true);
		var theBlock = '#' + blockStack[stackIdx - 1].getBlockID();
		document.getElementById(blockStack[stackIdx - 1].getBlockID()).scrollIntoView();
		$(theBlock).click();
	}
	
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
		unselectLastSelectedBlock();
	
		var stackIdx = isolateEnum(id) - 1;
	//			$('#' + id).hide();
		var block = blockStack[stackIdx];
		var other_block = blockStack[stackIdx + 1];
		blockStack.splice(stackIdx, 2, other_block, block); // switch the order
		$('#' + other_block.getBlockID()).hide();
		$('#' + block.getBlockID()).hide();
		
		// recalculate the ids and redisplay
		fillBlock(stackIdx + 1, blockStack[stackIdx], true);
		fillBlock(stackIdx + 2, blockStack[stackIdx + 1], true);
		document.getElementById(blockStack[stackIdx + 1].getBlockID()).scrollIntoView();
		$('#' + blockStack[stackIdx + 1].getBlockID()).click();
	}
}

function addFooterBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			FOOTER_OBJ,
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
	fillBlock(blockStack.length, block, display_edit_type);
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
	}
}

function addScheduleLink(suppress_click, display_edit_type)
{
	var id = '#' + idPrefix[SCHEDULE_OBJ - 1] + 'Block-' + (blockStack.length + 1);
	var block = new Block(
			EMAIL_CHANNEL,
			SCHEDULE_OBJ,
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
	fillBlock(blockStack.length, block, display_edit_type);
//	$(id).show();
	document.getElementById(block.getBlockID()).scrollIntoView();
	$(id).click();
}

function addSocialBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			SOCIAL_OBJ,
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
	fillBlock(blockStack.length, block, display_edit_type);
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
	}
}

function addGraphicBlock(suppress_click, display_edit_type)
{
	var block = new Block(
			EMAIL_CHANNEL,
			GRAPHIC_OBJ,
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
	fillBlock(blockStack.length, block, display_edit_type);
	if (!suppress_click)
	{
		document.getElementById(block.getBlockID()).scrollIntoView();
		$('#' + block.getBlockID()).click();
	}
}

// Map the edit field with the corresponding display field
function initFieldMap()
{
	// GRAPHIC BLOCK
	var hdr_arr = new Array();
	masterFields[GRAPHIC_OBJ - 1] = hdr_arr;

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

	// NONCLASS BLOCK
	var nonclass_arr = new Array();
	nonclass_arr.push(new FieldMap('nonclassHeader', 'nonclassEditHeader', true, INPUT_TEXT_FLD));
	nonclass_arr.push(new FieldMap('nonclassName', 'nonclassEditName', true, INPUT_TEXT_FLD));
	masterFields[NON_CLASS_OBJ - 1] = nonclass_arr;

	// NON WORKSHOP BLOCK
	var nonwrk_arr = new Array();
	nonwrk_arr.push(new FieldMap('nonworkshopHeader', 'nonworkshopEditHeader', true, INPUT_TEXT_FLD));
	nonwrk_arr.push(new FieldMap('nonworkshopName', 'nonworkshopEditName', true, INPUT_TEXT_FLD));
	masterFields[NON_WORKSHOP_OBJ - 1] = nonwrk_arr;

	// FOOTER BLOCK
	var foot_arr = new Array();
	foot_arr.push(new FieldMap('footerName', 'footerEditName', true, INPUT_TEXT_FLD));
	foot_arr.push(new FieldMap('footerDescription', 'footerEditDescription', true, INPUT_TEXT_FLD));
	masterFields[FOOTER_OBJ - 1] = foot_arr;
}

function initialBlockDisplay(the_starting_block)
{
	// display the panes (suppress selection)
	addGraphicBlock(true, false);
	addTextBlock(true, false);
	var count = blockStack.push(the_starting_block);
	var block = blockStack[count - 1];
	fillBlock(count, block, false);
	addSocialBlock(true, false);
	addFooterBlock(true, false);
}

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
	fillBlock(blockStack.length, block, display_edit_type);
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
	fillBlock(blockStack.length, block, true);
}
