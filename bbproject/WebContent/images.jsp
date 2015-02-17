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

<script type="text/javascript" src="js/jquery-2.1.1.js"></script>
<script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="js/jquery-ui.min.js"></script>
<script type="text/javascript" src="js/kendo.all.min.js"></script>

<head>

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
</div>		<!-- brndbotMain -->

<script type="text/javascript" src="js/images.js"></script>
</c:if>			<!-- end of session check for body ->
</body>
</html>
