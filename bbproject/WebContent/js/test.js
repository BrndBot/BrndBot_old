$(document).ready(function() 
{
	$('#expressButton').kendoButton({
		click: saveImage
	});

	function saveImage(e)
	{
		// Put the contents into the hidden form
		$('#hiddenHtml').val($('#finishedImage').html());

	    $.ajax({
	        type: 'POST',
	        url: 'SaveHTMLAsImageServlet',
	        data: $('#htmlForm').serialize(), // serializes the form's elements.
	        success: function(data)
	        {
	        	alert('the image was saved');
	        }
	      });

	}
});
