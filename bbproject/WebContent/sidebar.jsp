<!-- start of sidebar.jsp -->
<ul id="panelbar">
	<li id="homeLine" class="isInactive homeInactive" onclick="clickSideBar('homeLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span id="homeRight" class="rightSpan normalPrompt">
			home
		</span>
	</li>
	<li id="emailLine" class="isInactive emailInactive" onclick="clickSideBar('emailLine');return false;">
		<div class="leftSpan unit">
			&nbsp;
		</div>
		<div class="rightSpan lastUnit">
			<div class="normalPrompt">
				email
			</div>
			<div id="emailPs" style="display:none;">
				<p id="emailAll" onclick="emailTypeClicked('emailAll');return false;">all</p>
				<p id="emailScheduled" onclick="emailTypeClicked('emailScheduled');return false;">scheduled</p>
				<p id="emailDrafts" onclick="emailTypeClicked('emailDrafts');return false;">drafts</p>
				<p id="emailSent" onclick="emailTypeClicked('emailSent');return false;">sent</p>
				<p id="emailTrash" onclick="emailTypeClicked('emailTrash');return false;" style="padding-bottom:1rem">trash</p>
			</div>
		</div>
	</li>
	<li id="socialLine" class="isInactive socialInactive" onclick="clickSideBar('socialLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				social
		</span>
	</li>
	<li id="autoLine" class="isInactive autoInactive" onclick="clickSideBar('autoLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				autoresponder
		</span>
	</li>
	<li id="acctLine" class="isInactive acctInactive" onclick="clickSideBar('acctLine');return false;">
		<span class="leftSpan">
			&nbsp;
		</span>
		<span class="rightSpan normalPrompt">
				account settings
		</span>
	</li>
</ul>
<!-- end of sidebar.jsp -->
