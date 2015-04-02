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
/** Insert the fields in the content tab needed to edit a promotion. 
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
insertEditFields: function (dest) {
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: benchcontent.stylesToSourceData(bench.currentPromotion.styleSet)}),
	    selectable: true,
        template: kendo.template($('#contentFieldsTemplate').html())
	});
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
			fielddata.color = style.getColor();
		}
		else if (style.styleType == "image") {
			fielddata.fieldid = i.toString();
			fielddata.fieldname = field.name;
		}
		else if (style.styleType == "block") {
			fielddata.color = style.getColor();
		}
		else if (style.styleType == "logo") {
		}
		else if (style.styleType == "svg") {
		}
		srcdata.push(fielddata);
	}
	return srcdata;
},



/* These functions poll the
specified textarea while it has focus and apply the changes.
Need to somehow link the textarea, which is a DOM thingy,
to the ModelField. If we set the attribute data-linkedfield
to the field name, we should have what we need.  */
updatePrototypeText: function(tarea) {

	benchcontent.highlightTextArea(tarea);
	
    function testForChange() {
    	var style = benchcontent.elemToLinkedStyle(tarea);
    	if (tarea.value != style.fabricObject.getText()) {
    		style.setLocalText(tarea.value);
    		style.fabricObject.setText(tarea.value);
    		bench.currentPromotion.canvas.renderAll();
    	}
    }

    tarea.onblur = function() {
        window.clearInterval(timer);
        testForChange();
        benchcontent.unhighlightTextArea (tarea);
        tarea.onblur = null;
    };

    var timer = window.setInterval(function() {
        testForChange();
    }, 50);
},

updatePrototypePointSize: function(tarea) {

	benchcontent.highlightTextArea(tarea);

	function testForChange() {
    	if (!isNaN (tarea.value)) {
    		var style = benchcontent.elemToLinkedStyle(tarea);
    		var newsize = Number(tarea.value);
    		if (newsize != style.getPointSize ()) {
    			var pointSize = Number (tarea.value);
    			style.setLocalPointSize (pointSize);
    			style.fabricObject.setFontSize(pointSize);
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    }

    /*  Changing the font size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        benchcontent.unhighlightTextArea (tarea);
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
	
	// Scale to the display size of the image
//	var scaleX = styl.fabricObject.width / origSize.width;
//	var scaleY = styl.fabricObject.height / origSize.height;
//	coords.x *= scaleX;
//	coords.y *= scaleY;
//	coords.w *= scaleX;
//	coords.h *= scaleY;
	
//	styl.crop(coords.x, coords.y, coords.w, coords.h);
	bench.currentPromotion.canvas.renderAll();
},

galleryTarget: null,
galleryWin: null,

/* Bring up the image gallery for a selected iamge */
/* Bring up a cropping modal window for the specified image */
pickImage: function (btn) {
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

// Placeholder data source till the images are loaded
galleryDataSource: new kendo.data.DataSource({data: []}),

// This function is called from bench.js when the images are ready to
// populate the data source.
prepareDataSource: function () {
	benchcontent.galleryDataSource = new kendo.data.DataSource({
		data: bench.availableImages
	});
//	transport:
//	{
//		read:
//		{
//			url: "GetImagesServlet" ,
//			dataType: "json"
//		}
//	}
},


populateGallery: function (sel) {
	sel.kendoListView ({
		dataSource: benchcontent.galleryDataSource,
		template: kendo.template($("#galleryTemplate").html()),
	    selectable: true,
	    change: benchcontent.useClickedImage
	});
},


useClickedImage: function (e) {
	// "this" is the kendo List
    var index = this.select().index();
    var item = this.dataSource.view()[index];
    var id = item.ID;
    var style = benchcontent.galleryTarget.style;
    style.setLocalImageID (id, item.width, item.height);
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

