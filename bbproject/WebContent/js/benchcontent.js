/**
 * benchcontent.js
 * 
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
 * 
 * Gary McGath
 * February 5, 2015
 *
 * This populates the content pane of the editor.
 */

var benchcontent = {
		
selectedFieldID: null,

/** Document ready initialization code. 
 */
initTheBench: function () {
	$("#filesForm").on ('submit', function (e) {
		// don't let the form haul us off the page
		e.preventDefault();
	});
	$("#files").kendoUpload({
		multiple: false,
		async: {
            saveUrl: "SaveImageServlet?brndbotimageid=2",
            removeUrl: "dummy",			// don't quite understand what this signifies
            removeField: "fileNames[]"	// not sure what to do with this
	    },
	    complete: benchcontent.onComplete,
        localization: {
            select: "Upload a JPEG, GIF, or PNG image",
            headerStatusUploaded: "Image uploaded."
        }
	});
},

/* This is called when the upload is complete. */
onComplete: function () {
	//benchcontent.galleryDataSource.read();		// Reload the list
	// Reload the image list
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
	
		// Pagination control
	    $("#yourImagesPager").kendoPager({
	        dataSource: yourImagesDataSource
	    });
	
	   	benchcontent.populateGallery ($('#imagePicker'));
		$(".useThisImage").on ('click', function (e) {
			benchcontent.useClickedImage(this);
		});
		$(".useThisImage").hide();
		$(".galleryImage").on('click', function (e) {
			benchcontent.primeClickedImage(this);
		});
		// Assemble images in a list view.  It's setup in the styles that images will rollover to the 
		//  next line automatically.
//	    $("#yourImagesView").kendoListView({
//	    	dataSource: yourImagesDataSource,
//	    	template: kendo.template($("#imageTemplate").html()),
//			selectable: true,
//			dataBound: yourImagesViewSuccess,
//			complete: yourImagesViewSuccess
//		});
    });
  
	    
},


/** Insert the fields in the content tab needed to edit a promotion. 
  */
insertEditFields: function (dest) {
	
	var srcdata = benchcontent.stylesToSourceData (bench.currentPromotion.styleSet);
	srcdata = benchcontent.sortSourceData (srcdata);
	var group = 0;
	dest.append ("<div class='contentgroup'>TEXT</div>");
	for (var i = 0; i < srcdata.length; i++) {
		var fielddata = srcdata[i];
		var stltyp = fielddata.styleType;
		if (group == 0 && (stltyp == "block" || stltyp == "svg")) {
			dest.append ("<div class='contentgroup2'>SHAPE</div>");
			group = 1;
		}
		else if (group < 2 && (stltyp == "image" || stltyp == "logo")) {
			dest.append ("<div class='contentgroup2'>IMAGE</div>");
			group = 2;
		}
		var template = kendo.template($('#contentFieldsTemplate').html());
		var html = kendo.render (template, [fielddata]);
		dest.append (html);
	}
	benchcontent.selectedFieldID = null;
	benchcontent.collapseUnselectedFields();
	benchcontent.resizeTextAreas();
	benchcontent.setTypefaceOptions();
	benchcontent.setEditFieldHandlers ();
	dest.show();
},


/* This function takes the promotion fields and extracts the
 * essential data as a JavaScript Array suitable for a Kendo data source.
 * 
 */

