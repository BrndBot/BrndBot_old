<!--  GRAPHIC template for the EMAIL editor -- see the email/text.jsp file for detailed descriptions of how
templates work. -->
<div id="graphicBlock-<%=templateEnum %>" style="display:<%=((templateVisible) ? "" : "none") %>;border-bottom:0.1875rem solid <%=paletteArray.get(0).getColor() %>" 
	class="blockSelectable">
	<div style="width:94%;padding:0rem 1.25rem;background: #ffffff;overflow:hidden;height:1%;line-height:1%">
		<div style="width:100%;max-height:320px">
			<div style="text-align:center;padding-top:.5rem;padding-bottom:.5rem">
				<%=chosenImg /* must be defined externally getBoundLogo() */ %>
			</div>
			<div id="graphicBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
				<img src="images/bench/trashIcon.png" alt="" />
			</div> 
		</div>
	</div>
	<div id="graphicBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
		<img src="images/bench/sortUp.png" alt="" />
	</div>
	<div id="graphicBlock-<%=templateEnum %>SortDownBtn" class="sortDown" style="left:.125rem">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 

</div>
