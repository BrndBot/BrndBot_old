<!DOCTYPE html>
<html>
<%@ page session="true" %>

<%@ page import="com.brndbot.block.Block" %>
<%@ page import="com.brndbot.block.BlockStack" %>
<%@ page import="com.brndbot.block.BlockType" %>
<%@ page import="com.brndbot.block.ChannelEnum" %>
<%@ page import="com.brndbot.block.FBStyleType" %>
<%@ page import="com.brndbot.db.DbConnection" %>
<%@ page import="com.brndbot.system.Assert" %>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.user.ImageType" %>
<%@ page import="com.brndbot.user.Palette" %>
<%@ page import="com.brndbot.user.User" %>
<%@ page import="com.brndbot.user.UserLogo" %>

<%@ page import="java.util.ArrayList" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <title>Brndbot Builder</title>
    <meta charset="utf-8">

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">
    <link href="css/bench.css" rel="stylesheet">
    <link href="css/klist.css" rel="stylesheet">

	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

<%
	// Use this session variable to morph the bench for a particular channel
	int tmp_channel = Utils.getIntSession(session, SessionUtils.CHANNEL_KEY);
	if (tmp_channel == -1)
	{
		System.out.println("Channel is undefined, exiting bench.");
		response.sendRedirect("index.jsp");
		return;
	}
	ChannelEnum CHANNEL = ChannelEnum.create(tmp_channel);
	System.out.println("Channel in the bench: " + CHANNEL.getItemText() + " - " + CHANNEL.getValue().intValue());
%>

    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
    <script type="text/javascript" src="js/block.js"></script>
    <script type="text/javascript" src="js/session.js"></script>
    <script type="text/javascript" src="js/fieldmap.js"></script>
    <script type="text/javascript" src="js/bench.js"></script>

    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
    <script type="text/javascript" src="js/emailbench.js"></script>
	<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
    <script type="text/javascript" src="js/fbbench.js"></script>
	<% } %>

	<script type="text/javascript">
		var EMAIL_CHANNEL = <%=ChannelEnum.EMAIL.getValue().intValue() %>;
		var FACEBOOK_CHANNEL = <%=ChannelEnum.FACEBOOK.getValue().intValue() %>;
		var TWITTER_CHANNEL = <%=ChannelEnum.TWITTER.getValue().intValue() %>;
		var CHANNEL = <%=tmp_channel %>;
	</script>

</head>
<body>
<%
	System.out.println("-------------Entering bench.jsp---------------");
	int btype = Utils.getIntSession(session, SessionUtils.CONTENT_KEY);
	Assert.that(btype != 0 ,"No block type passed to bench.jsp.");
//	int id = Utils.getIntSession(session, SessionUtils.DATABASE_ID_KEY);
	int id = Utils.getIntParameter(request, SessionUtils.DATABASE_ID_KEY);
	Assert.that(id > 0 ,"No database id passed to bench.jsp.");

	int user_id = Utils.getIntSession(session, SessionUtils.USER_ID);
	if (user_id == 0)
	{
		System.out.println("USER NOT LOGGED IN, SENDING TO LOGIN PAGE");
		response.sendRedirect("index.jsp");
		return;
	}

	DbConnection con = DbConnection.GetDb();

	System.out.println(ChannelEnum.EMAIL + ", user: " + user_id + ", content: " + btype + ", db: " + id);

	int header_max_logo_width = CHANNEL.getDefaultImgWidth().intValue();
	int header_max_logo_height = CHANNEL.getDefaultImgWidth().intValue();  // same for now

	// Get the block chosen by the user on the dashboard
	Block starting_block = (Block)Block.getAsBlock(ChannelEnum.EMAIL, user_id, btype, id, header_max_logo_width);

