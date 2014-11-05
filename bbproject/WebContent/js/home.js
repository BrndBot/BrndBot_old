var HOME_JSP = "#homeJsp";
var EMAIL_JSP = "#emailJsp";  // hub
var CONTENT_JSP = "#contentTypeJsp";
var DASHBOARD_JSP = "#dashboardJsp";
var SOCIAL_JSP = "#socialJsp";
var CHANNEL_JSP = "#channelJsp";

var current_jsp = "undefined";

$(document).ready(function() 
{
/*
	$('#emailBadge').on('click', function(e)
	{
		emailBadge();
	});
*/
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
/*
	$('#twitterBadge').on('click', function(e)
	{
		twitterBadge();
	});

	$('#facebookBadge').on('click', function(e)
	{
		facebookBadge();
	});
*/
	$('#teacherBadge').on('click', function(e)
	{
		teacherBadge();
	});

	$('#scheduleBadge').on('click', function(e)
	{
		scheduleBadge();
	});
/*
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
	function classBadge()
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


