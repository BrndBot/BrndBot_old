<div id="staffBlock-<%=templateEnum %>" class="blockSelectable"
		style="display:<%=((templateVisible) ? "" : "none") %>">
	<div style="width:94%;padding:1.25rem;background:#ffffff;overflow:hidden;height:1%;">
		<!-- left side -->
		<div style="float:left;width:50%;">
			<div>
				<div style="float:left;width:10%;">
					<div id="staffBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
						<img src="images/bench/sortUp.png" alt="" />
					</div> 
				</div>
				<div style="float:none;font-size: 1.375rem;padding-bottom:0.625rem;">
					Teacher Spotlight!
				</div>
			</div>
			<div style="font-style:italic;font-size: 1.375rem;font-weight:bold;padding-bottom:0.9375rem;color:<%=paletteArray.get(0).getColor() %>">
			Meet <span id="staffName-<%=templateEnum %>"></span>
			</div>
			<div id="staffDescription-<%=templateEnum %>" style="font-size: .875rem;padding-bottom:0.625rem">
			</div>
		</div>
		
		<!-- right side -->
		<div class="unit lastUnit">
			<div>
				<div  style="float:left;width:90%;padding-top:2.5rem;padding-left:6.25rem">
					<img src="images/headshot.jpg" alt="" />
				</div>
				<div class="unit lastUnit">
					<div id="staffBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
						<img src="images/bench/trashIcon.png" alt="" />
					</div> 
				</div>
			</div>
			<div id="staffFullName-<%=templateEnum %>" 
				style="font-style:italic;font-size: 1.125rem;padding-left:7.9375rem">
			</div>
		</div>
	</div>
	<div id="staffBlock-<%=templateEnum %>SortDownBtn" class="sortDown">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 
</div>