%>
	<div id="brndbotMain" style="background-color:#f4f4f4;">
		<div id="brndbotHeader">
			&nbsp;
		</div> <!-- brndbotHeader -->
		<div id="benchBody">

			<div id="leftSideBar">
				<div style="position:relative">
				
					<div class="editorDiv">
						<div id="tabstrip2" class="rounded">
							<ul>
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
									<div id="workArea">

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/content.jsp" %>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
			<%@include file="templates/facebook/content.jsp" %>
		<% } %>

									</div>
								</div>
								<div id="toClassID" style="float:left;display:none">
									<button id="toNonClassButton" style="font-size: 1rem;width:7.5rem" 
										class="greenButton rounded" >non-feature</button>
								</div>
								<div id="toNonClassID" style="float:left;display:none">
									<button id="toClassButton" style="font-size: 1rem;width:7.5rem" 
										class="greenButton rounded" >featured</button>
								</div>
								<div id="toWorkshopID" style="float:left;display:none">
									<button id="toNonWorkshopButton" style="font-size: 1rem;width:7.5rem" 
										class="greenButton rounded" >non-feature</button>
								</div>
								<div id="toNonWorkshopID" style="float:none;display:none">
									<button id="toWorkshopButton" style="font-size: 1rem;width:7.5rem" 
										class="greenButton rounded" >featured</button>
								</div>
								<div style="clear:both;line-height:0rem;">&nbsp;</div>
							</div>							
							<div> <!-- Design tab -->
								<div style="padding-top: 3rem;height: 32rem;">

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/design.jsp" %>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
			<%@include file="templates/facebook/design.jsp" %>
		<% } %>

								</div>
							</div>
							<div> <!-- Layout tab -->
								<div style="padding-top: 3rem;height: 32rem;">

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/layout.jsp" %>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
			<%@include file="templates/facebook/layout.jsp" %>
		<% } %>

								</div>
							</div>
						</div>
					</div>
					<!-- Add new blocks menu -->
					<div id="tabstrip" class="rounded">
						<ul>
							<li class="k-state-active">
								Mindbody
							</li>
							<li>
								Generic
							</li>
						</ul>
						<div>
							<div class="linkText" style="padding-left:0.1rem;">
								<div id="newWorkshopLink" class="formSpacerLite addLink">
									Workshops
								</div>
								<div id="newClassLink" class="formSpacerLite addLink">
									Classes
								</div>
								<div id="newTeacherLink" class="formSpacerLite addLink">
									Teachers
								</div>
								<div id="newScheduleLink" class="formSpacerLite addLink">
									Schedule
								</div>
								<div id="newSocialLink" class="formSpacerLite addLink">
									Social Buttons
								</div>
								<div id="newTextLink" class="formSpacerLite addLink">
									Intro Header
								</div>
								<div id="newSaleLink" class="formSpacerLite addLink">
									Promotion / Sale
								</div>
								<div id="newGraphicLink" class="formSpacerLite addLink">
									Logo Header
								</div>
								<div id="newFooterLink" class="formSpacerLite addLink">
									Footer
							</div>
							</div>
						</div>
						<div>
							<div class="linkText" style="padding-left:0.1rem;">
								<div id="genericTextBlockLink" class="formSpacerLite addLink">
									Text
								</div>
								<div id="genericImageBlockLink" class="formSpacerLite addLink">
									Pictures / Images
								</div>
								<div id="genericVideoBlockLink" class="formSpacerLite addLink">
									Video link
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

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
				<div class="emailBenchHeader">
					<span style="padding-left:1.5rem">
						<img src="images/bench/emailIcon.png" alt="" />
					</span>
					&nbsp;Email Message Builder
				</div>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
				<div class="emailBenchHeader">
					<span style="padding-left:1.5rem">
						<img src="images/bench/emailIcon.png" alt="" />
					</span>
					&nbsp;Facebook Message Builder
				</div>
		<% } %>

 		<%
			// Options anticipated by the templates
			boolean templateVisible = true;
			boolean isPreview = true;
			// Must have the palette array, too
			ArrayList<Palette> paletteArray = User.getUserPalette(user_id, con);
//			Palette new1 = new Palette("#e67a1e");
//			Palette new2 = new Palette("#86cde5");
//		paletteArray.set(0, new1);
//			paletteArray.set(1, new2);
			String chosenImg = "";
			if (CHANNEL.equals(ChannelEnum.EMAIL)) 
			{
				chosenImg = UserLogo.getBoundLogo(user_id, 150, 150);
			}
/*			else if (CHANNEL.equals(ChannelEnum.FACEBOOK))
			{
				static public String getBoundImage(
						String local_image_file_name, 
						int max_img_height, 
						int max_img_width)

				chosenImg = UserLogo.getBoundImage("images/barre-1.jpg", 500, 500, true);
				System.out.println("CHOSEN IMAGE: " + chosenImg);
			}
*/
			templateVisible = false;
		%>
<%
			int templateEnum = 1;
%>

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/template-set.jsp" %>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
			<%@include file="templates/facebook/template-set.jsp" %>
		<% } %>

<%
			templateEnum = 2;
%>
		<div id="finishedImage">

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/template-set.jsp" %>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
			<%@include file="templates/facebook/template-set.jsp" %>
		<% } %>

