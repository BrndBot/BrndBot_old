<!DOCTYPE html>
<html>
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
    <title>Brndbot Builder</title>
    <meta charset="utf-8">

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    	<!--  use the Kendo "flat" style -->
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
    <link href="css/bench.css" rel="stylesheet">
    <link href="css/klist.css" rel="stylesheet">
	
	<!--  These include the Adobe Proximo Nova font -->
	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

<c:set var="sessionOK" value="1" scope="page"/>

<c:if test="${empty sessionScope.brndbotuser_id || sessionScope.brndbotuser_id <= 0}">
	<c:set var="sessionOK" value="0" scope="page"/>
	<c:redirect url="index.jsp"/>
</c:if>
<% 
/* Get parameters */
%>
<c:set var="tmp_channel" value='${param.channel}' scope="page" />
<c:set var="proto_name" value='${param.proto}' scope="page" />
<c:set var="model_name" value='${param.model}' scope="page" />

<c:set var="channel_email" value ="<%= ChannelEnum.CH_EMAIL %>" scope="page"/>
<c:set var="channel_facebook" value ="<%= ChannelEnum.CH_FACEBOOK %>" scope="page"/>
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
<%	benchHelper.setSession (session);	// Need Java to set binary values %>

    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
    <script type="text/javascript" src="js/block.js"></script>
    <script type="text/javascript" src="js/fieldmap.js"></script>
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
		var CHANNEL = ${tmp_channel};
	</script>

</head>
<body>
<%


	// Fill in the block for the content object chosen by the user on the dashboard.  See the JS below
	// for how the block JS object is used.
	//request.setAttribute ("starting_block", benchHelper.getStartingBlock());
%>
<jsp:useBean id="starting_block" class="com.brndbot.block.Block" scope="page" >
	<jsp:setProperty name="starting_block" property="channelType" value="${tmp_channel}"/>
	<jsp:setProperty name="starting_block" property="name" value="${proto_name}"/>
