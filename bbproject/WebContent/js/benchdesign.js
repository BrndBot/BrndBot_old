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
 * This populates the content pane of the editor.
 */

var benchdesign = {
/** Insert the fields in the content tab needed to edit a promotion. 
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
insertEditFields: function (dest) {
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: benchdesign.modelToSourceData(currentPromotion.model)}),
	    selectable: true,
        template: kendo.template($('#editFieldsTemplate').html())
	});
	dest.show();
},

/* This function takes the promotion fields and extracts the
 * essential data as a Java Array suitable for a Kendo data source.
 * 
 */

modelToSourceData: function (model) {
	var srcdata = [];
	for (var i = 0; i < model.fields.length; i++) {
		var field = model.fields[i];
		var fielddata = {};
		fielddata.fieldid = field.name;
		//TODO fill in geometric data for every field
		srcdata.push(fielddata);
	}
	return srcdata;
},

updateXPos: function(tarea) {

    function testForChange() {
    	if (!isNaN (tarea.value)) {
        	var target = $(tarea).attr("data-linkedfield");
        	var field = currentPromotion.model.findFieldByName (target);
    		var newpos = Number(tarea.value);
    		if (newpos != field.getX ()) {
    			field.offsetX = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setLeft(Number(field.fontSize));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    }

    /*  Changing the point size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},


updateYPos: function(tarea) {

    function testForChange() {
    	if (!isNaN (tarea.value)) {
        	var target = $(tarea).attr("data-linkedfield");
        	var field = currentPromotion.model.findFieldByName (target);
    		var newpos = Number(tarea.value);
    		if (newpos != field.getY ()) {
    			field.offsetY = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setTop(Number(field.fontSize));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    }

    /*  Changing the point size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateWidth: function(tarea) {

    function testForChange() {
    	if (!isNaN (tarea.value)) {
        	var target = $(tarea).attr("data-linkedfield");
        	var field = currentPromotion.model.findFieldByName (target);
    		var newwid = Number(tarea.value);
    		if (newwid != field.getWidth ()) {
    			field.width = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setWidth(Number(field.width));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    }

    /*  Changing the point size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateHeight: function(tarea) {

    function testForChange() {
    	if (!isNaN (tarea.value)) {
        	var target = $(tarea).attr("data-linkedfield");
        	var field = currentPromotion.model.findFieldByName (target);
    		var newht = Number(tarea.value);
    		if (newht != field.getHeight ()) {
    			field.height = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setHeight(Number(field.height));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    }

    /*  Changing the point size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},
}