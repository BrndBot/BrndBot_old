<!--  SOCIAL MEDIA template for the FACEBOOK editor -- see the email/text.jsp file for detailed descriptions of how
templates work. -->
<div id="socialBlock-<%=templateEnum %>" class="blockSelectable" style="display:<%=((templateVisible) ? "" : "none") %>">
	<div style="width:95%;padding:1.25rem;color:#ffffff;font-size: 1.375rem;background-color:<%=paletteArray.get(0).getColor() %>;overflow:hidden;height:1%;">
		<div style="width:100%;">
			<div style="width:100%;">
				<div style="float:left;width:10%">&nbsp;</div>
				<div id="socialName-<%=templateEnum %>" style="float:left;width:80%;text-align:center;">
				</div>
			</div>
			<div style="clear:both;line-height:.0625rem">&nbsp;</div>
		    <div style="width:100%;padding-top:1.125rem">
		    	<div class="unit size1of5">
		    		&nbsp;
		    	</div>
		    	<div class="unit size1of5">
					&nbsp;
		    	</div>
		    	<div class="unit size1of5" style="text-align:center">
		    		<img src="images/facebookChannelSelector.png" alt="Facebook" height="72" />
		    	</div>
		    	<div class="unit size1of5" style="text-align:center">
		    		<img src="images/twitterChannelSelector.png" alt="Twitter" height="72" />
		    	</div>
		    	<div class="unit size1of5">
					&nbsp;
		    	</div>
		    	<div class="unit lastUnit">
		    		&nbsp;
		    	</div>
		    </div>
		</div>
	</div>
</div>
