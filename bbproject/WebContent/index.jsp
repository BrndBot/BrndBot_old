<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.LoginCookie" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<!DOCTYPE html>
<html class="html" lang="en-US">
<%
	final Logger logger = LoggerFactory.getLogger(this.getClass());
%>

 <head>

  <script type="text/javascript">
   if(typeof Muse == "undefined") window.Muse = {}; window.Muse.assets = {"required":["jquery-1.8.3.min.js", "museutils.js", "jquery.watch.js", "webpro.js", "index.css"], "outOfDate":[]};
  </script>

  <meta http-equiv="Content-type" content="text/html;charset=UTF-8"/>
  <meta name="generator" content="2014.1.0.276"/>
  <title>Home</title>
  <!-- CSS -->
  <link rel="stylesheet" type="text/css" href="css/site_global.css?426517801"/>
  <link rel="stylesheet" type="text/css" href="css/index.css?3981839088" id="pagesheet"/>
  <link href="styles/kendo.flat.min.css" rel="stylesheet">
  <link rel="stylesheet" type="text/css" href="css/signup.css" />
  <link rel="stylesheet" type="text/css" href="css/shared.css" />

  <!--[if lt IE 9]>
  <link rel="stylesheet" type="text/css" href="css/iefonts_index.css?3941248064"/>
  <![endif]-->
  <!-- Other scripts -->

<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>

<script type="text/javascript" src="js/kendo.all.min.js"></script>

  <script type="text/javascript">
   document.documentElement.className += ' js';
var __adobewebfontsappname__ = "muse";
</script>
  <!-- JS includes -->
  <script type="text/javascript">
   document.write('\x3Cscript src="' + (document.location.protocol == 'https:' ? 'https:' : 'http:') + '//webfonts.creativecloud.com/open-sans:n6,i4:all.js" type="text/javascript">\x3C/script>');
</script>

<%
	logger.debug("Entering index.jsp ");

	// Log out the user
	session.removeAttribute(SessionUtils.USER_ID); 
	session.removeAttribute(SessionUtils.IS_PRIV);

	LoginCookie cookie = new LoginCookie(request, response);
	String useCookie = "";
	String emailVal = "";
	String pwVal = "";
	if (cookie.useCookie())
	{
		useCookie = "checked=\"checked\"";
		emailVal = cookie.getEmailFromCookie();
		pwVal = cookie.getPasswordFromCookie();
	}
