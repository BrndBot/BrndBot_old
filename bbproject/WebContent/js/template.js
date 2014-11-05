$(document).ready(function() 
{
    $("#files").kendoUpload({
        async: {
            saveUrl: "SaveTemplateServlet",
            autoUpload: true
        },
        localization: {
            select: "upload template"
        },
        error: onError,
        success: onSuccess,
        multiple: false,
        showFileList: false
    });
});  // document.ready(

function onError(e) {
	alert('onError');
}

function onSuccess(e)
{
	if (e.operation == 'upload')
	{
		alert('Voila!');
	}
	else
		alert('Unexpected operation: ' + e.operation);
}

