<!--  SCHEDULE template for the EMAIL editor -- see the email/text.jsp file for detailed descriptions of how
templates work. -->
<div id="scheduleBlock-<%=templateEnum %>" class="blockSelectable" 
		style="display:<%=((templateVisible) ? "" : "none") %>">
	<div style="width:100%;background: #ffffff;overflow:hidden;height:1%;">
		<div style="width:100%;margin:0rem auto 0rem auto;text-align:center;">
			<div id="scheduleBackground-<%=templateEnum %>" 
				style="padding:2.1875rem 2.1875rem 2.5rem 2.1875rem;color:#ffffff;background-color:<%=paletteArray.get(1).getColor() %>">
				<div>
					<div>
						<div style="float:left;width:10%">
							<div id="scheduleBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
								<img src="images/bench/sortUp.png" alt="" />
							</div>&nbsp;
						</div>
						<div id="scheduleName-<%=templateEnum %>" style="float:left;width:80%;font-size: 1.75rem;font-variant:small-caps;font-weight:bold;padding-bottom:0.375rem"></div>
						<div id="scheduleBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
							<img src="images/bench/trashIcon.png" alt="" />
						</div>
						<div style="clear:both;line-height:.0625rem">&nbsp;</div>
					</div>
					<div id="scheduleDescription-<%=templateEnum %>" style="font-size: 1.375rem;font-weight:lighter;padding-bottom:1.875rem;font-style:italic"></div>
				</div>
				<div>
					<button id="scheduleFullName-<%=templateEnum %>" class="orangeButton" 
						style="font-size: 1.125rem;width:9.375rem;color:#ffffff;background-color:<%=paletteArray.get(0).getColor() %>">Check Details</button>
				</div>
			</div>
		</div>
	</div>

	<div id="scheduleBlock-<%=templateEnum %>SortDownBtn" class="sortDown" style="left:.125rem">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 
	
</div>

