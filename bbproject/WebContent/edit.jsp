<!DOCTYPE html>
<html>
<!--
All rights reserved by Brndbot, Ltd. 2015
-->
<%@ page session="true" %>

<%@ page import="com.brndbot.block.Block" %>
<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.block.FBStyleType" %>
<%@ page import="com.brndbot.jsphelper.BenchHelper" %>
<%@ page import="com.brndbot.jsphelper.BlockRenderer" %>
<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.promo.Client" %>
<%@ page import="com.brndbot.client.ClientInterface" %>
<%@ page import="com.brndbot.system.Assert" %>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.db.ImageType" %>
<%@ page import="com.brndbot.db.Palette" %>
<%@ page import="com.brndbot.db.User" %>
<%@ page import="com.brndbot.db.UserLogo" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page import="org.slf4j.LoggerFactory" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
	final Logger logger = LoggerFactory.getLogger(this.getClass());
%>

<head>
	<link href='http://fonts.googleapis.com/css?family=Open+Sans:400,700' rel='stylesheet' type='text/css'>
    <title>Brndbot Builder</title>
    <meta charset="utf-8">

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    	<!--  use the Kendo "flat" style -->
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="styles/jquery.Jcrop.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
    <link href="css/bench.css" rel="stylesheet">
    <link href="css/klist.css" rel="stylesheet">
	
	<!--  These include the Adobe Proximo Nova font -->
	<!-- <script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>
	-->

<c:set var="sessionOK" value="1" scope="page"/>

<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>

<%
/* Get a system property to decide whether the debug features are on. 1 means they're on. */
%>
<c:set var="debug_mode" value="<%= SystemProp.get(SystemProp.DEBUG_MODE) != null ? 1 : 0 %>" scope="page"/>
<% 
/* Get parameters */
%>
<c:set var="tmp_channel" value='${param.channel}' scope="page" />
<c:set var="proto_name" value='${param.proto}' scope="page" />
<c:set var="model_name" value='${param.model}' scope="page" />

<c:set var="channel_email" value ="<%= ChannelEnum.CH_EMAIL.getValue() %>" scope="page"/>
<c:set var="channel_facebook" value ="<%= ChannelEnum.CH_FACEBOOK.getValue() %>" scope="page"/>
<c:if test="${tmp_channel <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="home.jsp"/>
</c:if>

<c:if test="${sessionOK != 0}">	<!-- encompasses whole rest of body -->


<jsp:useBean id="benchHelper" 
		class="com.brndbot.jsphelper.BenchHelper" 
		scope="page">
	<jsp:setProperty name="benchHelper" property="userId" value="${sessionScope.brndbotuser_id}"/>
	<jsp:setProperty name="benchHelper" property="channel" value="${tmp_channel}"/>
	<jsp:setProperty name="benchHelper" property="organization" value="${sessionScope.brndbotorg}"/>
	<jsp:setProperty name="benchHelper" property="promoProto" value="${proto_name}"/>
	<jsp:setProperty name="benchHelper" property="modelName" value="${model_name}"/>
</jsp:useBean>

    <script type="text/javascript" src="scripts/jquery-2.1.3.js"></script>
    <script type="text/javascript" src="scripts/kendo.all.min.js"></script>
    <script type="text/javascript" src="scripts/fabric.min.js"></script>
    <script type="text/javascript" src="scripts/jquery.Jcrop.min.js"></script>
	<script type="text/javascript" src="js/session.js"></script>
    <script type="text/javascript" src="js/block.js"></script>
    <script type="text/javascript" src="js/fieldmap.js"></script>
    <script type="text/javascript" src="js/ColorSelector.js"></script>
    <script type="text/javascript" src="js/BBModel.js"></script>
    <script type="text/javascript" src="js/BBStyle.js"></script>
    <script type="text/javascript" src="js/bench.js"></script>

    <%	 // tmp_channel indicates the type of editor needed (i.e. email, Facebook, Twitter, etc.)
         // Include the "editor-specific" javascript.  Each editor shares the javascript logic above,
    	 // but has it's own code in the respective javascript below.  There is an assumption in the shared
    	 // javascript above that certain functions will be implemented in each of the editor-specific files.
    %>
<c:choose>
  <c:when test="${tmp_channel == channel_email}">
    <script type="text/javascript" src="js/emailbench.js"></script>
  </c:when>
  <c:when test="${tmp_channel == channel_facebook}">
    <script type="text/javascript" src="js/fbbench.js"></script>
  </c:when>
