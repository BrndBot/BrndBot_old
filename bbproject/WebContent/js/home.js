var HOME_JSP = "#homeJsp";
var EMAIL_JSP = "#emailJsp";  // hub
var CONTENT_JSP = "#contentTypeJsp";
var DASHBOARD_JSP = "#dashboardJsp";
var SOCIAL_JSP = "#socialJsp";
var CHANNEL_JSP = "#channelJsp";

var current_jsp = "undefined";
var model_name = "";

var session_mgr = "";

$(document).ready(function() 
{
/*
	$('#emailBadge').on('click', function(e)
	{
		emailBadge();
	});

	$('#classBadge').on('click', function(e)
	{
		classBadge();
	});

	$('#workshopBadge').on('click', function(e)
	{
		workshopBadge();
	});

	$('#saleBadge').on('click', function(e)
	{
		saleBadge();
	});
*/
	/*
	$('#twitterBadge').on('click', function(e)
	{
		twitterBadge();
	});

	$('#facebookBadge').on('click', function(e)
	{
		facebookBadge();
	});

	$('#teacherBadge').on('click', function(e)
	{
		teacherBadge();
	});

	$('#scheduleBadge').on('click', function(e)
	{
		scheduleBadge();
	});

	$('#classContentBadge').on('click', function(e)
	{
		$('#viewClasses').click();
		hideOrShow(DASHBOARD_JSP);
	});

	$('#workshopContentBadge').on('click', function(e)
	{
		$('#viewWorkshops').click();
		hideOrShow(DASHBOARD_JSP);
	});

	$('#teacherContentBadge').on('click', function(e)
	{
		$('#viewTeachers').click();
		hideOrShow(DASHBOARD_JSP);
	});

	$('#scheduleContentBadge').on('click', function(e)
	{
		session_mgr.setSession(SESSION_SET, 0, SCHEDULE_OBJ,
				routeViaChannel);
	});
*/	
	$('#chanEmailBadge').on('click', function(e)
	{
		// change to the content (dash)
		session_mgr.setSession(SESSION_SET, EMAIL_CHANNEL, 0, 0, routeViaChannel);
	});

	$('#chanTwitterBadge').on('click', function(e)
	{
		// change to the content (dash)
		session_mgr.setSession(SESSION_SET, TWITTER_CHANNEL, 0, 0, routeViaChannel);
	});

	$('#chanFacebookBadge').on('click', function(e)
	{
		// set channel to FB
		session_mgr.setSession(SESSION_SET, FACEBOOK_CHANNEL, 0, 0, routeViaChannel);
	});
	
	/* This sets the function for all the badge buttons in one fell swoop */
	$('.homeBadgeButton').on('click', function(e)
	{
		session_mgr.setSession(SESSION_SET, 0, $(this).attr("data-model"), 0, showPrototypes);
		console.log ("set data model " + $(this).attr("data-model"));
		//TODO need to set a refresh callback
	});

	// init dashboard
	initDashboard();

	hideOrShow(HOME_JSP);
	session_mgr.setSession(SESSION_CLEAR);
/*
	function emailBadge()
	{
		// set channel to email
		session_mgr.setSession(SESSION_SET, EMAIL_CHANNEL, 0, 0, function()
		{
			$('#channelAction').html('Send by email');
			hideOrShow(CONTENT_JSP);
		});
	}

	function twitterBadge()
	{
		// set channel to FB
		session_mgr.setSession(SESSION_SET, TWITTER_CHANNEL, 0, 0, function()
		{
			$('#channelAction').html('Tweet on Twitter');
			hideOrShow(CONTENT_JSP);
		});
	}

	function facebookBadge()
	{
		// set channel to FB
		session_mgr.setSession(SESSION_SET, FACEBOOK_CHANNEL, 0, 0, function()
		{
			$('#channelAction').html('Post to Facebook');
			hideOrShow(CONTENT_JSP);
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
		hideOrShow(DASHBOARD_JSP);
	}

	function workshopBadge()
	{
		session_mgr.setSession(SESSION_SET, 0, WORKSHOP_OBJ, -1, clickWorkshopView);
	}

	function clickWorkshopView()
	{
		$('#viewWorkshops').click();
		hideOrShow(DASHBOARD_JSP);
	}

	function teacherBadge()
	{
		session_mgr.setSession(SESSION_SET, 0, STAFF_OBJ, -1, clickTeacherView);
	}

	function clickTeacherView()
	{
		$('#viewTeachers').click();
		hideOrShow(DASHBOARD_JSP);
	}

	function saleBadge()
	{
		alert('Sale/promotion template set is not yet installed on this server.');
//uncomment when fixed		session_mgr.setSession(SESSION_SET, 0, SALE_OBJ, -1);
	}
*/
/*
	function scheduleBadge()
	{
		session_mgr.setSession(SESSION_SET, 0, SCHEDULE_OBJ, -1);
		if (SESSION_CHANNEL == SESSION_UNDEFINED)
		{
			// No channel, go get it
			viewChannels();
			return;
		}
		else
		{
			// We know the channel, get which class
			$('#schedulContentBadge').click();  
		}
	}
*/
});

function viewChannels()
{
	hideOrShow(CHANNEL_JSP);
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

function hideOrShow(show_id)
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

// Kendo data source used to get list data for promotion Prototypes
var protosDataSource  = new kendo.data.DataSource({
	transport: 
	{
		read:
		{
			url: "DashboardServlet?type=" + model_name,
			dataType: "json"
		}
	}
})

/* Display the prototypes for the selected model */
function showPrototypes() 
	// First question: Where do I GET the prototypes?
	// Two possible approaches:
	// (1) The prototypes are fed to us as a JSON object.
	// (2) We do an Ajax callback to get them.
	// The JSON object is the simpler approach, though it could bloat
	// the memory used in the browser. Would have to POST the whole
	// big thing.
	// 10 MB seems to be a practical limit; that's enough for a whole Bible.
	// But Kendo callbacks fit the existing paradigm. 
	// Next question: Where do I get a value for badge??
{
	var badge = "";		// TODO placeholder
	model_name = $(badge).attr('data-model');
	$("promoProtosHere").kendoListView({
			dataSource: protosDataSource,
		    selectable: true,
	        template: kendo.template($("#" + idPrefix[CLASS_OBJ - 1] + "Template").html()),
		    change: selectPromoProtoItem,
		    dataBound: onPromoProtoSuccess
    });
}

