/**
 *  The functions to draw a promotion.
 *  For now, just enough to draw a block as proof of concept.
 *  
 *  Requires fabric.js
 */

/* Takes an ObservableArray and draws it to a specified div.
 * location is a DOM (not JQuery object). */
function drawPromotion (promotionArray, location) {
	var canvas = new fabric.canvas (location);
	// Where do I set its size?
	// Does Kendo add an array level?
	for (var i = 0, len = promotionArray.length; i < len; i++) {
		var elem = promotionArray[i];
		if (elem.name == "field") {
			console.log ("Got a field");
			var field = elem.field;
			//var fieldType = field.type;
			switch (field.type) {
			case "text":
				fabricateText (field);
				break;
			case "image":
				fabricateImage (field);
				break;
			case "logo":
				fabricateLogo (field);
				break;
			case "block":
				fabricateBlock (field);
				break;
			case "svg":
				fabricateSVG (field);
				break;
			}
		}
	}	
}

function fabricateText (field) {
	var font = field.font();
}

function fabricateImage (field) {
}

function fabricateLogo (field) {
}

function fabricateBlock (field) {
	var x = field.offset.x;
	var y = field.offset.y;
	var wid = field.dimensions.x;
	var ht = field.dimensions.y;
	var rect = new fabric.Rect ({
		width: wid,
		height: ht,
		left: x,
		top: y
	});
	canvas.add(rect);
}

function fabricateSVG (field) {
}

