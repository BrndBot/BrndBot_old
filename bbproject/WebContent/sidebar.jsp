<!-- sidebar.jsp 
 All rights reserved by Brndbot, Ltd. 2015
-->
<ul id="panelbar">
	<li id="homeLine" class="isInactive homeInactive" onclick="sidebar.clickSideBar('homeLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span id="homeRight" class="rightSpan normalPrompt">
			home
		</span>
	</li>
	<li id="emailLine" class="isInactive emailInactive" onclick="sidebar.clickSideBar('emailLine');return false;">
		<div class="leftSpan unit">
			&nbsp;
		</div>
		<div class="rightSpan lastUnit">
			<div class="normalPrompt">
				email
			</div>
			<div id="emailPs" style="display:none;">
				<p id="emailAll" onclick="sidebar.emailTypeClicked('emailAll');return false;">all</p>
				<p id="emailScheduled" onclick="sidebar.emailTypeClicked('emailScheduled');return false;">scheduled</p>
				<p id="emailDrafts" onclick="sidebar.emailTypeClicked('emailDrafts');return false;">drafts</p>
				<p id="emailSent" onclick="sidebar.emailTypeClicked('emailSent');return false;">sent</p>
				<p id="emailTrash" onclick="sidebar.emailTypeClicked('emailTrash');return false;" style="padding-bottom:1rem">trash</p>
			</div>
		</div>
	</li>
	<li id="socialLine" class="isInactive socialInactive" onclick="sidebar.clickSideBar('socialLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				social
		</span>
	</li>
<!--	<li id="autoLine" class="isInactive autoInactive" onclick="sidebar.clickSideBar('autoLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				autoresponder
		</span>
	</li>
-->
	<li id="imagesLine" class="isInactive autoInactive" onclick="sidebar.clickSideBar('imagesLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				images
		</span>
	</li>
	<li id="acctLine" class="isInactive acctInactive" onclick="sidebar.clickSideBar('acctLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				account settings
		</span>
	</li>
</ul>
<!-- end of sidebar.jsp -->