<%
			templateEnum = 3;
%>

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/template-set.jsp" %>
		<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
			<%@include file="templates/facebook/template-set.jsp" %>
		<% } %>

<%
			templateEnum = 4;
%>

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/template-set.jsp" %>
		<% } %>

<%
			templateEnum = 5;
%>

	    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
			<%@include file="templates/email/template-set.jsp" %>
		<% } %>

<% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
<%
			templateEnum = 6;
%>
			<%@include file="templates/email/template-set.jsp" %>
<%
}  // end of if block for email editor-only template-set.jsp inclusion
	templateVisible = true;
	con.close();
%>
			</div><!-- id="finishedImage">  -->

			</div>
			<div class="unit lastUnit">
				&nbsp;
			</div>

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
			    <div style="background-color: #ffffff">
					<div id="classHere" style="display:none;">Loading data, one moment please...</div>
					<div id="workshopHere" style="display:none;">Loading data, one moment please...</div>
					<div id="staffHere" style="display:none;">Loading data, one moment please...</div>
			    </div>
			</div>
	
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
						<li id="teachers">
							Your teachers
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
						<div class="galleryPane"> <!-- Teacher photos -->
	 						<div id="yourTeachersView"></div>
							<div id="yourTeachersPager" class="k-pager-wrap"></div>
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

			<form id="blockForm" method="post">
				<input id="hiddenBlocks" name="hiddenBlocks" type="text" style="display:none" />
			</form>

			<!-- Hidden form used to post data for fusing the image -->
			<form id="htmlForm" method="post">
				<input id="hiddenHtml" name="hiddenHtml" type="text" style="display:none" />
			</form>

		</div>

<%@include file="mbListFormats.jsp" %>

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
<script type="text/javascript">

<% if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
	var IMAGE_AND_BANNER_OBJ = <%=FBStyleType.FB_IMAGE_AND_BANNER.getValue().intValue()%>;
	var TEXT_OVER_IMAGE_OBJ = <%=FBStyleType.FB_TEXT_OVER_IMAGE.getValue().intValue()%>;
	var LOGO_ONLY_OBJ = <%=FBStyleType.FB_LOGO_ONLY.getValue().intValue()%>;
