<!doctype html>
  
<html lang="en">
<head>
  <meta charset="utf-8" />
  <title>Test</title>
  <script src="js/post-to-facebook.js"></script>
</head>
<body>
<div id="fb-root"></div>
<a href="#" onclick="publishWallPost()"> Post to Facebook </a>

<script>
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
(function()
{
	var e = document.createElement('script');
	e.async = true;
	e.src = document.location.protocol + '//connect.facebook.net/en_US/all.js';
	document.getElementById('fb-root').appendChild(e);
}());
</script>

</body>
</html>