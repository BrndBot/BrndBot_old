<!-- EMAIL layout.jsp
This file contains the HTML for the right-hand side LAYOUT tab for each type of data object.
Like most of the editor, it relies on a strict naming convention.  See the "var idPrefix =" in bench.jsp
to see where the prefixes are defined. So the naming convention is {prefix}BlockLayout.  The id for the editor
HTML defined herein must have the precise ID to be "found".  When an object in the editor is selected, the 
event handler looks for this id and shows it, hiding the previously shown editor, if any.

The layout is one tab on the right-hand side.  There are other files used in the same manner for the other tabs.
-->
<div id="classBlockLayout" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			class layout
		</div>
	</div>
</div>

<!-- placeholder for the graphic block edit form -->
<div id="graphicBlockLayout" style="display:none">
	<div class="blockEdit">
		Graphic layout
	</div>
</div>

<!-- placeholder for the nonclass block edit form -->
<div id="nonclassBlockLayout" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			non featured class layout
		</div>
	</div>
</div>

<!-- placeholder for the workshop block edit form -->
<div id="workshopBlockLayout" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			workshop layout
		</div>
	</div>
</div>

<!-- placeholder for the nonworkshop block edit form -->
<div id="nonworkshopBlockLayout" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			non featured workshop layout
		</div>
	</div>
</div>

<!-- placeholder for the workshop block edit form -->
<div id="staffBlockLayout" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			staff layout
		</div>
	</div>
</div>

<!-- placeholder for the schedule edit form -->
<div id="scheduleBlockLayout" style="display:none">
	<div class="blockEdit">
		<div>
			schedule layout
		</div>
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

<!-- placeholder for the social block edit form -->
<div id="socialBlockLayout" style="display:none">
	<div class="blockEdit">
		<div id="socialTitle">
			social layout
		</div>
	</div>
</div>

<!-- placeholder for the footer block edit form -->
<div id="footerBlockLayout" style="display:none">
	<div class="blockEdit">
		<div id="footerTitle">
			footer layout
		</div>
	</div>
</div>
