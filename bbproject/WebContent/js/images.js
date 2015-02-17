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
		});
	});
