<div id="workshopBlock-<%=templateEnum %>" class="blockSelectable"
		style="display:<%=((templateVisible) ? "" : "none") %>;border-bottom:0.1875rem solid <%=paletteArray.get(1).getColor() %>">
	<div style="width:94%;padding:1.188rem;background: #ffffff;overflow:hidden;height:1%;">
		<!-- left side -->
		<div style="float:left;width:50%;padding-right:0.9375rem">
			<div>
				<div style="float:left;width:10%">
					<div id="workshopBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
						<img src="images/bench/sortUp.png" alt="" />
					</div> 
				</div>
				<div id="workshopHeader-<%=templateEnum %>" style="float:none;font-size: 1.375rem;padding-bottom:0.625rem;font-weight:300">New Workshop!</div>
			</div>
			<div id="workshopName-<%=templateEnum %>" style="font-size: 1.7rem;color:<%=paletteArray.get(0).getColor() %>">
			</div>
			<div style="font-style:italic;font-size: 1.125rem;padding-bottom:0.9375rem">
				with <span id="workshopFullName-<%=templateEnum %>"></span>
			</div>
			<div id="workshopDescription-<%=templateEnum %>" style="font-size: .875rem;padding-bottom:0.625rem">
			</div>
			<div style="text-align:center">
				<button id="workshopSignUp-<%=templateEnum %>" class="rounded" 
					style="width:6rem;font-size: 1rem;line-height:2rem;color:#ffffff;background-color:<%=paletteArray.get(1).getColor() %>">sign up</button>
			</div>
		</div>
		<!-- end left side -->

		<!-- right side -->
		<div style="float:none;display:table-cell;width:45%">
			<div style="float:right;">
				<div id="workshopBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
					<img src="images/bench/trashIcon.png" alt="" />
				</div> 
			</div>
			<div style="line-height:.0625rem;clear:both">&nbsp;</div>
			
			<div id="workshopImgURL-<%=templateEnum %>" style="padding-bottom:1.25rem;text-align:center;;padding-top:1rem">
			</div>
		</div>
		<!-- end right side -->
	</div>

	<div id="workshopBlock-<%=templateEnum %>SortDownBtn" class="sortDown">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 

</div>