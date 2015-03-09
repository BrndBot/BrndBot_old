/**
 * benchstyle.js
 * 
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports listing and selection of styles
 * in the editor.
 * 
 * Gary McGath
 * February 11, 2015
 *
 * This populates the style pane of the editor.
 */

var benchstyle = {
/** Insert the fields in the content tab needed to edit a promotion. 
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
insertStyles: function (dest) {
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: benchstyle.styleSetsToDataSource(bench.styleSets)}),
	    selectable: true,
        template: kendo.template($('#styleFieldsTemplate').html())
	});
	var listItems = dest.find ("li");
	listItems.each (function () {
		var styleSet = benchstyle.elemToLinkedStyleSet (this);
		var cloneStyleSet = styleSet.copyForDisplay();
		cloneStyleSet.attachToModel(bench.currentPromotion.model);
		var promo = new Promotion (bench.currentPromotion.model, cloneStyleSet);
		var canvas = $(this).find("canvas");
		promo.draw (canvas.attr("id"));
		canvas.css("width", 135);
		canvas.css("height", 135);
	});
},

/** Update the promotion style to the one given in the list item
 *  that was just clicked. */
updateStyle: function (litem) {
	var styleSet = benchstyle.elemToLinkedStyleSet (litem);
	if (styleSet !== null) {
		console.log ("can switch to style set" + styleSet.name);
		bench.currentPromotion.applyStyleSet (styleSet);
		bench.currentPromotion.redraw();
	}
},

/** Find the styleset linked to by the element */
elemToLinkedStyleSet: function (elem) {
	var target = $(elem).attr("data-linkedstyle");
   	for (var i = 0; i < bench.styleSets.length; i++) {
   		var styleSet = bench.styleSets[i];
   		if (styleSet.name == target)
   			return styleSet;
   	}
   	return null;
},

/** Convert the StyleSet array into a simple data source. */
styleSetsToDataSource: function (styleSets) {
	console.log ("styleSetsToDataSource");
	var retval = [];
	var len = styleSets.length;
	for (i = 0; i < len; i++) {
		var styleSet = styleSets[i];
		console.log ("Adding style set " + styleSet.name);
		var newItem = {name: styleSet.name};
		retval.push (newItem);
	}
	console.log ("Returning " + retval);
	return retval;
}


};