stylesToSourceData: function (styleSet) {
	var srcdata = [];
	for (var i = 0; i < styleSet.styles.length; i++) {
		var style = styleSet.styles[i];
		var field = style.modelField;
		if (!field)
			continue;
		var fielddata = {};
		fielddata.fieldid = i.toString();
		fielddata.fieldname = field.name;
		fielddata.x = style.getX().toString();
		fielddata.y = style.getY().toString();
		fielddata.width = style.getWidth().toString();
		fielddata.height = style.getHeight().toString();
		fielddata.styleType = style.styleType;
		
		if (style.styleType == "text") {
			// Text content ModelField
			fielddata.content = style.getText();
			fielddata.ptsize = style.getPointSize().toString();
			fielddata.italicChecked = style.isItalic() ? "checked" : "";
			fielddata.boldChecked = style.isBold() ? "checked" : "";
			fielddata.dropShadowChecked = style.hasDropShadow() ? "checked" : "";
//			fielddata.dropShadowH = style.getDropShadowH() ? style.getDropShadowH() : "0";
//			fielddata.dropShadowV = style.hasDropShadow() ? style.getDropShadowV() : "0";
//			fielddata.dropShadowBlur = style.hasDropShadow() ? style.getDropShadowBlur() : "0";
			fielddata.dropShadowDisabled = style.hasDropShadow() ? "" : "disabled";
			fielddata.color = style.getColor();
		}
		else if (style.styleType == "image") {
			fielddata.fieldid = i.toString();
			fielddata.fieldname = field.name;
		}
		else if (style.styleType == "block") {
			fielddata.color = style.getColor();
			fielddata.dropShadowChecked = style.hasDropShadow() ? "checked" : "";
//			fielddata.dropShadowH = style.hasDropShadow() ? style.getDropShadowH() : "0";
//			fielddata.dropShadowV = style.hasDropShadow() ? style.getDropShadowV() : "0";
//			fielddata.dropShadowBlur = style.hasDropShadow() ? style.getDropShadowBlur() : "0";
			fielddata.dropShadowDisabled = style.hasDropShadow() ? "" : "disabled";
		}
		else if (style.styleType == "logo") {
		}
		else if (style.styleType == "svg") {
		}
		srcdata.push(fielddata);
	}
	return srcdata;
},

/* Sort the source data by field type; first "Text" (text), then 
 * "Shape" (block, SVG), then "Image" (image, logo).
 */
sortSourceData: function (srcdata) {
	var retdata = [];
	for (var g = 0; g < 3; g++) {
		for (var i = 0; i < srcdata.length; i++) {
			var srcitem = srcdata[i];
			var match;
			switch (g) {
			case 0:
				match = (srcitem.styleType == "text");
				break;
			case 1:
				match = (srcitem.styleType == "block" || srcitem.styleType == "svg");
				break;
			case 2:
				match = (srcitem.styleType == "image" || srcitem.styleType == "logo");
				break;
			default:
				console.log ("Weird error");
			}
			if (match)
				retdata.push(srcitem);
		}
	}
	return retdata;
},

/*  Collapse all fields in the content pane to a minimum set, consisting
 *  of whatever isn't in the selected pane (if any) and doesn't have the
 *  class "collapsible".
 */
collapseUnselectedFields: function () {
	$(".contentfield").each (function () {
		if ($(this).attr("data-linkedfield") != benchcontent.selectedFieldID) {
			$(this).find(".collapsible").hide();
		} else {
			$(this).find(".collapsible").show();
		}
	});
},

/*  Shrink textarea fields which aren't collapsible down to 1 row when they aren't
 *  selected and expand to 3 rows when they are.
 */
resizeTextAreas: function () {
	$(".contentfield").each (function () {
		if ($(this).attr("data-linkedfield") != benchcontent.selectedFieldID) {
			$(this).find(".editTextArea textarea").attr("rows", "1");
		} else {
			$(this).find(".editTextArea textarea").attr("rows", "3");
		}
	});
},

/*  Set the initially selected option for each style. We can't do this in
 *  the template, since there's no HTML attribute in the select element
 *  to pick the selected field.
 */
setTypefaceOptions: function () {
	$(".selectTypeface").each (function () {
		var style = benchcontent.elemToLinkedStyle($(this));
		var typeface = style.getTypeface();
		$(this).val(typeface);
	});
},

/*  Set up click handlers for the edit fields. Change the selection and
 *  expand and collapse appropriately.
 */
setEditFieldHandlers: function () {
	$(".fieldExpander").on ('click', function (e) {
		benchcontent.selectedFieldID = $(this).attr("data-linkedfield");
		benchcontent.collapseUnselectedFields();
		benchcontent.resizeTextAreas();
	});
},

