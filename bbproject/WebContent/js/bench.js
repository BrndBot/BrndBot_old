// bench.js - common javascript code for all the editors in BrndBot

// used to reinitialize the upload image button
var EMPTY_UPLOAD_HTML = '<input class="greyButton" name="files" id="files" type="file" />';

// This array tracks the template ids, indexed by the number for that template in the stack (0 thru total #)
var currentListID = new Array(0, 0, 0);

// The template that was last selected.  Usually, it's the currently selected template, but during transitions like 
//  sort up/down, it's the last selected.
var lastSelectedBlock = null;

// The Promotion object representing the promotion to draw.
var currentPromotion = null;

// An array of StyleSet objects applicable to the promotion.
var styleSets = null;


// One-time initialization called after the page fully loads.  These are initializations common
//  to all editor.  The functions called are typically editor-specific.
function initTheBench() 
{
	// Create enough room for all the field arrays and define the map that correlates data fields 
	//  to editor HTML fields by ID.
	// initFieldMap();

// left side tab control
	$("#tabstrip").kendoTabStrip({
        animation:  {
            open: {
                effects: "fadeIn"
            }
        }
    });

// right side tab control
    $("#tabstrip2").kendoTabStrip({
        animation:  {
            open: {
                effects: "fadeIn"
            }
        }
//        });
    
    });

    // Kendo data source used to retrieve image data for the appropriate gallery
    var yourImagesDataSource = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "GetImagesServlet?brndbotimageid=2",	// param 2 is uploaded images
				dataType: "json"
			}
		},
        pageSize: 6
    });

	// Pagination control
    $("#yourImagesPager").kendoPager({
        dataSource: yourImagesDataSource
    });

	// Assemble images in a list view.  It's setup in the styles that images will rollover to the 
	//  next line automatically.
    $("#yourImagesView").kendoListView({
        dataSource: yourImagesDataSource,
        template: kendo.template($("#imageTemplate").html()),
	    selectable: true,
	    change: selectYourImage,
	    dataBound: yourImagesViewSuccess,
	    complete: yourImagesViewSuccess
	});
    
	// placeholder in case it's needed
    function yourImagesViewSuccess(e)
    {
    }

	// Combo box-like control to select image type (Kendo widget)
    $("#imageTypeList").kendoMultiSelect({
		animation: false,
		maxSelectedItems: 1,
		change: function(e)
		{
			var str = "" + this.value();
			str = str.toLowerCase();
			var image_type = -1;
			if (str.indexOf("image") != -1)
			{
				image_type = 0;		// placeholder FIXME
				CURRENT_GALLERY_TAB = 0;
			}
			else if (str.indexOf("teacher") != -1)
			{
				image_type = 0;
				CURRENT_GALLERY_TAB = 1;
			}
			else if (str.indexOf("logo") != -1)
			{
				image_type = 0;
				CURRENT_GALLERY_TAB = 2;
			}
			session_mgr.setImageID(image_type);
			CURRENT_GALLERY_TAB = -1;
		}
	});

	// Initialize the file upload widget
    function initFileUpload()
    {
	    $("#files").kendoUpload({
	        async: 
	        {
	            saveUrl: "SaveImageServlet?brndbotimageid=0",
	//            saveUrl: "http://pictaculous.com/api/1.0/",
//	            removeUrl: "RemoveLogoServlet",
	            autoUpload: true
	        },
	        localization: {
	            select: "upload",
	            headerStatusUploaded: "",
	            headerStatusUploading: ""
	        },
	        error: onError,
	        success: onSuccess,
	        multiple: false,
	        showFileList: false
	    });

/*        var upload = $("#files").data("kendoUpload");

        $.extend(upload.options.localization, {
             headerStatusUploading: "",
             headerStatusUploaded: ""
         });
*/
	}

	// These two functions try to remove a previous uploader and create one fresh.
	//  Not sure if this works properly.  Kendo has a problem reinitializing a
	//  widget after it is initially initted.  There are destroy() methods, but 
	//  I've seen problems.
    takeApartUploader();
    initFileUpload();

    function onError(e)
    {
    	alert('onError');
    }

	// Try to remove an existing file uploader
    function takeApartUploader()
    {
		var upload = $("#files").data("kendoMultiSelect");
		if (upload)
		{
			upload.destroy();
			upload = null;
			$(".k-upload-files.k-reset").find("li").parent().remove();
			$("#filesHome").html(EMPTY_UPLOAD_HTML);
		}
    }

	// AJAX call to get images was successful.  Display correctly by gallery type.
    function onSuccess(e)
    {
		var dialog = $("#imageGalleryPopup").data("kendoWindow");
		if (dialog)
			dialog.close();

    }

    function selectYourImage(e)
    {
    	alert('hello your image');
    }


	// The tab control in the image gallery popup
	$("#galleryTabs").kendoTabStrip({
    });

	// Upload button
	$('#uploadTeacherPhoto').kendoButton({
	});

	// Event handler for something in a list getting selected (class, workshop, teacher).
	function publishClicked(e)
	{
		// Use the ID of the object selected to figure out what type of object it is.
		var spl = e.event.target.id.split("Button");
		if (spl.length != 2) alert('Unexpected button id: ' + e.event.target.id);
		var myType = '';
		var myDesc = '';
		if (spl[0] == 'class')
		{
			myType = CLASS_OBJ;
			myDesc = ' this Class';
		}
		else if (spl[0] == 'workshop')
		{
			myType = WORKSHOP_OBJ;
			myDesc = ' this Workshop';
		}
		else if (spl[0] == 'staff')
		{
			myType = STAFF_OBJ;
			myDesc = ' this Teacher';
		}
		else
			alert('Unexpected type from id: ' + spl[0]);

		// Calculate the ID for the overall list itself.  Extract the database ID from element ID.
		var id = '#' + idPrefix[myType - 1] + 'Here';
		var listView = $(id).data("kendoListView");
		var database_id = parseInt(spl[1]);

		// get data items for the selected options.
		var block = null;
		var dataItems = listView.dataSource._data;
		for (var i=0; i < dataItems.length; i++)
		{
	        var dataItem = dataItems[i];
	        var id = parseInt(dataItem.ID);
	        if (id == database_id)
	        {
				// Build a Javascript Block object to store in the blockStack array
	        	block = new Block(
    				CURRENT_CHANNEL,
    				myType,
    				myDesc,
    				database_id,
    				dataItem.Name,
    				dataItem.FullName,
    				'17-sep-2014',
    				'SchedRef',
    				dataItem.FullDescription,
    				dataItem.ShortDescription,
    				dataItem.ImgURL);
				// Add to stack
	        	editorSpecificDynamicPush(block);

				// Show the template that was just added
				document.getElementById(blockStack[blockStack.length - 1].getBlockID()).scrollIntoView();

				// Click on the new template to highlight it and load its data into the editor tab
				$('#' + blockStack[blockStack.length - 1].getBlockID()).click();
	        	i = dataItems.length;
	        }
		}

		// Close the popup
		var dialog = $("#contentPopup").data("kendoWindow");
		dialog.close();
	}

	// Initialize controls specific to the type of bench
	//benchSpecificInits();

	$('#genericTextLink').on('click', function(e)
	{
		addTextBlock(false, true);
	});

	$('#genericTextBlockLink').on('click', function(e)
	{
//		addFooterBlock();
	});

	$('#genericImageBlockLink').on('click', function(e)
	{
//		addFooterBlock();
	});

	$('#genericVideoBlockLink').on('click', function(e)
	{
//		addFooterBlock();
	});

	$('#newWebBlockLink').on('click', function(e)
	{
//		addFooterBlock();
	});

	$('#closeContentPopup').on('click', function(event)
	{
		var dialog = $("#contentPopup").data("kendoWindow");
		dialog.close();
	});

	$('#closeGalleryPopup').on('click', function(event)
	{
		var dialog = $("#imageGalleryPopup").data("kendoWindow");
		dialog.close();
	});

	// Make the popup window for the 3 main content types appear
