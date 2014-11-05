var emailListChoice = null;

$(document).ready(function() 
{
	$("#emailLists").kendoDropDownList({
        placeholder: "Choose your recipient list",
        change: emailListSelected
    });

	$("#sendButton").kendoButton({
		click: sendClicked
	});

    var dataSource = new kendo.data.DataSource({
        data: json_blocks
    });

    function emailListSelected(e)
    {
    	emailListChoice = this.value().substring(0, 3);
    	alert(emailListChoice);
    }

    $("#summaryHere").kendoListView({
        dataSource: dataSource,
	    selectable: true,
	    change: selectListItem,
	    dataBound: onListSuccess,
        template: kendo.template($("#template").html())
    });

	function onListSuccess(e)
	{
//		doListSuccess(CLASS_OBJ);
	}

    function selectListItem(e)
	{
    	//    	alert(e.target.id);
//        setListState(this, CLASS_OBJ);
	}

    
	$("#finishButton").kendoButton({
		click: finishClicked
	});

	function finishClicked()
	{
		window.location = 'home.jsp';
	}

	function sendClicked(e)
	{
		if (!ACCESS_TOKEN || ACCESS_TOKEN.length == 0)
		{
			alert('You are not able to post to Facebook from this server.  Are you running localhost?');
			return;
		}
		publishWallPost(json_blocks[1]);
		$('#finishPane').hide();
		$('#pleaseWaitPane').show();
		setTimeout('showSuccessPane()', 3000);
	    var pb = $("#progressBar").kendoProgressBar({
	        type: "percent",
	        animation: {
	            duration: 1800
	        }
	    }).data("kendoProgressBar");	
		pb.value(100);
	}
//	sendClicked();

});

function showSuccessPane()
{
	$('#pleaseWaitPane').hide();
	$('#successPane').show();
}
