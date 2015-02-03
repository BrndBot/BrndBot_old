/**
 *  The functions to draw a promotion.
 *  For now, just enough to draw a block as proof of concept.
 *  
 *  Requires fabric.js
 */

/* Takes an ObservableArray and draws it to a specified div.
 * location is a DOM (not JQuery object). */
function drawPromotion (promotion, location) {
	console.log ("drawPromotion");
	var canvas = new fabric.Canvas (location);
	// Where do I set its size?
	// Does Kendo add an array level?
	for (var i = 0, len = promotion.fields.length; i < len; i++) {
		var field = promotion.fields[i];
		console.log ("Got a field, its name is " + field.name);
		// Draw only if there's a style for this field
		if (field.style) {
			switch (field.style.styleType) {
			case "text":
				fabricateText (field, canvas);
				break;
			case "image":
				fabricateImage (field, canvas);
				break;
			case "logo":
				fabricateLogo (field, canvas);
				break;
			case "block":
				fabricateBlock (field, canvas);
				break;
			case "svg":
				fabricateSVG (field, canvas);
				break;
			default:
				console.log ("Unknown field type " + field.styleType);
				break;
			}
		}
	}	
}

function fabricateText (field, canvas) {
	var font = field.font;
	var x = field.offsetX;
	var y = field.offsetY;
	var wid = field.width;
	var ht = field.height;
	var content = field.text;
	var text = new fabric.Text(content, {
		fontSize: 12,		// just to get something
		left: x,
		top: y
	});
	canvas.add(text);
}

function fabricateImage (field, canvas) {
}

function fabricateLogo (field, canvas) {
}

function fabricateBlock (field, canvas) {
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

function fabricateSVG (field, canvas) {
}

