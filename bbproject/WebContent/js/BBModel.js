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
 *  BBStyle.js
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
		// propagate availableImages only if the styleset doesn't have any
		if (!styleSet.availableImages || styleSet.availableImages.length === 0)
			styleSet.assignAvailableImages (this.availableImages);

		styleSet.attachToModel (model);
		
	};

	this.applyStyleSet (styleSet);

	/* Assign an array of image objects, and propagate it to all
	 * the styles. Each element has the properties id, width, height.
	 */
	this.assignAvailableImages = function (imgs) {
		this.availableImages = imgs;
		styleSet.assignAvailableImages (imgs);
	};
	
	this.assignAvailableImages ([]);
	
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
	
	/* Redrawz a Promotion. */
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
