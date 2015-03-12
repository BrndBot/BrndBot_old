/**
 * BBModel.js
 * 
 *  *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
 * 
 * Overview:
 * 
 * A Promotion is something that's prepared for drawing and ultimately sending to
 * a channel. Its two principal components are a Model and a StyleSet. Drawing is
 * driven by the StyleSet, which contains an ordered sequence of Styles.
 * 
 * A Model contains a set of ModelFields. These simply define what kind of object
 * is being drawn: Text, Image, Logo, Block, or SVG, and they name the field. 
 * Different StyleSets can be applied to the same Model.
 * 
 * When used, a StyleSet is attached to a Model. Each StyleSet is intended for
 * use with only one Model. However, the user can change the contents of a ModelField,
 * so each Style works with a _copy_ of the ModelField ...
 * 
 * 
 * 
 * Gary McGath
 * February 5, 2015
 * 
 * Required packages:
 * 	fabric.js
 *  Some version of JQuery
 */

/* The Model prototype constructor */
function Model () {
	this.name = null;
	// create an array of ModelFields
	this.fields = [];
	// Assign some null values, mostly to document the fields
	// style should be null or a StyleSet
	this.styleSet = null;
	
	// Convert JSON data into data for this Model
	this.populateFromJSON = function (jsonObj) {
		this.name = jsonObj.name;
		for (var i = 0; i < jsonObj.fields.length; i++) {
			var jsonField = jsonObj.fields[i];
			var modelField = new ModelField ();
			modelField.populateFromJSON (jsonField);
			this.fields[i] = modelField;
		}
	};
	
	this.findFieldByName = function (nam) {
		for (var i = 0; i < this.fields.length; i++) {
			var field = this.fields[i];
			if (field.name == nam)
				return field;
		}
		return null;
	};
	
	/* Make a copy of a Model.  */
	this.copy = function () {
		var cloneModel = new Model();
		cloneModel.name = this.name;
		for (var i = 0; i < this.fields.length; i++) {
			cloneModel.fields[i] = this.fields[i].copy();
		}
		return cloneModel;
	};
}

/* The prototype constructor for one field of a Model. */
function ModelField () {
	this.name = null;
	this.styleType = null;
	this.localStyle = null;
	
	this.populateFromJSON = function (jsonField) {
		this.name = jsonField.name;
		this.styleType = jsonField.styleType;
	};
	

	

}

/* The prototype constructor for a StyleSet. A StyleSet contains an ordered
 * array of Styles, each of which is associated with a ModelField. */
