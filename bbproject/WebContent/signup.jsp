<!DOCTYPE html>
<html>
<%@ page session="true"%>
<%@ page import="com.brndbot.system.SessionUtils" %>
<%@ page import="com.brndbot.system.SystemProp" %>
<%@ page import="com.brndbot.system.Utils" %>
<%@ page import="com.brndbot.user.ImageType" %>
<%@ page import="com.brndbot.user.User" %>
<%@ page import="com.brndbot.user.UserLogo" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<head>
    <title>Brndbot Signup</title>
    <meta charset="utf-8">
	<script type="text/javascript" src="//use.typekit.net/wnn8jyx.js"></script>
	<script type="text/javascript">try{Typekit.load();}catch(e){}</script>

    <link href="styles/kendo.common.min.css" rel="stylesheet">
    <link href="styles/kendo.rtl.min.css" rel="stylesheet">
<!-- <link href="styles/kendo.default.min.css" rel="stylesheet">  -->
    <link href="styles/kendo.flat.min.css" rel="stylesheet">
    <link href="css/shared.css" rel="stylesheet">
    <link href="css/signup.css" rel="stylesheet">

    <script type="text/javascript" src="js/jquery-2.1.1.js"></script>
    <script type="text/javascript" src="js/jquery-migrate-1.2.1.js"></script>
    <script type="text/javascript" src="js/kendo.all.min.js"></script>
    <script type="text/javascript" src="js/signup.js"></script>
	<script type="text/javascript" src="js/session.js"></script>

<script type="text/javascript">

var MAX_BOUNDING_HEIGHT  = '<%=UserLogo.MAX_BOUNDING_HEIGHT %>';
var MAX_BOUNDING_WIDTH  = '<%=UserLogo.MAX_BOUNDING_WIDTH %>';

</script>

</head>
<body>
<% 
	System.out.println("-------------Entering signup.jsp---------------");
	int toLogo = Utils.getIntParameter(request, "toLogo");
