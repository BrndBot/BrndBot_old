/**
 * 
 */

	$(document).ready(function() {
		// This just initializes the upload button
		$("#files").kendoUpload({
			multiple: false,
			async: {
	            saveUrl: "SaveImageServlet?brndbotimageid=2",
	            removeUrl: "dummy",			// don't quite understand what this signifies
	            removeField: "fileNames[]"	// not sure what to do with this
    	    },
            localization: {
                select: "Upload a JPEG, GIF, or PNG image",
                headerStatusUploaded: "Image uploaded."
            }
		});
		
		imagesjs.populateGallery ($('#imageGallery'));
	});

var imagesjs = {
		
	saveUrl: "" ,
			
	/* Function called on file selection. Prompts to name the picture. */
    uploadImageSelect: function(e)
    {
    	if (!$("#nameImagePopup").data("kendoWindow")) {
	    	$("#nameImagePopup").kendoWindow ({
	    		modal: true,
	    		width: "240rem",
	    		height: "240rem",
	    	}).data("kendoWindow").center();
    	}
    	$("#nameImagePopup").data("kendoWindow").center().open(); 
    },
    
    uploadFilter: function (e) {
    	// We update the URL to specify the user selected name
    	console.log("uploadFilter");
        e.sender.options.async.saveUrl = imagesjs.saveUrl;
    },
    
	nameImageFormSubmit: function () {
		var newName = $("#imgname").val().trim();
		if (!newName)
			return false;
		var regexp = /^\\w/;
		if (regexp.test(newName)) {
			console.log ("Disallowed character in " + newName);
			return false;			// only alphanumerics allowed
		}
		console.log("Accepted name " + newName);
		imagesjs.saveUrl = "SaveImageServlet?brndbotimageid=2&imgName=" + newName; 
		return true;		// TODO placeholder
	},
    
    getSaveUrl: function () {
    	return imagesjs.saveUrl;
    },
    
    populateGallery: function (sel) {
    	// TODO use kendo list
    }
};