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
		
	// Fixed height for promotion canvas
	MAX_STYLE_DIM: 120,
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
		var scaleRatio = 1.0;
//		if (styleSet.width > benchstyle.MAX_STYLE_DIM || styleSet.height > benchstyle.MAX_STYLE_DIM) {
//			scaleRatio = Math.min (benchstyle.MAX_STYLE_DIM / styleSet.width,
//								benchstyle.MAX_STYLE_DIM / styleSet.height);
//		}
		scaleRatio = benchstyle.MAX_STYLE_DIM / styleSet.height;
		var canvasWidth = "" + Math.floor(styleSet.width * scaleRatio) + "px"
		canvas.css("width", canvasWidth);
		var canvasHeight = "" + benchstyle.MAX_STYLE_DIM + "px";
		canvas.css("height", canvasHeight);
	});
},

/** Update the promotion style to the one given in the list item
 *  that was just clicked. */
updateStyle: function (litem) {
	//var oldStyleSet = bench.currentPromotion.styleSet;
	//oldStyleSet.killFabricObjects();
	bench.currentPromotion.wipeClean();
	
	var styleSet = benchstyle.elemToLinkedStyleSet (litem);
	if (styleSet !== null) {
		styleSet = styleSet.copyForDisplay();		// ** will this help things?
		$('#contentArea').empty();
		bench.currentPromotion.applyStyleSet (styleSet);
		benchcontent.insertEditFields ( $('#contentArea'));
		//benchdesign.insertEditFields ( $('#designArea'));
		//bench.currentPromotion.redraw('finishedImage1');
		bench.currentPromotion.draw('finishedImage1');
		var canvas = $('#finishedImage1');
		var canvasWidth;
		var canvasHeight;
		if (styleSet.width > bench.MAX_PROMOTION_DIM || styleSet.height > bench.MAX_PROMOTION_DIM) {
			var scaleRatio = Math.min (bench.MAX_PROMOTION_DIM / styleSet.width,
					bench.MAX_PROMOTION_DIM / styleSet.height);
			canvasWidth = "" + Math.floor(styleSet.width * scaleRatio) + "px";
			canvasHeight = "" + Math.floor(styleSet.height * scaleRatio) + "px";
		}	
		else {
			canvasWidth = "" + Math.floor(styleSet.width) + "px";
			canvasHeight = "" + Math.floor(styleSet.height) + "px";
		}
		canvas.css("width", canvasWidth);
		canvas.css("height", canvasHeight);
		$('#finishedImage').css("width", canvasWidth);
		$('#finishedImage').css("height", canvasHeight);
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
	for (var i = 0; i < len; i++) {
		var styleSet = styleSets[i];
		console.log ("Adding style set " + styleSet.name);
		var newItem = {name: styleSet.name};
		retval.push (newItem);
	}
	console.log ("Returning " + retval);
	return retval;
}


};