%>
<div id="brndbotMain">
	<div id="brndbotHeader">
		&nbsp;
	</div> <!-- brndbotHeader -->
	<div id="brndbotBody">

		<div id="registerNewUser" style="display:none;">
			<div style="margin:auto;text-align:center">
				<div style="padding-top: 2rem; padding-bottom: 2rem;">
					<img src="images/signUpBot.png" alt="Brndbot Robot" height="190" />
				</div>
			</div>
			<div style="margin:auto;text-align:center">
				<div style="margin:auto;width:28%">
					<div id="signUpDiv" style="text-align:left">
						<div class="formHeader">
							Get Started
						</div>
						<div class="formLabelFor" style="font-variant: normal">
							Sign up in 30 seconds. No credit card required.<br />
							If you already have an account, <a href="index.jsp">log in here</a>.
						</div>
						<div id="errorMsg" style="display:none">
							&nbsp;
						</div>
				    	<div  class="formLabelFor" style="padding-top:1.9375rem">
				        	<label for="userEmail">email</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" class="k-textbox editWidth" autocomplete="off" id="userEmail" />
				       	</div>
				    	<div  class="formLabelFor" style="padding-top:1.9375rem">
				        	<label for="authCode">authorization code</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" class="k-textbox editWidth" autocomplete="off" id="authCode" />
				       	</div>
				    	<div class="formLabelFor">
				        	<label for="userPassword">password</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="password" class="k-textbox editWidth" autocomplete="off" id="userPassword" />
				       	</div>
				    	<div class="formLabelFor">
				        	<label for="userConfirmPassword">confirm password</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="password" class="k-textbox editWidth" autocomplete="off" id="userConfirmPassword" />
				       	</div>
						<div style="padding-top:1.25rem">
							<button id="createMyAccount" class="greenButton rounded" style="width:11.375rem">create my account</button>
						</div>
					</div>
				</div>
			</div>
		</div> <!-- registerNewUser -->

		<div id="tellUsAboutYourself" style="display:none;padding-top:2rem;">
			<div style="margin:auto;text-align:center">
				<div style="margin:auto;width:60%">
					<div style="text-align:left">
						<div class="formHeader formSpacer">
							Howdy!<br />
							We're BrndBot, its nice to meet you.
						</div>
				    	<div class="formLabelFor">
				        	<label for="companyName">your company name</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" class="k-textbox editWidth" autocomplete="off" id="companyName" />
				       	</div>
				    	<div class="formLabelFor">
				        	<label for="companyAddress">address</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" class="k-textbox editWidth" autocomplete="off" id="companyAddress" />
				       	</div>
				    	<div class="formLabelFor">
				        	<label for="companyURL">url of your website</label>
				       	</div>
				       	<div class="formSpacer">
				        	<input type="text" class="k-textbox editWidth" autocomplete="off" id="companyUrl" />
				       	</div>
				    	<div  class="formLabelFor">
				        	<label for="industryList">industry</label>
				       	</div>
				       	<div class="formSpacer" style="font-size:.7rem;width:26rem;">
					        <select id="industryList" multiple="multiple" 
					        	style="border-width:1px" data-placeholder="Select industries...">
					            <option>Aerobics</option>
					            <option>Yoga</option>
					            <option>Dance</option>
					            <option>Pilates</option>
					            <option>Gym</option>
					            <option>Fitness Center</option>
					            <option>Athletic Club</option>
					            <option>Spa</option>
					            <option>Nutrition Center</option>
					            <option>Country Club</option>
					        </select>
				       	</div>
						<div>
							<button id="updateAccount" class="greenButton rounded" style="width:9.375rem">continue</button>
						</div>
					</div>
				</div>
			</div>
		</div> <!-- tellUsAboutYourself -->

		<div id="areYouASocialite" style="display:none;padding-top:2rem;">
			<div style="margin:auto;text-align:center">
				<div style="margin:auto;width:60%">
					<div style="text-align:left">
						<div class="formHeader">
							Are you a socialite?
						</div>
						<div class="formLabelFor formSpacer" style="font-variant: normal">
							Its okay if you’re not. In fact, we can help! Click <a href="#">here</a> if you need help setting up some accounts.<br />
							Also, don’t be overwhelmed, these are all optional.
						</div>
						<div>
					    	<div class="formLabelFor">
					        	<label for="twitterHandle">twitter handle</label>
					       	</div>
					       	<div class="formSpacer">
					       		<span class="urlSpan">http://www.twitter.com/</span>
					        	<span>
					        		<input type="text" class="k-textbox editWidthShort" autocomplete="off" id="twitterHandle" />
					        	</span>
					       	</div>
					    	<div class="formLabelFor">
					        	<label for="facebookUrl">facebook url</label>
					       	</div>
					       	<div class="formSpacer">
					       		<span class="urlSpan">https://www.facebook.com/</span>
					        	<span>
						        	<input type="text" class="k-textbox editWidthShort" autocomplete="off" id="facebookUrl" />
					        	</span>
					       	</div>
					    	<div class="formLabelFor">
					        	<label for="linkedIn">linkedin url</label>
					       	</div>
					       	<div class="formSpacer">
					       		<span class="urlSpan">https://www.linkedin.com/company/</span>
					        	<span>
						        	<input type="text" class="k-textbox editWidthShort" autocomplete="off" id="linkedIn" />
					        	</span>
					       	</div>
					    	<div class="formLabelFor">
					        	<label for="youtubeUrl">youtube url</label>
					       	</div>
					       	<div class="formSpacer">
					       		<span class="urlSpan">https://www.youtube.com/channel/</span>
					        	<span>
						        	<input type="text" class="k-textbox editWidthShort" autocomplete="off" id="youtubeUrl" />
					        	</span>
					       	</div>
					    	<div class="formLabelFor">
					        	<label for="instagramUrl">instagram url</label>
					       	</div>
					       	<div class="formSpacer">
					       		<span class="urlSpan">https://www.instagram.com/channel/</span>
					        	<span>
						        	<input type="text" class="k-textbox editWidthShort" autocomplete="off" id="instagramUrl" />
					        	</span>
					       	</div>
							<div class="unit size1of2">
								<div>
									<!-- the user database gets updated with this button -->
									<button id="updateSocials" class="greenButton rounded" style="width:9.375rem">continue</button>
								</div>
							</div>
							<div class="unit lastUnit">
								<div>
									<button id="helpFindUrls" class="greyButton rounded" style="width:9.375rem">help me find urls</button>
								</div>
								<div style="clear:both">&nbsp;</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div> <!-- areYouASocialite -->

		<div id="talkColor" style="display:none;padding-top:2rem;">
			<div style="margin:auto;text-align:center">
				<div style="margin:auto;width:60%">
					<div style="text-align:left">
						<div class="formHeader">
							We love our logo.<br />
							What does yours look like?
						</div>
						<div id="uploadRequest" class="formLabelFor formSpacer" style="font-variant: normal">
							Please upload an image file of your logo.
						</div>
						<div id="uploadStatus" style="display:none;font-size:2rem">
						</div>
						<div>
							<div id="logoCrossHairs" style="margin-left:6.5rem;">
								<div style="position:relative;">
									<img id="imageTarget" src="images/image-target.png" alt="Image Target" />
									<div id="filesHome" style="top:2.375rem;left:1.125rem;position:absolute">
										<input class="greyButton" name="files" id="files" type="file" />
									</div>
								</div>
								<div id="showActualLogo" style="display:none;">
									<table id="imageWrapper" style="width:<%=UserLogo.MAX_BOUNDING_WIDTH %>px;height:<%=UserLogo.MAX_BOUNDING_HEIGHT %>px">
										<tr>
								    		<td id="actualLogoTD">
												<div class="image-section" id="actualLogo">
												</div>
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
<!-- 	 				<div id="removeButton" class="formLabelFor" style="display:none">
							<a href="#" onclick="startOver(true);">remove</a>
						</div>
 -->
 		 				<div id="logoUploadedSuccessfully" style="display:none;padding-left:0.9375rem">
							<button id="updateLogo" class="greenButton rounded" style="width:9.375rem">continue</button>
						</div>
					</div>
				</div> 
 			</div>
		</div> <!-- end talkColor pane -->

		<!-- Start PaletteFork Pane -->
		<div id="paletteForkPane" style="display:none;padding-top:2rem;">
			<div class="unit size1of5" style="min-height:21.875rem">
				&nbsp;
			</div>
			<div class="unit lastUnit">
				<div style="text-align:center">
					<div style="text-align: left;">
						<div class="formHeader formSpacer">
							We are color experts.  But not perfect.<br />
							Would you like to tweak your color palette?
						</div>
						<div class="formLabelFor formSpacer" style="font-variant: normal;">
							We have extracted your colors from your logo and put together something we think works pretty well.<br />
							If you have company colors that aren't in your logo, please feel free to place them in your palette.
						</div>
					</div>
				</div>
				<div>
					<div class="unit size1of3">
						<button id="skipPaletteButton" class="greenButton rounded" style="width:9.375rem">i trust you</button>
					</div>
					<div class="unit size1of3">
						<button id="viewPaletteButton" class="greyButton rounded" style="width:13.5rem">let me take a quick look</button>
					</div>
					<div class="lastUnit" style="clear:both">&nbsp;</div>
				</div>
			</div>
		</div>
		<!-- End of palette fork pane -->

		<!-- Start Palette Pane -->
		<div id="palettePane" style="display:none;padding-top:2rem;">
			<div class="unit size1of5" style="min-height:21.875rem">
				&nbsp;
			</div>
			<div class="unit lastUnit">
				<div style="text-align:center">
					<div style="text-align: left;">
						<div class="formHeader formSpacer">
							Here's your current color palette.<br />
							We've recommended some related colors that you may prefer.<br />
						</div>
						<div class="formLabelFor formSpacer" style="font-variant: normal;">
							You can click on a color in your palette to change it, then select a new color<br />
							to take its place from the swatch below.
						</div>
					</div>
				</div>
				<div class="formSpacer" style="font-size:2rem;">
					Your palette.
				</div>
				<div id="yourPaletteBlock">
					<div class="paletteCell">
						<div id="palette-0" class="paletteSquare">
						</div>
					</div>
					<div class="paletteCell">
						<div id="palette-1" class="paletteSquare">
						</div>
					</div>
					<div class="paletteCell">
						<div id="palette-2" class="paletteSquare">
						</div>
					</div>
					<div class="paletteCell">
						<div id="palette-3" class="paletteSquare">
						</div>
					</div>
					<div class="lastUnit" style="clear:both">&nbsp;
					</div>
				</div>
				<div class="formSpacer" style="font-size: 2rem;">
					You may prefer one of these related colors.
				</div>
				<div id="otherColors">
					<div id="otherColorsArray">
					</div>
					<div id="otherColorSwatch">
						<div class="swatchTitle">
							Choose Custom Color
						</div>
						<div style="padding-left:10px;">
			                <input id="swatchPicker" />
						</div>
					</div>
				</div>
				<div>
					<div class="unit size1of3">
						<button id="unforkToPos" class="greenButton rounded" style="width:9.375rem">continue</button>
					</div>
					<div class="lastUnit" style="clear:both">&nbsp;</div>
				</div>
			</div>
		</div>
		<!-- End of palette pane -->

		<!-- Start POS Select Pane -->
		<div id="posSelectPane" style="display:none;padding-top:2rem;">
			<div class="unit size1of5" style="min-height:21.875rem">
				&nbsp;
			</div>
			<div class="unit lastUnit">
				<div style="text-align:center">
					<div style="unit">
						<div id="posTitle" class="formHeader formSpacer">
							Choose your POS system.
						</div>
						<div class="lastUnit" style="clear:both">
							&nbsp;
						</div>
					</div>
 				</div>
 			</div>
			<div style="padding-top:3rem;">
				<div id="mindBodyPos" class="unit size1of4 posSystem">
					<img src="images/mindbody-logo.png" width="180" alt="" />
				</div>
				<div id="ncrPos" class="unit size1of4 posSystem" style="visibility:hidden">
					<img src="images/NCR-pos.png" alt="" width="140" />
				</div>
				<div id="ebayPos" class="unit lastUnit posSystem" style="visibility:hidden">
					<img src="images/ebay-pos.png" alt="" width="140" />
				</div>
				<div class="lastUnit" style="clear:both">&nbsp;</div>
			</div>
			<div style="padding-top:.5rem">&nbsp;</div>
			<div id="mindBodySpecific" style="display:none">
				<div style="padding-top:2rem">
					<div class="formHeader formSpacer">
						Input your MindBody studio ID.
					</div>
			       	<div class="formSpacer">
			       		<span class="urlSpan">clients.mindbody.com/studioid/?=</span>
			        	<span>
				        	<input type="text" class="k-textbox editWidthShort" autocomplete="off" id="mindbodyStudioID" />
			        	</span>
			       	</div>
					<div style="padding-left:17%">
						<button id="posContinue" class="greenButton rounded" style="width:9.375rem">continue</button>
					</div>
				</div>
			</div>
		</div>
		<!-- End of POS Select pane -->

		<!-- Start Look Pane -->
		<div id="lookPane" style="display:none;padding-top:2rem;">
			<div style="text-align:center">
				<div class="unit lastUnit">
					<div style="text-align:center">
						<div style="text-align: left;">
							<div class="formHeader formSpacer">
								Last step!<br />
								Choose a 'look' for your company.
							</div>
							<div class="formLabelFor formSpacer" style="font-variant: normal;">
								Choose the characterization that best matches your business.<br />
								This choice will help us craft your message content and look.
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div id="lookPane2" style="width:100%;display:none;">
			<div style="text-align:center;width:100%;">
				<div class="unit" style="width:26%;padding-right:3rem">
					<div id="mockup1" class="mockups">
						<img alt="" width="240px" src="images/saktiMockup.png" />
					</div>
				</div>
				<div class="unit" style="width:26%;padding-right:3rem">
					<div id="mockup2" class="mockups">
						<img alt="" width="240px" src="images/barreMockup.png" />
					</div>
				</div>
				<div class="unit" style="width:26%">
					<div id="mockup3" class="mockups">
						<img alt="" width="240px" src="images/fitMockup.png" />
					</div>
				</div>
				<div style="clear:both">&nbsp;
				</div>
			</div>
			<div style="padding-top:1.5rem">
				<div class="unit size1of3">
					<button id="signUpComplete" class="greenButton rounded" style="width:9.375rem">finished</button>
				</div>
				<div class="lastUnit" style="clear:both">&nbsp;</div>
			</div>
		</div>
		<!-- End of Look pane -->

		<!-- Hidden form used to post data -->
		<form id="userDataForm" method="post">
			<input id="hiddenEmail" name="hiddenEmail" type="text" style="display:none" />
			<input id="hiddenPassword" name="hiddenPassword" type="text" style="display:none" />
			<input id="hiddenConfirmPassword" name="hiddenConfirmPassword" type="text" style="display:none" />
			<input id="hiddenCompany" name="hiddenCompany" type="text" style="display:none" />
			<input id="hiddenUrl" name="hiddenUrl" type="text" style="display:none" />
			<input id="hiddenAddress" name="hiddenAddress" type="text" style="display:none" />
			<input id="hiddenFacebookUrl" name="hiddenFacebookUrl" type="text" style="display:none" />
			<input id="hiddenTwitterHandle" name="hiddenTwitterHandle" type="text" style="display:none" />
			<input id="hiddenLinkedIn" name="hiddenLinkedIn" type="text" style="display:none" />
			<input id="hiddenYouTube" name="hiddenYouTube" type="text" style="display:none" />
			<input id="hiddenInstagram" name="hiddenInstagram" type="text" style="display:none" />
			<input id="hiddenAuth" name="hiddenAuth" type="text" style="display:none" />
			<input id="hiddenOrgId" name="hiddenOrgId" type="text" style="display:none" />
		</form>

		<form id="paletteForm" method="post">
			<input id="hiddenSuggestedPalette" name="hiddenSuggestedPalette" type="text" style="display:none" />
			<input id="hiddenYourPalette" name="hiddenYourPalette" type="text" style="display:none" />
		</form>
	</div>
</div> <!-- brndbotMain -->

<script type="text/javascript">

var toLogo = <%= (toLogo == 1 ? "true" : "false") %>; 
var brndBotUserID = 0;
var MAX_LOGO_SIZE  = '<%=UserLogo.MAX_LOGO_SIZE %>';
var signUpUserKey = '<%=SessionUtils.USER_ID %>';
var PHP_SERVER_PAGE = '<%=SystemProp.get(SystemProp.PHP_SERVER_PAGE) %>';
var IMAGE_TYPE_LOGO = <%=ImageType.DEFAULT_LOGO.getValue().intValue() %>;

// Singleton class for client-side session management
var session_mgr = new Session();
session_mgr.setImageID(IMAGE_TYPE_LOGO);

</script>

</body>
</html>
