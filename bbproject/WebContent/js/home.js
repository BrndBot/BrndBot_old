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
	
		
		$('#chanEmailBadge').on('click', function(e)
		{
			// change to the content (dash)
			session_mgr.setSession(SESSION_SET, EMAIL_CHANNEL, 0, 0, homejs.routeViaChannel);
		});
	
	
		$('#chanFacebookBadge').on('click', function(e)
		{
			// set channel to FB
			session_mgr.setSession(SESSION_SET, FACEBOOK_CHANNEL, 0, 0, homejs.routeViaChannel);
		});
		
		/* This sets up the function for all the category buttons. */
		$('.categoryButton').on('click', function(e)
		{
			// NEW APPROACH: Suck in all the categories with a series of
			// DashboardServlet calls, then populate a table with them, making them
			// all initially invisible. By assigning appropriate classes,
			// JQuery calls can show or hide each column as a group.
			// Or perhaps simpler: Have each TD be a list of the prototypes
			// for that category, and Kendo-list it. Fewer changes that way too.
			var dataCat = $(this).attr("data-category");
			var template = $('template[data-category="' + dataCat + '"]');
			$('#modelRow').empty();
			$('#modelRow').append (template.clone().html());
			$('#modelRow').find('.modelButton').on ('click', handleModelButton);
		});
		
		/* This sets the function for all the content badge buttons in one fell swoop.
		 * Clicking on a content badge button will bring up the prototypes for
		 * that model. Since the buttons change dynamically, we need to assign this
		 * handler every time the button is loaded from a template.
		 * 
		 * TODO CHANGE IN SPEC: List the models just by name in a column below the
		 * category button.
		 */
		var handleModelButton = function (e)
		{
			model_name = $(this).attr('data-model');	// set the value for the callback
	
			// Kendo data source used to get list data for promotion Prototypes
			alert ("Getting promotion prototypes");
			homejs.protosDataSource  = new kendo.data.DataSource({
				transport: 
				{
					read:
					{
						type: 'POST',
						url: "DashboardServlet",
						dataType: "json",
					}
				}
			});
			session_mgr.setSession(SESSION_SET, 0, model_name, 0, homejs.showPrototypes);
			alert ("set data model " + model_name);
			$('#contentType').text (model_name);
		};
	
		// init dashboard
		initDashboard();
	
		homejs.fancyShow(homejs.HOME_JSP);
		session_mgr.setSession(SESSION_CLEAR);
	
	});
	
var homejs = {

	HOME_JSP: "#homeJsp",
	CHANNEL_JSP: "#channelJsp",
	
	current_jsp: "undefined",
	model_name: "",
	protosDataSource: null,
	session_mgr: "",
	
	/* Current selection in prototype list */
	currentItemID: -1,
	
	/* Kendo DataSource for listing the prototypes */
	

//	viewChannels: function ()
//	{
//		homejs.fancyShow(homejs.CHANNEL_JSP);
//	},
	
	/* This is called when the "promote" button for a prototype is clicked. */
	selectProto: function(btn) {	
		var protoName = $(btn).attr("data-proto");
		// TODO temp hard-code channel to 2
		window.location.assign ("edit.jsp?channel=2&proto=" + protoName + "&model=" + model_name);
		// TODO really should select channel next, but take a shortcut for now.
	},
	
	routeViaChannel: function(db_id)
	{
		if (SESSION_CHANNEL == SESSION_UNDEFINED)
		{
			alert('The channel is undefined in the function homejs.routeViaChannel().  This is a system error.');
			return;
		}
		window.location = 'edit.jsp?' + SESSION_DATABASE_ID_KEY + '=' + db_id;
	},
	
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
	
	
	
	/* Display the prototypes for the selected model */
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
			window.location.assign ("edit.jsp?channel=2&proto=Default&model=" + model_name);
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
		console.log ("showHoverImage");
		var img = $(button).find("img");
		img.attr("src", img.attr("data-hoversrc"));
	},
	
	/* Set a button to its non-hover state */
	showNormalImage: function (button) {
		console.log ("showNormalImage");
		var img = $(button).find("img");
		img.attr("src", img.attr("data-normalsrc"));
	}

};