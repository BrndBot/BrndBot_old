/**
 *  The functions to draw a promotion.
 *  For now, just enough to draw a block as proof of concept.
 *  
 *  DEPRECATED -- keep for a bit in case it's useful for reference.
 *  
 *  Requires fabric.js
 */

var drawPromo = {
		
currentPromotion: null,
		
/* Takes an ObservableArray and draws it to a specified div.
 * location is a DOM (not JQuery object). Fabric drawing objects
 * are added to the field as field.fabricObject so they
 * can be updated. */
drawPromotion: function (promotion, location) {
	console.log ("drawPromotion");
	drawPromo.currentPromotion = promotion;
	
	var canvas = new fabric.Canvas (location);
	// Where do I set its size?
	// Does Kendo add an array level?
	for (var i = 0, len = promotion.fields.length; i < len; i++) {
		var field = promotion.fields[i];
		console.log ("Got a field, its name is " + field.name);
		// Draw only if there's a style for this field
		if (field.style) {
			console.log ("Its style is " + field.style.styleType);
			switch (field.style.styleType) {
			case "text":
				drawPromo.fabricateText (field, canvas);
				break;
			case "image":
				drawPromo.fabricateImage (field, canvas);
				break;
			case "logo":
				drawPromo.fabricateLogo (field, canvas);
				break;
			case "block":
				drawPromo.fabricateBlock (field, canvas);
				break;
			case "svg":
				drawPromo.fabricateSVG (field, canvas);
				break;
			default:
				console.log ("Unknown field type " + field.styleType);
				break;
			}
		}
	}	
},

fabricateText: function (field, canvas) {
	console.log ("fabricateText");
	console.log (field);
	var font = field.font ? field.font : field.style.font;
	var x = field.offsetX ? field.offsetX : field.style.offsetX;
	var y = field.offsetY ? field.offsetY : field.style.offsetY;
	var wid = field.width ? field.width : field.style.width;
	var ht = field.height ? field.height : field.style.height;
	var anchor = field.anchor ? field.anchor : field.style.anchor;
	var originx = "left";
	console.log ("Anchor = " + anchor);
	if (anchor == "TR" || anchor == "BR") {
		originx = "right";
		x = -x;
	}
	var originy = "top";
	if (anchor == "BR" || anchor == "BL") {
		originy = "bottom";
		y = -y;
	}
	var content = "";
	if (field.text)
		content = field.text;
	else if (field.defaultText)
		content = field.defaultText;
	
	var text = new fabric.Text(content, {
		fontSize: 12,		// just to get something
		left: x,
		top: y,
		width: wid,
		height: ht
	});
	console.log(text);
	field.fabricObject = text;
	canvas.add(text);
},

fabricateImage: function  (field, canvas) {
},

fabricateLogo: function  (field, canvas) {
},

fabricateBlock: function  (field, canvas) {
	var x = field.offsetX ? field.offsetX : field.style.offsetX;
	var y = field.offsetY ? field.offsetY : field.style.offsetY;
	var wid = field.width ? field.width : field.style.width;
	var ht = field.height ? field.height : field.style.height;
	var color = field.color ? field.color : field.style.color;
	var rect = new fabric.Rect ({
		width: wid,
		height: ht,
		left: x,
		top: y,
		fill: color
	});
	field.fabricObject = rect;
	canvas.add(rect);
},

fabricateSVG: function (field, canvas) {
}

}