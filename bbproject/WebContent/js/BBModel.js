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

	this.fabricateText = function (canvas) {
		console.log ("fabricateText");
		var typeface = this.typeface ? this.typeface : this.style.typeface;
		var x = this.offsetX ? this.offsetX : this.style.offsetX;
		var y = this.offsetY ? this.offsetY : this.style.offsetY;
		var wid = this.width ? this.width : this.style.width;
		var ht = this.height ? this.height : this.style.height;
		var anchor = this.anchor ? this.anchor : this.style.anchor;
		var typeface = this.typeface ? this.typeface : this.style.typeface;
		var originx = "left";
		console.log ("Anchor = " + anchor);
		if (anchor == "TR" || anchor == "BR") {
			originx = "right";
			x = -x;
		}
		var originy = "top";
		if (anchor == "BR" || anchor == "BL") {
			originy = "bottom";
			y = -y;
		}
		var content = this.getText();
		
		var text = new fabric.Text(content, {
			fontSize: 12,		// just to get something
			left: x,
			top: y,
			width: wid,
			height: ht
		});
		fabricObject = text;
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
		fabricObject = rect;
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
	
	// other fields vary by the styleType
	
	/* Populating from a JSON object is particularly easy in
	 * this case. */
	this.populateFromJSON = function (jsonObj) {
		this.name = jsonObj.name;
		this.fieldName = jsonObj.fieldName;
		this.styleType = jsonObj.styleType;
		this.width = jsonObj.width;
		this.height = jsonObj.height;
		this.anchor = jsonObj.anchor;
		this.offsetX = jsonObj.offsetX;
		this.offsetY = jsonObj.offsetY;
		this.color = jsonObj.color;
		if (this.styleType == "text") {
			this.typeface = jsonObj.typeface;
			this.text = jsonObj.text;
			this.defaultText = jsonObj.defaultText;
		}
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
	this.styleSet = styleSet;
	
	// Merge the styles into the Model
	for (var i = 0; i < model.fields.length; i++) {
		var field = model.fields[i];
		var style = styleSet.findApplicableStyle (field);
		if (style)
			field.style = style;
	}
	
	// Draw a Promotion. The argument is the ID of a canvas element.
	this.draw = function (location) {
		var canvasElem = $('#' + location);
		canvasElem.attr("width", this.styleSet.width);
		canvasElem.attr("height", this.styleSet.height);
		var canvas = new fabric.Canvas (location);
		var fields = this.model.fields;
		for (var i = 0, len = fields.length; i < len; i++) {
			var field = fields[i];
			console.log ("Drawing a field named " + field.name);
			field.draw (location, canvas);
		}	
	};
	

}