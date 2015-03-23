/**
 * BBStyle.js
 * 
 *  *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
 * 
 * It contains the Style and StyleSet classes.
 * 
 *  * Required packages:
 * 	fabric.js
 *  Some version of JQuery
 *  BBModel.js
 * 
 */

/* The prototype constructor for a StyleSet. A StyleSet contains an ordered
 * array of Styles, each of which is associated with a ModelField. */
function StyleSet () {
	this.name = null;
	// styles is an array of Styles
	this.styles = [];
	// dimensions of the promotion to be drawn
	this.width = 0;
	this.height = 0;
	this.availableImages = [];
	this.paletteColors = [];
	this.logoData = null;

	// Convert JSON data into data for this Model
	this.populateFromJSON = function (jsonObj) {
		this.name = jsonObj.name;
		this.width = jsonObj.width;
		this.height = jsonObj.height;
		var jsonStyles = jsonObj.styles;
		for (var i = 0; i < jsonStyles.length; i++) {
			var jsonStyle = jsonStyles[i];
			var styl = new Style (jsonStyle.styleType, this);
			this.styles[i] = styl;
			styl.index = i;
			styl.populateFromJSON (jsonStyle);
		}
	};
	
	/* Make a copy that will be suitable for a separate display. This
	 * means all styles are copied, but local styles are SHARED, so
	 * that a change in one will affect the other.
	 */
	this.copyForDisplay = function() {
		var retval = new StyleSet();
		retval.name = this.name;
		retval.width = this.width;
		retval.height = this.height;
		retval.styles = [];
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			var copyStyle = style.copy(); 
			retval.styles.push (style.copy());
		}
		
		return retval;
	};

	
	/* Attach the styles of the styleset to fields of the model.
	 */
	this.attachToModel = function (model) {
		var len = this.styles.length;
		for (var i = 0; i < len; i++) {
			var style = this.styles[i];
			style.attachToModel (model);
		}
	};
	
	/** Assign an array of four palette color strings to the StyleSet.
	 *  Copies them into the StyleSet's own array rather than sharing
	 *  the array. */
	this.assignPaletteColors = function (colors) {
		this.paletteColors = [];		// reset
		for (var i = 0; i < colors.length; i++) {
			this.paletteColors.push(colors[i]);
		}
	}
	
	/** Assign the array of available images to the StyleSet.
	 *  This has to be done to each StyleSet that's activated.
	 *  Repeating it is harmless.
	 */
	this.assignAvailableImages = function (imgs) {
		this.availableImages = imgs;
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			style.availableImages = imgs;
		}
	};
}

/* The prototype constructor for one field in a Style.
 * A StyleSet's Style will have a local Style associated with it,
 * where all changes are made. It will also have a fabric.js object
 * associated with it for drawing. These can be replaced when the
 * Style is reused. */
