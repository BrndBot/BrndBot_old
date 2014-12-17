<!--  These are formal Kendo templates used to layout each row in the Kendo list widget.  The templates
      are referenced in the initialization of the widget by ID (template={id} option).
 -->
<script type="text/x-kendo-template" id="classTemplate">
	<div class="myListItem">
		<div style="padding-bottom:1.0625rem;padding-top:1.25rem">
			<div class="unit" style="width:4.75rem;padding-top:0.625rem;padding-left:1.9375rem">
				<img height="50px" width="50px" src="#:ItemLogo#" alt="" />
			</div>
			<div class="unit lastUnit">
				<div class="listTitle">
					#:Name#
				</div>
				<div class="listDescription">
					#:ShortDescription#
				</div>
				<div style="margin-top:0.9375rem;">
					<div class="unit templateDetail" style="padding-right:1.5625rem">
						<span>#:StartDate#</span>
					</div>
					<div class="unit templateDetail">
						<span>#:FullName#</span>
					</div>
				</div>
			</div>
		</div>
		<div class="listViewButton">
			<button id="classButton#:ID#" class="orangeButton" 
				style="width:4.6875rem;display:none;">promote</button>
		</div>

	</div>
</script>

<script type="text/x-kendo-template" id="workshopTemplate">
	<div class="myListItem">
		<div style="padding-bottom:1.0625rem;padding-top:1.25rem">
			<div class="unit" style="width:4.75rem;padding-top:0.625rem;padding-left:1.9375rem">
				<img height="50px" width="50px" src="#:ItemLogo#" alt="" />
			</div>
			<div class="unit lastUnit">
				<div class="listTitle">
					#:Name#
				</div>
				<div class="listDescription">
					#:ShortDescription#
				</div>
				<div style="margin-top:0.9375rem;">
					<div class="unit templateDetail" style="padding-right:1.5625rem">
						<span>#:StartDate#</span>
					</div>
					<div class="unit templateDetail">
						<span>#:FullName#</span>
					</div>
				</div>
			</div>
		</div>
		<div class="listViewButton">
			<button id="workshopButton#:ID#" class="orangeButton" 
				style="width:4.6875rem;display:none;">promote</button>
		</div>
	</div>
</script>

<script type="text/x-kendo-template" id="staffTemplate">
	<div class="myListItem">
		<div style="padding-bottom:1.0625rem;padding-top:1.25rem">
			<div class="unit" style="width:4.75rem;padding-top:0.625rem;padding-left:1.9375rem">
				<img height="50px" width="50px" src="#:ItemLogo#" alt="" />
			</div>
			<div class="unit lastUnit">
				<div class="listTitle">
					#:FullName#
				</div>
				<div class="listDescription" style="float:left;width:25rem">
					#:ShortDescription#
				</div>
			</div>
		</div>
		<div class="listViewButton">
			<button id="staffButton#:ID#" class="orangeButton" 
				style="width:4.6875rem;display:none;">promote</button>
		</div>
	</div>
</script>
