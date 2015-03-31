/**
 * home.js
 * 
 *  CONFIDENTIAL
 *  
 *  All rights reserved by Brndbot, Ltd. 2015
 *  
 * This JavaScript package supports home.jsp.
 * 
 */

$(document).ready(function() 
	{
	
		

		
		/* This sets up the function for all the category buttons. */
		$('.categoryButton').on('click', function(e)
		{
			var dataCat = $(this).attr("data-category");
			// We just make the corresponding column visible for the category,
			// and all the others invisible.
			var modelColumns = $(".modelsTable");
			modelColumns.each (function () {
				if ($(this).attr("data-category") == dataCat)
					$(this).show();
				else
					$(this).hide();
			});
		});
		
		/* This sets the function for all the content badge buttons in one fell swoop.
		 * Clicking on a content badge button will bring up the prototypes for
		 * that model. Since the buttons change dynamically, we need to assign this
		 * handler every time the button is loaded from a template.
		 * 
		 * TODO CHANGE IN SPEC: List the models just by name in a column below the
		 * category button.
		 */
		$('.modelButton').on('click', function(e)
		{
			homejs.model_name = $(this).attr("data-model");
			// TODO remove channel parameter, not set till next page
			// Kendo data source used to get list data for promotion Prototypes
			homejs.protosDataSource  = new kendo.data.DataSource({
				transport: 
				{
					read:
					{
						type: 'POST',
						url: "DashboardServlet",
						dataType: "json",
					}
				},
				error: function (xhr) {
					if (xhr && (xhr.statusCode() == 401))
							homejs.redirectToLogin();
				}
			});
			session_mgr.setSession(SESSION_SET, 0, homejs.model_name, 0, homejs.showPrototypes);
			$('#contentType').text (homejs.model_name);
		});
	
		// init dashboard
		//initDashboard();
	
		homejs.fancyShow(homejs.HOME_JSP);
		session_mgr.setSession(SESSION_CLEAR);
	
	});
	
var homejs = {

	HOME_JSP: "#homeJsp",
	
	current_jsp: "undefined",
	model_name: "",
	protosDataSource: null,
	session_mgr: "",
	
	/* Current selection in prototype list */
	currentItemID: -1,
	

	
	fancyShow: function (show_id)
	{
		if (show_id !== homejs.current_jsp)
		{
			if (homejs.current_jsp !== "undefined")
			{
				$(homejs.current_jsp).fadeOut(250, function() {
					$(show_id).show();
				});
			}
			else
				$(show_id).show();
	
			homejs.current_jsp = show_id;
		}
	},
	
	
	
	/* Display the prototypes for the selected model, or jump directly to
	 * channel.jsp if there are no prototypes. */
	showPrototypes: function() 
		// We use DashboardServlet to get the prototypes. 
		// model_name was set when the button was clicked.
	{
		console.log ("showPrototypes");
		currentItemID = -1;
		var showit = true;
		var hasRealData = true;
		if (homejs.protosDataSource.total() == 0)
			hasRealData = false;
		else if (homejs.protosDataSource.total() == 1) {
			var singleProto = protosDataSource.data()[0];
			if (singleProto.synthetic)
				hasRealData = false;
		}
		if (!hasRealData) {
			// Go directly to editor 
			window.location.assign ("channel.jsp?channel=0&proto=Default&model=" + homejs.model_name);
			showit = false;
		}
		$("#promoProtosHere").kendoListView({
				dataSource: homejs.protosDataSource,
			    selectable: true,
		        template: kendo.template($('#imageTemplate').html()),
			    change: homejs.selectPromoProtoItem,
			    dataBound: homejs.onPromoProtoSuccess
	    });
		if (showit) {
			$('#dashboardJsp').show();
			$('#promoProtosHere').show();
		}
	},
	
	/* Called on selection of a promotion prototype.
	 * The variable "this" is the Kendo list item. */
	selectPromoProtoItem: function (e) {
		homejs.setListState (this);
	},
	
	/* May not need to do anything here */
	onPromoProtoSuccess: function () {
	},
	
	/* Update the promotion prototype list so the currently
	   selected item is highlighted and its dispatch button visible. */
	setListState: function(kendoList) {
		// Get the selected item
	    var index = kendoList.select().index();
	    var item = kendoList.dataSource.view()[index];
	    // item is an item from the dataSource. We gave them sequential ID values.
	    var itemID = item.ID;
		if (currentItemID == itemID) {
			return;
		}
		currentItemID = itemID;
	},
	
	/* Set a button to its hover state. */
	showHoverImage: function (button) {
		var img = $(button).find("img");
		img.attr("src", img.attr("data-hoversrc"));
	},
	
	/* Set a button to its non-hover state */
	showNormalImage: function (button) {
		var img = $(button).find("img");
		img.attr("src", img.attr("data-normalsrc"));
	},
	
	redirectToLogin: function () {
		window.location.assign("index.jsp");
	}


};