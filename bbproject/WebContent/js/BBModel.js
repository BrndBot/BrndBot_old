/**
 * BBModel.js
 * 
 *  *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
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
}

/* The prototype constructor for one field of a Model. */
function ModelField () {
	this.name = null;
	this.styleType = null;
	// A ModelField may have a Style associated with it.
	this.style = null;
	// fabricObject is a fabric.js drawing object for the field
	this.fabricObject = null;
	
	// Values are initially null, which means the style governs
	this.offsetX = null;
	this.offsetY = null;
	this.width = null;
	this.height = null;
	this.anchor = null;
	this.text = null;
	this.color = null;
	this.typeface = null;
	this.pointSize = null;
	this.bold = null;
	this.italic = null;
	this.svg = null;
	this.dropShadowH = null;
	this.dropShadowV = null;
	this.dropShadowBlur = null;
	
	this.populateFromJSON = function (jsonField) {
		this.name = jsonField.name;
		this.styleType = jsonField.styleType;
	};
	
	/* For text fields only. Would it be cleaner to subclass
	 * ModelField? 
	 */
	this.getText = function () {
		var content = "";
		if (this.text)
			content = this.text;
		else if (this.style.text)
			content = this.style.text;
		else if (this.style.defaultText)
			content = this.style.defaultText;
		return content;
	};
	
	this.isBold = function () {
		return (this.bold !== null) ? this.bold : this.style.bold;
	};
	
	this.isItalic = function () {
		return (this.italic !== null) ? this.italic : this.style.italic;
	};
	
	this.draw = function (location, canvas ) {
		// Draw only if there's a style for this field
		if (this.style) {
			console.log ("Style exists, type " + this.style.styleType);
			switch (this.style.styleType) {
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
				console.log ("Unknown field type " + this.style.styleType);
				break;
			}
		}

	};
	
	this.getPointSize = function () {
		return this.pointSize ? this.pointSize : this.style.pointSize;
	};
	
	this.getTypeface = function () {
		return this.typeface ? this.typeface : this.style.typeface;
	};
	
	this.getX = function () {
		// We want this not to get an error even if there is no style,
		// because the kendo data source is rather blind.
		if (this.offsetX !== null) {
			return this.offsetX;
		}
		else if (this.style && this.style.offsetX) {
			return this.style.offsetX;
		}
		else 
			return 0;
	};

	this.getY = function () {
		// We want this not to get an error even if there is no style,
		// because the kendo data source is rather blind.
		if (this.offsetY !== null) {
			return this.offsetY;
		}
		else if (this.style && this.style.offsetY) {
			return this.style.offsetY;
		}
		else 
			return 0;
	};
	
	this.getWidth = function () {
		// We want this not to get an error even if there is no style,
		// because the kendo data source is rather blind.
		if (this.width !== null) {
			return this.width;
		}
		else if (this.style && this.style.width) {
			return this.style.width;
		}
		else 
			return 0;
	};
	
	this.getHeight = function () {
		// We want this not to get an error even if there is no style,
		// because the kendo data source is rather blind.
		if (this.height !== null) {
			return this.height;
		}
		else if (this.style && this.style.height) {
			return this.style.height;
		}
		else 
			return 0;
	};
	
	this.getColor = function () {
		return (this.color !== null) ? this.color : this.style.color;
	};
	
	this.getPointSize = function () {
		return (this.pointSize !== null) ? this.pointSize : this.style.pointSize;
	};
	
	this.getAlignment = function () {
		return (this.alignment !== null) ? this.alignment : this.style.alignment;
	};
	
	this.isBold = function () {
		return (this.bold !== null) ? this.bold : this.style.bold;
	};

	this.isItalic = function () {
		return (this.italic !== null) ? this.italic : this.style.italic;
	};
	
	this.getSVG = function () {
		return (this.svg !== null) ? this.svg : this.style.svg;
	};
	
	this.getDropShadowH = function () {
		return (this.dropShadowH !== null) ? this.dropShadowH : this.style.dropShadowH;
	}

	this.getDropShadowV = function () {
		return (this.dropShadowV !== null) ? this.dropShadowV : this.style.dropShadowV;
	}
	
	this.getDropShadowBlur = function () {
		return (this.dropShadowBlur !== null) ? this.dropShadowBlur : this.style.dropShadowBlur;
	}



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
		if (dropShadowH != 0 || dropShadowV != 0) {
			text.shadow = this.getColor() + ' ' +
				this.getDropShadowH() + ' ' +
				this.getDropShadowV() + ' ' +
				this.getDropShadowBlur();
		}
		this.fabricObject = text;
		canvas.add(text);
		canvas.bringToFront(text);
	};
	
	this.fabricateImage = function  (canvas) {
		console.log ("fabricateImage");
		var pos = this.getPosition();
		var width = this.getWidth();
		var height = this.getHeight();
		var field = this;
		var img = fabric.Image.fromURL("ImageServlet?img=default", function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = pos.x;
			img.top = pos.y;
			img.originX = pos.originx;
			img.oritinY = pos.originy;
			img.width = width;
			img.height = height;
			field.fabricObject = img;
			canvas.add(img);
			canvas.bringToFront(img);
		});
	};
	
	this.fabricateLogo = function  (canvas) {
		console.log ("fabricateLogo");
		var pos = this.getPosition();
		var width = this.getWidth();
		var height = this.getHeight();
		var field = this;
		var img = fabric.Image.fromURL("ImageServlet?img=logo", function (img) {
			img.hasControls = false;
			img.selectable = false;
			img.left = pos.x;
			img.top = pos.y;
			img.originX = pos.originx;
			img.oritinY = pos.originy;
			img.width = width;
			img.height = height;
			field.fabricObject = img;
			canvas.add(img);
			canvas.bringToFront(img);
		});
	};
	
	this.fabricateBlock = function  (canvas) {
		var pos = this.getPosition();
		var wid = this.getWidth();
		var ht = this.getHeight();
		var color = this.getColor ();
		var rect = new fabric.Rect ({
			hasControls: false,
			selectable: false,
			width: wid,
			height: ht,
			left: pos.x,
			top: pos.y,
			originX: pos.originx,
			originY: pos.originy,
			fill: color
		});
		this.fabricObject = rect;
		canvas.add(rect);
		canvas.bringToFront(rect);
	};
	
	/* See http://fabricjs.com/fabric-intro-part-3/ */
	this.fabricateSVG = function  (canvas) {
		console.log ("fabricateSVG");
		var pos = this.getPosition();
		var wid = this.getWidth();
		var ht = this.getHeight();
		var svg = this.getSVG();
		console.log ("Style: " + this.style);
		console.log ("SVG data: " + svg);
		var field = this;
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
			canvas.bringToFront(obj);
			field.fabricObject = obj;
		});
	};

	/* Function for the x, y, and anchor calculations. Returns an object with 
	 * fields x, y, and anchor. */
	this.getPosition = function () {
		var anchor = this.anchor ? this.anchor : this.style.anchor;
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
}

