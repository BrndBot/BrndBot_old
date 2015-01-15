/**
 * benchcontent.js
 * 
 * This populates the content pane of the editor.
 */

/** Insert the fields needed to edit a promotion. 
 *  src  JQuery selector for the div of the promotion being
 *       edited
 *  src  JQuery selector for the div into which the fields
 *       will be inserted
 */
function insertEditFields (src, dest) {
	console.log ("No. of fields: " + src.children().length);
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
	
	dest.kendoListView({
		dataSource: new kendo.data.DataSource({data: prototypeToSourceData(src)}),
	    selectable: true,
        template: kendo.template($('#editFieldsTemplate').html())
	});
	dest.show();
}

/* This function takes the div in the prototype display and extracts the
 * essential data as a Java Array suitable for a Kendo data source.
 */
function prototypeToSourceData (src) {
	var srcdata = [];
	src.children(".prmf_text").each ( function (idx) {
		var fielddata = {};
		// Extract the needed item from one div (field)
		var dispDiv = $(this);
		fielddata.clazz = dispDiv.attr("class");
		fielddata.content = dispDiv.text();
		fielddata.fieldid = dispDiv.attr("id");
		srcdata.push (fielddata);
	});
	return srcdata;
}

/* The onchange handler for the text area. Copies the text back to
   the div with the specified ID. */
//function updatePrototypeText(tarea) {
//	var target = '#' + $(tarea).attr("data-linkedfield");
//	console.log(target);
//	$(target).text(tarea.value);
//}

/* Handlers on keypress don't work very well. These functions poll the
specified textarea while it has focus and apply the changes. */
function updatePrototypeText(tarea) {
    var initialValue = tarea.value;

    function testForChange() {
        if (tarea.value != initialValue) {
        	var target = '#' + $(tarea).attr("data-linkedfield");
        	$(target).text(tarea.value);
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
};