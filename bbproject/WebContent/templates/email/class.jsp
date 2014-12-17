<!--  CLASS template for the EMAIL editor -- see the email/text.jsp file for detailed descriptions of how
templates work. -->
<div id="classBlock-<%=templateEnum %>" 
	class="blockSelectable" style="display:<%=((templateVisible) ? "" : "none") %>">

	<div style="width:94%;padding:1.188rem;background: #ffffff;overflow:hidden;height:1%;">
		<!-- left side -->
		<div style="float:left;width:50%;padding-right:0.9375rem">
			<div style="float:left;width:10%">
				<div id="classBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
					<img src="images/bench/sortUp.png" alt="" />
				</div> 
			</div>
			<div style="float:none;margin-left:-0.9375rem">
				<div id="classImgURL-<%=templateEnum %>" style="padding-bottom:1.25rem;text-align:center;">
				</div>
				<div style="text-align:center">
					<button id="classButton-<%=templateEnum %>" class="rounded" 
						style="width:6rem;font-size: 1rem;line-height:2rem;color:#ffffff;background-color:<%=paletteArray.get(1).getColor() %>">sign up</button>
				</div>
			</div>
		</div>
		<!-- end left side -->

		<!-- right side -->
		<div style="float:none;display:table-cell;width:100%">
			<div>
				<div id="classHeader-<%=templateEnum %>" style="float:left;width:90%;font-size: 1.375rem;padding-bottom:0.625rem;">New Class!</div>
				<div style="float:none">
					<div id="classBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
						<img src="images/bench/trashIcon.png" alt="" />
					</div> 
				</div>
			</div>
			<div style="clear: both">&nbsp;</div>
			<div id="className-<%=templateEnum %>" style="font-size: 1.7rem;color:<%=paletteArray.get(0).getColor() %>">
			</div>
			<div style="font-style:italic;font-size: 1.125rem;padding-bottom:0.9375rem">
			with <span id="classFullName-<%=templateEnum %>"></span>
			</div>
			<div id="classDescription-<%=templateEnum %>" style="font-size: .875rem;padding-bottom:0.625rem">
			</div>
		</div>
		<!-- end right side -->
	</div>

	<div id="classBlock-<%=templateEnum %>SortDownBtn" class="sortDown">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 
 
</div>
