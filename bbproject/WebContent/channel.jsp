<!DOCTYPE html>
<html>
<!--  *  All rights reserved by Brndbot, Ltd. 2015 -->
<%@ page session="true"%>

<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.jsphelper.ChannelHelper" %>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.db.User" %>
<%@ page import="com.brndbot.db.UserLogo" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="java.util.ArrayList" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="channel_email" value ="<%= ChannelEnum.CH_EMAIL.getValue() %>" scope="page"/>
<c:set var="channel_facebook" value ="<%= ChannelEnum.CH_FACEBOOK.getValue() %>" scope="page"/>
<c:set var="channel_twitter" value ="<%= ChannelEnum.CH_TWITTER.getValue() %>" scope="page"/>

<script type="text/javascript">
	var EMAIL_CHANNEL = ${channel_email};
	var FACEBOOK_CHANNEL = ${channel_facebook};
	var TWITTER_CHANNEL = ${channel_twitter};
</script>

<%
	final Logger logger = LoggerFactory.getLogger(this.getClass());
%>


<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<% /*

This page presents the channels for which there is at least one available style and requires
the user to pick one before proceeding to the editor.

If no model has been selected, it forces the user back to home.jsp.

*/
%>

<head>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <title>Brndbot Channel Selection</title>
    <meta charset="utf-8">

    <script type="text/javascript" src="scripts/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="scripts/jquery-ui.min.js"></script>
    <script type="text/javascript" src="scripts/kendo.all.min.js"></script>

    <link href="css/shared.css" rel="stylesheet">
	<link href="css/channel.css" rel="stylesheet">


<c:set var="sessionOK" value="1" scope="page"/>

<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>

<jsp:useBean id="channelHelper" 
		class="com.brndbot.jsphelper.ChannelHelper" 
		scope="page">
	<jsp:setProperty name="channelHelper" property="userId" value="${sessionScope.brndbotuser_id}"/>
	<jsp:setProperty name="channelHelper" property="organization" value="${sessionScope.brndbotorg}"/>
</jsp:useBean>

</head>

<body>
<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>

<c:if test="${sessionOK != 0}">	<!-- encompasses whole rest of body -->

<% 
/* Get parameters */
%>
<c:set var="proto_name" value='${param.proto}' scope="page" />
<c:set var="model_name" value='${param.model}' scope="page" />

<script type="text/javascript">
	var protoName = "${proto_name}";
	var modelName = "${model_name}";
</script>
<div id="brndbotMain">

	<div  id="leftColumn">
		<%@include file="sidebar.jsp" %>
	</div>

	<div class="unit spaceMe">
		<% /* The buttons are inserted here for picking a channel */ %>
		<table><tr>
		<c:out escapeXml="false" value="${channelHelper.renderChannelButtons}"/>
		</tr>
		</table>
	</div>
</c:if>		<!-- whole of body -->

</div> 	<!-- brndbotMain -->
	<script type="text/javascript" src="js/channel.js"></script>
    <script type="text/javascript" src="js/sidebar.js"></script>

</body>
</html>