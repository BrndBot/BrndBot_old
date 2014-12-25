<!DOCTYPE html>
<html>
<%@ page session="true"%>

<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.history.HistoryType" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.user.Palette" %>
<%@ page import="com.brndbot.user.User" %>
<%@ page import="com.brndbot.user.UserLogo" %>

<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<head>
    <title>Brndbot History</title>
    <meta charset="utf-8">

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/klist.css" rel="stylesheet">
    <link href="css/sidebar.css" rel="stylesheet">
    <link href="css/history.css" rel="stylesheet">

	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>
    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
	<script type="text/javascript" src="js/modernizr.custom.63321.js"></script>
    <script type="text/javascript" src="js/history.js"></script>
    <script type="text/javascript" src="js/sidebar.js"></script>
</head>
<body>
<%
	System.out.println("-------------Entering history.jsp---------------");
	int max_logo_height = 130;
	int max_logo_width = 200;

	int user_id = SessionUtils.getIntSession(session, SystemProp.USER_ID);
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
		response.sendRedirect("index.jsp");
		return;
	}
	System.out.println("UserID: " + user.getUserID().intValue());
%>
<div id="brndbotMain">
	<div id="brndbotHeader">
		<div id="mainLogo" class="unit size1of5 lastUnit">
			&nbsp;
		</div>
	</div> <!-- brndbotHeader -->

	<div id="brndbotBody">
		<div class="topRow">
			<div id="leftDashColumn" style="width:15.625rem" class="unit">
				<table id="imageWrapper">
					<tr>
			    		<td style="vertical-align: middle;">
			    			<div style="text-align:center">
								<%=UserLogo.getBoundLogo(user_id, max_logo_height, max_logo_width)%>
							</div>
						</td>
					</tr>
				</table>
			</div>

			<div id="centerDashColumn" class="titleRow unit lastUnit">
			    <div class="mainContentHeader" style="width:60rem">
			    	<div class="subContentHeader">
			    		<div style="padding-bottom:1.25rem">
							<div class="unit size2of3">
						    	Your <span id="contentType">&nbsp;History</span>
							</div>
					    	<div class="unit lastUnit">
								<img src="images/search.png" alt="" />
					    	</div>
					    </div>
					</div>
		    	</div>
			</div> <!-- end of centerDashColumn -->

		</div> <!--  end of toprow -->

		<div style="clear:both;line-height:.0625rem">
			&nbsp;
		</div>

<%@include file="sidebar.jsp" %>

		<div class="unit size3of5" style="padding-left:9.4375rem">
		    <div class="listBackground">

<!-- 			<div id="wait">
					<div style="padding-top:0.625rem;text-align:center">
						<img src="images/loader.gif" alt="" />
					</div>
				</div>
-->
 				<div id="emailHere" style="display:none;border-color:#ffffff"></div>
				<div id="facebookHere" style="display:none;border-color:#ffffff"></div>
				<div id="twitterHere" style="display:none;border-color:#ffffff"></div>
			</div>  <!-- end listBackground -->
		</div>

		<div id="rightColumn" class="unit lastUnit">
			&nbsp;
		</div> <!-- end rightColumn -->

	</div>  <!-- end brndbotBody -->
</div> <!-- end brndbotMain -->

<script type="text/javascript">
	var SB = new Sidebar('emailChannel');

	var emailType = <%=HistoryType.EMAIL.getValue().intValue() %> - 1;
	var facebookType = <%=HistoryType.FACEBOOK.getValue().intValue() %> - 1;
	var twitterType = <%=HistoryType.TWITTER.getValue().intValue() %> - 1;

</script>

</body>
</html>