function Style (styleType, styleSet) {
	this.index = 0;
	// The ModelField associated with the style
	this.modelField = null;
	// The name of the ModelField.
	this.fieldName = null;
	this.styleType = styleType;
	this.styleSet = styleSet;
	this.width = null;		// width of the frame
	this.height = null;		// height of the frame
	this.sourceWidth = null;	// width of the source image
	this.sourceHeight = null;	// height of the source image
	this.anchor = null;
	this.offsetX = null;
	this.offsetY = null;
	this.maskRect = null;
	this.text = null;
	this.defaultText = null;
	this.typeface = null;
	this.pointSize = null;
	this.color = null;
	this.paletteSelection = null;
	this.bold = null;
	this.italic = null;
	this.opacity = null;
	this.multiply = null;
	this.alignment = null;
	this.svg = null;
	this.dropShadowH = null;
	this.dropShadowV = null;
	this.dropShadowBlur = null;
	this.imageID = null;
	this.hCenter = null;
	this.fabricObject = null;
	this.availableImages = [];
	
	/* Make a copy of the Style. 
	 */
	this.copy = function () {
		var retval = new Style(this.styleType, this.styleSet);
		retval.index = this.index;
		retval.fieldName = this.fieldName;
		retval.width = this.width;
		retval.height = this.height;
		retval.sourceWidth = this.sourceWidth;
		retval.sourceHeight = this.sourceHeight;
		retval.anchor = this.anchor = "TL";
		retval.offsetX = this.offsetX;
		retval.offsetY = this.offsetY;
		retval.maskRect = this.maskRect;
		retval.text = this.text;
		retval.defaultText = this.defaultText;
		retval.typeface = this.typeface;
		retval.pointSize = this.pointSize;
		retval.color = this.color;
		retval.paletteSelection = this.paletteSelection;
		retval.bold = this.bold;
		retval.italic = this.italic;
		retval.opacity = this.opacity;
		retval.multiply = this.multiply;
		retval.alignment = this.alignment;
		retval.svg = this.svg;
		retval.dropShadowH = this.dropShadowH;
		retval.dropShadowV = this.dropShadowV;
		retval.dropShadowBlur = this.dropShadowBlur;
		retval.imageID = this.imageID;
		retval.hCenter = this.hCenter;
		retval.availableImages = this.availableImages;
		return retval;
	};
	
	/* Populating from a JSON object is particularly easy in
	 * this case. */
	this.populateFromJSON = function (jsonObj) {
		this.fieldName = jsonObj.fieldName;
		this.styleType = jsonObj.styleType;
		this.italic = (jsonObj.italic !== null ? jsonObj.italic : false);
		this.bold = (jsonObj.bold !== null ? jsonObj.bold : false);
		this.width = jsonObj.width;
		this.height = jsonObj.height;
		this.anchor = jsonObj.anchor;
		this.offsetX = jsonObj.offsetX;
		this.offsetY = jsonObj.offsetY;
		this.color = jsonObj.color;
		this.paletteSelection = jsonObj.paletteSelection;
		this.opacity = jsonObj.opacity;
		this.multiply = jsonObj.multiply;
		this.svg = jsonObj.svg;
		this.dropShadowH = jsonObj.dropShadowH;
		this.dropShadowV = jsonObj.dropShadowV;
		this.dropShadowBlur = jsonObj.dropShadowBlur;
		this.hCenter = jsonObj.hCenter;

		// Text-specific fields.
		if (this.styleType == "text") {
			this.typeface = jsonObj.typeface;
			this.pointSize = jsonObj.pointSize;
			this.text = jsonObj.text;
			this.defaultText = jsonObj.defaultText;
		}
		this.alignment = jsonObj.alignment;
	};
	
	/* Attach the style to the matching field in the model.
	 * When we do this, we also make a localStyle that can
	 * be modified for the presentation of this ModelField.
	*/ 
	this.attachToModel  = function (model) {
		this.model = model;
		var len = model.fields.length;
		for (var i = 0; i < len; i++) {
			var field = model.fields[i];
			if (field.name == this.fieldName) {
				this.modelField = field;
				break;
			}
		}
		// If the model field already has a local style, leave it;
		// otherwise give it a starter local style.
		if (this.modelField && !this.modelField.localStyle)
			this.modelField.localStyle = new Style(this.styleType, this.styleSet);
	};


	/* Draw a Style's ModelField under the control of this style. */
	this.draw = function (location, canvas ) {
		this.canvas = canvas;
		if (!this.modelField)
			return;		// Can't draw a style that has no model
		switch (this.styleType) {
		case "text":
			this.fabricateText (canvas);
			break;
		case "image":
			var imgData = this.findImage();
			if (imgData) {
				var dims = this.fitImage (imgData.width, imgData.height);
				this.fabricateImage (canvas, dims);
			}
			else {
				this.fabricateImage (canvas);
			}
			break;
		case "logo":
			if (this.styleSet.logoData) {
				var logoDims = this.fitImage (this.styleSet.logoData.width, this.styleSet.logoData.height);
				this.fabricateLogo (canvas, logoDims.width, logoDims.height);
			}
			else {
				this.fabricateLogo (canvas);
			}
			break;
		case "block":
			this.fabricateBlock (canvas);
			break;
		case "svg":
			this.fabricateSVG (canvas);
			break;
		default:
			console.log ("Unknown field type " + this.styleType);
			break;
		}

	};
	
	this.fabricateText = function (canvas) {
		var pos = this.getPosition();
		var weight = this.isBold() ? "bold" : "normal";
		var fstyle = this.isItalic() ? "italic" : "normal";
		
		// The X position depends on the alignment.
		// For right-aligned text, it's the right edge. For centered text,
		// it's the center.
		var xpos = pos.x;
		var alignment = this.getAlignment();
		if (alignment == "right")
			xpos = pos.x + this.getWidth();
		else if (alignment == "center")
			xpos = pos.x + this.getWidth() / 2;
		
		// TODO replace this with Textbox when it becomes available in fabric.js
		var text = new fabric.Text(this.getText(), {
			hasControls: false,
			selectable: false,
			fontSize: this.getPointSize(),
			fontFamily: this.getTypeface(),
			fill: this.getColor(),
			left: xpos,
			top: pos.y,
			originX: "left",
			originY: "top",
			width: this.getWidth(),
			height: this.getHeight(),
			fontWeight: weight,
			fontStyle: fstyle,
			textAlign: alignment
		});
		
		var dropShadowH = this.getDropShadowH();
		var dropShadowV = this.getDropShadowV();
		if (dropShadowH  || dropShadowV ) {
			text.shadow = new fabric.Shadow ();
			text.shadow.blur = this.getDropShadowBlur();
			text.shadow.offsetX = dropShadowH;
			text.shadow.offsetY = dropShadowV;
		}
		this.fabricObject = text;
		canvas.add(text);
		canvas.moveTo(text, this.index);
	};

	this.fabricateBlock = function  (canvas) {
		console.log ("fabricateBlock");
		var pos = this.getPosition();
		var wid = this.getWidth();
		var ht = this.getHeight();
		var color = this.getColor ();
		var opacity = this.getOpacity () * 0.01;	// Convert 0-100 to 0-1
		var gco = "source-over";
		if (this.getMultiply())
			gco = "multiply";
		var rect = new fabric.Rect ({
			hasControls: false,
			selectable: false,
			width: wid,
			height: ht,
			left: pos.x,
			top: pos.y,
			originX: "left",
			originY: "top",
			fill: color,
			globalCompositeOperation: gco,
			opacity: opacity
		});
		
		// If there's a drop shadow, it's implemented as an offset rectangle.
		// We need to compbine the two rectangles into a Group object for
		// management purposes.
		var dropShadowH = this.getDropShadowH();
		var dropShadowV = this.getDropShadowV();
		if (dropShadowH || dropShadowV) {
			// position rect at top left corner of group
			rect.left = 0;
			rect.top = 0;
			var shadowRect = new fabric.Rect ({
				hasControls: false,
				selectable: false,
				width: wid,
				height: ht,
				left: dropShadowH,
				top: dropShadowV,
				originX: "left",
				originY: "top",
				fill: "#000000",
				globalCompositeOperation: gco,
				opacity: opacity
			});
			var group = new fabric.Group ([shadowRect, rect],
				{
				hasControls: false,
				selectable: false,
				left: pos.x,
				top: pos.y,
				originX: "left",
				originY: "top"
				});
			canvas.add(group);
			canvas.moveTo(group, this.index);
		}
		else {
			// No drop shadow, just use the Rect object
			this.fabricObject = rect;
			canvas.add(rect);
			canvas.moveTo(rect, this.index);

		}
	};
	

	/** Draw the image. fitImage should already have been called to
	 *  set the values of dispWidth and dispHeight.
	 *  
	 *   dispWidth: the width at which the image should be drawn
	 *   dispHeight: the height at which the image should be drawn
	 *   
	 *   These dimensions should be at least as large as the style dimensions
	 *   and should preserve the original image's aspect ratio.
	 */
	this.fabricateImage = function  (canvas, dispDims) {
		console.log ("fabricateImage");
		var pos = this.getPosition();
		// Amount to displace the origin, if the display dimensions differ
		// from the style dimensions
		var xDisplace = 0;
		var yDisplace = 0;
		var width;
		var height;
		var id = this.getImageID();
		var imgdata = this.findImage (id);
		if (dispDims && dispDims.width) {
			width = dispDims.width;
			xDisplace = dispDims.x + (this.getWidth() - dispDims.width) / 2.0;
		}
		else if (imgdata)
			width = imgdata.width;
		else
			width = this.getWidth();
		
		if (dispDims && dispDims.height) {
			height = dispDims.height;
			yDisplace = dispDims.y + (this.getHeight() - dispDims.height) / 2.0;
		}
		else if (imgdata)
			height = imgdata.height;
		else
			height = this.getHeight();
		
		var opacity = this.getOpacity () * 0.01;	// Convert 0-100 to 0-1
		var gco = "source-over";
		if (this.getMultiply())
			gco = "multiply";
		
		var style = this;
		var img = fabric.Image.fromURL("ImageServlet?img=" + id, function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = pos.x + xDisplace;
			img.top = pos.y  + yDisplace;
			img.originX = "left";
			img.originY = "top";
			img.width = width;
			img.height = height;
			img.opacity = opacity;
			img.globalCompositeOperation = gco;

			img.clipTo = function (ctx) {
				ctx.rect (-style.getWidth() / 2, -style.getHeight() / 2, style.getWidth(), style.getHeight());
			};
			style.fabricObject = img;
			canvas.add(img);
			canvas.moveTo(img, style.index);
		});
	};
	
	this.fabricateLogo = function  (canvas, dispWidth, dispHeight) {
		console.log ("fabricateLogo");
		var pos = this.getPosition();
		// Amount to displace the origin, if the display dimensions differ
		// from the style dimensions
		var xDisplace = 0;
		var yDisplace = 0;
		var width;
		var height;
		if (dispWidth) {
			width = dispWidth;
			xDisplace = (this.getWidth() - dispWidth) / 2.0;
		}
		else
			width = this.getWidth();
		
		if (dispHeight) {
			height = dispHeight;
			yDisplace = (this.getHeight() - dispHeight) / 2.0;
		}
		else
			height = this.getHeight();

		var style = this;
		var img = fabric.Image.fromURL("ImageServlet?img=logo", function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = pos.x + xDisplace;
			img.top = pos.y  + yDisplace;
			img.originX = "left";
			img.oritinY = "top";
			img.width = width;
			img.height = height;
			img.clipTo = function (ctx) {
				ctx.rect (-style.getWidth() / 2, -style.getHeight() / 2, style.getWidth(), style.getHeight());
			};
			style.fabricObject = img;
			canvas.add(img);
			canvas.moveTo(img, style.index);
		});
	};
	
	/* See http://fabricjs.com/fabric-intro-part-3/ */
	this.fabricateSVG = function  (canvas) {
		console.log ("fabricateSVG");
		var pos = this.getPosition();
		var wid = this.getWidth();
		var ht = this.getHeight();
		var svg = this.getSVG();
		var style = this;
		// Use loadSVGFromString(string, callback, reviver)
		fabric.loadSVGFromString(svg, function(objects, options) {
			var obj = fabric.util.groupSVGElements(objects, options);
			obj.hasControls = false;
			obj.selectable = false;
			obj.width = wid;
			obj.height = ht;
			obj.left = pos.x;
			obj.top = pos.y;
			obj.originX = "left";
			obj.originY = "top";
			canvas.add(obj);
			canvas.moveTo(obj, style.index);
			style.fabricObject = obj;
		});
	};
	

	
	/* For text fields only. We apply defaultText
	 * if no other text has been set.
	 */
	this.getText = function () {
		var content = "";
		if (this.modelField.localStyle.text)
			content = this.modelField.localStyle.text;
		else if (this.text)
			content = this.text;
		else if (this.defaultText)
			content = this.defaultText;
		return content;
	};
	
	this.setLocalText = function (txt) {
		this.modelField.localStyle.text = txt;
	};
	
	this.isBold = function () {
		return (this.modelField.localStyle.bold !== null) ? this.modelField.localStyle.bold : this.bold;
	};
	
	this.setLocalBold = function (b) {
		this.modelField.localStyle.bold = b;
	};
	
	this.isItalic = function () {
		return (this.modelField.localStyle.italic !== null) ? this.modelField.localStyle.italic : this.italic;
	};
	
	this.setLocalItalic = function (b) {
		this.modelField.localStyle.italic = b;
	};
	
	this.getPointSize = function () {
		return this.modelField.localStyle.pointSize ? this.modelField.localStyle.pointSize : this.pointSize;
	};
	
	this.setLocalPointSize  = function (n) {
		this.modelField.localStyle.pointSize = n;
	};
	
	this.getTypeface = function () {
		return this.modelField.localStyle.typeface ? this.modelField.localStyle.typeface : this.typeface;
	};
	
	this.setLocalTypeface = function (t) {
		this.modelField.localStyle.typeface = t;
	};
	
	this.getX = function () {
		if (this.modelField.localStyle.offsetX !== null) {
			return this.modelField.localStyle.offsetX;
		}
		else if (this.offsetX) {
			return this.offsetX;
		}
		else 
			return 0;		// Always return something, for Kendo's benefit
	};
	
	this.setLocalX = function (x) {
		this.modelField.localStyle.offsetX = x;
	};

	this.getY = function () {
		if (this.modelField.localStyle.offsetY !== null) {
			return this.modelField.localStyle.offsetY;
		}
		else if (this.offsetY) {
			return this.offsetY;
		}
		else 
			return 0;
	};
	
	this.setLocalY = function (y) {
		this.modelField.localStyle.offsetY = y;
	};
	
	this.getWidth = function () {
		if (this.modelField.localStyle.width !== null) {
			return this.modelField.localStyle.width;
		}
		else if (this.width) {
			return this.width;
		}
		else 
			return 0;
	};
	
	this.setLocalWidth = function (w) {
		this.modelField.localStyle.width = w;
	};
	
	this.getHeight = function () {
		if (this.modelField.localStyle.height !== null) {
			return this.modelField.localStyle.height;
		}
		else if (this.height) {
			return this.height;
		}
		else 
			return 0;
	};
	
	this.setLocalHeight = function (h) {
		this.modelField.localStyle.height = h;
	};
	
	this.getMaskRect = function () {
		if (this.modelField.localStyle.maskRect !== null) {
			return this.modelField.localStyle.maskRect;
		}
		else if (this.maskRect) {
			// I don't think we ever set the maskRect of the primary style,
			// but follow the pattern anyway.
			return this.maskRect;
		}
		else
			return null;
	};
	
	/* Set the masking rectangle. This doesn't do anything until the next time
	 * the image is drawn.
	 */
	this.setLocalMask = function (x, y, wid, ht) {
		this.modelField.localStyle.maskRect = { x: x,
				y: y,
				width: wid,
				height: ht};
	};

	/* getColor returns black as a last resort, so it always returns
	 * a color string. */
	this.getColor = function () {
		var paletteArray = this.styleSet.paletteColors;
		if (this.modelField.localStyle.paletteSelection) {
			return paletteArray[this.modelField.localStyle.paletteSelection - 1];
		}
		else if (this.paletteSelection) {
			return paletteArray[this.paletteSelection - 1];
		}
		else if (this.modelField.localStyle.color) {
			return this.modelField.localStyle.color;
		}
		else if (this.color) {
			return this.color;
		}
		else return "#000000";
	};
	
	this.setLocalColor = function (c) {
		this.modelField.localStyle.color = c;
	};
	
	this.getMultiply = function () {
		if (this.modelField.localStyle.multiply !== null)
			return this.modelField.localStyle.multiply;
		else if (this.multiply !== null)
			return this.multiply;
		else return false;
	};
	
	this.getAlignment = function () {
		return (this.modelField.localStyle.alignment !== null) ? this.modelField.localStyle.alignment : this.alignment;
	};
	
	this.setLocalAlignment = function (al) {
		this.modelField.localStyle.alignment = al;
	};
	
	this.isBold = function () {
		return (this.modelField.localStyle.bold !== null) ? this.modelField.localStyle.bold : this.bold;
	};
	
	this.setLocalBold = function (b) {
		this.modelField.localStyle.bold = b;
	};

	this.isItalic = function () {
		return (this.modelField.localStyle.italic !== null) ? this.modelField.localStyle.italic : this.italic;
	};
	
	this.setLocalItalic = function (it) {
		this.modelField.localStyle.italic = it;
	};
	
	this.getSVG = function () {
		return (this.modelField.localStyle.svg !== null) ? this.modelField.localStyle.svg : this.svg;
	};
	
	this.setLocalSVG = function (svg) {
		this.modelField.localStyle.svg = svg;
	};
	
	/* Opacity is 0-100. Will need to be normalized to 0-1 for Fabric. */
	this.getOpacity = function () {
		if (this.modelField.localStyle.opacity !== null)
			return this.modelField.localStyle.opacity;
		else if (this.opacity !== null)
			return this.opacity;
		else return 100;
	};
	
	this.setLocalOpacity = function (op) {
		this.modelField.localStyle.opacity = op;
	};
	
	this.getDropShadowH = function () {
		return (this.modelField.localStyle.dropShadowH !== null) ? this.modelField.localStyle.dropShadowH : this.dropShadowH;
	};
	
	this.setLocalDropShadowH = function (h) {
		this.modelField.localStyle.dropShadowH = h;
	};

	this.getDropShadowV = function () {
		return (this.modelField.localStyle.dropShadowV !== null) ? this.modelField.localStyle.dropShadowV : this.dropShadowV;
	};

	this.setLocalDropShadowV = function (v) {
		this.modelField.localStyle.dropShadowV = v;
	};

	this.getDropShadowBlur = function () {
		return (this.modelField.localStyle.dropShadowBlur !== null) ? this.modelField.localStyle.dropShadowBlur : this.dropShadowBlur;
	};

	this.setLocalDropShadowBlur = function (b) {
		this.modelField.localStyle.dropShadowBlur = b;
	};
	
	this.getHCenter = function () {
		if (this.modelField.localStyle.hCenter !== null)
			return this.modelField.localStyle.hCenter;
		else if (this.hCenter !== null)
			return this.hCenter;
		else
			return false;
	}

	/* Function for the x, y, and anchor calculations. Returns an object with 
	 * fields x and y. */
	this.getPosition = function () {
		var anchor = this.modelField.localStyle.anchor ? this.modelField.localStyle.anchor : this.anchor;
		var retval = {x: 0, y: 0 };
		var hCenter = this.getHCenter();
		retval.x = this.getX();
		if (hCenter) {
			// hCenter means to center the field horizontally, regardless
			// of the x position
			retval.x = (this.styleSet.width - this.getWidth()) / 2;
		}
		else if (anchor == "TR" || anchor == "BR") {
			retval.x = this.styleSet.width - retval.x - this.getWidth();
		}
		retval.y = this.getY();
		if (anchor == "BR" || anchor == "BL") {
			retval.y = this.styleSet.height - retval.y - this.getHeight();
		}
		return retval;
	};
	
	/* getImageID returns the ID of the first available image if no image has 
	 * been set, or "default" if there are none. */
	this.getImageID = function () {
		if (this.modelField.localStyle.imageID !== null)
			return this.modelField.localStyle.imageID;
		else if (this.imageID !== null)
			return this.imageID;
		else if (this.availableImages && this.availableImages.length > 0) {
			return this.availableImages[0].ID;
		}
		else return "default";
	};
	
	/* Unlike most of the setters, this one also sets the fabricObject,
	 * since it's complicated. */
	this.setLocalImageID = function (id, width, height) {
		this.modelField.localStyle.imageID = id;
		if (this.fabricObject) {
			this.fabricObject.remove ();
		}
		if (width && height) {
			displayDims = this.fitImage (width, height);
			this.fabricateImage (this.canvas, displayDims.width, displayDims.height);
		}
		else {
			this.fabricateImage (this.canvas);
		}
	};
	
	/* Calculate the display size of the image based on its source width and height.
	 * Return an object with properties x, y, width and height.
	 * x and y can be nonzero if there's a maskRect.
	 * We apply Procrustean logic to the image. Whether it's too
	 * big or too small, size it to the smallest size that will 
	 * fill the rectangle given by width and height, centering
	 * it in the other dimension. */
	this.fitImage = function (w, h) {
		this.sourceWidth = w;
		this.sourceHeight = h;
		this.sourceX = 0;
		this.sourceY = 0;
		var retval = {x: 0, y: 0};
		if (maskRect) {
			// Hmm ... I need to do this first, don't I, so it knows
			// which dimension it has to trim.
			this.sourceWidth = maskRect.width;
			this.sourceHeight = maskRect.height;
			retval.x = maskRect.x;
			retval.y = maskRect.y;
		}

		// TODO Doesn't mask to frame yet.
		var widthRatio = this.sourceWidth / this.width;
		var heightRatio = this.sourceHeight / this.height;
		if (widthRatio < heightRatio) {
			// We'll fill the horizontal and overflow the vertical
			retval.width = this.width;
			retval.height = this.sourceHeight * (this.width / this.sourceWidth);
			// probably need to redraw
		}
		else {
			// Fill the vertical and overflow the horizontal, or fit perfectly
			retval.height = this.height;
			retval.width = this.sourceWidth * (this.height / this.sourceHeight);
		}
		var maskRect = this.getMaskRect();
		return retval;
	};
	
	/* Crop the field to a specified rectangle. This works only for ...
	 * well, right now, it doesn't work for anything, but it should
	 * for images and maybe logos.
	 * 
	 * The x and y arguments are relative to the top left of the image.
	 */
//	this.crop = function (x, y, wid, ht) {
//		// We have to translate the coordinates to center-based coordinates.
//		// This is a slightly brute-force approach, but the fabric functions
//		// don't do what I expect.
//		x -= this.fabricObject.width / 2;
//		y -= this.fabricObject.height / 2;
//		console.log ("Cropping to " + x + ", " + y + ", " + wid + ", " + ht);
//		this.fabricObject.clipTo = function (ctx) {
//			ctx.rect (x, y, wid, ht);
//		};
//	};


	
	/* Return the image object from availableImages that matches id.
	 * The returne object has the properties id, width, height.
	 * Returns null if no match. 
	 */
	this.findImage = function (id) {
		if (!id)
			id = this.getImageID();
		if (!this.availableImages)
			return null;
		for (var i = 0; i < this.availableImages.length; i++) {
			var img = this.availableImages[i];
			if (img.ID == id) {
				console.log ("findImage, id = " + id + "  width = " + img.width + "  height = " + img.height);
				return img;
			}
		}
		return null;
	};
}
