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
			fielddata.dropShadowH = style.getDropShadowH() ? style.getDropShadowH() : "0";
			fielddata.dropShadowV = style.hasDropShadow() ? style.getDropShadowV() : "0";
			fielddata.dropShadowBlur = style.hasDropShadow() ? style.getDropShadowBlur() : "0";
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
			fielddata.dropShadowH = style.hasDropShadow() ? style.getDropShadowH() : "0";
			fielddata.dropShadowV = style.hasDropShadow() ? style.getDropShadowV() : "0";
			fielddata.dropShadowBlur = style.hasDropShadow() ? style.getDropShadowBlur() : "0";
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

/*  Set up click handlers for the edit fields. Change the selection and
 *  expand and collapse appropriately.
 */
setEditFieldHandlers: function () {
	var fieldNames = $(".fieldname");
	fieldNames.on ('click', function (e) {
		benchcontent.selectedFieldID = $(this).attr("data-linkedfield");
		benchcontent.collapseUnselectedFields();
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
    	if (tarea.value != style.fabricObject.getText()) {
    		style.setLocalText(tarea.value);
    		style.fabricObject.scale (1.0);		// Reset scale for initial drawing  **** experimental ****
    		style.fabricObject.setText(tarea.value);
    		console.log ("bounding box width: " + style.fabricObject.getBoundingRectWidth());
    		console.log ("text width: " + style.fabricObject.getWidth());
    		
    		/***** EXPERIMENTAL CODE *******/
    		benchcontent.makeTextFit(style);
    		bench.currentPromotion.canvas.renderAll();
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
	var drawnWidth = style.fabricObject.getWidth();
	var styleWidth = style.getWidth ();
	if (drawnWidth > styleWidth) {
		style.fabricObject.scale (styleWidth / drawnWidth);
	}
},

updatePrototypePointSize: function(tarea) {

	//benchcontent.highlightTextArea(tarea);

	function testForChange() {
    	if (!isNaN (tarea.value)) {
    		var style = benchcontent.elemToLinkedStyle(tarea);
    		var newsize = Number(tarea.value);
    		if (newsize != style.getPointSize ()) {
    			var pointSize = Number (tarea.value);
    			style.fabricObject.scale (1.0);
    			style.setLocalPointSize (pointSize);
    			style.fabricObject.setFontSize(pointSize);
    			makeTextFit(style);
    			bench.currentPromotion.canvas.renderAll();
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
	style.fabricObject.fill = color;
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
   		style.fabricObject.setFontStyle (nowChecked ? "italic" : "normal");
		bench.currentPromotion.canvas.renderAll();
   	}
},

updatePrototypeBold: function (cbox) {
	
	var style = benchcontent.elemToLinkedStyle(cbox);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != style.isBold()) {
   		style.setLocalBold(nowChecked);
   		style.fabricObject.setFontWeight (nowChecked ? "bold" : "normal");
		bench.currentPromotion.canvas.renderAll();
	}
},

updatePrototypeDropShadow: function (cbox) {
	var style = benchcontent.elemToLinkedStyle(cbox);
	var nowChecked = $(cbox).prop('checked');
	var table = $(cbox).closest('table');
	var dsh = table.find('.dsh');
	var dsv = table.find('.dsv');
	var dsb = table.find('.dsb');
	if (nowChecked) {
		// Checking it merely enables the specific controls
		table.find('.dsh').prop('disabled', false);
		table.find('.dsv').prop('disabled', false);
		table.find('.dsb').prop('disabled', false);
		style.setLocalDropShadowEnabled (true);
		style.setLocalDropShadowH(dsh.val());
		style.setLocalDropShadowV(dsv.val());
		style.setLocalDropShadowBlur(dsb.val());
		style.updateDropShadow();
		bench.currentPromotion.canvas.renderAll();
	}
	else {
		dsh.prop('disabled', true);
		dsv.prop('disabled', true);
		dsb.prop('disabled', true);
		
		style.setLocalDropShadowEnabled (false);
		style.updateDropShadow();
		bench.currentPromotion.canvas.renderAll();
	}
},

updatePrototypeDropShadowH: function (input) {
	
	function testForChange() {
    	if (!isNaN (input.value)) {
    		var h = Number (input.value);
    		var style = benchcontent.elemToLinkedStyle (input);
    		if (h >= 0) {
    			style.setLocalDropShadowH (h);
    			style.updateDropShadow();
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    }

    input.onblur = function() {
        testForChange();
        input.onblur = null;
    };

},

updatePrototypeDropShadowV: function (input) {
	function testForChange() {
    	if (!isNaN (input.value)) {
    		var v = Number (input.value);
    		var style = benchcontent.elemToLinkedStyle (input);
    		if (v >= 0) {
    			style.setLocalDropShadowV (v);
    			style.updateDropShadow();
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    }

    input.onblur = function() {
        testForChange();
        input.onblur = null;
    };
},

updatePrototypeDropShadowBlur: function (input) {
	function testForChange() {
    	if (!isNaN (input.value)) {
    		var b = Number (input.value);
    		var style = benchcontent.elemToLinkedStyle (input);
    		if (b >= 0) {
    			style.setLocalDropShadowBlur (b);
    			style.updateDropShadow();
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    }

    input.onblur = function() {
        testForChange();
        input.onblur = null;
    };
},

updatePrototypeTypeface: function (sel) {
	var style = benchcontent.elemToLinkedStyle(sel);
   	var selected = $(sel).children().filter(":selected");
   	var typeface = selected.val();
   	console.log (typeface);
   	var currentTypeface = style.getTypeface();
   	if (typeface != currentTypeface) {
   		style.setLocalTypeface (typeface);
   		style.fabricObject.setFontFamily (typeface);
		bench.currentPromotion.canvas.renderAll();
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
    			style.fabricObject.setLeft(Number(style.getX()));
    			bench.currentPromotion.canvas.renderAll();
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
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setTop(Number(style.getY()));
    			bench.currentPromotion.canvas.renderAll();
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
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setWidth(Number(style.getWidth()));
    			bench.currentPromotion.canvas.renderAll();
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
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setHeight(Number(style.getHeight()));
    			bench.currentPromotion.canvas.renderAll();
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
	style.fabricObject.setBackgroundColor('#C0C0C0');
	bench.currentPromotion.canvas.renderAll();
	
},

unhighlightTextArea: function(tarea) {
	var style = benchcontent.elemToLinkedStyle(tarea);
	style.fabricObject.setBackgroundColor('');
	bench.currentPromotion.canvas.renderAll();
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
	
	
	bench.currentPromotion.canvas.renderAll();
},

galleryTarget: null,
//galleryWin: null,

/* OLD CODE */
pickImageOld: function (btn) {
	session_mgr.checkSession();	// redirect to login if expired
	var style = benchcontent.elemToLinkedStyle(btn);
	benchcontent.galleryTarget = { style: style };
	if (benchcontent.galleryWin) {
		benchcontent.galleryWin.data("kendoWindow").open();
	}
	else {
		benchcontent.galleryWin = $("#galleryWindow").kendoWindow ({
			actions: ["Custom", "Minimize", "Maximize", "Close"],
			title: "Select Image",
			draggable: true,
			modal: true,
			height: "450px",
			width: "700px",
			position: {
				top: 50,
				left: 50
			},
			close: function () {
			}
		});

		benchcontent.populateGallery($('#imageGallery'));
		var win = $("#galleryWindow").data("kendoWindow");
		win.center();
	}
},

/* Replace old pick image code with pane which conceals editor */
pickImage: function (btn) {
	var style = benchcontent.elemToLinkedStyle(btn);
	benchcontent.galleryTarget = { style: style };
	$("#promoViewHolder").hide();
	benchcontent.populateGallery ($("#imagePickerHolder"));
	$(".useThisImage").on ('click', function (e) {
		benchcontent.useClickedImage(this);
	});
	$(".useThisImage").hide();
	$(".galleryImage").on('click', function (e) {
		benchcontent.primeClickedImage(this);
	});
	$("#imagePickerHolder").show();
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
	var id = $(btn).parent().attr("data-id");
	var style = benchcontent.galleryTarget.style;
	style.setLocalImageID(id);
	$("#imagePickerHolder").hide();
	$("#promoViewHolder").show();
	bench.currentPromotion.canvas.renderAll();
},

/* For the a DOM element which has the data-linkedfield attribute,
 * return the linked style by its index.
 */
elemToLinkedStyle: function (elem) {
	var target = $(elem).attr("data-linkedfield");
   	return bench.currentPromotion.styleSet.styles[parseInt(target, 10)];
}
};

