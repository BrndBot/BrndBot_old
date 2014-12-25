<!DOCTYPE html>
<html>
<%@ page session="true"%>

<%@ page import="com.brndbot.block.BlockType" %>
<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.user.Palette" %>
<%@ page import="com.brndbot.user.User" %>
<%@ page import="com.brndbot.user.UserLogo" %>

<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <title>Brndbot</title>
    <meta charset="utf-8">
	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/home.css" rel="stylesheet">
	<link href="css/klist.css" rel="stylesheet">
    <link href="css/content.css" rel="stylesheet">

    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="js/jquery-ui.min.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
	<script type="text/javascript" src="js/modernizr.custom.63321.js"></script>
    <script type="text/javascript" src="js/sidebar.js"></script>
    <script type="text/javascript" src="js/home.js"></script>
	<script type="text/javascript" src="js/content.js"></script>

</head>
<body>
<% 
	System.out.println("-------------Entering home.jsp---------------");

	int max_logo_height = 130;
	int max_logo_width = 200;

	int user_id = SessionUtils.getIntSession(session, SessionUtils.USER_ID);
	if (user_id == 0)
	{
		System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
		response.sendRedirect("index.jsp");
		return;
	}
	DbConnection con = DbConnection.GetDb();
	User user = User.getUserNameAndLogo(user_id, con);
	con.close();
	if (user == null)
	{
		System.out.println("User returned null, something is wrong.  Userid: " + user_id);
		// Assume the problem is that no logo was uploaded.
		response.sendRedirect("signup.jsp?toLogo=1");
		return;
	}
	System.out.println("UserID: " + user.getUserID().intValue());

%>
<div id="brndbotMain">
	<div id="brndbotHeader">
		&nbsp;
	</div> <!-- brndbotDashHeader -->

	<div id="brndbotDashBody">

		<div  id="leftColumn">
			<div class="topRow">
				<table id="imageWrapper2" class="super-centered">
					<tr>
			    		<td align="center" valign="middle">
							<%=UserLogo.getBoundLogo(user_id, max_logo_height, max_logo_width)%>
						</td>
					</tr>
				</table>
			</div>

<%@include file="sidebar.jsp" %>

		</div>

		<div class="unit size1of2" style="padding-left:9.4375rem;padding-top:3rem">
			<div style="width:100%">

				<!--  BEGINNING HOME.JSP -->
				<div id="homeJsp">
					<div class="spaceMe">
						<div style="text-align:left;">
							<div class="formHeader formSpacer">
								Hey there!<br />
								What would you like to do today?
							</div>
							<div class="formLabelFor formSpacer" style="font-variant: normal;">
								Add text and instruction here.
							</div>
						</div>
					</div>
					<div class="spaceMe">
						<div id="saleBadge" class="homeBadge">
							<div>
								<img src="images/home/sale.png" alt="" />
							</div>
							<div class="badgePrompt">
								Promote Sale
							</div>
						</div>
						<div id="classBadge" class="homeBadge">
							<div>
								<img src="images/home/classes.png" alt="" />
							</div>
							<div class="badgePrompt">
								Promote Class
							</div>
						</div>
						<div id="workshopBadge" class="homeBadge lastUnit">
							<div>
								<img src="images/home/workshops.png" alt="" />
							</div>
							<div class="badgePrompt">
								Promote Workshop
							</div>
						</div>
					</div>
					<div style="clear:both">&nbsp;</div>
					<div style="text-align:center;">
						<div id="teacherBadge" class="homeBadge">
							<div>
								<img src="images/home/teacher.png" alt="" />
							</div>
							<div class="badgePrompt">
								Promote Teacher
							</div>
						</div>
						<div id="scheduleBadge" class="homeBadge lastUnit">
							<div>
								<img src="images/home/schedule.png" alt="" />
							</div>
							<div class="badgePrompt">
								Promote Schedule
							</div>
						</div>
					</div>
				</div>
				<!--  END HOME.JSP -->


				<!--  BEGINNING DASHBOARD.JSP -->
				<div id="dashboardJsp" style="display:none;">
				    <div class="mainContentHeader" style="width:60rem">
				    	<div class="subContentHeader">
				    		<div style="padding-bottom:1.25rem">
								<div class="unit size2of3" style="font-size: 2.25rem;font-weight: strong;">
							    	Your MindBody <span id="contentType">Content</span>
								</div>
						    	<div class="unit lastUnit">
						        	<input id="contentSearch" type="text" class="k-textbox editWidth" placeholder="search..." />
						    	</div>
						    </div>
						</div>
			    	</div>

				    <div class="listBackground">
				    	<div id="viewButtonPanel" class="viewButtonPanel">
				    		<div style="padding-top:1.25rem">
					    		<div class="unit eachButton">
					    			<button id="viewWorkshops" class="greenNoHoverButton">workshops</button>
					    		</div>
					    		<div class="unit eachButton">
					    			<button id="viewClasses" class="grayNoHoverButton">classes</button>
					    		</div>
					    		<div class="unit eachButton">
					    			<button id="viewTeachers" class="grayNoHoverButton">teachers</button>
					    		</div>
					    		<div class="lastUnit">
									&nbsp;
					    		</div>
					    	</div>
						</div>
<!-- 					<div id="wait">
							<div style="padding-top:0.625rem;">
								<img src="images/loader.gif" alt="" />
							</div>
						</div>
-->
		 				<div id="classHere" style="display:none;border-color:#ffffff"></div>
		 				<div id="classPager" style="display:none;"></div>
						<div id="workshopHere" style="display:none;border-color:#ffffff"></div>
		 				<div id="workshopPager" style="display:none;"></div>
						<div id="staffHere" style="display:none;border-color:#ffffff"></div>
		 				<div id="staffPager" style="display:none;"></div>
					</div>  <!-- end listBackground -->
				</div>
				<!--  END DASHBOARD.JSP -->

				<!--  BEGINNING channelJsp -->
				<div id="channelJsp" style="display:none;">
					<div class="spaceMe">
						<div style="text-align:left;">
							<div class="formHeader formSpacer">
								Okay, and how would you like to promote<br />
								this <span id="chanTypeContent">class</span> today?
							</div>
							<div class="formLabelFor formSpacer" style="font-variant: normal;">
								Choose the type of message you'd like to send.
							</div>
						</div>
					</div>
					<div style="text-align:center;">
						<div id="chanEmailBadge" class="homeBadge">
							<div>
								<img src="images/home/email.png" alt="" />
							</div>
							<div class="badgePrompt">
								Create Email
							</div>
						</div>
					
						<div id="chanTwitterBadge" class="homeBadge">
							<div>
								<img src="images/home/twitter.png" alt="" />
							</div>
							<div class="badgePrompt">
								Create Tweet
							</div>
						</div>
						<div id="chanFacebookBadge" class="homeBadge">
							<div>
								<img src="images/home/facebook.png" alt="" />
							</div>
							<div class="badgePrompt">
								Create Post
							</div>
						</div>
					</div>
					<div style="clear:both">&nbsp;</div>
				</div>
				<!--  END channelJsp -->

			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	// Init the left-side panel
	clickSideBar('homeLine');

	var EMAIL_CHANNEL = <%=ChannelEnum.EMAIL.getValue().intValue() %>;
	var FACEBOOK_CHANNEL = <%=ChannelEnum.FACEBOOK.getValue().intValue() %>;
	var TWITTER_CHANNEL = <%=ChannelEnum.TWITTER.getValue().intValue() %>;
	

	// Singleton class for client-side session management
	var session_mgr = new Session();
</script>

</body>
</html>