//	function showPopup(e, listID)
//	{
//		// Show the correct list, hide the others
//		(listID == 'classHere') ? $('#classHere').show() : $('#classHere').hide(); 
//		(listID == 'staffHere') ? $('#staffHere').show() : $('#staffHere').hide(); 
//		(listID == 'workshopHere') ? $('#workshopHere').show() : $('#workshopHere').hide(); 
//
//		var myWin = $("#contentPopup");
//		// If not initalized, init and show
//		if (!myWin.data("kendoWindow"))
//		{
//			var dialog = myWin.kendoWindow({
//				title: false,
//				visible: false,
//				modal: true
//			}).data("kendoWindow");
//		    dialog.center().open();
//		    $('body').blur();
//		} 
//		else 
//	    {
//	        // reopening window
//			myWin.data("kendoWindow").center().open(); // open the window
//		    $('body').blur();
//	    }
//	}

	// Button to popup the image gallery
	$('.viewImageGallery').each(function ()
	{
		$(this).kendoButton({
    	});

		$(this).on('click', function(e)
		{
			popupImageGallery(this.id);
		});
	});
	
	// Show the image gallery popup
	function popupImageGallery(id)
	{
		var myWin = $("#imageGalleryPopup");
		if (!myWin.data("kendoWindow"))
		{
			// window not yet initialized
			var dialog = myWin.kendoWindow({
		        modal: true,
		        width: "50rem",
		        height: "37.5rem",
		        pinned: false,
		        resizable: false,
		        title: false,
		        visible: false
	        }).data("kendoWindow").center();

			// Position window
		    myWin.parent().css({ "width" : "800px"});
		    myWin.parent().css({ "height" : "550px"});
		    myWin.parent().css({ "top" : "50px"});
		    dialog.center().open();
		    $('body').blur();
		} 
		else 
	    {
	        // reopening window
	        myWin.data("kendoWindow").center().open(); // open the window
		    $('body').blur();
	    }
	}

	// Select onclick handler for any block with the class 'blockSelectable'.
	// If the template has that class, if you click on it with the mouse, this
	// function gets called.
	$('div[class^="blockSelectable"]').each(function ()
	{
		$(this).on('click', function(e)
		{
			var unselected = unselectLastSelectedBlock();
			if (this.id === unselected)
			{
//				$('#applyDiv').hide();
				// Exit now, this is the same block so just unselect it
				return;
			}
			var stackIdx = isolateEnum(this.id);
			lastSelectedBlock = '#' + this.id;

			// See if this block has an edit form
			var form = prepEditID(lastSelectedBlock);
			if (form)
			{
				// Yes it does, so show it and hide unnecessary stuff
				form.show();
				showOtherTabs(lastSelectedBlock);

				// lastSelectedBlock is the block that is being clicked on
				var type = getBlockType(lastSelectedBlock);
			}

			// Put the highlight border on the template
			$('#editType').html(blockStack[stackIdx - 1].getBlockTypeDesc());
			$(lastSelectedBlock).css('border','.0625rem solid #000000');

			// Show up/down buttons where appropriate
			if (stackIdx > 0)
			{
				if (stackIdx > 1)
					$(lastSelectedBlock + 'SortUpBtn').css('display', 'inline');
				if (stackIdx < blockStack.length)
					$(lastSelectedBlock + 'SortDownBtn').css('display', 'inline');
			}

			// The trash button (X)
			$(lastSelectedBlock + 'TrashBtn').css('display', 'inline');
			return false;
		});
	});

	// Event handler for when the trash button (X) is clicked
	$('div[class^="trashBlock"]').each(function ()
	{
		$(this).on('click', function(e)
		{
			// Remove from the blockStack and slide anything following the now-deleted
			//  entry up one.
			var form = prepEditID(lastSelectedBlock);
			if (form)
			{
//				$('#applyButton').hide();
				form.hide();
				hideOtherTabs(lastSelectedBlock);
			}
			trashBlockClicked(e, this.id, true);
		});
	});

	$("#viewClasses").kendoButton({
		click: openPopup
	});

	// Apply the values currently in the editor to the currently selected object.
	// Redisplay the updated values in the template
	$("#applyButton").kendoButton({
		click: function()
		{
			var stackIdx = isolateEnum(lastSelectedBlock);
			var type = getBlockType(lastSelectedBlock);
			if (type > 0)
			{
				var fields = masterFields[type - 1];
				for (var x = 0; x < fields.length; x++)
				{
					var field = fields[x];
					var html = field.getEditValue();
					field.setDisplayValue(html, stackIdx);
				}					
			}
			else
				alert('type no apply button is wrong: ' + type);
		}
	});

	// Redirect to the page that sends the email
	function sendToFinalEmail()
	{
		window.location='emailfull.jsp';		
	}

	// Redirect to the page that posts to Facebook
	function sendToFinalFacebook()
	{
		// Put the contents into the hidden form
		$('#hiddenHtml').val($('#finishedImage').html());

	    $.ajax({
	        type: 'POST',
	        url: 'SaveHTMLAsImageServlet?brndbotimageid=0',
	        data: $('#htmlForm').serialize(), // serializes the form's elements.
	        success: function(data)
	        {
	        	window.location = 'facebookfull.jsp';
	        }
	      });
	}

	$("#checkOutButton").kendoButton({
		click: function()
		{
			if (SESSION_CHANNEL == EMAIL_CHANNEL)
			{
				// This call will forward to the final fulfillment page for email
				session_mgr.storeBlocks(blockStack, sendToFinalEmail);
			}
			else if (SESSION_CHANNEL == FACEBOOK_CHANNEL)
			{
				// This call will fuse the image and then forward to the final 
				// fulfillment page for facebook
				session_mgr.storeBlocks(blockStack, sendToFinalFacebook);
			}
		}
	});

	$("#viewTeachers").kendoButton({
		click: openPopup
	});

	$("#viewWorkshops").kendoButton({
		click: openPopup
	});

	// Track the item that is selected in the popup list
	function setListState(list, bType)
	{
        var index = list.select().index();
        var item = list.dataSource.view()[index];
        var listID = item.ID;
        var currentID = currentListID[bType - 1];
		if (currentID == listID)
		{
			// Same as last time, so skip
			return;
		}

		if (currentID != 0)
		{
			$('#' + idPrefix[bType - 1] + 'Button' + currentID).hide();
		}

		if (listID != 0)
		{
			$('#' + idPrefix[bType - 1] + 'Button' + listID).show();
		}

		currentListID[bType- 1] = listID;
	}


	// When you select an item from the popup list, and you are adding it to 
	//  the stack in the editor, this is the routine that handles that.
	function doListSuccess(cType)
	{
		var id = '#' + idPrefix[cType - 1] + 'Here'; 
//		$(id + '>div').css({'border-bottom' : '.0625rem #000000 solid' });

		var listView = $(id).data("kendoListView");

		// get data items for the selected options.
		var dataItems = listView.dataSource._data;
		for (var i=0; i < dataItems.length; i++)
		{
	        var dataItem = dataItems[i];
	        var id = dataItem.ID;
	    	$('#' + idPrefix[cType - 1] + 'Button' + id).kendoButton({
	    		click: publishClicked
	    	});
		}
	}



	// Show the popup window.  Initialize it if necessary.
	function openPopup(e)
	{
		var myWin = $("#contentPopup");
		if (!myWin.data("kendoWindow"))
		{
			// window not yet initialized
			var dialog = myWin.kendoWindow({
		        width: "50rem",
		        height: "37.5rem",
		        modal: true,
		        pinned: true,
		        resizable: false,
		        title: false,
		        visible: false
	        }).data("kendoWindow").center();
		    myWin.parent().css({ "top" : "4.6875rem"});
		    dialog.center().open();
		} 
		else 
	    {
	        // reopening window
	        myWin.data("kendoWindow").center().open(); // open the window
	    }
	}
}