/* The prototype constructor for a StyleSet. */
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
			this.styles[i] = new Style (jsonStyle.name, jsonStyle.styleType);
			this.styles[i].populateFromJSON (jsonStyle);
		}
	};
	
	// Find a Style that matches a ModelField, or null
	this.findApplicableStyle = function (modelField) {
		console.log ("findApplicableStyle, field name = " + modelField.name);
		var fieldName = modelField.name;
		for (var i = 0; i < this.styles.length; i++) {
			var style = this.styles[i];
			console.log ("Checking style " + style.fieldName);
			if (style.fieldName == fieldName) {
				console.log ("Found a style");
				return style;
			}
		}
		return null;
	};
}

/* The prototype constructor for one field in a Style. */
function Style (name, styleType) {
	this.name = name;
	// The name of the ModelField associated with the style
	this.fieldName = null;
	this.styleType = styleType;
	this.width = 0;
	this.height = 0;
	this.anchor = "TL";
	this.offsetX = 0;
	this.offsetY = 0;
	this.typeface = null;
	this.pointSize = null;
	this.bold = false;
	this.italic = false;
	this.alignment = "left";
	this.svg = null;
	this.dropShadowH = 0;
	this.dropShadowV = 0;
	this.dropShadowBlur = 0;
	
	// other fields vary by the styleType
	
	/* Populating from a JSON object is particularly easy in
	 * this case. */
	this.populateFromJSON = function (jsonObj) {
		this.name = jsonObj.name;
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
		this.svg = jsonObj.svg;
		this.dropShadowH = jsonObj.dropShadowH;
		this.dropShadowV = jsonObj.dropShadowV;
		this.dropShadowBlur = jsonObj.dropShadowBlur;
		
		if (this.svg !== null)
			console.log ("found svg value");
		if (this.styleType == "text") {
			this.typeface = jsonObj.typeface;
			this.pointSize = jsonObj.pointSize;
			this.text = jsonObj.text;
			this.defaultText = jsonObj.defaultText;
		}
		this.alignment = jsonObj.alignment;
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

		// Merge the styles into the Model
		for (var i = 0; i < this.model.fields.length; i++) {
			var field = this.model.fields[i];
			var style = styleSet.findApplicableStyle (field);
			if (style)
				field.style = style;
		}

	};

	this.applyStyleSet (styleSet);

	// Draw a Promotion. The argument is the ID of a canvas element.
	this.draw = function (location) {
		var canvasElem = $('#' + location);
		canvasElem.attr("width", this.styleSet.width);
		canvasElem.attr("height", this.styleSet.height);
		this.canvas = new fabric.Canvas (location);
		var fields = this.model.fields;
		for (var i = 0, len = fields.length; i < len; i++) {
			var field = fields[i];
			console.log ("Drawing a field named " + field.name);
			field.draw (location, this.canvas);
		}	
	};
	
	// Redraw a Promotion.
	this.redraw = function () {
		var fields = this.model.fields;
		for (var i = 0, len = fields.length; i < len; i++) {
			var field = fields[i];
			this.canvas.remove (field.fabricObject);
			field.fabricObject = null;
			field.draw (location, this.canvas);
		}	
	};
	

}