function StyleSet () {
	this.name = null;
	// styles is an array of Styles
	this.styles = [];
	// dimensions of the promotion to be drawn
	this.width = 0;
	this.height = 0;

	// Convert JSON data into data for this Model
	this.populateFromJSON = function (jsonObj) {
		this.name = jsonObj.name;
		this.width = jsonObj.width;
		this.height = jsonObj.height;
		var jsonStyles = jsonObj.styles;
		for (var i = 0; i < jsonStyles.length; i++) {
			var jsonStyle = jsonStyles[i];
			var styl = new Style (jsonStyle.styleType);
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
		for (i = 0; i < this.styles.length; i++) {
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
		for (i = 0; i < len; i++) {
			var style = this.styles[i];
			style.attachToModel (model);
		}
	}
	
	// Find a Style that matches a ModelField, or null
	this.findApplicableStyle = function (modelField) {
		console.log ("findApplicableStyle, field name = " + modelField.name);
		var fieldName = modelField.name;
		if (!fieldName)
			return null;
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			if (!style)
				continue;
			console.log ("Checking style " + style.fieldName);
			if (style.fieldName == fieldName) {
				console.log ("Found a style");
				return style;
			}
		}
		return null;
	};
}

/* The prototype constructor for one field in a Style.
 * A StyleSet's Style will have a local Style associated with it,
 * where all changes are made. It will also have a fabric.js object
 * associated with it for drawing. These can be replaced when the
 * Style is reused. */
function Style (styleType) {
	this.index = 0;
	// The ModelField associated with the style
	this.modelField = null;
	// The name of the ModelField.
	this.fieldName = null;
	this.styleType = styleType;
	this.width = null;
	this.height = null;
	this.anchor = null;
	this.offsetX = null;
	this.offsetY = null;
	this.typeface = null;
	this.pointSize = null;
	this.color = null;
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
	this.fabricObject = null;
	
	/* Make a copy of the Style. 
	 */
	this.copy = function () {
		var retval = new Style(this.styleType);
		retval.index = this.index;
		retval.fieldName = this.fieldName;
		retval.width = this.width;
		retval.height = this.height;
		retval.anchor = this.anchor = "TL";
		retval.offsetX = this.offsetX;
		retval.offsetY = this.offsetY;
		retval.typeface = this.typeface;
		retval.pointSize = this.pointSize;
		retval.color = this.color;
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
		return retval;
	}
	
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
		this.opacity = jsonObj.opacity;
		this.multiply = jsonObj.multiply;
		this.svg = jsonObj.svg;
		this.dropShadowH = jsonObj.dropShadowH;
		this.dropShadowV = jsonObj.dropShadowV;
		this.dropShadowBlur = jsonObj.dropShadowBlur;

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
		for (i = 0; i < len; i++) {
			var field = model.fields[i];
			if (field.name == this.fieldName) {
				this.modelField = field;
				break;
			}
		}
		// If the model field already has a local style, leave it;
		// otherwise give it a starter local style.
		if (this.modelField && !this.modelField.localStyle)
			this.modelField.localStyle = new Style();
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
			this.fabricateImage (canvas);
			break;
		case "logo":
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
		
		var text = new fabric.Text(this.getText(), {
			hasControls: false,
			selectable: false,
			fontSize: this.getPointSize(),
			fontFamily: this.getTypeface(),
			fill: this.getColor(),
			left: pos.x,
			top: pos.y,
			originX: pos.originx,
			originY: pos.originy,
			width: this.getWidth(),
			height: this.getHeight(),
			fontWeight: weight,
			fontStyle: fstyle,
			alignment: this.getAlignment()
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
			originX: pos.originx,
			originY: pos.originy,
			fill: color,
			globalCompositeOperation: gco,
			opacity: opacity
		});
		if (this.multiply) {
			
		}
		this.fabricObject = rect;
		canvas.add(rect);
		canvas.moveTo(rect, this.index);
	};
	

	this.fabricateImage = function  (canvas) {
		console.log ("fabricateImage");
		var pos = this.getPosition();
		var width = this.getWidth();
		var height = this.getHeight();
		var opacity = this.getOpacity () * 0.01;	// Convert 0-100 to 0-1
		var style = this;
		var id = this.getImageID();
		var img = fabric.Image.fromURL("ImageServlet?img=" + id, function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = pos.x;
			img.top = pos.y;
			img.originX = pos.originx;
			img.oritinY = pos.originy;
			img.width = width;
			img.height = height;
			img.opacity = opacity;
			style.fabricObject = img;
			canvas.add(img);
			canvas.moveTo(img, style.index);
		});
	};
	
	this.fabricateLogo = function  (canvas) {
		console.log ("fabricateLogo");
		var pos = this.getPosition();
		var width = this.getWidth();
		var height = this.getHeight();
		var style = this;
		var img = fabric.Image.fromURL("ImageServlet?img=logo", function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = pos.x;
			img.top = pos.y;
			img.originX = pos.originx;
			img.oritinY = pos.originy;
			img.width = width;
			img.height = height;
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
			obj.originX = pos.originx;
			obj.originY = pos.originy;
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
	}
	
	this.isBold = function () {
		return (this.modelField.localStyle.bold !== null) ? this.modelField.localStyle.bold : this.bold;
	};
	
	this.setLocalBold = function (b) {
		this.modelField.localStyle.bold = b;
	}
	
	this.isItalic = function () {
		return (this.modelField.localStyle.italic !== null) ? this.modelField.localStyle.italic : this.italic;
	};
	
	this.setLocalItalic = function (b) {
		this.modelField.localStyle.italic = b;
	}
	
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
	}
	
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
	}

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
	}
	
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
	}

	/* getColor returns black as a last resort, so it always returns
	 * a color string. */
	this.getColor = function () {
		if (this.modelField.localStyle.color)
			return this.modelField.localStyle.color;
		else if (this.color)
			return this.color;
		else return "#000000";
	};
	
	this.setLocalColor = function (c) {
		this.modelField.localStyle.color = c;
	}
	
	this.getMultiply = function () {
		if (this.modelField.localStyle.multiply !== null)
			return this.modelField.localStyle.multiply;
		else if (this.multiply !== null)
			return this.multiply;
		else return false;
	}
	
	this.getAlignment = function () {
		return (this.modelField.localStyle.alignment !== null) ? this.modelField.localStyle.alignment : this.alignment;
	};
	
	this.setLocalAlignment = function (al) {
		this.modelField.localStyle.alignment = al;
	}
	
	this.isBold = function () {
		return (this.modelField.localStyle.bold !== null) ? this.modelField.localStyle.bold : this.bold;
	};
	
	this.setLocalBold = function (b) {
		this.modelField.localStyle.bold = b;
	}

	this.isItalic = function () {
		return (this.modelField.localStyle.italic !== null) ? this.modelField.localStyle.italic : this.italic;
	};
	
	this.setLocalItalic = function (it) {
		this.modelField.localStyle.italic = it;
	}
	
	this.getSVG = function () {
		return (this.modelField.localStyle.svg !== null) ? this.modelField.localStyle.svg : this.svg;
	};
	
	this.setLocalSVG = function (svg) {
		this.modelField.localStyle.svg = svg;
	}
	
	/* Opacity is 0-100. Will need to be normalized to 0-1 for Fabric. */
	this.getOpacity = function () {
		if (this.modelField.localStyle.opacity !== null)
			return this.modelField.localStyle.opacity;
		else if (this.opacity != null)
			return this.opacity;
		else return 100;
	}
	
	this.setLocalOpacity = function (op) {
		this.modelField.localStyle.opacity = op;
	}
	
	this.getDropShadowH = function () {
		return (this.modelField.localStyle.dropShadowH !== null) ? this.modelField.localStyle.dropShadowH : this.dropShadowH;
	};
	
	this.setLocalDropShadowH = function (h) {
		this.modelField.localStyle.dropShadowH = h;
	}

	this.getDropShadowV = function () {
		return (this.modelField.localStyle.dropShadowV !== null) ? this.modelField.localStyle.dropShadowV : this.dropShadowV;
	};

	this.setLocalDropShadowV = function (v) {
		this.modelField.localStyle.dropShadowV = v;
	}

	this.getDropShadowBlur = function () {
		return (this.modelField.localStyle.dropShadowBlur !== null) ? this.modelField.localStyle.dropShadowBlur : this.dropShadowBlur;
	};

	this.setLocalDropShadowBlur = function (b) {
		this.modelField.localStyle.dropShadowBlur = b;
	}

	/* Function for the x, y, and anchor calculations. Returns an object with 
	 * fields x, y, and anchor. */
	this.getPosition = function () {
		var anchor = this.modelField.localStyle.anchor ? this.modelField.localStyle.anchor : this.anchor;
		var retval = {x: 0, y: 0, originx: "left", originy: "top" };
		retval.x = this.getX();
		if (anchor == "TR" || anchor == "BR") {
			retval.originx = "right";
			retval.x = -retval.x;
		}
		retval.y = this.getY();
		if (anchor == "BR" || anchor == "BL") {
			retval.originy = "bottom";
			retval.y = -retval.y;
		}

		return retval;
	};
	
	/* getImageID returns the string "default" if no image has been set. */
	this.getImageID = function () {
		if (this.modelField.localStyle.imageID !== null)
			return this.modelField.localStyle.imageID;
		else if (this.imageID !== null)
			return this.imageID;
		else return "default";
	}
	
	/* Unlike most of the setters, this one also sets the fabricObject,
	 * since it's complicated. */
	this.setLocalImageID = function (id) {
		this.modelField.localStyle.imageID = id;
		if (this.fabricObject) {
			this.fabricObject.remove ();
//			this.fabricObject.setSrc ("ImageServlet?img=" + id);
			this.fabricateImage (this.canvas);
		}
	}
	
	/* Crop the field to a specified rectangle. This works only for ...
	 * well, right now, it doesn't work for anything, but it should
	 * for images and maybe logos.
	 * 
	 * The x and y arguments are relative to the top left of the image.
	 */
	this.crop = function (x, y, wid, ht) {
		// We have to translate the coordinates to center-based coordinates.
		// This is a slightly brute-force approach, but the fabric functions
		// don't do what I expect.
		x -= this.fabricObject.width / 2;
		y -= this.fabricObject.height / 2;
		console.log ("Cropping to " + x + ", " + y + ", " + wid + ", " + ht);
		this.fabricObject.clipTo = function (ctx) {
			ctx.rect (x, y, wid, ht);
		};
	};
}