// If you click on a selected block, it unselects the block.  This is the 
//  function that unselects a selected block.
function unselectLastSelectedBlock()
{
	var lastOne = 'x';
	if (lastSelectedBlock)
	{
		lastOne = lastSelectedBlock.substring(1);
		var stackIdx = isolateEnum(lastSelectedBlock);
		// Reverse things out for the last selected block

		$(lastSelectedBlock).css('border-width','0rem');
		if (lastSelectedBlock.lastIndexOf('#graphicBlock', 0) !== -1)
		{
			$(lastSelectedBlock).css('border-bottom','0.1875rem solid #3d474a');
		}
		if (stackIdx > 0)
		{
			$(lastSelectedBlock + 'SortUpBtn').css('display', 'none');
			$(lastSelectedBlock + 'SortDownBtn').css('display', 'none');
			$('#editType').html('');
		}
		$(lastSelectedBlock + 'TrashBtn').css('display', 'none');
		var baseID = prepEditID(lastSelectedBlock);
		hideBlock(baseID);
	}
	return lastOne;
}

// Function that affects the removal of an item from the stack.  Has to handle
//  the blockStack data array as well as the display templates
function trashBlockClicked(e, id, scrollIntoView)
{
	var stackIdx = isolateEnum(id) - 1;
	var blockRoot = id.substr(0, id.length - 8);
	// -1 means its not enumerated, not in the stack
	if (stackIdx == -1)
	{
		// should never happen
		alert('this should never happen, contact tech support.');
	}
	else
	{
//		$('#' + blockRoot).hide();
		$('#' + blockStack[stackIdx].blockID).hide();
		blockStack.splice(stackIdx, 1); // delete item
		// walk the rest of the blocks to 'slide' them up
		var finalID = null;
		for (var i = stackIdx; i < blockStack.length; i++)
		{
			finalID = blockStack[i].getBlockID();
			$('#' + finalID).hide();
			fillBlock(i + 1, blockStack[i], true);
		}
		if (scrollIntoView && stackIdx > 0)
			document.getElementById(blockStack[stackIdx].getBlockID()).scrollIntoView();
	}
	if (e)
		e.preventDefault();
}

