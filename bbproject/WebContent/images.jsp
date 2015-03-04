<!DOCTYPE html>
<html>
<!--  *  All rights reserved by Brndbot, Ltd. 2015 -->
<%@ page session="true"%>

<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%
	final Logger logger = LoggerFactory.getLogger(this.getClass());
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>

<script type="text/javascript" src="scripts/jquery-2.1.1.js"></script>
<script type="text/javascript" src="scripts/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="scripts/jquery-ui.min.js"></script>
<script type="text/javascript" src="scripts/kendo.all.min.js"></script>

<link href="styles/kendo.common.min.css" rel="stylesheet">
<link href="styles/kendo.rtl.min.css" rel="stylesheet">
<link href="styles/kendo.flat.min.css" rel="stylesheet">
<link href="css/shared.css" rel="stylesheet">
<link href="css/images.css" rel="stylesheet">

    <title>Brndbot: Image Management</title>
    <meta charset="utf-8">
</head>
<body>

<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>

<c:if test="${sessionOK != 0}">	<!-- encompasses whole rest of body -->

<div id="brndbotMain">
	<div id="brndbotHeader">
		&nbsp;
	</div> <!-- brndbotHeader -->

	<form method="post">
		<div class="demo-section k-header">
				<input name="files" id="files" type="file" />
		</div>
	</form>

<ul id="imageGallery" style="border-style:none">

</ul> <!-- imageGallery -->
<div style="float:none"><div>

</div>		<!-- brndbotMain -->


<!-- Template for popup dialog to name the image that was just uploaded. -->
<div id="nameImagePopup" class="rounded" style="display:none">
	<form id="nameImageForm" >
	<ul class="namefileform">
		<li><label>Image name:
			<input type="text" name="imgname" id="imgname">
			</label>
		</li>
		<li>
			<input type="submit" value="Set name">
		</li>
		</ul>
	</form>
</div>		<!-- nameImagePopup -->

<script type="text/javascript" src="js/images.js"></script>

<script type="text/x-kendo-template" id="galleryTemplate">
	<li class="stylefield" style="float:left;padding:20px;list-style-type:none">
		<img src="ImageServlet?brndbotimageid=2&img=#:ID#" style="max-width:300px;max-height:300px">
	</li>
</script>

</c:if>			<!-- end of session check for body ->
</body>
</html>
