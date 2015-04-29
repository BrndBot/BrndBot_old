<!DOCTYPE html>
<html>
<!--  *  All rights reserved by Brndbot, Ltd. 2015 -->
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

<% /*

This page presents the promotion categories that are available and lets the user choose one,
which then presents the models. When the user chooses a model, it presents the promotion
prototypes to select, and the user picks one for processing in the editor (bench).

*/
%>

<head>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    
	<title>Brndbot</title>
    <meta charset="utf-8">
	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/home.css" rel="stylesheet">
	<link href="css/klist.css" rel="stylesheet">
    <link href="css/content.css" rel="stylesheet">
    
	
    <script type="text/javascript" src="scripts/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="scripts/jquery-ui.min.js"></script>
    <script type="text/javascript" src="scripts/kendo.all.min.js"></script>
	<script type="text/javascript" src="scripts/modernizr.custom.63321.js"></script>
    <script type="text/javascript" src="js/sidebar.js"></script>
	<script type="text/javascript" src="js/session.js"></script>
    <script type="text/javascript" src="js/home.js"></script>

</head>
<body>

<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>


<jsp:useBean id="homeHelper" 
		class="com.brndbot.jsphelper.HomeHelper" 
		scope="page">
	<jsp:setProperty name="homeHelper" property="userId" value="${sessionScope.brndbotuser_id}"/>
	<jsp:setProperty name="homeHelper" property="clientKey" value = "${sessionScope.brndbotclient}"/>
	<jsp:setProperty name="homeHelper" property="organization" value="${sessionScope.brndbotorg}"/>
</jsp:useBean>

<c:if test="${homeHelper.logoName == null}">
	<c:redirect url="signup.jsp?toLogo=1"/>
	<c:set var="sessionOK" value="0" scope="page"/>
</c:if>

<c:if test="${sessionOK != 0}">	<!-- encompasses whole rest of body -->


<div id="brndbotMain">


	<div id="brndbotDashBody">

		<div  id="leftColumn">

		<c:set var="icon_image" value="images/sidebar/TopNavMenuButton.png" />
		<%@include file="sidebar.jsp" %>

		</div>

		<div class="homeBody">
			<div style="width:100%">

				<div id="homeJsp">
					<div class="spaceMe">
						<div style="text-align:left;">
							<div class="formHeader formSpacer">
								Hi ${homeHelper.organization}!<br />
								What would you like to do today?
							</div>
						</div>
					</div>
					<div class="spaceMe" id="modelTableHolder" style="position:relative">
						<% /* The buttons are inserted here for picking a model */ %>
						<c:out escapeXml="false" value="${homeHelper.renderDoToday}"/>
					</div>
					<div style="clear:both">&nbsp;</div>
				</div>


				<div id="dashboardJsp" style="display:none;">
				    <div class="mainContentHeader" style="width:60rem">
				    	<div class="subContentHeader">
				    		<div style="padding-bottom:1.25rem">
								<div class="unit size2of3" style="font-size: 2.25rem;font-weight: strong;">
							    	Your <span id="contentType">Content</span>
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

						<!-- This probably replaces all of the ones below  -->
						<div id="promoProtosHere" style="display:none;border-color:#ffffff"></div>

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
</div>		<!-- brndbotMain -->

<script type="text/javascript">

	// Singleton class for client-side session management
	// There's something dumb about defining globals in the JSP, but Javascript doesn't have an include!
	var session_mgr = new Session();
</script>

<!-- Template for listing promotion prototypes -->
<script type="text/x-kendo-template" id="imageTemplate">
	<div class="listPromoProto">
		<div class="listTitle">
		#:name#
		</div>
		<div class="listDescription">
		#:description#
		</div>
		<button id="button#:ID#" data-proto="#:protoName#" onclick="homejs.selectProto(this);">Promote</button>
	</div>
</script>
		
</c:if>		<!-- whole of body -->
</body>
</html>