function hideBlock(baseID)
{
	if (baseID) baseID.hide();
	hideOtherTabs(lastSelectedBlock);
	lastSelectedBlock = null;
}

function fillBlock(i, block, display_edit_type)
{
	block.getBlockElementIDs(i);
	block.fillMyValues(false, display_edit_type);
}

function isolateEnum(id)
{
	var idx = 0;
	var spl = id.split("-");
	if (spl.length > 1)
	{
		idx = parseInt(spl[1].substr(0,1));
	}
	return idx;
}

function getBlockType(idarg) 
{
	// Is this anything useful?
	var type = -1;
	return type;
}

// Construe the editor ID based on the field being edited, via
//  the assumed naming convention
function prepEditID(id)
{
	var baseID = id.split("-");
	var oldExists = $(baseID[0] + 'Edit');
	if (oldExists)
		return oldExists;
	return null;
}

function prepDesignID(id)
{
	var baseID = id.split("-");
	var oldExists = $(baseID[0] + 'Design');
	if (oldExists)
		return oldExists;
	return null;
}

function prepLayoutID(id)
{
	var baseID = id.split("-");
	var oldExists = $(baseID[0] + 'Layout');
	if (oldExists)
		return oldExists;
	return null;
}

function showOtherTabs(id)
{
	var layout = prepLayoutID(id);
	if (layout) layout.show();
	var design = prepDesignID(id);
	if (design) design.show();
}

