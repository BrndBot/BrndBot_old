<!-- placeholder for the class block edit form for Facebook -->
<!-- FACEBOOK content.jsp
This file contains the HTML for the right-hand side EDITOR for each type of data object.
Like most of the editor, it relies on a strict naming convention.  See the "var idPrefix =" in bench.jsp
to see where the prefixes are defined. So the naming convention is {prefix}BlockDesign.  The id for the editor
HTML defined herein must have the precise ID to be "found".  When an object in the editor is selected, the 
event handler looks for this id and shows it, hiding the previously shown editor, if any.

The editor is one tab on the right-hand side.  There are other files used in the same manner for the other tabs.
-->
<div id="classBlockEdit" style="display:none">
	<div class="blockEdit">
		<div style="float:none">
			class header
		</div>
		<div>
			<input id="classEditHeader" class="k-textbox" type="text" />
		</div>
		<div>
			class name
		</div>
		<div>
			<input id="classEditName" class="k-textbox" type="text" />
		</div>
		<div>
			class description
		</div>
		<div>
			<div style="float:left;width:10%;margin-left:-0.3125rem;">
				<input type="checkbox" id="classEditUseMBDesc"
					value="useMBDescription" />
			</div>
			<div class="smallEditFont" style="float:none;padding-top:0.625rem;">
				use MindBody description
			</div>
		</div>
		<div class="editTextArea">
			<textarea id="classEditDescription" rows="4" style="width:100%"></textarea>
		</div>
		<div style="padding:2rem">
			<button id="viewGallery1" class="viewImageGallery">Image Gallery</button>
		</div>
	</div>
</div>

<!-- placeholder for the workshop block edit form -->
<div id="workshopBlockEdit" style="display:none">
	<div class="blockEdit">
		<div style="float:none">workshop header</div>
		<div>
			<input id="workshopEditHeader" class="k-textbox" type="text" />
		</div>
		<div>workshop name
		</div>
		<div>
			<input id="workshopEditName" class="k-textbox" type="text" />
		</div>
		<div>
			workshop description
		</div>
		<div>
			<div style="float:left;width:10%;margin-left:-0.3125rem;">
				<input type="checkbox" id="workshopEditUseMBDesc"
					value="useMBDescription" />
			</div>
			<div class="smallEditFont" style="float:none;padding-top:0.625rem;">use MindBody description</div>
		</div>
		<div class="editTextArea">
			<textarea id="workshopEditDescription" rows="4" style="width:100%"></textarea>
		</div>
		<div style="padding:2rem">
			<button id="viewGallery3" class="viewImageGallery">Image Gallery</button>
		</div>
	</div>
</div>

<!-- placeholder for the workshop block edit form -->
<div id="staffBlockEdit" style="display:none">
	<div class="blockEdit">
		<div style="float:none">workshop header</div>
		<div>teacher name
		</div>
		<div>
			<input id="staffEditName" class="k-textbox" type="text" />
		</div>
		<div>
			teacher bio
		</div>
		<div>
			<div style="float:left;width:10%;margin-left:-0.3125rem;">
				<input type="checkbox" id="staffEditUseMBDesc"
					value="useMBDescription" />
			</div>
			<div class="smallEditFont" style="float:none;padding-top:0.625rem;">use MindBody bio</div>
		</div>
		<div class="editTextArea">
			<textarea id="staffEditDescription" rows="4" style="width:100%"></textarea>
		</div>
		<div class="blockEdit">
			teacher photo
		</div>
		<div>
			<button type="button" id="uploadTeacherPhoto">Upload</button>
		</div>
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
				&nbsp;include customer name
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
