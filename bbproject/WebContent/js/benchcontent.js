/**
 * benchcontent.js
 * 
 * This populates the content pane of the editor.
 */

var benchcontent = {
/** Insert the fields needed to edit a promotion. 
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
insertEditFields: function (dest) {
//	src.children().each ( function (idx) {
//		var dispDiv = $(this);
//		if (dispDiv.hasClass("prmf_text")) {
//			dest.append ("<div>Text field</div>");
//			console.log ("Text div");
//		}
//		else if (dispDiv.hasClass("prmf_image")) {
//			dest.append ("<div>Image field</div>");
//			console.log ("Image div");
//		}
//		else if (dispDiv.hasClass("prmf_logo")) {
//			dest.append ("<div>Logo field</div>");
//			console.log ("Logo div");
//		}
//		else if (dispDiv.hasClass("prmf_svg")) {
//			dest.append ("<div>SVG field</div>");
//			console.log ("SVG div");
//		}
//		else if (dispDiv.hasClass("prmf_block")) {
//			dest.append ("<div>Block field</div>");
//			console.log ("Block div");
//		}
//		else if (dispDiv.hasClass("prmf_button")) {
//			dest.append ("<div>Button field</div>");
//			console.log ("Button div");
//		}
//	});
	var fields = currentPromotion.model.fields;
	for (var i = 0; i < fields.length; i++) {
		var field = fields[i];
		switch (field.styleType) {
		case "text":
			dest.append ("<div>Text field</div>");
			break;
		case "image":
			dest.append ("<div>Image field</div>");
			break;
		case "logo":
			dest.append ("<div>Logo field</div>");
			break;
		case "svg":
			dest.append ("<div>SVG field</div>");
			break;
		case "block":
			dest.append ("<div>Block field</div>");
			break;
		default:
			console.log ("Unknown styleType " + field.styleType);
			break;
		}
		// Updated to fabric -- but where are this divs actually used?
	}
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: fields}),
	    selectable: true,
        template: kendo.template($('#editFieldsTemplate').html())
	});
	dest.show();
},

/* This function takes the promotion fields and extracts the
 * essential data as a Java Array suitable for a Kendo data source.
 * 
 * Don't think we need this any more. The promotion fields themselves
 * should be sufficient.
 */
prototypeToSourceData: function (src) {
	var srcdata = [];
//	src.children(".prmf_text").each ( function (idx) {
//		var fielddata = {};
//		// Extract the needed item from one div (field)
//		var dispDiv = $(this);
//		fielddata.clazz = dispDiv.attr("class");
//		fielddata.content = dispDiv.text();
//		fielddata.fieldid = dispDiv.attr("id");
//		srcdata.push (fielddata);
//	});
	var fields = drawpromo.currentPromotion.fields;
	for (var i = 0; i = fields.length; i++) {
		var field = fields[i];
		// Whatever. Think we don't need this any more.
	}
	return srcdata;
},


/* These functions poll the
specified textarea while it has focus and apply the changes.
Need to somehow link the textarea, which is a DOM thingy,
to the ModelField. If we set the attribute data-linkedfield
to the field name, we should have what we need.  */
updatePrototypeText: function(tarea) {
    var initialValue = tarea.value;

    function testForChange() {
        if (tarea.value != initialValue) {
        	var target = $(tarea).attr("data-linkedfield");
        	//$(target).text(tarea.value);
        	// TODO need to tie this to the promo field, update in fabric
        	
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
}

}