/* These functions poll the
specified textarea while it has focus and apply the changes.
Need to somehow link the textarea, which is a DOM thingy,
to the ModelField. If we set the attribute data-linkedfield
to the field name, we should have what we need.  */
updatePrototypeText: function(tarea) {

	//benchcontent.highlightTextArea(tarea);
	
    function testForChange() {
    	var style = benchcontent.elemToLinkedStyle(tarea);
    	if (style && (tarea.value != style.getFabricText())) {
    		style.setLocalText(tarea.value);
    		//style.fabricObject.scale (1.0);		// Reset scale for initial drawing  **** experimental ****
    		style.setFabricText(tarea.value);
    		style.adjustFabricText (bench.currentPromotion);
    		console.log ("bounding box width: " + style.getFabricBoundingRectWidth());
    		console.log ("text width: " + style.getFabricWidth());
    		
    		/***** EXPERIMENTAL CODE *******/
    		//benchcontent.makeTextFit(style);
    		bench.currentPromotion.render();
    	}
    }

    tarea.onblur = function() {
        window.clearInterval(timer);
        testForChange();
        //benchcontent.unhighlightTextArea (tarea);
        tarea.onblur = null;
    };

    var timer = window.setInterval(function() {
        testForChange();
    }, 50);
},

makeTextFit: function (style) {
	var drawnWidth = style.getFabricWidth();
	var styleWidth = style.getWidth ();
	if (drawnWidth > styleWidth) {
		style.setFabricScale (styleWidth / drawnWidth);
	}
},

updatePrototypePointSize: function(tarea) {

	function testForChange() {
    	if (!isNaN (tarea.value)) {
    		var style = benchcontent.elemToLinkedStyle(tarea);
    		var newsize = Number(tarea.value);
    		if (newsize != style.getPointSize ()) {
    			var pointSize = Number (tarea.value);
    			style.setFabricScale (1.0);
    			style.setLocalPointSize (pointSize);
    			style.setFabricFontSize(pointSize);
        		style.adjustFabricText (bench.currentPromotion);
    			bench.currentPromotion.render();
    		}
    	}
    }

    /*  Changing the font size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        //benchcontent.unhighlightTextArea (tarea);
        tarea.onblur = null;
    };

},

/* Callback function to complete setting the color from a
 * ColorSelector */
setColor: function (style, color ) {
	style.setLocalColor (color);
	style.setFabricFill (color);
	var fieldid = style.positionInStyleSet().toString();
	var btn = $(".colorSelectButton[data-linkedfield='" + fieldid + "']");
	btn.css("background-color", color);
},

/* This is called by an onchange event, so we already know there's a change */
updatePrototypeItalic: function (cbox) {
		
	var style = benchcontent.elemToLinkedStyle(cbox);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != style.isItalic()) {
   		style.setLocalItalic(nowChecked);
   		style.setFabricFontStyle (nowChecked ? "italic" : "normal");
		style.adjustFabricText (bench.currentPromotion);
		bench.currentPromotion.render();
   	}
},

updatePrototypeBold: function (cbox) {
	
	var style = benchcontent.elemToLinkedStyle(cbox);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != style.isBold()) {
   		style.setLocalBold(nowChecked);
   		style.setFabricFontWeight (nowChecked ? "bold" : "normal");
		style.adjustFabricText (bench.currentPromotion);
		bench.currentPromotion.render();
	}
},

//updatePrototypeDropShadow: function (cbox) {
//	var style = benchcontent.elemToLinkedStyle(cbox);
//	var nowChecked = $(cbox).prop('checked');
//	var table = $(cbox).closest('table');
//	var dsh = table.find('.dsh');
//	var dsv = table.find('.dsv');
//	var dsb = table.find('.dsb');
//	if (nowChecked) {
//		// Checking it merely enables the specific controls
//		table.find('.dsh').prop('disabled', false);
//		table.find('.dsv').prop('disabled', false);
//		table.find('.dsb').prop('disabled', false);
//		style.setLocalDropShadowEnabled (true);
//		style.updateDropShadow();
//		bench.currentPromotion.render();
//	}
//	else {
//		dsh.prop('disabled', true);
//		dsv.prop('disabled', true);
//		dsb.prop('disabled', true);
//		
//		style.setLocalDropShadowEnabled (false);
//		style.updateDropShadow();
//		bench.currentPromotion.render();
//	}
//},

//updatePrototypeDropShadowH: function (input) {
//	
//	function testForChange() {
//    	if (!isNaN (input.value)) {
//    		var h = Number (input.value);
//    		var style = benchcontent.elemToLinkedStyle (input);
//    		if (h >= 0) {
//    			style.setLocalDropShadowH (h);
//    			style.updateDropShadow();
//    			bench.currentPromotion.render();
//    		}
//    	}
//    }
//
//    input.onblur = function() {
//        testForChange();
//        input.onblur = null;
//    };
//
//},

