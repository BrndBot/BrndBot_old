/**
 * benchcontent.js
 * 
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
 * 
 * Gary McGath
 * February 5, 2015
 *
 * This populates the content pane of the editor.
 */

var benchcontent = {
/** Insert the fields in the content tab needed to edit a promotion. 
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
insertEditFields: function (dest) {
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: benchcontent.modelToSourceData(currentPromotion.model)}),
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
		if (field.styleType == "text") {
			// Text content ModelField
			var fielddata = {};
			fielddata.fieldid = field.name;
			fielddata.content = field.getText();
			fielddata.ptsize = field.getFontSize().toString();
			fielddata.styleType = "text";
			fielddata.italicChecked = field.isItalic() ? "checked" : "";
			fielddata.boldChecked = field.isBold() ? "checked" : "";
			srcdata.push(fielddata);
		}
	}
	return srcdata;
},



/* These functions poll the
specified textarea while it has focus and apply the changes.
Need to somehow link the textarea, which is a DOM thingy,
to the ModelField. If we set the attribute data-linkedfield
to the field name, we should have what we need.  */
updatePrototypeText: function(tarea) {

    function testForChange() {
		var target = $(tarea).attr("data-linkedfield");
		var field = currentPromotion.model.findFieldByName (target);
    	if (tarea.value != field.fabricObject.getText()) {
    		field.text = tarea.value;
    		field.fabricObject.setText(tarea.value);
    		currentPromotion.canvas.renderAll();
    	}
    }

    tarea.onblur = function() {
        window.clearInterval(timer);
        testForChange();
        tarea.onblur = null;
    };

    var timer = window.setInterval(function() {
        testForChange();
    }, 50);
},

updatePrototypePointSize: function(tarea) {

    function testForChange() {
    	if (!isNaN (tarea.value)) {
        	var target = $(tarea).attr("data-linkedfield");
        	var field = currentPromotion.model.findFieldByName (target);
    		var newsize = Number(tarea.value);
    		if (newsize != field.getFontSize ()) {
    			field.fontSize = Number (tarea.value);
    			field.fabricObject.setFontSize(Number(field.fontSize));
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

/* This is called by an onchange event, so we already know there's a change */
updatePrototypeItalic: function (cbox) {
		
   	var target = $(cbox).attr("data-linkedfield");
   	var field = currentPromotion.model.findFieldByName (target);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != field.isItalic()) {
   		field.italic = nowChecked;
   		field.fabricObject.setFontStyle (nowChecked ? "italic" : "normal");
		currentPromotion.canvas.renderAll();
   	}
},

updatePrototypeBold: function (cbox) {
	
   	var target = $(cbox).attr("data-linkedfield");
   	var field = currentPromotion.model.findFieldByName (target);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != field.isBold()) {
   		field.bold = nowChecked;
   		field.fabricObject.setFontWeight (nowChecked ? "bold" : "normal");
		currentPromotion.canvas.renderAll();
	}
},

updatePrototypeTypeface: function (sel) {
   	var target = $(sel).attr("data-linkedfield");
   	var field = currentPromotion.model.findFieldByName (target);
   	var selected = $(sel).children().filter(":selected");
   	var typeface = selected.val();
   	console.log (typeface);
   	var currentTypeface = field.getTypeface();
   	if (typeface != currentTypeface) {
   		field.typeface = typeface;
   		field.fabricObject.setFontFamily (typeface);
		currentPromotion.canvas.renderAll();
   	}
}


}