</c:choose>

	<script type="text/javascript">
		// To share the enumerated values used on the server, we define local variables using the 
		//  same enumerated class variables.
		var EMAIL_CHANNEL = <jsp:text>${channel_email}</jsp:text>;
		var FACEBOOK_CHANNEL = <jsp:text>${channel_facebook}</jsp:text>;
	</script>

</head>
<body>

<jsp:useBean id="starting_block" class="com.brndbot.block.Block" scope="page" >
	<jsp:setProperty name="starting_block" property="channelType" value="${tmp_channel}"/>
	<jsp:setProperty name="starting_block" property="name" value="${proto_name}"/>
</jsp:useBean>
	<div id="brndbotMain" >
		<div id="benchBody">

			<div id="leftSideBar">
				<div style="position:relative">
					<!--  This is a Kendo UI widget for a tab control.  It follows a specific structure
					       required by the widget.
					-->
					<c:set var="icon_image" value="images/sidebar/Social-Editor_LeftButton.png" />
					<%@include file="sidebar.jsp" %>

					<!--  These are positioned using the position:relative for the parent style, and the 
					      position:absolute for these children styles.  This allows positioning of these child
					      divs in an absolute position relative to the parent.  Meaning, always exactly positioned
					      in a certain spot relative to wherever the parent resides.  This positioning uses top, bottom, right
					      and left.
					 -->
					<div id="applyDiv2">
						<button id="checkOutButton" onclick="bench.exportPresentation();" 
							style="font-size: 1rem;width:8rem" class="orangeButton" >
						Continue
						</button>
					</div>



				</div>
			</div>

					<div class="editorDiv">
						<div id="tabstrip2" style="float:left">
							<ul class="tabs" style="display:none">
							</ul>
							<div>
								<div class="editTab" id="contentPane">
									<div id="contentArea" ><!--edit fields go here--></div>
								</div>
								<div style="clear:both;line-height:0rem;">&nbsp;</div>
							</div>							
							<div> <!-- Layout tab -->
								<div class="editTab" id="stylePane" style="display:none">
								  <ul id="styleArea"><!--styles go here--></ul>
								</div>
								<div style="clear:both;line-height:0rem;">&nbsp;</div>
							</div>
						</div>		<!-- tabstrip2 -->
						<div style="float:right;position:relative;width:5rem;height:100%;background-color:#DBDEE0">
							<table style="width:100%;">
								<tr>
								<td class="tabButton activePaneButton" id="contentTabButton" class="activePaneButton">
								<img src="images/bench/EditButton.png" style="align:top" alt="Edit button">
								<br>EDIT
								</td>
								</tr><tr>
								<td class="tabButton" id="styleTabButton">
								<img src="images/bench/StyleButton.png" style="align:top" alt="Style button">
								<br>STYLE
								</td>
								</tr>
							</table>
						</div>
						<div style="float:none"></div>
					</div>			<!-- editorDiv -->

			<div class="benchHeader">
			<table><tr>
			<td style="min-width:25%; font-size:20px;">
			EDIT THIS POST
			</td>
			<td style="min-width:15%;" >
			<a class="goBackLink" href="channel.jsp?channel=0&proto=${proto_name}&model=${model_name}">go back</a>
			</td>
			<td style="min-width:45%">
			&nbsp;
			</td>
			<td style="min-width:15%">
			<button id="checkOutButton" onclick="bench.exportPresentation();" 
						style="font-size: 1rem;width:8rem" class="checkOutButton" >
			CONTINUE
			</button>
			</td>
			</tr></table>
			<div style="position:relative;">
			<div class="promoPane" id="promoViewHolder">
			<div style="position:relative;" >
 		<%
			// Options anticipated by the templates.  The templates are separate JSPs and expect thse variables
			//  to be instantiated.  So you'll see Eclipse think the templates have errors, but that's because
			//  the variables are defined here, in the main JSP.

			boolean templateVisible = true;
			boolean isPreview = true;
			// Must have the palette array, too.  Retrieves the palette set by the user when signed-up.
			//ArrayList<Palette> paletteArray = benchHelper.getUserPalette ();
			String chosenImg = "";

				// Args 2 and 3 are part of the mess regarding image bounding.  All these functions for 
				//  "getImage, getBoundLogo" etc should be assembled into a new class that does a great
				//  job on image resizing.  Future effort for image cropping could use th same class.  This is
				//  a mess currently.  Needs good requirements!
			chosenImg = UserLogo.getBoundLogo(benchHelper.getUserId(), 150, 150);

			templateVisible = false;
		%>
