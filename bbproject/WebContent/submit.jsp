<!DOCTYPE html>
<html>
<!--
All rights reserved by Brndbot, Ltd. 2015
-->
<%@ page session="true" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<head>
    <title>Brndbot Channel</title>
    <meta charset="utf-8">

    <script type="text/javascript" src="scripts/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="scripts/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="scripts/jquery-ui.min.js"></script>
    <script type="text/javascript" src="scripts/kendo.all.min.js"></script>

	<link href="css/shared.css" rel="stylesheet">

<c:set var="sessionOK" value="1" scope="page"/>

<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>
</head>

<body>
<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>

<c:if test="${sessionOK != 0}">	<!-- encompasses whole rest of body -->
<div id="brndbotMain">

	<div  id="leftColumn">
		<c:set var="icon_image" value="images/sidebar/Social-Editor_LeftButton.png" />
		<%@include file="sidebar.jsp" %>
	</div>

	<div class="unit spaceMe">

		<img src="ImageServlet?img=fused" alt="Composed promotion">
		<p>
		You can save this image in most browsers by right-clicking on it.
		</p>
	</div>
</div>		<!-- brndbotMain -->

    <script type="text/javascript" src="js/sidebar.js"></script>

</c:if>		<!-- sessionOK -->
</body>
</html>