function hideOtherTabs(id)
{
	var layout = prepLayoutID(id);
	if (layout) layout.hide();
	var design = prepDesignID(id);
	if (design) design.hide();
}



/* Load up the promotion for drawing. */
function loadPromotion (promoName) {
	console.log ("loadPromotion");
	var promotionDataSource = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "DashboardServlet?promo=" + promoName,
				dataType: "json"
			}
		},
		change: 
		function (e) {
			console.log ("got promotion data");
			promotionData = promotionDataSource.data()[0];
			promotionModel = new Model ();
			promotionModel.populateFromJSON (promotionData);
			var canvas = $('finishedImage1');
			// Because of the way completion routines are hooked up,
			// styleSets will now be available.
			var defaultStyleSet = styleSets[0];		// TODO find the one named "default" by preference
			currentPromotion = new Promotion (promotionModel, defaultStyleSet);
			canvas.attr("width", defaultStyleSet.width);
			canvas.attr("height", defaultStyleSet.height);

			currentPromotion.draw ('finishedImage1');
			benchcontent.insertEditFields ( $('#contentArea'));
			benchdesign.insertEditFields ( $('#designArea'));
		}
	});	
	loadStyles (promotionDataSource);
}

/* Load up the styles for the user. This is called from the completion routine
 * of loadPromotion so that reading of style is initated first, and loading
 * of the promotion happens only after the styles are loaded. */
function loadStyles (dataSource) {
	console.log ("loadStyles");
	var styleDataSource = new kendo.data.DataSource({
		transport:
		{
			read:
			{
				url: "StyleServlet",
				// TODO figure out what it will need as a parameter
				dataType: "json"
			}
		},
		change: 
		function (e) {
			console.log ("got style data");
			jsonStyleSets = styleDataSource.data();
			// styleSets is a global array of StyleSet objects
			styleSets = [];
			for (var i = 0; i < jsonStyleSets.length; i++) {
				var jsonStyleSet = jsonStyleSets[i];
				var styleSet = new StyleSet();
				styleSet.populateFromJSON(jsonStyleSets[i]);
				styleSets[i] = styleSet;
			}
			console.log (styleSets);
			// This guarantees both styles and promotion are available for drawing.
			dataSource.read ();
		}
	});
	styleDataSource.read();
}

