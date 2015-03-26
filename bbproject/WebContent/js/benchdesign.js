/**
 * benchdesign.js
 * 
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
 * 
 * Gary McGath
 * February 9, 2015
 *
 * This populates the design pane of the editor.
 */

var benchdesign = {
/** Insert the fields in the content tab needed to edit a promotion. 
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
insertEditFields: function (dest) {
		
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: benchdesign.styleToSourceData(bench.currentPromotion.styleSet)}),
	    selectable: true,
        template: kendo.template($('#designFieldsTemplate').html())
	});
	//dest.show();
},

/* This function takes the promotion fields and extracts the
 * essential data as a JavaScript Array suitable for a Kendo data source.
 * 
 */

styleToSourceData: function (styleSet) {
	var srcdata = [];
	for (var i = 0; i < styleSet.styles.length; i++) {
		var style = styleSet.styles[i];
		var field = style.modelField;
		if (!field)
			continue;
		var fielddata = {};
		fielddata.fieldid = i.toString();
		fielddata.fieldname = field.name;
		fielddata.x = style.getX().toString();
		fielddata.y = style.getY().toString();
		fielddata.styleType = style.styleType;
		fielddata.width = style.getWidth().toString();
		fielddata.height = style.getHeight().toString();
		srcdata.push(fielddata);
	}
	return srcdata;
},

updateXPos: function(tarea) {

    function testForChange() {
    	var style = benchdesign.elemToLinkedStyle($(tarea));
    	if (!isNaN (tarea.value)) {
    		var newpos = Number(tarea.value);
    		if (newpos != style.getX ()) {
    			style.setLocalX(Number (tarea.value));
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setLeft(Number(style.getX()));
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getX().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},


updateYPos: function(tarea) {

    function testForChange() {
    	var style = benchdesign.elemToLinkedStyle($(tarea));
    	if (!isNaN (tarea.value)) {
    		var newpos = Number(tarea.value);
    		if (newpos != style.getY ()) {
    			style.setLocalY(Number (tarea.value));
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setTop(Number(style.getY()));
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getY().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateWidth: function(tarea) {

    function testForChange() {
    	var style = benchdesign.elemToLinkedStyle($(tarea));
    	if (!isNaN (tarea.value)) {
    		var newwid = Number(tarea.value);
    		if (newwid != style.getWidth ()) {
    			style.setLocalWidth(Number (tarea.value));
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setWidth(Number(style.getWidth()));
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getWidth().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateHeight: function(tarea) {

    function testForChange() {
    	var style = benchdesign.elemToLinkedStyle($(tarea));
//    	var field = bench.currentPromotion.model.findFieldByName (target);
    	if (!isNaN (tarea.value)) {
    		var newht = Number(tarea.value);
    		if (newht != style.getHeight ()) {
    			style.setLocalHeight(Number (tarea.value));
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			style.fabricObject.setHeight(Number(style.getHeight()));
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (style.getHeight().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

/* Callback function to complete setting the color from a
 * ColorSelector */
setColor: function (style, color ) {
	style.setLocalColor (color);
	style.fabricObject.fill = color;
},

elemToLinkedStyle: function (elem) {
	var target = $(elem).attr("data-linkedfield");
   	return bench.currentPromotion.styleSet.styles[parseInt(target, 10)];
}
};