%>

   </head>
 <body>

  <div class="clearfix" id="page"><!-- column -->
   <div class="position_content" id="page_position_content">
    <div class="browser_width colelem" id="u88-bw">
     <div id="u88"><!-- group -->
      <div class="clearfix" id="u88_align_to_page">
       <div class="clearfix grpelem" id="u199-4"><!-- content -->
        <p style="cursor:pointer" onclick="javascript:signIn();return false">sign in</p>
       </div>

      	<div id="signInPanel" style="display:none">
			<form id="loginForm" action="javascript:signInGoClick();" method="post">
				<div class="lastUnit formHeader" style="padding-top:0.9375rem;display:none">
					Sign In Please
				</div>
			   	<div class="formLabelFor"style="padding-top:.75rem; color: #ED752D;">
			       	<label for="userEmail">your email</label>
			      	</div>
			      	<div class="formSpacer">
						<input type="text" class="k-textbox editWidth" id="<%=LoginCookie.EMAIL_ADDRESS %>" name="<%=LoginCookie.EMAIL_ADDRESS %>" 
							size="40" value="<%=emailVal %>" style="height:1.5rem;padding-left:.3rem;" />
			      	</div>
			   	<div class="formLabelFor" style=" color: #ED752D;">
			       	<label for="userPassword">your password</label>
				</div>
				<div class="formSpacer">
					<input type="password" class="k-textbox editWidth" id="<%=LoginCookie.PASSWORD %>" name="<%=LoginCookie.PASSWORD %>"
						size="40"  value="<%=pwVal %>" style="height:1.5rem;padding-left:.3rem;" />
		      	</div>
				<div id="forceLoginStyle">
					<div class="unit size1of2">
					   	<div style="color: #ED752D;line-height:34px">
							<button id="signInGo" class="greenButton rounded" style="width:5rem;cursor:pointer">sign in</button>
						</div>
					</div>
					<div class="betterHref">
						<a href="#" onclick="closeSignIn();">cancel</a>
					</div>
					<div style="clear:both">&nbsp;</div>
				</div>
				<div class="formLabelFor" style="padding-top:.5rem">
					<div style="float:left;margin-right:0.4375rem">
			        	<input type="checkbox" id="<%=LoginCookie.USE_COOKIE_CB %>" name="<%=LoginCookie.USE_COOKIE_CB %>" 
			        		value="<%=LoginCookie.USE_COOKIE_CB %>" <%=useCookie %> />
			       	</div>
					<div class="unit lastUnit" style="color: #ED752D;">
						<label for="<%=LoginCookie.USE_COOKIE_CB %>">&nbsp;Remember Me</label>
					</div>
		      	</div>
		      	<div class="formSpacer">
				&nbsp;
		      	</div>
			</form>
		</div> <!-- end of signInPanel -->
       
      </div>
     </div>
    </div>
    <div class="clearfix colelem" id="pu86"><!-- group -->
     <div class="clip_frame grpelem" id="u86"><!-- image -->
      <img class="block" id="u86_img" src="images/first-pane.jpg" alt="" width="1560" height="688"/>
     </div>
     <img class="svg grpelem" id="u89" src="images/svg_u89.svg" width="176" height="39" alt="" data-mu-svgfallback="images/_poster_u89.png"/><!-- svg -->
     <div class="clearfix grpelem" id="u95-6"><!-- content -->
      <p>We make beautiful marketing easy.</p>
      <p>So, so easy.</p>
     </div>
     <div class="clearfix grpelem" id="u100-4"><!-- content -->
      <p>Giving you back hours every week, we are the easiest marketing tool for your MindBody business.</p>
     </div>
     <div class="rounded-corners clearfix grpelem" id="u103"><!-- group -->
      <div class="clearfix grpelem" id="u104-4"><!-- content -->
       <p onclick="signUp();return false;" style="cursor:pointer;">Get Started</p>
      </div>
     </div>
     <div class="clearfix grpelem" id="u106-4"><!-- content -->
      <p style="cursor:pointer;">contact us</p>
     </div>
    </div>
    <div class="browser_width colelem" id="u85-bw">
     <div id="u85"><!-- group -->
      <div class="clearfix" id="u85_align_to_page">
       <div class="clearfix grpelem" id="u110-4"><!-- content -->
        <p>We are currently in invitation only beta.</p>
       </div>
      </div>
     </div>
    </div>
    <div class="clearfix colelem" id="pu111"><!-- group -->
     <div class="clip_frame grpelem" id="u111"><!-- image -->
      <img class="block" id="u111_img" src="images/secondpane.jpg" alt="" width="1560" height="688"/>
     </div>
     <div class="clearfix grpelem" id="u119-4"><!-- content -->
      <p>Send emails your clients want to open.</p>
     </div>
     <div class="clearfix grpelem" id="u120-4"><!-- content -->
      <p>Make emails that look and act professional in less than five minutes.</p>
     </div>
    </div>
    <div class="clip_frame colelem" id="u121"><!-- image -->
     <img class="block" id="u121_img" src="images/robotlove.png" alt="" width="754" height="196"/>
    </div>
    <div class="clearfix colelem" id="pu127"><!-- group -->
     <div class="clip_frame grpelem" id="u127"><!-- image -->
      <img class="block" id="u127_img" src="images/thirdpane.jpg" alt="" width="1560" height="688"/>
     </div>
     <div class="clearfix grpelem" id="u136-4"><!-- content -->
      <p>Social Media Simplified</p>
     </div>
     <div class="clearfix grpelem" id="u135-4"><!-- content -->
      <p>We make it easy to share content, attract new followers, and manage your social media accounts.</p>
     </div>
    </div>
    <div class="clearfix colelem" id="pu137"><!-- group -->
     <div class="browser_width grpelem" id="u137-bw">
      <div id="u137"><!-- column -->
       <div class="clearfix" id="u137_align_to_page">
        <div class="clearfix colelem" id="u157-4"><!-- content -->
         <p>Features</p>
        </div>
        <div class="clearfix colelem" id="pu144"><!-- group -->
         <div class="clip_frame grpelem" id="u144"><!-- image -->
          <img class="block" id="u144_img" src="images/email.png" alt="" width="84" height="51"/>
         </div>
         <div class="clip_frame grpelem" id="u149"><!-- image -->
          <img class="block" id="u149_img" src="images/social.png" alt="" width="74" height="62"/>
         </div>
         <div class="clip_frame grpelem" id="u138"><!-- image -->
          <img class="block" id="u138_img" src="images/connected.png" alt="" width="60" height="64"/>
         </div>
        </div>
        <div class="clearfix colelem" id="pu158-4"><!-- group -->
         <div class="clearfix grpelem" id="u158-4"><!-- content -->
          <p>Send beautiful emails packed with great content. It is the fastest way to create and send professional emails.</p>
         </div>
         <div class="clearfix grpelem" id="u160-4"><!-- content -->
          <p>Easily promote your content on social media. We integrate your Mindbody account seamlessly so all you have to do is post.</p>
         </div>
         <div class="clearfix grpelem" id="u162-4"><!-- content -->
          <p>We integrate all of your marketing needs into one easy to use system. It gives you the power to take marketing into your own hands.</p>
         </div>
        </div>
       </div>
      </div>
     </div>
     <div class="clip_frame grpelem" id="u163"><!-- image -->
      <img class="block" id="u163_img" src="images/pasted%20image%20789x35.png" alt="" width="789" height="35"/>
     </div>
    </div>
    <div class="clearfix colelem" id="pu173"><!-- group -->
     <div class="browser_width grpelem" id="u173-bw">
      <div id="u173"><!-- group -->
       <div class="clearfix" id="u173_align_to_page">
        <div class="clearfix grpelem" id="u175-4"><!-- content -->
         <p>Please sign up for news and releases.</p>
        </div>
       </div>
      </div>
     </div>
     <div class="clip_frame grpelem" id="u168"><!-- image -->
      <img class="block" id="u168_img" src="images/pasted%20image%20789x352.png" alt="" width="789" height="35"/>
     </div>
     <form class="form-grp clearfix grpelem" id="widgetu176" method="post" enctype="multipart/form-data" action="scripts/form-u176.php"><!-- none box -->
      <div class="fld-grp clearfix grpelem" id="widgetu188" data-required="true" data-type="email"><!-- none box -->
       <span class="fld-input NoWrap actAsDiv rounded-corners clearfix grpelem" id="u189-4"><!-- content --><input class="wrapped-input" type="text" spellcheck="false" id="widgetu188_input" name="Email" tabindex="1"/><label class="wrapped-input fld-prompt" id="widgetu188_prompt" for="widgetu188_input"><span class="actAsPara">Enter Email</span></label></span>
      </div>
      <div class="clearfix grpelem" id="u183-4"><!-- content -->
       <p>Submitting Form...</p>
      </div>
      <div class="clearfix grpelem" id="u181-4"><!-- content -->
       <p>The server encountered an error.</p>
      </div>
      <div class="clearfix grpelem" id="u192-4"><!-- content -->
       <p>Form received.</p>
      </div>
      <input class="submit-btn NoWrap grpelem" id="u182-17" type="submit" value="" tabindex="2"/><!-- state-based BG images -->
     </form>
    </div>
    <div class="verticalspacer"></div>
   </div>
  </div>
  <div class="preload_images">
   <img class="preload" src="images/u182-17-r.png" alt=""/>
   <img class="preload" src="images/u182-17-m.png" alt=""/>
   <img class="preload" src="images/u182-17-fs.png" alt=""/>
  </div>
  <!-- JS includes -->
  <script type="text/javascript">
   if (document.location.protocol != 'https:') document.write('\x3Cscript src="http://musecdn2.businesscatalyst.com/scripts/4.0/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script type="text/javascript">
   window.jQuery || document.write('\x3Cscript src="scripts/jquery-1.8.3.min.js" type="text/javascript">\x3C/script>');
</script>
  <script src="scripts/museutils.js?135030331" type="text/javascript"></script>
  <script src="scripts/jquery.watch.js?377079819" type="text/javascript"></script>
  <script src="scripts/webpro.js?221070874" type="text/javascript"></script>
  <!-- Other scripts -->
  <script type="text/javascript">
   $(document).ready(function() { 
	try {
	(function(){
		var a={},b=function(a){
			if(a.match(/^rgb/))
				return a=a.replace(/\s+/g,"").match(/([\d\,]+)/gi)[0].split(","),(parseInt(a[0])<<16)+(parseInt(a[1])<<8)+parseInt(a[2]);
			if(a.match(/^\#/))
				return parseInt(a.substr(1),16);return 0
		};
		(function(){
			$('link[type="text/css"]').each(function(){
				var b=($(this).attr("href")||"").match(/\/?css\/([\w\-]+\.css)\?(\d+)/);
				b&&b[1]&&b[2]&&(a[b[1]]=b[2])})})();
		(function(){
			$("body").append('<div class="version" style="display:none; width:1px; height:1px;"></div>');
		// WHAT IN THE LOWEST HELL OF DANTE'S IS THIS?????!!!!!!!!!
		for(var c=$(".version"),d=0;d<Muse.assets.required.length;){
			var f=Muse.assets.required[d],g=f.match(/([\w\-\.]+)\.(\w+)$/),k=g&&g[1]?g[1]:null,g=g&&g[2]?g[2]:null;
			switch(g.toLowerCase()){
				case "css":
					k=k.replace(/\W/gi,"_").replace(/^([^a-z])/gi,"_$1");
					c.addClass(k);
					var g=b(c.css("color")),h=b(c.css("background-color"));
					g!=0||h!=0?(Muse.assets.required.splice(d,1),"undefined"!=typeof a[f]&&(g!=a[f]>>>24||h!=(a[f]&16777215))&&Muse.assets.outOfDate.push(f)):d++;
					c.removeClass(k);
					break;
				case "js":
					k.match(/^jquery-[\d\.]+/gi)&&
						typeof $!="undefined"?Muse.assets.required.splice(d,1):d++;
						break;
				default:
					throw Error("Unsupported file type: "+g);
			}
		}
		c.remove();
		if(Muse.assets.outOfDate.length||Muse.assets.required.length)
			c="Some files on the server may be missing or incorrect. Clear browser cache and try again. If the problem persists please contact website author.",(d=location&&location.search&&location.search.match&&location.search.match(/muse_debug/gi))&&Muse.assets.outOfDate.length&&(c+="\nOut of date: "+Muse.assets.outOfDate.join(",")),d&&Muse.assets.required.length&&(c+="\nMissing: "+Muse.assets.required.join(",")),alert(c)})()})();		/* body */
		Muse.Utils.transformMarkupToFixBrowserProblemsPreInit();/* body */
		Muse.Utils.prepHyperlinks(true);/* body */
		Muse.Utils.resizeHeight()/* resize height */
		Muse.Utils.initWidget('#widgetu176', function(elem) { new WebPro.Widget.Form(elem, {validationEvent:'submit',errorStateSensitivity:'high',fieldWrapperClass:'fld-grp',formSubmittedClass:'frm-sub-st',formErrorClass:'frm-subm-err-st',formDeliveredClass:'frm-subm-ok-st',notEmptyClass:'non-empty-st',focusClass:'focus-st',invalidClass:'fld-err-st',requiredClass:'fld-err-st',ajaxSubmit:true}); });/* #widgetu176 */
		Muse.Utils.fullPage('#page');/* 100% height page */
		Muse.Utils.showWidgetsWhenReady();/* body */
		Muse.Utils.transformMarkupToFixBrowserProblems();/* body */
	} catch(e) { 
		if (e && 'function' == typeof e.notify) 
			e.notify(); 
		else 
			Muse.Assert.fail('Error calling selector function:' + e); 
	}

	$("#forceLoginStyle").attr();	// TEST***** if JQuery is available
	$("#signInGo").kendoButton({
		click: signInGoClick
	});
	
});

	function signInGoClick(arg)
	{
		var form_val = 'loginForm';
		var post_to = 'LoginServlet';
		document.getElementById(form_val).action = post_to;
		document.getElementById(form_val).submit();
	}

	function signUp()
	{
		window.location = "signup.jsp";
	}
	
	function signIn()
	{
		$("#u103").hide();
		$("#u106-4").hide();
		$("#signInPanel").slideToggle(400);
	}
	
	function closeSignIn()
	{
		$("#signInPanel").slideToggle(400, function()
		{
			$("#u103").show();
			$("#u106-4").show();
		});
	}
	
	
	document.getElementById("page").scrollIntoView();

</script>
   </body>
</html>
