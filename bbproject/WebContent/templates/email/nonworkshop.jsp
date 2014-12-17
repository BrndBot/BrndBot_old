<!--  Non-featured WORKSHOP template for the EMAIL editor -- see the email/text.jsp file for detailed descriptions of how
templates work. -->
<div id="nonworkshopBlock-<%=templateEnum %>" 
	class="blockSelectable" style="display:<%=((templateVisible) ? "" : "none") %>">

	<div style="width:94%;padding:1.25rem;background: #ffffff;overflow:hidden;height:1%;">
		<!-- left side -->
		<div id="nonworkshopHeader-<%=templateEnum %>" 
			style="float:none;font-size: 1.125rem;padding-bottom:0.625rem;">New Workshop!</div>
		<div style="clear:both;line-height:.0625rem">&nbsp;</div>
		<div>
			<div style="width:64%;float:left;">
				<div id="nonworkshopName-<%=templateEnum %>" style="font-size: 1.625rem;color:<%=paletteArray.get(0).getColor() %>">
				</div>
			</div>
			<div style="float:none;">
				<div style="text-align:center;padding-top:.3rem">
					<button id="nonworkshopButton-<%=templateEnum %>" class="rounded" 
						style="font-size: 1.125rem;color:#ffffff;background-color:<%=paletteArray.get(2).getColor() %>">sign up</button>
				</div>
			</div>
		</div>
		<div style="clear:both;line-height:.0625rem">&nbsp;</div>
		<div style="font-style:italic;font-size: 1rem;">
		with <span id="nonworkshopFullName-<%=templateEnum %>"></span>
		</div>
	</div>

	<div id="nonworkshopBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
		<img src="images/bench/sortUp.png" alt="" />
	</div>
	<div id="nonworkshopBlock-<%=templateEnum %>SortDownBtn" class="sortDown">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 
	<div style="float:none;width:5%">
		<div id="nonworkshopBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
			<img src="images/bench/trashIcon.png" alt="" />
		</div>
	</div> 
 
</div>
