<!-- FACEBOOK design.jsp
This file contains the HTML for the right-hand side STYLE for each type of data object.
Like most of the editor, it relies on a strict naming convention.  See the "var idPrefix =" in bench.jsp
to see where the prefixes are defined. So the naming convention is {prefix}BlockDesign.  The id for the editor
HTML defined herein must have the precise ID to be "found".  When an object in the editor is selected, the 
event handler looks for this id and shows it, hiding the previously shown editor, if any.

The design is one tab on the right-hand side.  There are other files used in the same manner for the other tabs.
-->
<div id="graphicBlockDesign" style="display:none">
	<div class="blockEdit">
		Graphic design
	</div>
</div>

<!-- placeholder for the text block edit form -->
<div id="textBlockDesign" style="display:none">
	<div class="blockEdit">
		<div id="textTitle formSpacer">
			Add live text
		</div>
		<div class="textTitle formSpacer">
			(need icons)
		</div>
		<div class="textTitle formSpacer">
			add workshop link
		</div>
		<div class="textTitle formSpacer">
			add class link
		</div>
		<div class="textTitle formSpacer">
			add teacher profile name
		</div>
		<div class="textTitle formSpacer">
			add hashtag campaign
		</div>
	</div>
</div>

