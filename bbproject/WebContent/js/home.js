var HOME_JSP = "#homeJsp";
var EMAIL_JSP = "#emailJsp";  // hub
var CONTENT_JSP = "#contentTypeJsp";
var DASHBOARD_JSP = "#dashboardJsp";
var SOCIAL_JSP = "#socialJsp";
var CHANNEL_JSP = "#channelJsp";

var current_jsp = "undefined";
var model_name = "";
var protosDataSource = null;
var session_mgr = "";

/* Current selection in prototype list */
var currentItemID = -1;

$(document).ready(function() 
{

	
	$('#chanEmailBadge').on('click', function(e)
	{
		// change to the content (dash)
		session_mgr.setSession(SESSION_SET, EMAIL_CHANNEL, 0, 0, routeViaChannel);
	});


	$('#chanFacebookBadge').on('click', function(e)
	{
		// set channel to FB
		session_mgr.setSession(SESSION_SET, FACEBOOK_CHANNEL, 0, 0, routeViaChannel);
	});
	
	/* This sets up the function for all the category buttons. */
	$('.categoryButton').on('click', function(e)
	{
		// So how should I do this? Best way is probably to have a template
		// for each available row and copy the appropriate one here.
		// Time to get HTML5 templates working right.
		console.log("Click!");
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
	 */
	var handleModelButton = function (e)
	{
		model_name = $(this).attr('data-model');	// set the value for the callback

		// Kendo data source used to get list data for promotion Prototypes
		protosDataSource  = new kendo.data.DataSource({
			transport: 
			{
				read:
				{
					url: "DashboardServlet",
					dataType: "json"
				}
			}
		});
		session_mgr.setSession(SESSION_SET, 0, model_name, 0, showPrototypes);
		console.log ("set data model " + model_name);
		$('#contentType').text (model_name);
	}

	// init dashboard
	initDashboard();

	fancyShow(HOME_JSP);
	session_mgr.setSession(SESSION_CLEAR);

});

function viewChannels()
{
	fancyShow(CHANNEL_JSP);
}

/* This is called when the "promote" button for a prototype is clicked. */
function selectProto (btn) {	
	var protoName = $(btn).attr("data-proto");
	// TODO temp hard-code channel to 1
	window.location.assign ("edit.jsp?channel=1&proto=" + protoName + "&model=" + model_name);
	// TODO really should select channel next, but take a shortcut for now.
}

function routeViaChannel(db_id)
{
	if (SESSION_CHANNEL == SESSION_UNDEFINED)
	{
		alert('The channel is undefined in the function home.js.routeViaChannel().  This is a system error.');
		return;
	}
	window.location = 'edit.jsp?' + SESSION_DATABASE_ID_KEY + '=' + db_id;
}

function fancyShow(show_id)
{
	if (show_id !== current_jsp)
	{
		if (current_jsp !== "undefined")
		{
			$(current_jsp).fadeOut(250, function() {
				$(show_id).show();
			});
		}
		else
			$(show_id).show();

		current_jsp = show_id;
	}
}



/* Display the prototypes for the selected model */
function showPrototypes() 
	// We use DashboardServlet to get the prototypes. 
	// model_name was set when the button was clicked.
{
	console.log ("showPrototypes");
	currentItemID = -1;
	$("#promoProtosHere").kendoListView({
			dataSource: protosDataSource,
		    selectable: true,
	        template: kendo.template($('#imageTemplate').html()),
		    change: selectPromoProtoItem,
		    dataBound: onPromoProtoSuccess
    });
	$('#dashboardJsp').show();
	$('#promoProtosHere').show();
}

/* Called on selection of a promotion prototype.
 * The variable "this" is the Kendo list item. */
function selectPromoProtoItem (e) {
	setListState (this);
}

/* May not need to do anything here */
function onPromoProtoSuccess () {
}

/* Update the promotion prototype list so the currently
   selected item is highlighted and its dispatch button visible. */
function setListState (kendoList) {
	// Get the selected item
    var index = kendoList.select().index();
    var item = kendoList.dataSource.view()[index];
    // item is an item from the dataSource. We gave them sequential ID values.
    var itemID = item.ID;
    console.log ("itemID = " + itemID);
	if (currentItemID == itemID) {
		return;
	}
	// TODO hide old button, hide new one.
	currentItemID = itemID;
}