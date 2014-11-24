// bench.js - common javascript code for all the editors in BrndBot

// used to reinitialize the upload image button
var EMPTY_UPLOAD_HTML = '<input class="greyButton" name="files" id="files" type="file" />';

var currentListID = new Array(0, 0, 0);
var lastSelectedBlock = null;

function initTheBench() 
{
	// Create enough room for all the field arrays
	initFieldMap();

	var classDataSource  = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "DashboardServlet?type=" + CLASS_OBJ,
				dataType: "json"
			}
		}
	});

	$("#" + idPrefix[CLASS_OBJ - 1] + "Here").kendoListView({
		dataSource: classDataSource,
	    selectable: true,
        template: kendo.template($("#" + idPrefix[CLASS_OBJ - 1] + "Template").html()),
	    change: selectClassListItem,
	    dataBound: onClassListSuccess
    });

	var workshopDataSource  = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "DashboardServlet?type=" + WORKSHOP_OBJ,
				dataType: "json"
			}
		}
	});

	$("#" + idPrefix[WORKSHOP_OBJ - 1] + "Here").kendoListView({
		dataSource: workshopDataSource,
	    selectable: true,
        template: kendo.template($("#" + idPrefix[WORKSHOP_OBJ - 1] + "Template").html()),
	    change: selectWorkshopListItem,
	    dataBound: onWorkshopListSuccess
    });

	var staffDataSource  = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "DashboardServlet?type=" + STAFF_OBJ,
				dataType: "json"
			}
		}
	});

	$("#" + idPrefix[STAFF_OBJ - 1] + "Here").kendoListView({
		dataSource: staffDataSource,
	    selectable: true,
        template: kendo.template($("#" + idPrefix[STAFF_OBJ - 1] + "Template").html()),
	    change: selectStaffListItem,
	    dataBound: onStaffListSuccess
    });

