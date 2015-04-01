/**
 * channel.js
 * 
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports management and drawing of models with styles.
 * 
 * Gary McGath
 * March 31, 2015
 *
 * This provides JavaScript services for channel.jsp.
 */

$(document).ready(function() {

		$('.channelButton').on('click', function(e) {
			channeljs.model_name = $(this).attr("data-channel");
			var channel = "2";		// Replace with getting data
			window.location.assign ("edit.jsp?proto=" + 
				protoName +
				"&model=" + 
				modelName +
				"&channel=" +
				channel);

			});

	});


var channeljs = {
	channel_name: "",
		
	/* Set a button to its hover state. */
	showHoverImage: function (button) {
		var img = $(button).find("img");
		img.attr("src", img.attr("data-hoversrc"));
	},
		
	/* Set a button to its non-hover state */
	showNormalImage: function (button) {
		var img = $(button).find("img");
		img.attr("src", img.attr("data-normalsrc"));
	},

};