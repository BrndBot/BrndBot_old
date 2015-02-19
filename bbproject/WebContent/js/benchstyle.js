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
	
	console.log($('#styleFieldsTemplate').html());
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: styleSets}),
	    selectable: true,
        template: kendo.template($('#styleFieldsTemplate').html())
	});
},

/** Update the promotion style to the one given in the list item */
updateStyle: function (litem) {
	var styleSet = benchstyle.elemToLinkedStyleSet (litem);
	if (styleSet !== null) {
		console.log ("can switch to style set" + styleSet.name);
		currentPromotion.applyStyleSet (styleSet);
		currentPromotion.redraw();
	}
},

/** Find the styleset linked to by the element */
elemToLinkedStyleSet: function (elem) {
	var target = $(elem).attr("data-linkedstyle");
   	for (var i = 0; i < styleSets.length; i++) {
   		var styleSet = styleSets[i];
   		if (styleSet.name == target)
   			return styleSet;
   	}
   	return null;
}


};