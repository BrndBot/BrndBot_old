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
		dataSource: new kendo.data.DataSource({data: benchcontent.modelToSourceData(bench.currentPromotion.model)}),
	    selectable: true,
        template: kendo.template($('#contentFieldsTemplate').html())
	});
	dest.show();
},


/* This function takes the promotion fields and extracts the
 * essential data as a JavaScript Array suitable for a Kendo data source.
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
			fielddata.ptsize = field.getPointSize().toString();
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

	benchcontent.highlightTextArea(tarea);
	
    function testForChange() {
    	var field = benchcontent.elemToLinkedField(tarea);
    	if (tarea.value != field.fabricObject.getText()) {
    		field.text = tarea.value;
    		field.fabricObject.setText(tarea.value);
    		bench.currentPromotion.canvas.renderAll();
    	}
    }

    tarea.onblur = function() {
        window.clearInterval(timer);
        testForChange();
        benchcontent.unhighlightTextArea (tarea);
        tarea.onblur = null;
    };

    var timer = window.setInterval(function() {
        testForChange();
    }, 50);
},

updatePrototypePointSize: function(tarea) {

	benchcontent.highlightTextArea(tarea);

	function testForChange() {
    	if (!isNaN (tarea.value)) {
    		var field = benchcontent.elemToLinkedField(tarea);
    		var newsize = Number(tarea.value);
    		if (newsize != field.getPointSize ()) {
    			field.fontSize = Number (tarea.value);
    			field.fabricObject.setFontSize(Number(field.fontSize));
    			bench.currentPromotion.canvas.renderAll();
    		}
    	}
    }

    /*  Changing the point size with every keystroke probably isn't a
     *  great idea, so we don't put this on timer. */
    tarea.onblur = function() {
        testForChange();
        benchcontent.unhighlightTextArea (tarea);
        tarea.onblur = null;
    };

},

updatePrototypeColor: function (input) {
	var field = benchcontent.elemToLinkedField(input);
//	var newcolor = input.value;
//	console.log("color = " + newcolor);
//	field.color = newcolor;
//	field.fabricObject.setFill(newcolor);
	var fieldid = $(input).attr("data-linkedfield");
	var buttondiv = $('#' + fieldid + "-select");
	buttondiv.toggle();
	bench.currentPromotion.canvas.renderAll();
},


/* This is called by an onchange event, so we already know there's a change */
updatePrototypeItalic: function (cbox) {
		
	var field = benchcontent.elemToLinkedField(cbox);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != field.isItalic()) {
   		field.italic = nowChecked;
   		field.fabricObject.setFontStyle (nowChecked ? "italic" : "normal");
		bench.currentPromotion.canvas.renderAll();
   	}
},

updatePrototypeBold: function (cbox) {
	
	var field = benchcontent.elemToLinkedField(cbox);
   	var nowChecked = $(cbox).prop('checked');
   	if (nowChecked != field.isBold()) {
   		field.bold = nowChecked;
   		field.fabricObject.setFontWeight (nowChecked ? "bold" : "normal");
		bench.currentPromotion.canvas.renderAll();
	}
},

updatePrototypeTypeface: function (sel) {
	var field = benchcontent.elemToLinkedField(sel);
   	var selected = $(sel).children().filter(":selected");
   	var typeface = selected.val();
   	console.log (typeface);
   	var currentTypeface = field.getTypeface();
   	if (typeface != currentTypeface) {
   		field.typeface = typeface;
   		field.fabricObject.setFontFamily (typeface);
		bench.currentPromotion.canvas.renderAll();
   	}
},

highlightTextArea: function (tarea) {
	var field = benchcontent.elemToLinkedField(tarea);
	field.fabricObject.setBackgroundColor('#C0C0C0');
	bench.currentPromotion.canvas.renderAll();
	
},

unhighlightTextArea: function(tarea) {
	var field = benchcontent.elemToLinkedField(tarea);
	field.fabricObject.setBackgroundColor('');
	bench.currentPromotion.canvas.renderAll();
},

/* For the a DOM element which has the data-linkedfield attribute,
 * return the linked field.
 */
elemToLinkedField: function (elem) {
	var target = $(elem).attr("data-linkedfield");
   	return bench.currentPromotion.model.findFieldByName (target);
}
};