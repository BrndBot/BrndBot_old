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
	
	console.log($('#designFieldsTemplate').html());
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: benchdesign.modelToSourceData(currentPromotion.model)}),
	    selectable: true,
        template: kendo.template($('#designFieldsTemplate').html())
	});
	//dest.show();
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
		fielddata.x = field.getX().toString();
		fielddata.y = field.getY().toString();
		fielddata.width = field.getWidth().toString();
		fielddata.height = field.getHeight().toString();
		srcdata.push(fielddata);
	}
	return srcdata;
},

updateXPos: function(tarea) {

    function testForChange() {
    	var target = $(tarea).attr("data-linkedfield");
    	var field = currentPromotion.model.findFieldByName (target);
    	if (!isNaN (tarea.value)) {
    		var newpos = Number(tarea.value);
    		if (newpos != field.getX ()) {
    			field.offsetX = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setLeft(Number(field.offsetX));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (field.getX().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},


updateYPos: function(tarea) {

    function testForChange() {
    	var target = $(tarea).attr("data-linkedfield");
    	var field = currentPromotion.model.findFieldByName (target);
    	if (!isNaN (tarea.value)) {
    		var newpos = Number(tarea.value);
    		if (newpos != field.getY ()) {
    			field.offsetY = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setTop(Number(field.offsetY));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (field.getY().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateWidth: function(tarea) {

    function testForChange() {
    	var target = $(tarea).attr("data-linkedfield");
    	var field = currentPromotion.model.findFieldByName (target);
    	if (!isNaN (tarea.value)) {
    		var newwid = Number(tarea.value);
    		if (newwid != field.getWidth ()) {
    			field.width = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setWidth(Number(field.width));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (field.getWidth().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},

updateHeight: function(tarea) {

    function testForChange() {
    	var target = $(tarea).attr("data-linkedfield");
    	var field = currentPromotion.model.findFieldByName (target);
    	if (!isNaN (tarea.value)) {
    		var newht = Number(tarea.value);
    		if (newht != field.getHeight ()) {
    			field.height = Number (tarea.value);
    			//TODO this assumes a top left anchor. Need to adjust for the various cases.
    			field.fabricObject.setHeight(Number(field.height));
    			currentPromotion.canvas.renderAll();
    		}
    	}
    	else {
    		// Restore to last good value
    		$(tarea).val (field.getHeight().toString());
    	}
    }

    tarea.onblur = function() {
        testForChange();
        tarea.onblur = null;
    };

},
}