//updatePrototypeDropShadowV: function (input) {
//	function testForChange() {
//    	if (!isNaN (input.value)) {
//    		var v = Number (input.value);
//    		var style = benchcontent.elemToLinkedStyle (input);
//    		if (v >= 0) {
//    			style.setLocalDropShadowV (v);
//    			style.updateDropShadow();
//    			bench.currentPromotion.render();
//    		}
//    	}
//    }
//
//    input.onblur = function() {
//        testForChange();
//        input.onblur = null;
//    };
//},

//updatePrototypeDropShadowBlur: function (input) {
//	function testForChange() {
//    	if (!isNaN (input.value)) {
//    		var b = Number (input.value);
//    		var style = benchcontent.elemToLinkedStyle (input);
//    		if (b >= 0) {
//    			style.setLocalDropShadowBlur (b);
//    			style.updateDropShadow();
//    			bench.currentPromotion.render();
//    		}
//    	}
//    }
//
//    input.onblur = function() {
//        testForChange();
//        input.onblur = null;
//    };
//},

updatePrototypeTypeface: function (sel) {
	var style = benchcontent.elemToLinkedStyle(sel);
   	var selected = $(sel).children().filter(":selected");
   	var typeface = selected.val();
   	console.log (typeface);
   	var currentTypeface = style.getTypeface();
   	if (typeface != currentTypeface) {
   		style.setLocalTypeface (typeface);
   		style.setFabricFontFamily (typeface);
		bench.currentPromotion.render();
   	}
},

