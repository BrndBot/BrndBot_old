<!-- EMAIL design.jsp
This file contains the HTML for the right-hand side STYLE for each type of data object.
Like most of the editor, it relies on a strict naming convention.  See the "var idPrefix =" in bench.jsp
to see where the prefixes are defined. So the naming convention is {prefix}BlockDesign.  The id for the editor
HTML defined herein must have the precise ID to be "found".  When an object in the editor is selected, the 
event handler looks for this id and shows it, hiding the previously shown editor, if any.

The design is one tab on the right-hand side.  There are other files used in the same manner for the other tabs.
-->
<div id="classBlockDesign" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			class design
		</div>
	</div>
</div>

<!-- placeholder for the graphic block edit form -->
<div id="graphicBlockDesign" style="display:none">
	<div class="blockEdit">
		Graphic design
	</div>
</div>

<!-- placeholder for the nonclass block edit form -->
<div id="nonclassBlockDesign" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			non featured class design
		</div>
	</div>
</div>

<!-- placeholder for the workshop block edit form -->
<div id="workshopBlockDesign" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			workshop design
		</div>
	</div>
</div>

<!-- placeholder for the nonworkshop block edit form -->
<div id="nonworkshopBlockDesign" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			non featured workshop design
		</div>
	</div>
</div>

<!-- placeholder for the workshop block edit form -->
<div id="staffBlockDesign" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			staff design
		</div>
	</div>
</div>

<!-- placeholder for the schedule edit form -->
<div id="scheduleBlockDesign" style="display:none">
	<div class="blockEdit">
		<div>
			schedule design
		</div>
	</div>
</div>

<!-- placeholder for the text block edit form -->
<div id="textBlockDesign" style="display:none">
	<div class="blockEdit">
		<div id="textTitle">
			text design
		</div>
	</div>
</div>

<!-- placeholder for the social block edit form -->
<div id="socialBlockDesign" style="display:none">
	<div class="blockEdit">
		<div id="socialTitle">
			social design
		</div>
	</div>
</div>

<!-- placeholder for the footer block edit form -->
<div id="footerBlockDesign" style="display:none">
	<div class="blockEdit">
		<div id="footerTitle">
			footer design
		</div>
	</div>
</div>
