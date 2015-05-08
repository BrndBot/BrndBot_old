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
	this.defaultImage = null;
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
	
	
	/* Call this before switching style sets to delete all existing fabric objects.
	 */
	this.killFabricObjects = function () {
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			if (style.fabricObject) {
				style.fabricObject.remove();
				style.fabricObject = null;
			}
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
	};
	
	/** Assign the array of available images to the StyleSet.
	 *  This has to be done to each StyleSet that's activated.
	 *  Repeating it is harmless. Each image objects has the fields
	 *  ID, width, and height.
	 */
	this.assignAvailableImages = function (imgs) {
		this.availableImages = imgs;
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			style.availableImages = imgs;
		}
	};
	
	/** Reset transient values in the local style to undefined.
	 */
	this.resetTransientStyles = function () {
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			style.resetTransientValues ();
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
	
	/* The drawing of an image or logo is defined by displayDims and the style's dimensions. 
	 * 
	 * displayDims gives the dimensions of the whole image as drawn on the canvas, in canvas
	 * coordinates. It will always be masked to the style's dimensions.
	 */
	this.displayDims = {
			x: 0,
			y: 0,
			width: 0,
			height: 0
	};	// The eimensions of the whole image as scaled to the canvas
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
	this.dropShadowEnabled = true;
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
		retval.displayDims = this.displayDims;
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
		retval.dropShadowEnabled = this.dropShadowEnabled;
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
		this.dropShadowEnabled = this.dropShadowH !== null && this.dropShadowV !== null;
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

	/* Return the Style's position (0-based) in its StyleSet. Not the same
	 * as index, which is display stacking order. If it can't be found
	 * in the StyleSet, return -1. 
	 */
	this.positionInStyleSet = function () {
		if (!this.styleSet)
			return -1;
		for (var i = 0; i < this.styleSet.styles.length; i++) {
			var style = this.styleSet.styles[i];
			if (style === this)
				return i;
		}
		return -1;
	};
	
	/* Call this to update the fabric object after changing the
	 * drop shadow parameters.
	 */
	this.updateDropShadow = function () {
		if (!this.hasDropShadow ()) {
			// May have to remove drop shadow
			this.fabricObject.shadow = null;
		}
		else {
			var fobj = this.fabricObject;
			fobj.shadow = new fabric.Shadow ();
			fobj.shadow = new fabric.Shadow ();
			fobj.shadow.blur = this.getDropShadowBlur();
			fobj.shadow.offsetX = this.getDropShadowH();
			fobj.shadow.offsetY = this.getDropShadowV();		}
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
	
	/* Image styles only: Update drawing to use the specified cropping dims. */
	this.drawToMask = function (x, y, w, h) {
		this.fitImageToMask (x, y, w, h);
		// Remove old canvas object
		if (this.fabricObject) {
			this.fabricObject.remove ();
		}
		this.fabricateImage(this.canvas);
	}


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
				this.sourceWidth = imgData.width;
				this.sourceHeight = imgData.height;
			}
			else {
				// No image available. Make up dimensions for the default image.
				this.sourceWidth = 100;
				this.sourceHeight = 100;
			}
			this.fitImageToMask ();
			this.fabricateImage (canvas);
			break;
		case "logo":
			if (this.styleSet.logoData) {
				this.sourceWidth = this.styleSet.logoData.width;
				this.sourceHeight = this.styleSet.logoData.height;
			}
			this.fitLogo();
			this.fabricateLogo (canvas);
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
		
		var text = new fabric.Text(this.getText(), {
			hasControls: false,
			selectable: false,
			fontFamily: this.getTypeface(),
			fontSize: this.getPointSize(),
			fill: this.getColor(),
			left: xpos,
			top: pos.y,
			originX: "left",
			originY: "top",
			width: this.getWidth(),
			height: this.getHeight(),
			fontWeight: weight,
			fontStyle: fstyle,
			textAlign: alignment,
			textExtension: new TextExtension (this)
		});
		
		if (this.hasDropShadow ()) {
			var dropShadowH = this.getDropShadowH();
			var dropShadowV = this.getDropShadowV();
			text.shadow = new fabric.Shadow ();
			text.shadow.blur = this.getDropShadowBlur();
			text.shadow.offsetX = dropShadowH;
			text.shadow.offsetY = dropShadowV;
		}
		this.fabricObject = text;
		canvas.add(text);
		canvas.moveTo(text, this.index);
		this.prepareFabricText (text);
		var bRectWidth = text.getBoundingRect().width;

		if (this.hCenter) {
			// Reposition to center and redraw
			xpos += (this.getWidth() - bRectWidth) / 2;
			text.left = xpos;
			text.width = bRectWidth;
		}
		if (bRectWidth > text.width) {
			this.wrapText();
			this.canvas.renderAll();		// needed to get rid of old text
		}
		else {
			text.render(canvas.getContext());
		}
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

		if (this.hasDropShadow ()) {
			var dropShadowH = this.getDropShadowH();
			var dropShadowV = this.getDropShadowV();
			rect.shadow = new fabric.Shadow ();
			rect.shadow.blur = this.getDropShadowBlur();
			rect.shadow.offsetX = dropShadowH;
			rect.shadow.offsetY = dropShadowV;
		}

		this.fabricObject = rect;
		canvas.add(rect);
		canvas.moveTo(rect, this.index);

	};
	

	/** Draw the image. displayDims governs the positioning and size.
	 *  
	 *   displayDims properties:
	 *   width: the width at which the image should be drawn
	 *   height: the height at which the image should be drawn
	 *   x: The x offset of the left edge of the masked rectangle
	 *   y: The y offset of the top of the masked rectangle
	 *   
	 *   If dispDims is missing ... something is still wrong here! What DOES it do?
	 *   
	 *   These dimensions should be at least as large as the style dimensions
	 *   and should preserve the original image's aspect ratio.
	 */
	this.fabricateImage = function  (canvas) {
		console.log ("fabricateImage");
		var dispDims = this.displayDims;	// Just for brevity
		var pos = this.getPosition();
		var id = this.getImageID();
		var imgdata = this.findImage (id);
		var opacity = this.getOpacity () * 0.01;	// Convert 0-100 to 0-1
		var gco = "source-over";
		if (this.getMultiply())
			gco = "multiply";
		
		// Calculate the mask rectangle relative to the image, then
		// convert it to fabric's silly coordinate system in which 0,0
		// is the center of the image.
		// The mask is the style's dimensions, because of the way we set it up.
		
		var clipX = pos.x - dispDims.x;
		var clipY = pos.y - dispDims.y;
		var clipW = this.getWidth();
		var clipH = this.getHeight();
		
		clipX -= dispDims.width / 2;
		clipY -= dispDims.height / 2;
		
		var style = this;
		// Use the default image if no image ID is available
		var imgURL;
		if (id)
			imgURL = "ImageServlet?img=" + id;
		else
			imgURL = "ImageServlet?img=default";
		var img = fabric.Image.fromURL("ImageServlet?img=" + id, function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = dispDims.x;
			img.top = dispDims.y;
			img.originX = "left";
			img.originY = "top";
			img.width = dispDims.width;
			img.height = dispDims.height;
			img.opacity = opacity;
			img.globalCompositeOperation = gco;

			img.clipTo = function (ctx) {
				ctx.rect (clipX, clipY, clipW, clipH);
			};
			style.fabricObject = img;
			canvas.add(img);
			canvas.moveTo(img, style.index);
		});
	};
	
	
	this.fabricateLogo = function  (canvas) {
		console.log ("fabricateLogo");
		var dispDims = this.displayDims;	// Just for brevity
		var pos = this.getPosition();

		var style = this;
		var img = fabric.Image.fromURL("ImageServlet?img=logo", function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = dispDims.x;
			img.top = dispDims.y;
			img.originX = "left";
			img.oritinY = "top";
			img.width = dispDims.width;
			img.height = dispDims.height;
//			img.clipTo = function (ctx) {
//				ctx.rect (-style.getWidth() / 2, -style.getHeight() / 2, style.getWidth(), style.getHeight());
//			};

			if (style.hasDropShadow ()) {
				var dropShadowH = style.getDropShadowH();
				var dropShadowV = style.getDropShadowV();
				img.shadow = new fabric.Shadow ();
				img.shadow.blur = style.getDropShadowBlur();
				img.shadow.offsetX = dropShadowH;
				img.shadow.offsetY = dropShadowV;
			}

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
	
	/* Get the actual text in the fabric object. */
	this.getFabricText = function () {
		return this.fabricObject ? this.fabricObject.getText() : "";
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
	
	/* Set the dimensions of the source image. */
	this.setSourceDims = function (w, h) {
		this.sourceWidth = w;
		this.sourceHeight = h;
	}
	
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
	
	/* This operates on the fabricObject to update the color. 
	 * In the case of a Block, there's an extra complication, since
	 * it might have a group object with a drop shadow. */
	this.setLocalColor = function (c) {
		this.modelField.localStyle.color = c;
		if (this.styleType == "block") {
			if (this.hasDropShadow()) {
				// It's a group, and we have to fish out the second rect. TODO?
			}
		}
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
		if (!this.hasDropShadow())
			return null;
		return (this.modelField.localStyle.dropShadowH !== null) ? this.modelField.localStyle.dropShadowH : this.dropShadowH;
	};
	
	this.setLocalDropShadowH = function (h) {
		this.modelField.localStyle.dropShadowH = h;
	};
	
	this.getDropShadowV = function () {
		if (!this.hasDropShadow())
			return null;
		return (this.modelField.localStyle.dropShadowV !== null) ? this.modelField.localStyle.dropShadowV : this.dropShadowV;
	};

	this.setLocalDropShadowV = function (v) {
		this.modelField.localStyle.dropShadowV = v;
	};

	this.getDropShadowBlur = function () {
		if (!this.hasDropShadow())
			return null;
		return (this.modelField.localStyle.dropShadowBlur !== null) ? this.modelField.localStyle.dropShadowBlur : this.dropShadowBlur;
	};

	this.setLocalDropShadowBlur = function (b) {
		this.modelField.localStyle.dropShadowBlur = b;
	};
	
	this.setLocalDropShadowEnabled = function (b) {
		// Takes true or false
		this.modelField.localStyle.dropShadowEnabled = b;
	};
	
	this.hasDropShadow = function () {
		if (this.modelField.localStyle.dropShadowEnabled !== null)
			return this.modelField.localStyle.dropShadowEnabled;
		else if (this.dropShadowEnabled !== null)
			return this.dropShadowEnabled;
		else
			return false;
	};

	this.getHCenter = function () {
		if (this.modelField.localStyle.hCenter !== null)
			return this.modelField.localStyle.hCenter;
		else if (this.hCenter !== null)
			return this.hCenter;
		else
			return false;
	};

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
		// Recalculate dimensions. Any previous masking goes away.
		this.fitImageToMask();
		this.fabricateImage (this.canvas);
	};
	
	
	/* Reset local values to undefined where they shouldn't be retained over a 
	 * style change. Keep color and content. */
	this.resetTransientValues = function () {
		var ls = this.modelField.localStyle;
		ls.height = null;
		ls.width = null;
		ls.anchor = null;
		ls.offsetX = null;
		ls.offsetY = null;
		ls.typeface = null;
		ls.pointSize = null;
		ls.bold = null;
		ls.italic = null;
		ls.opacity = null;
		ls.multiply = null;
		ls.alignment = null;
		ls.dropShadowEnabled = null;
		ls.dropShadowH = null;
		ls.dropShadowV = null;
		ls.dropShadowBlur = null;
		ls.hCenter = null;
	};

	/***** FUNCTIONS THAT DIRECTLY AFFECT THE CANVAS *****/
	
	/* Set the drawing text. */
	this.setFabricText = function (s) {
		var text = this.fabricObject;
		text.setText (s);
	};
	
	/* This bit is necessary to handle centering and wrapping after setFabricText */
	this.adjustFabricText = function (promo) {
		
		var text = this.fabricObject;
		var bRectWidth = text.getBoundingRect().width;
		var xpos = this.getPosition().x;
		if (this.hCenter) {
			// Reposition to center and redraw
			xpos += (this.getWidth() - bRectWidth) / 2;
			text.left = xpos;
			text.width = bRectWidth;
			text.render(promo.canvas.getContext());
		}
		this.wrapText();
	};
	
	/* Set the font size in the drawing object */
	this.setFabricFontSize = function (n) {
		this.fabricObject.setFontSize (n);
		this.fabricObject.textExtension.fontSize = n;
	};
	
	/* Set the drawing scale. */
	this.setFabricScale = function (sc) {
		this.fabricObject.scale (sc);
	};
	
	/* Set the fill color. */
	this.setFabricFill = function (f) {
		this.fabricObject.fill = f;
	};
	
	/* Set the font style. Usual arguments will be "italic" or "normal". */
	this.setFabricFontStyle = function (s) {
		this.fabricObject.setFontStyle (s);
	};
	
	/* Set the font weight. */
	this.setFabricFontWeight = function (w) {
		this.fabricObject.setFontWeight (w);
	};
	
	/* Set the font family. */
	this.setFabricFontFamily = function (ff) {
		this.fabricObject.setFontFamily (ff);
	};
	
	/* Set left boundary. */
	this.setFabricLeft = function (n) {
		this.fabricObject.setLeft (n);
	};
	
	this.setFabricTop = function (n) {
		this.fabricObject.setTop (n);
	};
	
	this.setFabricWidth = function (n) {
		this.fabricObject.setWidth (n);
	};
	
	this.setFabricHeight = function (n) {
		this.fabricObject.setHeight (n);
	};
	
	this.setFabricBackgroundColor = function (c) {
		this.fabricObject.setBackgroundColor (c);
	};
	
	/* Fabric object getters */
	
	this.getFabricWidth = function () {
		return this.fabricObject.getWidth();
	};
	
	this.getFabricBoundingRectWidth = function () {
		return this.fabricObject.getBoundingRect().width;
	}
	
	
	/* Set displayDims so as to set a masking rectangle, then redraw.
	 * 
	 * The arguments are the dimensions of the masking rectangle in source
	 * image coordinates.
	 * 
	 * This means redefining displayDims (which the return value is usually set to)
	 * so that what's in the rectangle will be just what's in the
	 * mask. Assumes that sourceWidth and sourceHeight have been set.
	 * Forces a redraw.
	 * 
	 * If no arguments are given, we mask to the full image.
	 */
	this.fitImageToMask = function (maskX, maskY, maskW, maskH) {
		// Messy, but not to bad if done a step at a time.
		
		// Set defaults if no arguments
		if (typeof maskX == 'undefined') {
			maskX = 0;
			maskY = 0;
			maskW = this.sourceWidth;
			maskH = this.sourceHeight;
		}
		// First, let's get a "true" mask that reflects what we can actually show,
		// given that we may have to cut back one dimension or the other of the
		// user-supplied mask.
		var maskAspectRatio = maskH / maskW;
		var width = this.getWidth();		// style's width and height
		var height = this.getHeight();
		var pos = this.getPosition();
		var widthScale = maskW / width;		// Scaling factor to fit full width
		var heightScale = maskH / height;
		var styleAspectRatio = height / width;
		
		if (styleAspectRatio > maskAspectRatio) {
			// we have to eliminate some of the sides.
			var newMaskW = maskH / styleAspectRatio;
			maskX += (maskW - newMaskW) / 2;
			maskW = newMaskW;
		}
		else {
			// exact proportions, or else eliminate some top & bottom
			var newMaskH = maskW * styleAspectRatio;
			maskY += (maskH - newMaskH) / 2;
			maskH = newMaskH;
		}
		
		// We now have a mask, still in original image coordinates,
		// that reflects what will actually be seen.
		// Now our scale factor is the ratio of the style width to mask width,
		// which is equal to the ratio of style height to mask height;
		var scaleFactor = width / maskW;
		
		// Now scale the mask to the style coordinates.
		maskX *= scaleFactor;
		maskY *= scaleFactor;
		maskW *= scaleFactor;
		maskH *= scaleFactor;
		
		// The display dimensions are the source dimensions multiplied by
		// the same scaleFactor.
		this.displayDims.width = this.sourceWidth * scaleFactor;
		this.displayDims.height = this.sourceHeight * scaleFactor;
		
		// Finally the X and Y position.
		
		this.displayDims.x = pos.x - maskX;
		this.displayDims.y = pos.y - maskY;
	};


	/*  Fit the logo in its allotted space. It's like fitImageToMask,
	 *  except there's no mask (so we always use the mask = frame case),
	 *  and we do an inside fit rather than an outside fit.
	 */
	this.fitLogo = function () {
		var width = this.getWidth();
		var height = this.getHeight();
		var styleRatio = height / width;
		var logoRatio = this.sourceHeight / this.sourceWidth;
		this.displayDims.width = width;
		this.displayDims.height = height;
		
		
		if (styleRatio > logoRatio) {
			// We have a "tall" style compared to the logo, so fit the logo width
			// to the style.
			this.displayDims.height = this.sourceHeight * (width / this.sourceWidth);
			this.displayDims.width = width;
			this.displayDims.x = 0;
			this.displayDims.y = (this.sourceHeight - this.displayDims.height) / 2;
		}
		else {
			// We have a "wide" style or a perfect fit. Fit to the height.
			this.displayDims.width = this.sourceWidth * (height / this.sourceHeight);
			this.displayDims.height = height;
			this.displayDims.x = (this.sourceWidth - this.displayDims.width) / 2;
			this.displayDims.y = 0;
		}
	}


	
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
				return img;
			}
		}
		return null;
	};
	
	/* Call this before drawing or redrawing a fabric.Text object 
	 * property to make it draw according to its textExtension
	 * property.
	 */
	this.prepareFabricText = function () {
		var fabtext = this.fabricObject;
		var ext = fabtext.textExtension;
		if (ext) {
			fabtext.setText (ext.wrappedText);
			fabtext.setFontSize (ext.fontSize);
		}
	};
	
	/* Wrap a text Style. */
	this.wrapText = function () {
		var styleWidth = this.getWidth();
		// Don't wrap on pathologically narrow fields or huge point sizes
		if (this.getPointSize() * 2 > styleWidth)
			return;
		var textTokens = this.getText().split(/( +|\n)/);
		// This split will put spaces and newlines in their own tokens. 
		// multiple spaces will become one token, but each newline will be its
		// own token. 

		// Now we build a string one token at a time, and check its width at
		// each point. Whenever we find that the width exceeds the box, 
		// we take the last token away and append a newline. 
		var tokidx = 0;
		var fabtext = this.fabricObject;
		var textExt = fabtext.textExtension;
		textExt.wrappedText = "";
		var singleWordLine = false;
		for (;;) {
			var oldWrappedText = textExt.wrappedText;
			textExt.wrappedText += textTokens[tokidx];
			this.prepareFabricText();
			var wid = fabtext.getBoundingRect().width;
			if (wid > styleWidth) {
				if (!singleWordLine) {
					// back up
					textExt.wrappedText = oldWrappedText + "\n";
					singleWordLine = true;
				}
				else {
					// The line has just one word, and it doesn't fit.
					// Split the word with a line break, working end to
					// beginning, until it fits. If we can't get in even
					// one letter, then forget wrapping altogether.
					var holdText = textExt.wrappedText;
					var fixedit = false;
					for (var i = holdText.length; i >= oldWrappedText.length; --i) {
						textExt.wrappedText = holdText.substring (0, i) +
							"\n" +
							holdText.substring (i);
						this.prepareFabricText();
						wid = fabtext.getBoundingRect().width;
						if (wid <= styleWidth) {
							fixedit = true;
							tokidx++;
							break;
						}
					}
					// If we couldn't do anything, just punt the whole wrapping thing.
					if (!fixedit) {
						textExt.wrappedText = this.getText();
						this.prepareFabricText();
						return;
					}
				}
			}
			else {
				tokidx++;
				singleWordLine = false;
			}
			if (tokidx >= textTokens.length)
				break;
		}
	};
	

}



/* Add a TextExtension as a property of a fabric.Text instance to add a level
 * of management.
 */
function TextExtension (styl) {
	/* Text with line breaks added to wrap */
	this.wrappedText = styl.getText();
	/* Size of actual drawing, <= style size */
	this.fontSize = styl.getPointSize();
}