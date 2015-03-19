/**
 *  Functions for applying styles to promotions.
 *  All the functions are packaged in the applyStyle object.
 *  
 *  DOES ANYONE USE THIS??
 */

var applyStyle = {

	
	/** Apply the default style to the specified promotion. For the
	   moment, use the first style in styles. */
	applyDefaultStyle: function (promotion) {
		console.log ("applyDefaultStyle");
		var defaultStyleSet = bench.styleSets[0];		// TODO find the one named "default" by preference
		console.log ("Applying styleset " + defaultStyleSet.name);
		applyStyle.applyStyleSetToPromotion (defaultStyleSet, promotion);
	},
	
	/** Augment a promotion by applying a styleset to it.
	 */
	applyStyleSetToPromotion: function  (ss, promotion) {
		console.log ("applyStyleSetToPromotion, no. of fields = " + promotion.fields.length);
		console.log (ss);
		for (var i = 0, len = promotion.fields.length; i < len; i++) {
			var field = promotion.fields[i];
			var style = applyStyle.findApplicableStyle (field, ss);
			if (style !== null) {
				console.log ("Applying style");
				field.style = style;
			}
		}
		ss.attachToModel(promotion.model);
	},
	
	/** Find a style whose name matches a promotion field */
	findApplicableStyle: function (field, styleSet) {
		console.log ("findApplicableStyle, field name = " + field.name);
		var fieldName = field.name;
		for (var i = 0; i < styleSet.styles.length; i++) {
			var style = styleSet.styles[i];
			console.log ("Checking style " + style.fieldName);
			if (style.fieldName == fieldName) {
				console.log ("Found a style");
				return style;
			}
		}
		return null;
	},
	
	/** Apply fields which are common to all styles. */
	applyCommon: function  (field, style) {
		field.width = style.width;
		field.height = style.height;
		field.offsetX = style.offsetX;
		field.offsetY = style.offsetY;
		field.anchor = style.anchor;
	},
	
	/* Stubs for later expansion */
	applyTextStyle: function  (field, style) {
		console.log("applyTextStyle");
	},
	
	applyImageStyle: function  (field, style) {
	},
	
	applyLogoStyle: function  (field, style) {
	},
	

	applyBlockStyle: function  (field, style) {
	},
	

	applySVGStyle: function  (field, style) {
	}
	

};