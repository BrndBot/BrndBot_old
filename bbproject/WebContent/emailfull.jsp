<!DOCTYPE html>
<html>
<%@ page session="true"%>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <title>Brndbot to Email</title>
    <meta charset="utf-8">

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
<!-- <link href="styles/kendo.default.min.css" rel="stylesheet">  -->
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
    <script type="text/javascript" src="js/finaltouches.js"></script>
</head>
<body>
<% 
	System.out.println("-------------Entering facebookfull.jsp---------------");
	int CHANNEL = Utils.getIntSession(session, SessionUtils.CHANNEL_KEY);
	int FUSED_IMAGE_ID = Utils.getIntSession(session, SessionUtils.FUSED_IMAGE_ID_KEY);
	System.out.println("CHANNEL: " + CHANNEL);
	System.out.println("FUSED_IMAGE_ID: " + FUSED_IMAGE_ID);
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
				<div style="text-align:left">
					<form id="finalForm" action="post">
						<div class="formHeader">
							Email Final Touches
						</div>
				    	<div class="formLabelFor ftPrompt" style="padding-top:1.9375rem">
				        	<label for="subject">subject</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" class="formTextInput" autocomplete="off" id="subject" />
				       	</div>
				    	<div class="formLabelFor ftPrompt">
				        	<label for="emailLists">add email lists</label>
				       	</div>
				       	<div class="formSpacer" style="font-weight:normal;">
				       	<!-- If you change the option value text, change the function finaltouches.js.emailListSelected() -->
					      <select id="emailLists" style="width:20rem">
					            <option>Active customers</option>
					            <option>Inactive customers</option>
					            <option>All customers</option>
					            <option>Upload list of email addresses</option>
					    	</select>
				       	</div>
				    	<div class="formLabelFor ftPrompt">
				        	<label for="fromName">from name</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" size=25 class="formTextInput" id="fromName" />
				       	</div>
				    	<div class="formLabelFor ftPrompt">
				        	<label for="fromEmail">from email</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" size=25 class="formTextInput" id="fromEmail" />
				       	</div>
				    	<div class="formLabelFor ftPrompt">
				        	<label for="replyToEmail">reply-to email</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" size=25 class="formTextInput" id="replyToEmail" />
				       	</div>
					</form>
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
	</div>
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

var json_blocks = <%=session.getAttribute(SessionUtils.BLOCKS) %>;

</script>
</body>
</html>