/* The Promotion prototype constructor. A Promotion associates a Model
 * with a StyleSet and custom content and provides drawing capability.
 * The constructor finds matching fields for the model and styleset
 * and sets them up as the promotion fields.
 */
function Promotion (model, styleSet) {
	// model is a Model
	this.model = model;
	// styleSet is a StyleSet
	this.canvas = null;
	
	this.applyStyleSet = function (styleSet) {
		this.styleSet = styleSet;

		styleSet.attachToModel (model);
		
	};

	this.applyStyleSet (styleSet);

	/* Draw a Promotion. The argument is the ID of a canvas element. */
	this.draw = function (location) {
		var canvasElem = $('#' + location);
		canvasElem.attr("width", this.styleSet.width);
		canvasElem.attr("height", this.styleSet.height);
		this.canvas = new fabric.Canvas (location);
		
	
		
		var styles = this.styleSet.styles;
		var len = styles.length;
		for (var i = 0; i < len; i++) {
			var style = styles[i];
			style.draw (location, this.canvas);
		}
	};
	
	/* Redraw a Promotion. */
	this.redraw = function (location) {
		var styles = this.styleSet.styles;
		var len = styles.length;
		for (var i = 0; i < len; i++) {
			var style = styles[i];
			this.canvas.remove (this.fabricObject);
			this.fabricObject = null;
			this.draw (location);
		}	
	};
	
	// Export the Promotion as a data URL
	this.export = function () {
		return this.canvas.toDataURL({
			format: "jpg",
			quality: 0.9
		});
	};
}