</jsp:useBean>
	<div id="brndbotMain" style="background-color:#f4f4f4;">
		<div id="brndbotHeader">
			&nbsp;
		</div> <!-- brndbotHeader -->
		<div id="benchBody">

			<div id="leftSideBar">
				<div style="position:relative">
					<!--  This is a Kendo UI widget for a tab control.  It follows a specific structure
					       required by the widget.
					-->
					<div class="editorDiv">
						<div id="tabstrip2" class="rounded">
							<ul class="tabs">
								<li class="k-state-active">
									Content
								</li>
								<li>
									Design
								</li>
								<li>
									Style
								</li>
							</ul>
							<div>
								<div style="width:100%;height:32rem;background-color: #ffffff;margin-bottom:0.9375rem">
									<div id="workArea"><!--edit fields go here--></div>
								</div>
								<!--  These get hidden or shown depending on the button selections made.
								      Actually, we won't use these at all, but leave one around
								      as an example to work from. -->
								<div id="toClassID" style="float:left;display:none">
									<button id="toNonClassButton" style="font-size: 1rem;width:7.5rem" 
										class="greenButton rounded" >non-feature</button>
								</div>
								<div style="clear:both;line-height:0rem;">&nbsp;</div>
							</div>							
							<div> <!-- Design tab -->
								<div style="padding-top: 3rem;height: 32rem;">

								  <c:choose>
									<c:when test="${tmp_channel == channel_email}">
										<%@include file="templates/email/design.jsp" %>
									</c:when>
									<c:when test="${tmp_channel == channel_facebook}">
										<%@include file="templates/facebook/design.jsp" %>
									</c:when>
								  </c:choose>

								</div>
							</div>
							<div> <!-- Layout tab -->
								<div style="padding-top: 3rem;height: 32rem;">

								  <c:choose>
									<c:when test="${tmp_channel == channel_email}">
										<%@include file="templates/email/layout.jsp" %>
									</c:when>
									<c:when test="${tmp_channel == channel_facebook}">
										<%@include file="templates/facebook/layout.jsp" %>
									</c:when>
								  </c:choose>

								</div>
							</div>
						</div>		<!-- tabstrip2 -->
					</div>			<!-- editorDiv -->
					<!-- Add new blocks menu.  This is another Kendo widget, so the structure is required to
					     initialize the widget correctly.
					 -->
					<div id="tabstrip" class="rounded">
						<ul>
							<li class="k-state-active">
								Generic
							</li>
						</ul>
						<div>
							<!-- The images for each choice come from the styles. -->
							<!-- ****** REPLACE THIS WITH A SERIES OF LINKS BASED ON 
							     THE AVAILABLE MODELS
							-->
							<div class="linkText" style="padding-left:0.1rem;">
								<c:out escapeXml="false" value="${benchHelper.renderModelLinks}"/>

								<div id="newSocialLink" class="formSpacerLite addLink">
									Social Buttons
								</div>
								<div id="newTextLink" class="formSpacerLite addLink">
									Intro Header
								</div>
								<div id="newGraphicLink" class="formSpacerLite addLink">
									Logo Header
								</div>
								<div id="newFooterLink" class="formSpacerLite addLink">
									Footer
								</div>
							</div>		<!-- linkText -->
						</div>
						<div>
							<div class="linkText" style="padding-left:0.1rem;">
								<div id="genericTextBlockLink" class="formSpacerLite addLink">
									Text
								</div>
								<div id="genericImageBlockLink" class="formSpacerLite addLink">
									Pictures / Images
								</div>
								<div id="genericWebBlockLink" class="formSpacerLite addLink">
									Web site link
								</div>
								<div class="formSpacerLite addLink">
									&nbsp;
								</div>
								<div class="formSpacerLite addLink">
									&nbsp;
								</div>
								<div class="formSpacerLite addLink">
									&nbsp;
								</div>
								<div class="formSpacerLite addLink">
									&nbsp;
								</div>
								<div class="formSpacerLite addLink">
									&nbsp;
								</div>
							</div>
						</div>
					</div>

	 				<div id="addNewBlock2">
	 					<div style="text:center">
							Edit<span id="editType"></span>
						</div>
					</div>
					<!--  These are positioned using the position:relative for the parent style, and the 
					      position:absolute for these children styles.  This allows positioning of these child
					      divs in an absolute position relative to the parent.  Meaning, always exactly positioned
					      in a certain spot relative to wherever the parent resides.  This positioning uses top, bottom, right
					      and left.
					 -->
					<div id="applyDiv2">
						<button id="checkOutButton" style="font-size: 1rem;width:8rem" class="orangeButton" >continue</button>
					</div>

					<div id="applyDiv">
						<button id="applyButton" style="font-size: 1rem;width:8rem" class="greenButton rounded" >apply change</button>
					</div>

				</div>
			</div>
			<!-- End add new blocks menu -->

			<div id="addNewBlock">
				Add New Block
			</div>

			<div class="rounded benchHeader">

			<c:choose>
			  <c:when test="${tmp_channel == channel_email}">
				<div class="emailBenchHeader">
					<span style="padding-left:1.5rem">
						<img src="images/bench/emailIcon.png" alt="" />
					</span>
					&nbsp;Email Message Builder
				</div>
			  </c:when>
			  <c:when test="${tmp_channel == channel_facebook}">
				<div class="emailBenchHeader">
					<span style="padding-left:1.5rem">
						<img src="images/bench/emailIcon.png" alt="" />
					</span>
					&nbsp;Facebook Message Builder
				</div>
			  </c:when>
			</c:choose>
			  <div style="position:relative;"><div style="position:absolute;top:36px;"><div style="position:relative;" id="promoview">
 		<%
			// Options anticipated by the templates.  The templates are separate JSPs and expect thse variables
			//  to be instantiated.  So you'll see Eclipse think the templates have errors, but that's because
			//  the variables are defined here, in the main JSP.

			boolean templateVisible = true;
			boolean isPreview = true;
			// Must have the palette array, too.  Retrieves the palette set by the user when signed-up.
			//ArrayList<Palette> paletteArray = User.getUserPalette(benchHelper.getUserId(), benchHelper.getConnection());
			ArrayList<Palette> paletteArray = benchHelper.getUserPalette ();
			String chosenImg = "";

				// Args 2 and 3 are part of the mess regarding image bounding.  All these functions for 
				//  "getImage, getBoundLogo" etc should be assembled into a new class that does a great
				//  job on image resizing.  Future effort for image cropping could use th same class.  This is
				//  a mess currently.  Needs good requirements!
			chosenImg = UserLogo.getBoundLogo(benchHelper.getUserId(), 150, 150);