<%
		// This is where the editor structures placeholders for template/content.  This is where the layouts
		//  are filled in with data and shown.  For each slot, numbered from 1 to MAX_BLOCK_STACK_SIZE defined 
		//  in the JS below, any template type that can exist in that slow is included.  The templates are all
		//  hidden, but when content is chosen, the appropriate template for the appropriate slot in the stack
		//  is filled with the content from the blockStack array.  The blockStack array holds the data.  It relies
		//  on a static structure that accommodates all types of content.  
		//
		//  The template JSP files have fields that also follow a strict naming convention so that locations 
		//  to place data can be found by the generic editor functions.
		//
		//  Some templates use templateEnum as a tie-breaker used in the ID fields of its respective HTML.  Any
		//  template that uses templateEnum is expected by the generic editor JS to have a few characteristics:
		//
		//   1.  There can be more than one of the type of object in the stack.  For example, since the workshop
		//       template for email uses templateEnum in the naming convention of its IDs, there can be more than
		//       one workshop in an email.
		//   2.  This object can be moved up or down in the stack.  That means that it should have the up and down
		//       image controls defined in the template.
		//   3.  Likewise, templates that do not use templateEnum are singletons (can only have 1 template for
		//       that type of object) and it cannot be moved up or down.

		
		// IMPORTANT:  The number of sections included here for sets of templates must equal the value of the
		// JS variable MAX_BLOCK_STACK_SIZE defined below!
		int templateEnum = 1;
