<!-- placeholder for the class block edit form for email -->
<!-- EMAIL content.jsp
This file contains the HTML for the right-hand side EDITOR for each type of data object.
Like most of the editor, it relies on a strict naming convention.  See the "var idPrefix =" in bench.jsp
to see where the prefixes are defined. So the naming convention is {prefix}BlockDesign.  The id for the editor
HTML defined herein must have the precise ID to be "found".  When an object in the editor is selected, the 
event handler looks for this id and shows it, hiding the previously shown editor, if any.

The editor is one tab on the right-hand side.  There are other files used in the same manner for the other tabs.
-->
<div id="blockEdit" >
<!-- What goes here???? -->
	
</div>

<!-- placeholder for the graphic block edit form -->
<div id="graphicBlockEdit" style="display:none">
	<div style="padding:2rem">
		<button id="viewGallery2" class="viewImageGallery">Image Gallery</button>
	</div>
</div>




<!-- placeholder for the text block edit form -->
<div id="textBlockEdit" style="display:none">
	<div class="blockEdit">
		<div id="textTitle">
			intro greeting
		</div>
		<div>
			<div style="float:left;width:10%;margin-left:-0.3125rem;">
				<input type="checkbox" id="textEditIncludeCustName"
					 value="includeCustName" />
			</div>
			<div class="smallEditFont" style="float:none;padding-top:0.625rem;">
				include customer name
			</div>
		</div>
		<div>
			<input id="textEditName" class="k-textbox" type="text"  />
		</div>
		<div class="editTextArea">
			<textarea id="textEditDescription" rows="4" style="width:100%"></textarea>
		</div>
	</div>
</div>

<!-- placeholder for the social block edit form -->
<div id="socialBlockEdit" style="display:none">
	<div class="blockEdit">
		<div id="socialTitle">
			connect to social
		</div>
		<div>
			message
		</div>
		<div>
			<input id="socialEditName" class="k-textbox" type="text"  />
		</div>
	</div>
</div>

<!-- placeholder for the footer block edit form -->
<div id="footerBlockEdit" style="display:none">
	<div class="blockEdit">
		<div id="footerTitle">
			message footer
		</div>
		<div>
			name and address
		</div>
		<div>
			<input id="footerEditName" class="k-textbox" type="text"  />
		</div>
		<div>
			additional text
		</div>
		<div>
			<input id="footerEditDescription" class="k-textbox" type="text"  />
		</div>
	</div>
</div>
