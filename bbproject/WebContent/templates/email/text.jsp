<!--  TEXT template for the EMAIL editor -- see the email/text.jsp file for detailed descriptions of how
templates work. -->

<!-- 
HOW TEMPLATES WORK:

- Strict naming conventions for IDs.  The IDs begin with the prefix for the object type ("text"), then field type
 ("Name", "Description", etc.).  If the template is enumerated, then all IDs should have a hyphen followed by
 the enumerated valued, defined external to the template (bench.jsp).
 
- Templates are enumerated to do two things.  First, there can be multiple objects of the same type in the message.
  So you can define two or more classes, teachers, workshops, etc. if the template is enumerated.  Second, enumerated
  templates are typically movable (in email anyway). So there should be the up and down buttons.
  
  The sort and trash buttons are positioned using a very specific CSS style combo.  The CSS class "blockSelectable" 
  includes the property "position: relative".   That's so the buttons, which have the CSS property "position:absolute"
  can be positioned in a precise manner, relative to the parent.  Meaning you can put the up button exactly in one 
  spot and the down in another, relative to the template in which they are defined.  The top:, bottom:, left: and right:
  CSS properties are used to move controls into position.
  
- The fields in the template also follow a strict naming convention.  There is a map to and from fields in the
  template to / from the editor for the data in this template defined in the initFieldMap() function for the
  appropriate editor bench file (e. g. emailbench.js for EMAIL).

- In general, do not rely on CSS classes for layout, rather embed the properties directly on the element.  This
  guarantees a more reliable representation of the layout when we send the HTML via email, for example.
 -->

<div id="textBlock-<%=templateEnum %>" class="blockSelectable" style="background: #ffffff;display:<%=((templateVisible) ? "" : "none") %>">
	<div style="width:94%;padding-top:0.3125rem;margin-left:1rem;overflow:hidden;height:1%;">
		<div style="width:100%">
			<div style="font-size: 1.25rem;padding:1rem">
				<div>
				<div id="textName-<%=templateEnum %>" style="float:left;width:95%;font-weight: bold">Generic</div>
					<div style="float:none">
						<div id="textBlock-<%=templateEnum %>TrashBtn" class="trashBlock">
							<img src="images/bench/trashIcon.png" alt="" />
						</div> 
					</div>
				</div>
				<div id="textDescription-<%=templateEnum %>" style="font-size:1.25rem">Generic</div>
			</div>
		</div>
	</div>
	<!--  The sort up and down buttons -->
	<div id="textBlock-<%=templateEnum %>SortUpBtn" class="sortUp">
		<img src="images/bench/sortUp.png" alt="" />
	</div>
	<div id="textBlock-<%=templateEnum %>SortDownBtn" class="sortDown" style="left:.125rem">
		<img src="images/bench/sortDown.png" alt="" />
	</div> 
	
</div>
