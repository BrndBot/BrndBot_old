<!-- FACEBOOK layout.jsp
This file contains the HTML for the right-hand side LAYOUT tab for each type of data object.
Like most of the editor, it relies on a strict naming convention.  See the "var idPrefix =" in bench.jsp
to see where the prefixes are defined. So the naming convention is {prefix}BlockLayout.  The id for the editor
HTML defined herein must have the precise ID to be "found".  When an object in the editor is selected, the 
event handler looks for this id and shows it, hiding the previously shown editor, if any.

The layout is one tab on the right-hand side.  There are other files used in the same manner for the other tabs.
-->
<div id="graphicBlockLayout" style="display:none">
	<div class="blockEdit formSpacer">
		Graphic layout
	</div>
	<div class="blockEdit formSpacer">
		(need icons)
	</div>
	<div class="blockEdit formSpacer">
		Graphic only
	</div>
	<div class="blockEdit formSpacer">
		text on banner on top
	</div>
	<div class="blockEdit formSpacer">
		text on banner middle
	</div>
	<div class="blockEdit formSpacer">
		text only centered
	</div>
</div>

<!-- placeholder for the text block edit form -->
<div id="textBlockLayout" style="display:none">
	<div class="blockEdit">
		<div id="textTitle">
			text layout
		</div>
	</div>
</div>
