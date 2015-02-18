/**
 * 
 */

	$(document).ready(function() {
		// This just initializes the upload button
		$("#files").kendoUpload({
			multiple: false,
			async: {
	            saveUrl: "SaveImageServlet?brndbotimageid=2"
    	    },
            localization: {
                select: "Upload a JPEG, GIF, or PNG image",
                headerStatusUploaded: "Image uploaded."
            },
            success: imagesjs.uploadImageSuccess
		});
	});

var imagesjs = {
	/* Function called on successful upload. Prompts to name the picture. */
    uploadImageSuccess: function(e)
    {
    	if (!$("#nameImagePopup").data("kendoWindow")) {
	    	$("#nameImagePopup").kendoWindow ({
	    		modal: true,
	    		width: "240rem",
	    		height: "240rem",
	    	}).data("kendoWindow").center();
    	}
    	$("#nameImagePopup").data("kendoWindow").center().open(); 
//		var dialog = $("#nameImagePopup").data("kendoWindow");
//		if (dialog)
//			dialog.close();

    },

	nameImageFormSubmit: function () {
		var newName = $("#imgname").val();
		if (!newName)
			return false;
		return true;		// TODO placeholder
	}
}