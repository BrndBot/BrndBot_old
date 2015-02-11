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
	}
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
	
	this.populateFromJSON = function (jsonField) {
		this.name = jsonField.name;
		this.styleType = jsonField.styleType;
	}
	
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
	}
	
	this.isBold = function () {
		return (this.bold !== null) ? this.bold : this.style.bold;
	}
	
	this.isItalic = function () {
		return (this.italic !== null) ? this.italic : this.style.italic;
	}
	
	this.draw = function (location, canvas ) {
		// Draw only if there's a style for this field
		if (this.style) {
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

	}
	
	this.getPointSize = function () {
		return this.pointSize ? this.pointSize : this.style.pointSize;
	}
	
	this.getTypeface = function () {
		return this.typeface ? this.typeface : this.style.typeface;
	}
	
	this.getX = function () {
		return (this.offsetX !== null) ? this.offsetX : this.style.offsetX;
	}

	this.getY = function () {
		return (this.offsetY !== null) ? this.offsetY : this.style.offsetY;
	}
	
	this.getWidth = function () {
		return (this.width !== null) ? this.width : this.style.width;
	}
	
	this.getHeight = function () {
		return (this.height !== null) ? this.height : this.style.height;
	}
	
	this.getPointSize = function () {
		return (this.pointSize !== null) ? this.pointSize : this.style.pointSize;
	}
	
	this.getAlignment = function () {
		return (this.alignment !== null) ? this.alignment : this.style.alignment;
	}
	
	this.isBold = function () {
		return (this.bold !== null) ? this.bold : this.style.bold;
	}

	this.isItalic = function () {
		return (this.italic !== null) ? this.italic : this.style.italic;
	}

	this.fabricateText = function (canvas) {
		console.log ("fabricateText");
		var anchor = this.anchor ? this.anchor : this.style.anchor;
		var originx = "left";
		console.log ("Anchor = " + anchor);
		var x = this.getX();
		if (anchor == "TR" || anchor == "BR") {
			originx = "right";
			x = -x;
		}
		var originy = "top";
		var y = this.getY();
		if (anchor == "BR" || anchor == "BL") {
			originy = "bottom";
			y = -y;
		}
		var weight = this.isBold() ? "bold" : "normal";
		var fstyle = this.isItalic() ? "italic" : "normal";
		
		var text = new fabric.Text(this.getText(), {
			pointSize: this.getPointSize(),
			left: x,
			top: y,
			width: this.getWidth(),
			height: this.getHeight(),
			fontWeight: weight,
			fontStyle: fstyle,
			alignment: this.getAlignment()
		});
		this.fabricObject = text;
		canvas.add(text);
	};
	
	this.fabricateImage = function  (canvas) {
	};
	
	this.fabricateLogo = function  (canvas) {
	};
	
	this.fabricateBlock = function  (canvas) {
		var x = this.offsetX ? this.offsetX : this.style.offsetX;
		var y = this.offsetY ? this.offsetY : this.style.offsetY;
		var wid = this.width ? this.width : this.style.width;
		var ht = this.height ? this.height : this.style.height;
		var color = this.color ? this.color : this.style.color;
		var rect = new fabric.Rect ({
			width: wid,
			height: ht,
			left: x,
			top: y,
			fill: color
		});
		this.fabricObject = rect;
		canvas.add(rect);
	};
	
	this.fabricateSVG = function  (field, canvas) {
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
	}
	

}