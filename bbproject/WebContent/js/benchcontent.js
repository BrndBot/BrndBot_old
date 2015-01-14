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
	src.children().each ( function (idx) {
		var dispDiv = $(this);
		if (dispDiv.hasClass("prmf_text")) {
			console.log ("Text div");
		}
		else if (dispDiv.hasClass("prmf_image")) {
			console.log ("Image div");
		}
		else if (dispDiv.hasClass("prmf_logo")) {
			console.log ("Logo div");
		}
		else if (dispDiv.hasClass("prmf_svg")) {
			console.log ("SVG div");
		}
		else if (dispDiv.hasClass("prmf_block")) {
			console.log ("Block div");
		}
		else if (dispDiv.hasClass("prmf_button")) {
			console.log ("Button div");
		}
	});
}