//    $(document).ready(function() {
	$("#tabstrip").kendoTabStrip({
        animation:  {
            open: {
                effects: "fadeIn"
            }
        }
    });

    $("#tabstrip2").kendoTabStrip({
        animation:  {
            open: {
                effects: "fadeIn"
            }
        }
//        });
    
    });

    // Gallery views
    var yourImagesDataSource = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "GetImagesServlet?" + SESSION_IMAGE_ID_KEY + "=" + IMAGE_TYPE_USER,
				dataType: "json"
			}
		},
        pageSize: 6
    });

    $("#yourImagesPager").kendoPager({
        dataSource: yourImagesDataSource
    });

    $("#yourImagesView").kendoListView({
        dataSource: yourImagesDataSource,
        template: kendo.template($("#imageTemplate").html()),
	    selectable: true,
	    change: selectYourImage,
	    dataBound: yourImagesViewSuccess,
	    complete: yourImagesViewSuccess
	});
    
    function yourImagesViewSuccess(e)
    {
    }

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
				image_type = IMAGE_TYPE_USER;
				CURRENT_GALLERY_TAB = 0;
			}
			else if (str.indexOf("teacher") != -1)
			{
				image_type = IMAGE_TYPE_TEACHER;
				CURRENT_GALLERY_TAB = 1;
			}
			else if (str.indexOf("logo") != -1)
			{
				image_type = IMAGE_TYPE_LOGO;
				CURRENT_GALLERY_TAB = 2;
			}
			session_mgr.setImageID(image_type);
			CURRENT_GALLERY_TAB = -1;
		}
	});

    function initFileUpload()
    {
	    $("#files").kendoUpload({
	        async: 
	        {
	            saveUrl: "SaveImageServlet?" + SESSION_IMAGE_ID_KEY + "=" + SESSION_IMAGE_ID,
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

    takeApartUploader();
    initFileUpload();

    function onError(e)
    {
    	alert('onError');
    }

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

    function onSuccess(e)
    {
		var dialog = $("#imageGalleryPopup").data("kendoWindow");
		if (dialog)
			dialog.close();
		if (CURRENT_GALLERY_TAB == 0)  // user images
		{
			$('#gallery').kendoTabStrip({}).data('kendoTabStrip').select(0);
			yourImagesDataSource.read();
		}
		else if (CURRENT_GALLERY_TAB == 1) // teacher
		{
			$('#gallery').kendoTabStrip({}).data('kendoTabStrip').select(1);
			yourTeachersDataSource.read();
		}
		else if (CURRENT_GALLERY_TAB == 1) // teacher
		{
			$('#gallery').kendoTabStrip({}).data('kendoTabStrip').select(2);
//			yourImagesDataSource.read();
		}
    }

    function selectYourImage(e)
    {
    	alert('hello your image');
    }

    // teachers
    var yourTeachersDataSource = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "GetImagesServlet?" + SESSION_IMAGE_ID_KEY + "=" +  IMAGE_TYPE_TEACHER,
				dataType: "json"
			}
		},
        pageSize: 6
    });

    $("#yourTeachersPager").kendoPager({
        dataSource: yourTeachersDataSource
    });

    $("#yourTeachersView").kendoListView({
        dataSource: yourTeachersDataSource,
        template: kendo.template($("#imageTemplate").html()),
	    selectable: true,
	    change: selectTeacherPhoto
//	    ,
//	    dataBound: teachersViewSuccess,
//	    complete: teachersViewSuccess
    });
    
    function teachersViewSuccess(e)
    {
    }

    function selectTeacherPhoto(e)
    {
    	alert('select teacher photo!');
    }

	function getGalleryIdx(id)
	{
		if (id == "images")
			return 0;
		else if (id == "teachers")
			return 1;
		else if (id == "logos")
			return 2;
		else if (id == "stock")
			return 3;
		return 4; //upload
	}

	$("#galleryTabs").kendoTabStrip({
    });

	$('#uploadTeacherPhoto').kendoButton({
	});

	function publishClicked(e)
	{
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
	        	editorSpecificDynamicPush(block);
				document.getElementById(blockStack[blockStack.length - 1].getBlockID()).scrollIntoView();

				$('#' + blockStack[blockStack.length - 1].getBlockID()).click();
	        	i = dataItems.length;
	        }
		}
		var dialog = $("#contentPopup").data("kendoWindow");
		dialog.close();
	}

	// Initialize controls specific to the type of bench
	benchSpecificInits();

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

	$('#newClassLink').on('click', function(e)
	{
		$('#promoteThisTxt').html('&nbsp;class');
		showPopup(e, 'classHere');
	});

	$('#newTeacherLink').on('click', function(e)
	{
		$('#promoteThisTxt').html('&nbsp;teacher');
		showPopup(e, 'staffHere');
	});

	$('#newWorkshopLink').on('click', function(e)
	{
		$('#promoteThisTxt').html('&nbsp;workshop');
		showPopup(e, 'workshopHere');
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

	function showPopup(e, listID)
	{
		// Show the correct list
		(listID == 'classHere') ? $('#classHere').show() : $('#classHere').hide(); 
		(listID == 'staffHere') ? $('#staffHere').show() : $('#staffHere').hide(); 
		(listID == 'workshopHere') ? $('#workshopHere').show() : $('#workshopHere').hide(); 

		var myWin = $("#contentPopup");
		if (!myWin.data("kendoWindow"))
		{
			var dialog = myWin.kendoWindow({
				title: false,
				visible: false,
				modal: true
			}).data("kendoWindow");
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

	// Popup to image gallery
	$('.viewImageGallery').each(function ()
	{
		$(this).kendoButton({
    	});

		$(this).on('click', function(e)
		{
			popupImageGallery(this.id);
		});
	});
	
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

	// Select onclick handler for any block with the class 'blockSelectable'
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
				form.show();
				showOtherTabs(lastSelectedBlock);
				$('#toClassID').hide();
				$('#toNonClassID').hide();
				$('#toWorkshopID').hide();
				$('#toNonWorkshopID').hide();
//				$('#applyDiv').hide();
				var type = getBlockType(lastSelectedBlock);
				if (type > 0)
				{
					if (type == CLASS_OBJ)
						$('#toClassID').show();
					if (type == NON_CLASS_OBJ)
						$('#toNonClassID').show();
					if (type == WORKSHOP_OBJ)
						$('#toWorkshopID').show();
					if (type == NON_WORKSHOP_OBJ)
						$('#toNonWorkshopID').show();
					var fields = masterFields[type - 1];
					for (var x = 0; x < fields.length; x++)
					{
						var field = fields[x];
						var html = $(field.getDisplayID(stackIdx)).html();
						field.setEditValue(html, stackIdx);
					}
				}
			}
			$('#editType').html(blockStack[stackIdx - 1].getBlockTypeDesc());
			$(lastSelectedBlock).css('border','.0625rem solid #000000');
			if (stackIdx > 0)
			{
				if (stackIdx > 1)
					$(lastSelectedBlock + 'SortUpBtn').css('display', 'inline');
				if (stackIdx < blockStack.length)
					$(lastSelectedBlock + 'SortDownBtn').css('display', 'inline');
			}
			$(lastSelectedBlock + 'TrashBtn').css('display', 'inline');
			return false;
		});
	});

	$('div[class^="trashBlock"]').each(function ()
	{
		$(this).on('click', function(e)
		{
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

	function sendToFinalEmail()
	{
		window.location='emailfull.jsp';		
	}

	function sendToFinalFacebook()
	{
		// Put the contents into the hidden form
		$('#hiddenHtml').val($('#finishedImage').html());

	    $.ajax({
	        type: 'POST',
	        url: 'SaveHTMLAsImageServlet?' + SESSION_IMAGE_ID_KEY + '=' + SESSION_FUSED_IMAGE_ID_KEY,
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

	function selectClassListItem(e)
	{
        setListState(this, CLASS_OBJ);
	}

	function selectWorkshopListItem(e)
	{
        setListState(this, WORKSHOP_OBJ);
	}

	function selectStaffListItem(e)
	{
        setListState(this, STAFF_OBJ);
	}

	function onClassListSuccess(e)
	{
		doListSuccess(CLASS_OBJ);
	}

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

	function onWorkshopListSuccess(e)
	{
		doListSuccess(WORKSHOP_OBJ);
		$('#contentType').html("Workshops");
		$('#wait').hide();
	}

	function onStaffListSuccess(e)
	{
		doListSuccess(STAFF_OBJ);
	}

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
	var id = idarg.toLowerCase();
	if (id.substr(0,1) == '#')
		id = id.substr(1,id.length);
	var type = -1;
	if (id.substr(0,5) == 'class')
		type = CLASS_OBJ;
	else if (id.substr(0,5) == 'works')
		type = WORKSHOP_OBJ;
	else if (id.substr(0,5) == 'staff')
		type = STAFF_OBJ;
	else if (id.substr(0,5) == 'clien')
		type = CLIENT_OBJ;
	else if (id.substr(0,5) == 'finde')
		type = FINDER_OBJ;
	else if (id.substr(0,4) == 'sale')
		type = SALE_OBJ;
	else if (id.substr(0,4) == 'site')
		type = SITE_OBJ;
	else if (id.substr(0,5) == 'appoi')
		type = APPOINTMENT_OBJ;
	else if (id.substr(0,5) == 'sched')
		type = SCHEDULE_OBJ;
	else if (id.substr(0,4) == 'text')
		type = TEXT_OBJ;
	else if (id.substr(0,5) == 'foote')
		type = FOOTER_OBJ;
	else if (id.substr(0,5) == 'socia')
		type = SOCIAL_OBJ;
	else if (id.substr(0,5) == 'graph')
		type = GRAPHIC_OBJ;
	else if (id.substr(0,5) == 'noncl')
		type = NON_CLASS_OBJ;
	else if (id.substr(0,5) == 'nonwo')
		type = NON_WORKSHOP_OBJ;
	else alert('Unknown block_id, error: ' + id);

	return type;
}

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
