<!DOCTYPE html>
<html>
<%@ page session="true"%>


<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.jsphelper.HomeHelper" %>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.db.Palette" %>
<%@ page import="com.brndbot.db.User" %>
<%@ page import="com.brndbot.db.UserLogo" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="java.util.ArrayList" %>


<%
	final Logger logger = LoggerFactory.getLogger(this.getClass());
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<c:set var="channel_email" value ="<%= ChannelEnum.CH_EMAIL %>" scope="page"/>
<c:set var="channel_facebook" value ="<%= ChannelEnum.CH_FACEBOOK %>" scope="page"/>

<script type="text/javascript">
	var EMAIL_CHANNEL = ${channel_email};
	var FACEBOOK_CHANNEL = ${channel_facebook};
</script>
<% /*

This page presents the promotion categories that are available and lets the user choose one
for processing in the editor (bench).

*/
%>

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
	<script type="text/javascript" src="js/session.js"></script>
    <script type="text/javascript" src="js/home.js"></script>
	<script type="text/javascript" src="js/content.js"></script>

</head>
<body>
Server info: <%= application.getServerInfo() %><br>
Servlet version: <%= application.getMajorVersion() %>.<%= application.getMinorVersion() %><br>
JSP version: <%= JspFactory.getDefaultFactory().getEngineInfo().getSpecificationVersion() %><br>
Java version: <%= System.getProperty("java.version") %><br>

<c:if test="${sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>

<%
	logger.debug ("User ID = {}", session.getAttribute("brndbotuser_id"));
	logger.debug ("Org = {}", session.getAttribute("brndbotorg"));
%>

<jsp:useBean id="homeHelper" 
		class="com.brndbot.jsphelper.HomeHelper" 
		scope="page">
	<jsp:setProperty name="homeHelper" property="userId" value="${sessionScope.brndbotuser_id}"/>
	<jsp:setProperty name="homeHelper" property="organization" value="${sessionScope.brndbotorg}"/>
</jsp:useBean>

<%
	logger.debug ("Logo name = {}", homeHelper.getLogoName());
%>

<p>Logo name: <c:out value="${homeHelper.logoName}"/></p>

<c:if test="${homeHelper.logoName == null}">
	<c:redirect url="signup.jsp?toLogo=1"/>
	<c:set var="sessionOK" value="0" scope="page"/>
</c:if>

<p>Final SessionOK: <c:out value="${sessionOK}"/> <br>


<c:if test="${sessionOK != 0}">	<!-- encompasses whole rest of body -->


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
							<c:out escapeXml="false" value="${homeHelper.boundLogo}"/>
						</td>
					</tr>
				</table>
			</div>

<%@include file="sidebar.jsp" %>

		</div>

		<div class="unit size1of2" style="padding-left:9.4375rem;padding-top:3rem">
			<div style="width:100%">

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
<c:out escapeXml="false" value="${homeHelper.renderDoToday}"/>
					</div>
					<div style="clear:both">&nbsp;</div>
				</div>


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
<c:out escapeXml="false" value="${homeHelper.renderModelListButtons}"/>
					    		<div class="lastUnit">
									&nbsp;
					    		</div>
					    	</div>
						</div>

						<!-- WHAT exactly do these do? -->
		 				<div id="classHere" style="display:none;border-color:#ffffff"></div>
		 				<div id="classPager" style="display:none;"></div>
						<div id="workshopHere" style="display:none;border-color:#ffffff"></div>
		 				<div id="workshopPager" style="display:none;"></div>
						<div id="staffHere" style="display:none;border-color:#ffffff"></div>
		 				<div id="staffPager" style="display:none;"></div>
					</div>  <!-- end listBackground -->
				</div>

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
					
<!--						<div id="chanTwitterBadge" class="homeBadge">
							<div>
								<img src="images/home/twitter.png" alt="" />
							</div>
							<div class="badgePrompt">
								Create Tweet
							</div>
-->
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


	// Singleton class for client-side session management
	// There's something dumb about defining globals in the JSP, but Javascript doesn't have an include!
	var session_mgr = new Session();
</script>

</c:if>		<!-- whole of body -->
</body>
</html>
