/**
 * ColorSelector.js
 * 
 *  *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 *  This provides the code pieces for a color selector for text or blocks.
 */

/* The ColorSelector prototype constructor */
function ColorSelector() {
	
	/* This is for showing and hiding the group of palette buttons
	 * and the Custom button. It doesn't affect the state of the
	 * color picker. The argument is the DOM representation of the
	 * control's main button. */
	this.showHideColorSelect = function (button) {
		var fieldid = $(button).attr("data-linkedfield");
		var buttondiv = $('#' + fieldid + "-select");
		buttondiv.toggle();
		//bench.currentPromotion.canvas.renderAll();
	};
	
	/* This is for the Custom button in the color controls.
	 * btn is a button in a td, and the color picker is in
	 * the next td. */
	this.showHideColorPicker = function (btn) {
		var picker = $(btn).parent().parent().find("input");
		var style = this.elemToLinkedStyle(picker);
		picker.prop("defaultValue", style.getColor());
		picker.toggle();
	};
	
	/* Set field to the color indicated by a palette button.
	 * setterFunc is a callback function to handle the details of
	 * setting the color in the style. */
	this.setToPaletteColor = function (input, setterFunc) {
		var style = this.elemToLinkedStyle(input);
		var color = $(input).attr("data-color");
		setterFunc (style, color);
//		style.setLocalColor (color);
//		style.fabricObject.fill = color;
		bench.currentPromotion.canvas.renderAll();
	};
	
	/* Set field to the color indicated by the color picker */
	this.setToInputColor = function (input, setterFunc) {
		var style = benchcontent.elemToLinkedStyle(input);
		var color = $(input).val();
		setterFunc (style, color);
		bench.currentPromotion.canvas.renderAll();
	};
	
	/* For the a DOM element which has the data-linkedfield attribute,
	 * return the linked style by its index.
	 */
	this.elemToLinkedStyle = function (elem) {
		var target = $(elem).attr("data-linkedfield");
	   	return bench.currentPromotion.styleSet.styles[parseInt(target, 10)];
	};
}