%>

	    <%  // Whatever is included here defines what type of data can be displayed in this particular slot
	    	//  of the editor.  Currently, each slow may contain all types of data/templates, but it doesn't 
	    	//  have to be that way. 

		%>
			<c:set var="templateEnum" value="1" scope="page"/> 

			<div id="finishedImage">

				<% // Here's where the promotion goes
				%>
				<canvas id="finishedImage1" ></div>


			</div><!-- id="finishedImage">  -->

			</div>		<!-- #promoView ->
			</div>		<!-- .promoPane -->
			
			<div class="promoPane" id="imagePickerHolder" style="visibility:none">
				<!-- Image picker will go here -->
			</div>
			</div>		<!-- relative --> 
			<div class="unit lastUnit">
				&nbsp;
			</div>

			<!-- This div is the popup for choosing the type of content (workshop, class, teacher) from
			       a Kendo list widget.
			 -->
			<div id="contentPopup" class="rounded" style="display:none;">
			    <div>
			    	<div class="unit size4of5 channelTitle">
			    		Choose a <span id="promoteThisTxt">&nbsp;workshop</span>.
			    	</div>
			    	<div id="closeContentPopup" class="rightUnit" style="margin-top: -0.875rem; margin-right: -0.625rem"> 
			    		<img src="images/Cancel-Button.png" alt="Cancel" />
			    	</div>
			    </div>
			    <div style="clear:both;line-height:0rem">
			    	&nbsp;
			    </div>
			    <!--  Each of these divs gets initialized as a Kendo list widget. -->
			    <div style="background-color: #ffffff">
					<div id="classHere" style="display:none;">Loading data, one moment please...</div>
					<div id="workshopHere" style="display:none;">Loading data, one moment please...</div>
					<div id="staffHere" style="display:none;">Loading data, one moment please...</div>
			    </div>
			</div>
	
			<!-- This div is the popup for choosingan image from the gallery.  There is a known bug in this
				 version of the software.  You cannot use the Kendo upload widget when placed on the pane of
				 a Kendo tab control widget.  There are new designs for this to avoid this bug.
			 -->
			<div id="imageGalleryPopup" class="rounded" style="display:none;">
			    <div>
			    	<div class="unit size4of5 channelTitle" style="padding:10px 0 0 10px">
			    		Image gallery and upload
			    	</div>
			    	<div id="closeGalleryPopup" class="rightUnit" style="margin-top: 0rem; margin-right: 0.1rem"> 
			    		<img src="images/Cancel-Button.png" alt="Cancel" />
			    	</div>
			    </div>
			    <div style="clear:both;line-height:0rem">
			    	&nbsp;
			    </div>
				<div id="galleryTabs" class="rounded">
					<ul>
						<li id="images" class="k-state-active">
							Your images
						</li>
						<li id ="logos">
							Your logos
						</li>
						<li id="stock">
							Stock images
						</li>
						<li id="upload" style="font-weight:bold;font-style:italic">
							<span style="color:red;">Upload</span>
						</li>
					</ul>
					<div>
						<div class="galleryPane"> <!-- Your images -->
	 						<div id="yourImagesView"></div>
	 						<div id="yourImagesPager" class="k-pager-wrap"></div>
						</div>
					</div>
					<div>
						<div class="galleryPane"> <!-- Logos -->
	 						<div id="yourLogosView"></div>
						</div>
					</div>
					<div>
						<div class="galleryPane"> <!-- Stock images -->
	 						<div id="yourStockView"></div>
						</div>
					</div>
					<div>
						<div style="padding:50px"> <!-- Upload -->
					    	<div  class="formLabelFor">
					        	<label for="imageTypeList">type of image</label>
					       	</div>
					        <select id="imageTypeList" multiple="multiple" 
					        	style="border-width:1px;font-size:1rem;width:300px;" data-placeholder="Choose the type of image to upload...">
					            <option>An image or photo</option>
					            <option>A teacher photo</option>
 					            <option>A logo</option>
					        </select>
							<div style="padding-top:30px">
								<div id="logoCrossHairs" style="margin-left:6.5rem;">
									<div style="position:relative;">
										<img id="imageTarget" src="images/image-target.png" alt="Image Target" />
										<div id="filesHome" style="top:2.375rem;left:1.125rem;position:absolute">
											<input class="greyButton" name="files" id="files" type="file" />
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
			    </div>
			    <div style="clear:both;line-height:0rem">
			    	&nbsp;
			    </div>
			</div>

			<!-- Hidden form used to post the blockStack array of data assembled in the editor to 
			     the server, which puts it on the session for later reuse if needed.
			 -->
			<form id="blockForm" method="post">
				<input id="hiddenBlocks" name="hiddenBlocks" type="text" style="display:none" />
			</form>

			<!-- Hidden form used to post data for fusing the image and HTML into a single image. -->
			<form id="htmlForm" method="post">
				<input id="hiddenHtml" name="hiddenHtml" type="text" style="display:none" />
			</form>

		</div>


		<script type="text/x-kendo-template" id="imageTemplate">
			<div class="photoBlock">
				<table id="imageWrapper" style="width:100%;height:100%">
					<tr>
			    		<td class="super-centered">
							<img  id="#:ID#" src="#:url#" alt="" height="#:height#" width="#:width#" />
						</td>
					</tr>
				</table>
			</div>
		</script>

	
	</div> <!-- brndbotMain -->

	<!-- Template for cropping modal window -->
	<div display="none">
		<div id="cropWindow">
			<img class="cropImage">
		</div>
	</div>
	
	<!-- Template for image gallery modal window -->
	<div display="none"
		<div id="galleryWindow">
			<ul id="imageGallery" style="border-style:none"></ul>
		</div>
	</div>

	<% /* Invisible holder for palette colors */ %>
	<c:forEach var="color" items="${benchHelper.userPaletteColors}">
			<div class="hiddenPalette" data-color="${color}">
			</div>
	</c:forEach>


	<script type="text/javascript" src="js/benchcontent.js"></script>
	<script type="text/javascript" src="js/benchstyle.js"></script>

	<!-- KENDO TEMPLATES -->
	
<script type="text/x-kendo-template" id="galleryTemplate">
	<li class="galleryPickerField" style="float:left;padding:20px;list-style-type:none">
		<div>
			<img src="ImageServlet?brndbotimageid=2&img=#:ID#" style="">
			<button class="useThisImage" data-id="#:ID#">USE THIS IMAGE</button>
		</div>
	</li>
</script>

<!-- Script for generating the data to populate the editor pane. 
     Big, but apparently you can't put a Kendo template in a separate file. -->