<% } %>

	var EMAIL_CHANNEL = <%=ChannelEnum.EMAIL.getValue().intValue() %>;
	var FACEBOOK_CHANNEL = <%=ChannelEnum.FACEBOOK.getValue().intValue() %>;
	var CURRENT_CHANNEL = <%=CHANNEL.getValue().intValue() %>;
	
	var CLASS_OBJ = <%=BlockType.CLASS.getValue().intValue() %>;
	var WORKSHOP_OBJ = <%=BlockType.WORKSHOP.getValue().intValue() %>;
	var STAFF_OBJ = <%=BlockType.STAFF.getValue().intValue() %>;
	var SALE_OBJ = <%=BlockType.SALE.getValue().intValue() %>;
	var SCHEDULE_OBJ = <%=BlockType.SCHEDULE.getValue().intValue() %>;

	var CLIENT_OBJ = <%=BlockType.CLIENT.getValue() %>;
	var FINDER_OBJ = <%=BlockType.FINDER.getValue() %>;
	var SITE_OBJ = <%=BlockType.SITE.getValue() %>;
	var APPOINTMENT_OBJ = <%=BlockType.APPOINTMENT.getValue() %>;
	var TEXT_OBJ = <%=BlockType.TEXT.getValue() %>;
	var FOOTER_OBJ = <%=BlockType.FOOTER.getValue() %>;
	var SOCIAL_OBJ = <%=BlockType.SOCIAL.getValue() %>;
	var GRAPHIC_OBJ = <%=BlockType.GRAPHIC.getValue() %>;
	var NON_CLASS_OBJ = <%=BlockType.NONCLASS.getValue() %>;
	var NON_WORKSHOP_OBJ = <%=BlockType.NONWORKSHOP.getValue() %>;
	var WEB_LINK_OBJ = <%=BlockType.WEB_LINK.getValue() %>;
	var VIDEO_OBJ = <%=BlockType.VIDEO.getValue() %>;

	// These are for the lists of objects that popup when you click on
	//  an icon on the right pane.
	var idPrefix = new Array(17);
	idPrefix[CLASS_OBJ - 1] = '<%=BlockType.CLASS.getItemTextLowerCase()%>';
	idPrefix[WORKSHOP_OBJ - 1] = '<%=BlockType.WORKSHOP.getItemTextLowerCase()%>';
	idPrefix[STAFF_OBJ - 1] = '<%=BlockType.STAFF.getItemTextLowerCase()%>';
	idPrefix[CLIENT_OBJ - 1] = '<%=BlockType.CLIENT.getItemTextLowerCase()%>';
	idPrefix[FINDER_OBJ - 1] = '<%=BlockType.FINDER.getItemTextLowerCase()%>';
	idPrefix[SALE_OBJ - 1] = '<%=BlockType.SALE.getItemTextLowerCase()%>';
	idPrefix[SITE_OBJ - 1] = '<%=BlockType.SITE.getItemTextLowerCase()%>';
	idPrefix[APPOINTMENT_OBJ - 1] = '<%=BlockType.APPOINTMENT.getItemTextLowerCase()%>';
	idPrefix[SCHEDULE_OBJ - 1] = '<%=BlockType.SCHEDULE.getItemTextLowerCase()%>';
	idPrefix[TEXT_OBJ - 1] = '<%=BlockType.TEXT.getItemTextLowerCase()%>';
	idPrefix[FOOTER_OBJ - 1] = '<%=BlockType.FOOTER.getItemTextLowerCase()%>';
	idPrefix[SOCIAL_OBJ - 1] = '<%=BlockType.SOCIAL.getItemTextLowerCase()%>';
	idPrefix[GRAPHIC_OBJ - 1] = '<%=BlockType.GRAPHIC.getItemTextLowerCase()%>';
	idPrefix[NON_CLASS_OBJ - 1] = '<%=BlockType.NONCLASS.getItemTextLowerCase()%>';
	idPrefix[NON_WORKSHOP_OBJ - 1] = '<%=BlockType.NONWORKSHOP.getItemTextLowerCase()%>';
	idPrefix[WEB_LINK_OBJ - 1] = '<%=BlockType.NONWORKSHOP.getItemTextLowerCase()%>';
	idPrefix[VIDEO_OBJ - 1] = '<%=BlockType.NONWORKSHOP.getItemTextLowerCase()%>';

	// Variable content in the stack
	var masterFields = new Array(VIDEO_OBJ);

    <% if (CHANNEL.equals(ChannelEnum.EMAIL)) { %>
	var MAX_BLOCK_STACK_SIZE = 6;
	SESSION_CHANNEL = EMAIL_CHANNEL;
	<% } else if (CHANNEL.equals(ChannelEnum.FACEBOOK)) { %>
	var MAX_BLOCK_STACK_SIZE = 3;
	SESSION_CHANNEL = FACEBOOK_CHANNEL;
	<% } %>

	var blockStack = new Array();

	// Image types
	var IMAGE_TYPE_USER = <%=ImageType.USER_UPLOAD.getValue() %>;
	var IMAGE_TYPE_LOGO = <%=ImageType.DEFAULT_LOGO.getValue() %>;
	var IMAGE_TYPE_TEACHER = <%=ImageType.TEACHER_PHOTO.getValue() %>;
	var IMAGE_TYPE_STOCK = <%=ImageType.STOCK.getValue() %>;
	var IMAGE_TYPE_FUSED = <%=ImageType.FUSED_IMAGE.getValue() %>;

	var blockForm = $('#blockForm');

	// Singleton class for client-side session management
	var session_mgr = new Session();
	session_mgr.setImageID(IMAGE_TYPE_USER);

	$(document).ready(function() 
	{
		// If we were able to retrieve user's starting choice, run with it.
		<% if (starting_block != null) { %>
			var the_starting_block = new Block(
			<%=starting_block.get_channel_type().getValue().intValue() %>,
			<%=starting_block.get_block_type().getValue().intValue() %>,
			'<%=Utils.jsSafeStr(starting_block.get_block_type_name()) %>',
			<%=starting_block.get_database_id().intValue() %>,
			'<%=Utils.jsSafeStr(starting_block.get_name()) %>',
			'<%=Utils.jsSafeStr(starting_block.get_full_name()) %>',
			'<%=starting_block.get_starting_date() %>',
			'<%=starting_block.get_schedule_reference() %>',
			'<%=Utils.jsSafeStr(starting_block.get_description()) %>',
			'<%=Utils.jsSafeStr(starting_block.get_short_description()) %>',
			'<%=starting_block.get_img_url() %>'
			);

			// doc.ready init for the bench, in bench.js
			initTheBench();

			initialBlockDisplay(the_starting_block);

			document.getElementById("brndbotMain").scrollIntoView();
		<% } %>
	});
</script>

</body>
</html>
