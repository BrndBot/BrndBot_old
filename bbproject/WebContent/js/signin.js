$(document).ready(function() 
{
	$("#signIn").kendoButton({
		click: signInClick
	});

	function signInClick()
	{
		window.location = 'home.jsp';
	}
});  // document.ready(
