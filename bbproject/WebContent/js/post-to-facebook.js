/*
	function publishWallPost(block)
	{
		var data=
		{
			message: "This is the message param.",
			display: 'iframe',
			caption: "Caption",
			name: block.name,  
			picture: block.imgURL,    
			link: "http://client.brndbot.com/",  // Go here if user click the picture
			description: block.description,
			actions: [{ name: block.name, link: 'http://client.brndbot.com' }],			
		};
		FB.api('/me/photos', 'post', data, onPostToWallCompleted);
	}

	function onPostToWallCompleted()
	{
		alert('onPostToWallCompleted!');
	}

function publishWallPost(block) 
{
	var attachment = 
    {
    	'name':block.name,
    	'description': block.description,
    	'media':
    	[{
    	        'type':'image',
    	        'src': block.imgURL, width: '700',
    	        'href':'http://client.brndbot.com/'
    	}]
    };
    FB.ui(
    {
        method: 'stream.publish',
        message: block.name,
        attachment: attachment,
        user_message_prompt: 'Post this to your wall?'
    });
}
*/
function publishWallPost(block)
{
	alert(ACCESS_TOKEN);
    FB.api(
    '/me/photos', 
    'post', 
    {
        message: block.description,
        access_token: ACCESS_TOKEN,
        url: block.imgURL
    },
    function(response)
    {
    	if (!response || response.error) 
    	{
    		alert('Error occured:' + response);
        }
    	else
    	{
    		alert('Post ID: ' + response.id);
    	}
	});
}

// put this in the JSP that will make the call
/*
window.fbAsyncInit = function() 
{
	FB.init(
	{
		appId: '1651845631708568', 
		status: true, 
		cookie: true, 
		xfbml: true
	});
};
// And this, too
(function()
{
	var e = document.createElement('script'); 
	e.async = true; 
	e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
	document.getElementById('fb-root').appendChild(e);
}());
*/

// And this will make the call
//  <a href="#" onclick="publishWallPost('{{ page.slug }}')"> Post to Facebook </a>