/*			else if (CHANNEL.equals(ChannelEnum.FACEBOOK))
			{
				static public String getBoundImage(
						String local_image_file_name, 
						int max_img_height, 
						int max_img_width)

				chosenImg = UserLogo.getBoundImage("images/barre-1.jpg", 500, 500, true);
				logger.debug("chosenImg: {}", chosenImg);
			}
*/
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
			<c:choose>
				<c:when test="${tmp_channel == channel_email}">
					<%@include file="templates/email/promo.jsp" %>
				</c:when>
				<c:when test="${tmp_channel == channel_facebook}">
					<%@include file="templates/facebook/promo.jsp" %>
				</c:when>
			</c:choose>

			<div id="finishedImage">

				<c:set var="templateEnum" value="2" scope="page"/> 
				  <c:choose>
					<c:when test="${tmp_channel == channel_email}">
						<%@include file="templates/email/promo.jsp" %>
					</c:when>
					<c:when test="${tmp_channel == channel_facebook}">
						<%@include file="templates/facebook/promo.jsp" %>
					</c:when>
				  </c:choose>

				  <c:set var="templateEnum" value="3" scope="page"/> 
				  <c:choose>
					<c:when test="${tmp_channel == channel_email}">
						<%@include file="templates/email/promo.jsp" %>
					</c:when>
					<c:when test="${tmp_channel == channel_facebook}">
						<%@include file="templates/facebook/promo.jsp" %>
					</c:when>
				  </c:choose>

					<%  // Currently, only email has more than 3 slots in the editor.
					%>
				  <c:set var="templateEnum" value="4" scope="page"/> 
				  <c:choose>
					<c:when test="${tmp_channel == channel_email}">
						<%@include file="templates/email/promo.jsp" %>
			  		</c:when>
				  </c:choose>


				  <c:set var="templateEnum" value="5" scope="page"/> 
				  <c:choose>
					<c:when test="${tmp_channel == channel_email}">
						<%@include file="templates/email/promo.jsp" %>
					</c:when>
				  </c:choose>

				  <c:set var="templateEnum" value="6" scope="page"/> 
				  <c:choose>
					<c:when test="${tmp_channel == channel_email}">
						<%@include file="templates/email/promo.jsp" %>
					</c:when>
				  </c:choose>

					<%  // end of if block for email editor-only promo.jsp inclusion
					%>
			</div><!-- id="finishedImage">  -->

			</div></div></div>	<!-- relative --> 
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
							<img  id="#:ID#" src="#:imgTag#" alt="" height="#:height#" width="#:width#" />
						</td>
					</tr>
				</table>
			</div>
		</script>

	
	</div> <!-- brndbotMain -->
	<script type="text/javascript" src="js/benchcontent.js"></script>
<script type="text/javascript">
// This is the naiscent "style" implementation.  The design and implementation of the "style" (aka "layout variation")
//  needs requirements before it can be done correctly.  At the time the implementation of this editor was halted,
//  "styles" was all over the map.

// It's probably all wrong, so rip it all out!! -- GDM


	// This function is called when all of page including HTML, JS and CSS are fully loaded.
	$(document).ready(function() 
	{
		// doc.ready init for the bench, in bench.js
		initTheBench();

		// ensure the top of the page is shown
		document.getElementById("brndbotMain").scrollIntoView();

		// Set up the editor for the initial pane 
		insertEditFields ($('#promoview'), $('#workArea'));
	});
</script>

<!-- Script for generating the data to populate the editor pane. 
     Big, but apparently you can't put a Kendo template in a separate file. -->
<script type="text/x-kendo-template" id="editFieldsTemplate">
	<div>
		# if (clazz == 'prmf_text') {   #
                <div class="editTextArea" >
                        <textarea data-linkedfield="#:fieldid#"
							onfocus="updatePrototypeText(this)" rows="4" 
							style="width:100%">#:content#
						</textarea>
                </div>
		# } #
	</div>
</script>
</c:if>		<!-- sessionOK -->

</body>
</html>