<script type="text/x-kendo-template" id="contentFieldsTemplate">
	<div class="contentfield"  data-linkedfield="#:fieldid#">
		<div data-linkedfield="#:fieldid#" class="fieldname">#:fieldname#</div>

		# if (styleType == 'text') {   #
                <div class="editTextArea" >
                        <textarea data-linkedfield="#:fieldid#"
							onfocus="benchcontent.updatePrototypeText(this)" rows="4" 
							style="width:98%">#:content#
						</textarea>
                </div>
                <div class="collapsible">Font size</div>
                <div class="editTextArea collapsible" >
                    <input type="number" data-linkedfield="#:fieldid#"
							onfocus="benchcontent.updatePrototypePointSize(this)" 
							style="width:98%" value="#:ptsize#">
                </div>
                <div class="collapsible">
                	<label>Typeface
                	<select data-linkedfield="#:fieldid#"
                			onchange="benchcontent.updatePrototypeTypeface(this)">
                		<option value="serif" selected>Serif</option>
                		<option value="sans-serif">Sans Serif</option>
                	</select>
                	</label>
                </div>
                <div class="collapsible">
                	<label><input type="checkbox" data-linkedfield="#:fieldid#" #:italicChecked#
                			onchange="benchcontent.updatePrototypeItalic(this)">
                		Italic
                	</label>
                </div>		
                <div class="collapsible">
                	<label><input type="checkbox" data-linkedfield="#:fieldid#" #:boldChecked#
                			onchange="benchcontent.updatePrototypeBold(this)">
                		Bold
                	</label>
                </div>	
				<div class="collapsible">
					<label>Color
						<button type="button" data-linkedfield="#:fieldid#" 
							class="colorSelectButton"
							style="background-color:#:color#"
							onclick="benchcontent.colorSelector.showHideColorSelect(this)"></button>
					</label>
				</div>
				<div class="collapsible" id="#:fieldid#-select" style="display:none">
					<table>
						<tr>
						<c:forEach var="color" items="${benchHelper.userPaletteColors}">
							<td class="paletteButton">
								<button type="button" style="background-color:${color}"
									data-linkedfield="#:fieldid#"
									data-color="${color}"
									onclick="benchcontent.colorSelector.setToPaletteColor(this, benchcontent.setColor)">
								</button>
							</td>
						</c:forEach>
						</tr>
						<tr>
						<td class="paletteButton">
							<button type="button" style="width:40px;height:15px;font-size:60%;" 
									name="color" onclick="benchcontent.colorSelector.showHideColorPicker(this)">
								Custom
							</button>
						</td><td colspan="2">
							<input style="height:15px;display:none" type="color" 
								onchange="benchcontent.colorSelector.setToInputColor(this, benchcontent.setColor)"
								data-linkedfield="#:fieldid#">
						</td>
					</tr></table>
				</div>
				<div class="collapsible">
					<table>
						<tr><td colspan="3">
						<label><input type="checkbox" data-linkedfield="#:fieldid#" #:dropShadowChecked#
        		    		onchange="benchcontent.updatePrototypeDropShadow(this)">
        	    		Drop shadow
			           	</label>
			           	</td></tr>
			           	<tr>
			           	<td>
			           		<label> H
		                    <input type="number" data-linkedfield="#:fieldid#" #:dropShadowDisabled#
		                    	class="dsh"
								onfocus="benchcontent.updatePrototypeDropShadowH(this)" 
								style="width:50%" value="#:dropShadowH#">
							</label>
			           	</td>
			           	<td>
			           		<label> V
		                    <input type="number" data-linkedfield="#:fieldid#" #:dropShadowDisabled#
		                    	class="dsv"
								onfocus="benchcontent.updatePrototypeDropShadowV(this)" 
								style="width:50%" value="#:dropShadowV#">
							</label>
			           	</td>
			           	<td>
			           		<label> Blur
		                    <input type="number" data-linkedfield="#:fieldid#" #:dropShadowDisabled#
		                    	class="dsb"
								onfocus="benchcontent.updatePrototypeDropShadowBlur(this)" 
								style="width:50%" value="#:dropShadowBlur#">
							</label>
			           	</td>
			           	<tr>
		           	</table>
				</div>
				
                <p>&nbsp;</p>	
		# } #		<!-- text -->
		# if (styleType == 'image') {   #
				<table class="collapsible"><tr>
				<td>
				<button type="button" style="width:70px;height:20px;font-size:85%;" 
					data-linkedField="#:fieldid#"
					onclick="benchcontent.showCrop(this);">
					Cropping
				</button>
				</td><td>
				<button type="button" style="width:70px;height:20px;font-size:85%;" 
					data-linkedField="#:fieldid#"
					onclick="benchcontent.pickImage(this);">
					Select
				</button>
				</td>
				</tr></table>
		# } #		<!-- image -->
		# if (styleType == 'block') {   #
		<div>
			<label>Color
				<button type="button" data-linkedfield="#:fieldid#" 
					class="colorSelectButton"
					style="background-color:#:color#"
					onclick="benchcontent.colorSelector.showHideColorSelect(this)"></button>
			</label>
		</div>
		<div class="collapsible" id="#:fieldid#-select" style="display:none">	<!-- color control -->
			<table>
				<tr>
				<c:forEach var="color" items="${benchHelper.userPaletteColors}">
					<td class="paletteButton">
						<button type="button" style="background-color:${color}"
							data-linkedfield="#:fieldid#"
							data-color="${color}"
							onclick="benchcontent.colorSelector.setToPaletteColor(this, benchcontent.setColor)">
						</button>
					</td>
				</c:forEach>
				</tr>
				<tr>
				<td class="paletteButton">
					<button type="button" style="width:40px;height:15px;font-size:60%;" 
							name="color" onclick="benchcontent.colorSelector.showHideColorPicker(this)">
						Custom
					</button>
				</td><td colspan="2">
					<input style="height:15px;display:none" type="color" 
						onchange="benchcontent.colorSelector.setToInputColor(this, benchcontent.setColor)"
						data-linkedfield="#:fieldid#">
				</td>
			</tr></table>
		</div>							<!-- color control -->
		<div class="collapsible">
			<table>
				<tr><td colspan="3">
				<label><input type="checkbox" data-linkedfield="#:fieldid#" #:dropShadowChecked#
      		    		onchange="benchcontent.updatePrototypeDropShadow(this)">
      	    		Drop shadow
	           	</label>
	           	</td></tr>
	           	<tr>
	           	<td>
	           		<label> H
                    <input type="number" data-linkedfield="#:fieldid#" #:dropShadowDisabled#
                    	class="dsh"
						onchange="benchcontent.updatePrototypeDropShadowH(this)" 
						style="width:50%" value="#:dropShadowH#">
					</label>
	           	</td>
	           	<td>
	           		<label> V
                    <input type="number" data-linkedfield="#:fieldid#" #:dropShadowDisabled#
                    	class="dsv"
						onchange="benchcontent.updatePrototypeDropShadowV(this)" 
						style="width:50%" value="#:dropShadowV#">
					</label>
	           	</td>
	           	<td>
	           		<label> Blur
                    <input type="number" data-linkedfield="#:fieldid#" #:dropShadowDisabled#
                    	class="dsb"
						onchange="benchcontent.updatePrototypeDropShadowBlur(this)" 
						style="width:50%" value="#:dropShadowBlur#">
					</label>
	           	</td>
	           	<tr>
           	</table>
		</div>
        <p>&nbsp;</p>	
		# } #		<!-- block -->
		<% /* Debug feature: Adjust block size */ %>
		<c:if test="${debug_mode != 0}">
			<table>
			<tr>
				<td>X</td>
				<td><input type="number" class="editTextArea" data-linkedfield="#:fieldid#"
						onfocus="benchcontent.updateXPos(this)" 
							style="width:98%" value="#:x#">
				</td>
			</tr>
			<tr>
				<td>Y</td>
				<td><input type="number" class="editTextArea" data-linkedfield="#:fieldid#"
						onfocus="benchcontent.updateYPos(this)" 
							style="width:98%" value="#:y#">
				</td>
			</tr>
			<tr>
				<td>Width</td>
				<td><input type="number" class="editTextArea" data-linkedfield="#:fieldid#"
						onfocus="benchcontent.updateWidth(this)" 
							style="width:98%" value="#:width#"
				</td>
			</tr>
			<tr>
				<td>Height</td>
				<td><input type="number" class="editTextArea" data-linkedfield="#:fieldid#"
						onfocus="benchcontent.updateHeight(this)" 
							style="width:98%" value="#:height#"
				</td>
			</tr>
			</table>
		</c:if>

	</div>
</script>



<script type="text/x-kendo-template" id="styleFieldsTemplate">
	<li class="stylefield" data-linkedstyle="#:name#"
			onclick="benchstyle.updateStyle(this)">
		<canvas id="styleCanvas#:name#" ">
	</li>
</script>
	
</c:if>		<!-- sessionOK -->

</body>
</html>
