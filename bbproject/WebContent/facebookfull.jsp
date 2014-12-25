<!DOCTYPE html>
<html>
<%@ page session="true"%>
<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.user.Image" %>
<%@ page import="com.brndbot.system.Assert" %>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@ page import="com.restfb.DefaultFacebookClient" %>
<%@ page import="com.restfb.FacebookClient.AccessToken" %>

<head>
    <title>Brndbot to Facebook</title>
    <meta charset="utf-8">

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/finalKlist.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
    <link href="css/finaltouches.css" rel="stylesheet">

	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>
    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
    <script type="text/javascript" src="js/facebookfull.js"></script>
    <script type="text/javascript" src="js/post-to-facebook.js"></script>
    <script type="text/javascript" src="js/FBLogin.js"></script>
</head>
<body>
<% 
	int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
	if (user_id == 0)
	{
		System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
		response.sendRedirect("index.jsp");
		return;
	}

	int CHANNEL = SessionUtils.getIntSession(session, SessionUtils.CHANNEL_KEY);
	Assert.that(CHANNEL == ChannelEnum.FACEBOOK.getValue().intValue(), "Channel is not Facebook, value: " + CHANNEL);
	int image_id = SessionUtils.getIntSession(session, SessionUtils.FUSED_IMAGE_ID_KEY);
	System.out.println("FUSED_IMAGE_ID: " + image_id);
	DbConnection con = DbConnection.GetDb();
	Image image = Image.makeThisImage(image_id, user_id, con);
	Assert.that(image != null, "Image is NULL!  DB ID: " + image_id);
	con.close();
	String image_fqdn = Utils.Slashies(SystemProp.get(SystemProp.ASSETS) + "\\" + image.getImageName());
	System.out.println(image_fqdn);

	String appId = "1651845631708568";
    String appSecret = "5141edde769b33b531d83d87b9f6bce7";

    AccessToken accessToken = new DefaultFacebookClient().obtainAppAccessToken(appId, appSecret);
%>
<div id="brndbotMain">
	<div id="brndbotHeader">
		<div id="mainLogo">
<!-- 			<img src="images/brndbot-logo.png" alt="Brndbot Logo" />  -->
		</div>
	</div> <!-- brndbotHeader -->
	<div id="brndbotBody" style="width:40rem">
		<div id="finishPane">
			<div style="text-align:center;">
				<div style="text-align:left;padding-bottom:3rem">
					<div class="formHeader" style="padding-bottom:3rem">
						Post to Facebook
					</div>
					<div id="status"></div>
				</div>
				<div style="padding-top:1.25rem;float:right">
					<button id="sendButton" style="width:9.375rem">send</button>
				</div>
			</div>
		</div> <!-- end of finishPane -->
		
		<div id="pleaseWaitPane" style="padding:1.875rem;font-size: 2.25rem;display:none">
			<div>
				Please wait while we send your email...
			</div>
			<div id="progressBar" style="width:61.5625rem"></div>
		</div>

		<div id="successPane" style="display:none">
			<div class="brndbotFinishImg">
				<img src="images/MailMan.jpg" alt="" />
			</div>
			<div class="formHeader" style="text-align:center;padding-bottom:1.25rem">
				Congrats! Email Sent.
			</div>
			<hr style="color:#ccc" />
			<div class="noticeTxt">
				We noticed you've promoted these, would<br />
				you like to also promote them on your social networks?
			</div>
			<div id="summaryHere" style="width:765px">
			</div>
			<div>
				<div class="unit size3of4">
					&nbsp;
				</div>
				<div class="unit lastUnit">
					<div style="float:right;padding-top:2rem;">
						<button id="finishButton" class="orangeButton rounded" style="width:9.375rem">Finish</button>
					</div>				
				</div>
			</div>
		</div>

		<fb:login-button scope="publish_actions" onlogin="checkLoginState();">
		</fb:login-button>

	</div>
	<div id="fb-root"></div>

	<!-- This button will sign up the user to the brndbot app and log them in  -->

	<!-- end of FB login -->
</div> <!-- brndbotMain -->

<script type="text/x-kendo-template" id="template">
	<div class="myListItem">
		<div style="padding-bottom:1.0625rem;padding-top:1.25rem">
			<div class="unit" style="width:4.75rem;padding-top:0.625rem;padding-left:1.9375rem">
				<img height="25px" width="25px" src="#:imgURL #" alt="" />
			</div>
			<div class="unit lastUnit">
				<div class="listTitle">
					#:full_name#
				</div>
				<div class="listDescription">
					#:short_description#
				</div>
				<div style="margin-top:0.6rem;">
					<div class="unit templateDetail" style="padding-right:1rem">
						<span>#:starting_date#</span>
					</div>
					<div class="unit templateDetail">
						<span>#:block_type_desc#</span>
					</div>
				</div>
			</div>
		</div>
		<div class="listViewButton">
			<button id="#:nameID#" class="orangeButton" 
				style="width:4.6875rem;display:none;">promote</button>
		</div>

	</div>
</script>

<script type="text/javascript">

var ACCESS_TOKEN = null;
var json_blocks = <%=session.getAttribute(SessionUtils.BLOCKS) %>;
// right now, idx 1 is the fused image.  Reset the FQDN name into block 1
json_blocks[1].imgURL = '<%=image_fqdn %>';

</script>
</body>
</html>