updateXPos: function(tarea) {

    function testForChange() {
    	var style = benchcontent.elemToLinkedStyle($(tarea));
    	if (!isNaN (tarea.value)) {
    		var newpos = Number(tarea.value);
    		if (newpos != style.getX ()) {
    			style.setLocalX(Number (tarea.value));
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.setFabricLeft(Number(style.getX()));
    			bench.currentPromotion.render();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getX().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},


updateYPos: function(tarea) {

    function testForChange() {
    	var style = benchcontent.elemToLinkedStyle($(tarea));
    	if (!isNaN (tarea.value)) {
    		var newpos = Number(tarea.value);
    		if (newpos != style.getY ()) {
    			style.setLocalY(Number (tarea.value));
    			style.setFabricTop(Number(style.getY()));
    			bench.currentPromotion.render();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getY().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateWidth: function(tarea) {

    function testForChange() {
    	var style = benchcontent.elemToLinkedStyle($(tarea));
    	if (!isNaN (tarea.value)) {
    		var newwid = Number(tarea.value);
    		if (newwid != style.getWidth ()) {
    			style.setLocalWidth(Number (tarea.value));
    			style.setFabricWidth(Number(style.getWidth()));
    			bench.currentPromotion.render();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getWidth().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateHeight: function(tarea) {

    function testForChange() {
    	var style = benchcontent.elemToLinkedStyle($(tarea));
//    	var field = bench.currentPromotion.model.findFieldByName (target);
    	if (!isNaN (tarea.value)) {
    		var newht = Number(tarea.value);
    		if (newht != style.getHeight ()) {
    			style.setLocalHeight(Number (tarea.value));
    			style.setFabricHeight(Number(style.getHeight()));
    			bench.currentPromotion.render();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getHeight().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

highlightTextArea: function (tarea) {
	var style = benchcontent.elemToLinkedStyle(tarea);
	style.setFabricBackgroundColor('#C0C0C0');
	bench.currentPromotion.render();
	
},

unhighlightTextArea: function(tarea) {
	var style = benchcontent.elemToLinkedStyle(tarea);
	style.setFabricBackgroundColor('');
	bench.currentPromotion.render();
},

cropper: null,
cropWin: null,

/* Bring up a cropping modal window for the specified image */
showCrop: function (btn) {
	var style = benchcontent.elemToLinkedStyle(btn);
	var img = $("#cropWindow .cropImage");
	img.attr ("src", "ImageServlet?img=" + style.getImageID());
	if (benchcontent.cropWin) {
		benchcontent.cropWin.data("kendoWindow").open();
	}
	else {
		benchcontent.cropWin = $("#cropWindow").kendoWindow ({
			actions: ["Minimize", "Close"],
			title: "Crop Image",
			draggable: true,
			modal: true,
			close: function () {
				benchcontent.closeCrop();
			}
		});
		var win = $("#cropWindow").data("kendoWindow");
		win.center();
	}
	img.Jcrop({
		onSelect: benchcontent.applyCrop
	},
	function () {
		benchcontent.cropper = {
				jcrop: this,	// the JCROP API object
				style: style
		};	// the JCROP API 
	}
	);
},

/* Call this when the crop window is closed */
closeCrop: function () {
	if (benchcontent.cropper) {
		benchcontent.cropper.jcrop.destroy ();
		benchcontent.cropper = null;
	}
},

/* Apply the cropping from Jcrop */
applyCrop: function (coords) {
	var styl = benchcontent.cropper.style;
	// need to convert from window coordinates to source coordinates.
	var img = $("#cropWindow > img");
	var dispWid = img.width();
	var dispHt = img.height();
	var widRatio = styl.sourceWidth / dispWid;
	var htRatio = styl.sourceHeight / dispHt;
	// Should both be the same, but be picky
	var cropx = coords.x * widRatio;
	var cropy = coords.y * htRatio;
	var cropw = coords.w * widRatio;
	var croph = coords.h * htRatio;
	
	//var origSize = styl.fabricObject.getOriginalSize();
	styl.drawToMask (cropx, cropy, cropw, croph);
	
	
	bench.currentPromotion.render();
},

galleryTarget: null,


/* Image picker which takes over editor */
pickImage: function (btn) {
	var style = benchcontent.elemToLinkedStyle(btn);
	benchcontent.galleryTarget = { style: style };
	$("#promoViewHolder").hide();
	benchcontent.populateGallery ($("#imagePicker"));
	$(".useThisImage").on ('click', function (e) {
		benchcontent.useClickedImage(this);
	});
	$(".useThisImage").hide();
	$(".galleryImage").on('click', function (e) {
		benchcontent.primeClickedImage(this);
	});
	$("#cancelGalleryControl").off('click');	// avoid accumulating handlers
	$("#cancelGalleryControl").on('click', function (e) {
		$("#imagePickerHolder").hide();
		$("#promoViewHolder").show();
		$("#galleryBenchHeader").hide();
		$("#normalBenchHeader").show();
		$("#cancelGalleryPane").hide();
		$("#contentPane").show();
	});
	$("#files").off('click');
	$("#files").on ('click', function (e) {

	});
	$("#imagePickerHolder").show();
	$("#normalBenchHeader").hide();
	$("#galleryBenchHeader").show();
	$("#contentPane").hide();
	$("#cancelGalleryPane").show();
},

// Placeholder data source till the images are loaded
galleryDataSource: new kendo.data.DataSource({data: []}),

// This function is called from bench.js when the images are ready to
// populate the data source.
prepareDataSource: function () {
	benchcontent.galleryDataSource = new kendo.data.DataSource({
		data: bench.availableImages
	});
},


populateGallery: function (sel) {
	sel.kendoListView ({
		dataSource: benchcontent.galleryDataSource,
		template: kendo.template($("#galleryTemplate").html()),
	    selectable: true
	});
},

/* We use the two-click approach, once on the image to show a button
 * and then on the button to pick the image.
 */
primeClickedImage: function (img) {
	console.log("primeClickedImage");
	var id = $(img).parent().attr("data-id");
	$(".useThisImage").each (function (e) {
		if ($(this).parent().attr("data-id") == id) {
			$(this).show();
		}
		else {
			$(this).hide();
		}
	});
},

/* Use the image based on the "USE THIS IMAGE" button being clicked.
 */
useClickedImage: function (btn) {
	var id = parseInt ($(btn).parent().attr("data-id"), 10);
	var imgdata = bench.getImageDataById (id);
	var style = benchcontent.galleryTarget.style;
	style.setSourceDims (imgdata.width, imgdata.height);
	style.setLocalImageID(id);
	$("#imagePickerHolder").hide();
	$("#promoViewHolder").show();
	bench.currentPromotion.render();
	$("#galleryBenchHeader").hide();
	$("#normalBenchHeader").show();
	$("#cancelGalleryPane").hide();
	$("#contentPane").show();
},

/* For the a DOM element which has the data-linkedfield attribute,
 * return the linked style by its index.
 */
elemToLinkedStyle: function (elem) {
	var target = $(elem).attr("data-linkedfield");
   	return bench.currentPromotion.styleSet.styles[parseInt(target, 10)];
}
};

