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
	
	/* This sets the function for all the content badge buttons in one fell swoop.
	 * Clicking on a content badge button will bring up the prototypes for
	 * that model.
	 */
	$('.homeBadgeButton').on('click', function(e)
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
	});

	// init dashboard
	initDashboard();

	fancyShow(HOME_JSP);
	session_mgr.setSession(SESSION_CLEAR);
/*
	function emailBadge()
	{
		// set channel to email
		session_mgr.setSession(SESSION_SET, EMAIL_CHANNEL, 0, 0, function()
		{
			$('#channelAction').html('Send by email');
			fancyShow(CONTENT_JSP);
		});
	}

	function twitterBadge()
	{
		// set channel to FB
		session_mgr.setSession(SESSION_SET, TWITTER_CHANNEL, 0, 0, function()
		{
			$('#channelAction').html('Tweet on Twitter');
			fancyShow(CONTENT_JSP);
		});
	}

	function facebookBadge()
	{
		// set channel to FB
		session_mgr.setSession(SESSION_SET, FACEBOOK_CHANNEL, 0, 0, function()
		{
			$('#channelAction').html('Post to Facebook');
			fancyShow(CONTENT_JSP);
		});
	}
*/
/*	function classBadge()
	{
		session_mgr.setSession(SESSION_SET, 0, CLASS_OBJ, -1, clickClassView);
	}
	function clickClassView()
	{
		$('#viewClasses').click();
		fancyShow(DASHBOARD_JSP);
	}

	function workshopBadge()
	{
		session_mgr.setSession(SESSION_SET, 0, WORKSHOP_OBJ, -1, clickWorkshopView);
	}

	function clickWorkshopView()
	{
		$('#viewWorkshops').click();
		fancyShow(DASHBOARD_JSP);
	}

	function teacherBadge()
	{
		session_mgr.setSession(SESSION_SET, 0, STAFF_OBJ, -1, clickTeacherView);
	}

	function clickTeacherView()
	{
		$('#viewTeachers').click();
		fancyShow(DASHBOARD_JSP);
	}

	function saleBadge()
	{
		alert('Sale/promotion template set is not yet installed on this server.');
//uncomment when fixed		session_mgr.setSession(SESSION_SET, 0, SALE_OBJ, -1);
	}
*/

});

function viewChannels()
{
	fancyShow(CHANNEL_JSP);
}

/* This is called when the "promote" button for a prototype is clicked. */
function selectProto (btn) {	
	var protoName = $(btn).attr("data-proto");
	// TODO temp hard-code channel to 1
	window.location.assign ("bench.jsp?channel=1&proto=" + protoName + "&model=" + model_name);
	// TODO really should select channel next, but take a shortcut for now.
}

function routeViaChannel(db_id)
{
	if (SESSION_CHANNEL == SESSION_UNDEFINED)
	{
		alert('The channel is undefined in the function home.js.routeViaChannel().  This is a system error.');
		return;
	}
	window.location = 'bench.jsp?' + SESSION_DATABASE_ID_KEY + '=' + db_id;
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