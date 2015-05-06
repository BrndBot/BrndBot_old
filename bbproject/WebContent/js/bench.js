// bench.js - common javascript code for all the editors in BrndBot

var session_mgr = new Session();

var bench = {
	// used to reinitialize the upload image button
	EMPTY_UPLOAD_HTML: '<input class="greyButton" name="files" id="files" type="file" />',
	
	// This array tracks the template ids, indexed by the number for that template in the stack (0 thru total #)
	currentListID: new Array(0, 0, 0),
	
	// The template that was last selected.  Usually, it's the currently selected template, but during transitions like 
	//  sort up/down, it's the last selected.
	lastSelectedBlock: null,
	
	// The Promotion object representing the promotion to draw.
	currentPromotion: null,
	
	// The array of available images
	availableImages: null,
	
	// An array of StyleSet objects applicable to the promotion.
	styleSets: null,
	
	// object with logo information: ID, width, height (note odd capitalization)
	logoData: null,

	// Max width and height for promotion canvas
	MAX_PROMOTION_DIM: 600,

	// One-time initialization called after the page fully loads.  These are initializations common
	//  to all editor.  The functions called are typically editor-specific.
	initTheBench: function() 
	{

		
		// Tab selection buttons
		$("#contentTabButton").on('click', function (e) {
			if (! $("#contentTabButton").hasClass("activePaneButton")) {
				$("#styleTabButton").removeClass("activePaneButton");
				$("#contentTabButton").addClass("activePaneButton");
				$('#stylePane').hide();
				$('#contentPane').show();
			}
		});
		
		$("#styleTabButton").on('click', function (e) {
			if (! $("#styleTabButton").hasClass("activePaneButton")) {
				$("#contentTabButton").removeClass("activePaneButton");
				$("#styleTabButton").addClass("activePaneButton");
				$('#contentPane').hide();
				$('#stylePane').show();
			}
		});
	    
	    // Set up ColorSelectors
	    benchcontent.colorSelector = new ColorSelector ();
	    //benchdesign.colorSelector = new ColorSelector();
	
	    // Get data on the logo dimensions.
	    $.ajax({
            type: 'GET',
            dataType: 'json',
            url: "GetImagesServlet?brndbotimageid=5",	// param of 5 means default logo
            statusCode: {
                401: function() {
                  bench.redirectToLogin();
                }
            }

	    }).done (function (imgdata, textStatus, jqXHR) {
	    	bench.logoData = imgdata[0];
	    	
		    // Get the available images, and create a Deferred object for things
		    // that depend on them.
		    $.ajax({
	            type: 'GET',
	            dataType: 'json',
	            url: "GetImagesServlet?brndbotimageid=2",	// param of 2 means uploaded images
	            statusCode: {
	                401: function() {
	                  bench.redirectToLogin();
	                }
	            }
		    }).done (function (imgdata, textStatus, jqXHR) {
		    	bench.availableImages = imgdata;
		    	benchcontent.prepareDataSource();
		    	
			    // Kendo data source used to retrieve image data for the appropriate gallery
			    var yourImagesDataSource = new kendo.data.DataSource({
					data: imgdata,
			        pageSize: 6
			    });
			
			
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
			
				// load the promotion and all the styles in bench.js
				bench.loadPromotion ("${proto_name}");
	
				// ensure the top of the page is shown
				document.getElementById("brndbotMain").scrollIntoView();
			
	
		    });		// end uploaded images done function
	    });			// end logo done function

	
	
		// Button to popup the image gallery
//		$('.viewImageGallery').each(function ()
//		{
//			$(this).kendoButton({
//	    	});
//	
//			$(this).on('click', function(e)
//			{
//				popupImageGallery(this.id);
//			});
//		});
		
		// Show the image gallery popup
//		function popupImageGallery(id)
//		{
//			var myWin = $("#imageGalleryPopup");
//			if (!myWin.data("kendoWindow"))
//			{
//				// window not yet initialized
//				var dialog = myWin.kendoWindow({
//			        modal: true,
//			        width: "50rem",
//			        height: "37.5rem",
//			        pinned: false,
//			        resizable: false,
//			        title: false,
//			        visible: false
//		        }).data("kendoWindow").center();
//	
//				// Position window
//			    myWin.parent().css({ "width" : "800px"});
//			    myWin.parent().css({ "height" : "550px"});
//			    myWin.parent().css({ "top" : "50px"});
//			    dialog.center().open();
//			    $('body').blur();
//			} 
//			else 
//		    {
//		        // reopening window
//		        myWin.data("kendoWindow").center().open(); // open the window
//			    $('body').blur();
//		    }
//		}
	
		// Select onclick handler for any block with the class 'blockSelectable'.
		// If the template has that class, if you click on it with the mouse, this
		// function gets called.
		
		// This block stack stuff may be obsolete with the current design.
		$('div[class^="blockSelectable"]').each(function ()
		{
			$(this).on('click', function(e)
			{
				var unselected = bench.unselectLastSelectedBlock();
				if (this.id === unselected)
				{
	//				$('#applyDiv').hide();
					// Exit now, this is the same block so just unselect it
					return;
				}
				var stackIdx = bench.isolateEnum(this.id);
				bench.lastSelectedBlock = '#' + this.id;
	
				// See if this block has an edit form
				var form = bench.prepEditID(bench.lastSelectedBlock);
				if (form)
				{
					// Yes it does, so show it and hide unnecessary stuff
					form.show();
					bench.showOtherTabs(bench.lastSelectedBlock);
	
					// lastSelectedBlock is the block that is being clicked on
					var type = bench.getBlockType(bench.lastSelectedBlock);
				}
	
				// Put the highlight border on the template
				$('#editType').html(blockStack[stackIdx - 1].getBlockTypeDesc());
				$(bench.lastSelectedBlock).css('border','.0625rem solid #000000');
	
				// Show up/down buttons where appropriate
//				if (stackIdx > 0)
//				{
//					if (stackIdx > 1)
//						$(bench.lastSelectedBlock + 'SortUpBtn').css('display', 'inline');
//					if (stackIdx < blockStack.length)
//						$(bench.lastSelectedBlock + 'SortDownBtn').css('display', 'inline');
//				}
	
				// The trash button (X)
				//$(bench.lastSelectedBlock + 'TrashBtn').css('display', 'inline');
				return false;
			});
		});
	

	
		// Apply the values currently in the editor to the currently selected object.
		// Redisplay the updated values in the template
		$("#applyButton").kendoButton({
			click: function()
			{
				var stackIdx = bench.isolateEnum(bench.lastSelectedBlock);
				var type = bench.getBlockType(bench.lastSelectedBlock);
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
		

	},				// end initTheBench
	
	
	hideBlock: function (baseID)
	{
		if (baseID) baseID.hide();
		bench.hideOtherTabs(bench.lastSelectedBlock);
		bench.lastSelectedBlock = null;
	},
	
	fillBlock: function (i, block, display_edit_type)
	{
		block.getBlockElementIDs(i);
		block.fillMyValues(false, display_edit_type);
	},
	
	isolateEnum: function(id)
	{
		var idx = 0;
		var spl = id.split("-");
		if (spl.length > 1)
		{
			idx = parseInt(spl[1].substr(0,1));
		}
		return idx;
	},
	
	getBlockType: function(idarg) 
	{
		// Is this anything useful?
		var type = -1;
		return type;
	},
	
	// Construe the editor ID based on the field being edited, via
	//  the assumed naming convention
	prepEditID: function(id)
	{
		var baseID = id.split("-");
		var oldExists = $(baseID[0] + 'Edit');
		if (oldExists)
			return oldExists;
		return null;
	},
	
	prepDesignID: function(id)
	{
		var baseID = id.split("-");
		var oldExists = $(baseID[0] + 'Design');
		if (oldExists)
			return oldExists;
		return null;
	},
	
	prepLayoutID: function(id)
	{
		var baseID = id.split("-");
		var oldExists = $(baseID[0] + 'Layout');
		if (oldExists)
			return oldExists;
		return null;
	},
	
	showOtherTabs: function(id)
	{
		var layout = bench.prepLayoutID(id);
		if (layout) layout.show();
		var design = bench.prepDesignID(id);
		if (design) design.show();
	},
	
	hideOtherTabs: function(id)
	{
		var layout = bench.prepLayoutID(id);
		if (layout) layout.hide();
		var design = bench.prepDesignID(id);
		if (design) design.hide();
	},
	
	
	/* Load up the promotion for drawing. */
	loadPromotion: function (promoName) {
		console.log ("loadPromotion");
		var promotionDataSource = new kendo.data.DataSource({
			transport: 
			{
				read:
				{
					url: "DashboardServlet?promo=" + promoName,
					dataType: "json"
				},
				error: function (xhr) {
					if (xhr && (xhr.statusCode() == 401))
							homejs.redirectToLogin();
				}
			},
			change: 
			function (e) {
				promotionData = promotionDataSource.data()[0];
				promotionModel = new Model ();
				promotionModel.populateFromJSON (promotionData);
				var canvas = $('#finishedImage1');
				// Because of the way completion routines are hooked up,
				// styleSets will now be available.
				for (var i = 0; i < bench.styleSets.length; i++) {
					var stlset = bench.styleSets[i];
					stlset.assignAvailableImages (bench.availableImages);
				}
				var defaultStyleSet = bench.styleSets[0];		// TODO find the one named "default" by preference
				bench.currentPromotion = new Promotion (promotionModel, defaultStyleSet);
				bench.currentPromotion.assignAvailableImages (bench.availableImages);
				bench.currentPromotion.setLogoData(bench.logoData);
				canvas.attr("width", defaultStyleSet.width);
				canvas.attr("height", defaultStyleSet.height);
	
				bench.currentPromotion.draw ('finishedImage1');
				benchcontent.insertEditFields ( $('#contentArea'));
				//benchdesign.insertEditFields ( $('#designArea'));
				benchstyle.insertStyles ( $('#styleArea'));
				var canvasWidth;
				var canvasHeight;
				if (defaultStyleSet.width > bench.MAX_PROMOTION_DIM || defaultStyleSet.height > bench.MAX_PROMOTION_DIM) {
					var scaleRatio = Math.min (bench.MAX_PROMOTION_DIM / defaultStyleSet.width,
							bench.MAX_PROMOTION_DIM / defaultStyleSet.height);
					canvasWidth = Math.floor(defaultStyleSet.width * scaleRatio) + "px";
					canvasHeight = Math.floor(defaultStyleSet.height * scaleRatio) + "px";
				}
				else {
					canvasWidth = Math.floor(defaultStyleSet.width) + "px";
					canvasHeight = Math.floor(defaultStyleSet.height) + "px";
				}
				canvas.css("width", canvasWidth);
				canvas.css("height", canvasHeight);
				// Set enclosing div to same.
				$('#finishedImage').css("width", canvasWidth);
				$('#finishedImage').css("height", canvasHeight);
			},
			error: function (xhr) {
				if (xhr && (xhr.status == 401))
						bench.redirectToLogin();
			}
		});	
		bench.loadStyles (promotionDataSource);
	},
	
	/* Load up the styles for the user. This is called from the completion routine
	 * of loadPromotion so that reading of style is initated first, and loading
	 * of the promotion happens only after the styles are loaded. */
	loadStyles: function (dataSource) {
		console.log ("loadStyles");
		var styleDataSource = new kendo.data.DataSource({
			transport:
			{
				read:
				{
					url: "StyleServlet",
					// TODO figure out what it will need as a parameter
					dataType: "json"
				},
				error: function (xhr) {
					if (xhr && (xhr.status == 401))
							homejs.redirectToLogin();
				}
			},
			change: 
			function (e) {
				console.log ("got style data");
				jsonStyleSets = styleDataSource.data();
				// styleSets is a global array of StyleSet objects
				bench.styleSets = [];
				for (var i = 0; i < jsonStyleSets.length; i++) {
					var jsonStyleSet = jsonStyleSets[i];
					var styleSet = new StyleSet();
					styleSet.populateFromJSON(jsonStyleSets[i]);
					styleSet.logoData = bench.logoData;
					styleSet.assignPaletteColors(bench.buildPaletteColors());
					bench.styleSets[i] = styleSet;
				}
				console.log (bench.styleSets);
				// This guarantees both styles and promotion are available for drawing.
				dataSource.read ();
			},
			error: function (xhr) {
				if (xhr && (xhr.statusCode() == 401))
						bench.redirectToLogin();
			}
		});
		styleDataSource.read();
	},

	/* Build an array of palette colors, using the data in the divs of class
	 * hiddenPalette. */
	buildPaletteColors: function () {
		var hiddenColorDivs = $(".hiddenPalette");
		var colorArray = [];
		hiddenColorDivs.each (function () {
			var color = $(this).attr("data-color");
			// This has leading backslashes for protection in Kendo templates.
			// Need to strip them.
			if (color.substring(0,2) == "\\\\")
				color = color.substring (2);
			colorArray.push(color);
		});
		return colorArray;
	},
	
	
	/* Export the presentation to the database. */
	exportPresentation: function () {
		var imageURL = bench.currentPromotion.export ();
		$.ajax({
			  type: "POST",
			  url: 'SavePromotionServlet',
			  data: { 
			     imgBase64: imageURL
			  },
			  statusCode: {
				  401: function() {
					  bench.redirectToLogin();
				  }
			  }
		}).done(function() {
			  console.log('saved'); 
			  switch (curChannel) {
			  case FACEBOOK_CHANNEL:
				  window.location.assign ("submitfacebook.jsp");
				  break;
			  case TWITTER_CHANNEL:
				  window.location.assign ("submittwitter.jsp");
				  break;
			  case PRINT_CHANNEL:
				  window.location.assign ("submit.jsp");
				  break;
			  default:
				  window.location.assign ("submit.jsp");
			  	  break;
			  }
		}).fail(function (jqXHR, textStatus, errorThrown) {
			console.log ("exportPresentation failed");
			if (errorThrown)
				console.log (errorThrown);
		});
	},
	
	exportPresError: function (xhr, textStatus, errorThrown) {
		console.log ("Error: " + textStatus);
		console.log (errorThrown);
	},
	
	redirectToLogin: function () {
		window.location.assign("index.jsp");
	},
	
	/** Return an object holding the availableImages data for the specified ID */
	getImageDataById: function (id) {
		for (var i = 0; i < bench.availableImages.length; i++) {
			var imgData = bench.availableImages[i];
			if (imgData.ID == id)
				return imgData;
		}
		return null;		// This is an error condition
	}
	
};

$(document).ready(function() 
	{
		// doc.ready init for the bench, in bench.js
		bench.initTheBench();
		benchcontent.initTheBench();
	});