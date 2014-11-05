$(document).ready(function() 
{
	var currentType = emailTYpe; // default is list of email history

	var facebookDataSource  = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "HistoryServlet?type=" + facebookType,
				dataType: "json"
			}
		}
	});

	var twitterDataSource  = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "HistoryServlet?type=" + twitterType,
				dataType: "json"
			}
		}
	});

	var emailDataSource  = new kendo.data.DataSource({
		transport: 
		{
			read:
			{
				url: "HistoryServlet?type=" + emailType,
				dataType: "json"
			}
		}
	});

	$("#emailHere").kendoListView({
		dataSource: emailDataSource,
	    selectable: true,
        template: kendo.template($("#emailTemplate").html()),
	    change: selectEmailListItem,
	    dataBound: onEmailListSuccess
    });

	function selectEmailListItem(e)
	{
		alert('email');
	}

	function selectFacebookListItem(e)
	{
		alert('facebook');
	}

	function selectTwitterListItem(e)
	{
		alert('twitter');
	}

	function onEmailListSuccess(e)
	{
		alert('email success');
	}

	function onFacebookListSuccess(e)
	{
		alert('facebook success');
	}

	function onTwitterListSuccess(e)
	{
		alert('twitter success');
	}

});
