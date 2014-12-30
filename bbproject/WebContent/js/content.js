var template =
{
	id : "",
	name : "object name",
	staffname : "teacher name",
	full_description : "full_description",
	short_description : "short_description",
	imgURL : "image url"
};

var currentListID = new Array(0, 0, 0);

function initDashboard() 
{
	// Currently selected List items
	var currentType = "";
	var currentChannel = EMAIL_CHANNEL;
	


/*
	function turnOffActiveChannel(nextChannel)
	{
		if (nextChannel !== 'email' && currentChannel === EMAIL_CHANNEL)
		{
			doEmailClick();
		}
		if (nextChannel !== 'fb' && currentChannel === FACEBOOK_CHANNEL)
		{
			doFacebookClick();
		}
		if (nextChannel !== 'twitter' && currentChannel === TWITTER_CHANNEL)
		{
			doTwitterClick();
		}
	}
*/
	function selectClassListItem(e)
	{
        setListState(this, CLASS_OBJ);
	}

	function selectWorkshopListItem(e)
	{
        setListState(this, WORKSHOP_OBJ);
	}

	function selectStaffListItem(e)
	{
        setListState(this, STAFF_OBJ);
	}

	function onClassListSuccess(e)
	{
		doListSuccess(CLASS_OBJ);
	}

	function doListSuccess(cType)
	{
		var id = '#' + idPrefix[cType - 1] + 'Here';

//		$(id + '>div').css({'border-bottom' : '.0625rem #000000 solid' });
		
		var listView = $(id).data("kendoListView");

		// get data items for the selected options.
		var dataItems = listView.dataSource._data;
		for (var i=0; i < dataItems.length; i++)
		{
	        var dataItem = dataItems[i];
	        var id = dataItem.ID;
	    	$('#' + idPrefix[cType - 1] + 'Button' + id).kendoButton({
	    		click: publishClicked
	    	});
		}
	}

	function onWorkshopListSuccess(e)
	{
		doListSuccess(WORKSHOP_OBJ);
		$('#contentType').html("Workshops");
		$('#viewButtonPanel').show();
		showHideLists();
	}

	function onStaffListSuccess(e)
	{
		doListSuccess(STAFF_OBJ);
	}

	function publishClicked(e)
	{
        var currentID = currentListID[currentType - 1];
		if (validChannel() && validContent())
		{
	        session_mgr.setSession(SESSION_SET, 0, 0, currentID, routeViaChannel);
		}
		else
		{
//			alert(validContent() + ', ' + validChannel());
			session_mgr.setSession(SESSION_SET, 0, 0, currentID, viewChannels);			
/*			if (validContent())
			{
			}
			else
				alert('Unexpected circumstance in content.js.publishClicked().  Please contact tech support.');
*/
			}
	}

	function setListState(list, bType)
	{
        var index = list.select().index();
        var item = list.dataSource.view()[index];
        var listID = item.ID;
        var currentID = currentListID[bType - 1];
		if (currentID === listID)
		{
			// Same as last time, so skip
			return;
		}

		if (currentID !== 0)
		{
			$('#' + idPrefix[bType - 1] + 'Button' + currentID).hide();
		}

		if (listID !== 0)
		{
			$('#' + idPrefix[bType - 1] + 'Button' + listID).show();

			// Save the variables for the block templates
			template.id = listID;
			template.name = item.Name;
			template.fullname = item.FullName;
			template.full_description = item.FullDescription;
			template.short_description = item.ShortDescription;
			template.imgURL = item.ImgURL;
		}

		currentListID[bType - 1] = listID;
	}

	function viewClassList(e)
	{
		setButtonClass('viewClasses');
		currentType = CLASS_OBJ;
		$('#contentType').html('Classes');
		$('#promoteThisTxt').html('&nbsp;class');
		showHideLists();
		session_mgr.setSession(SESSION_SET, 0, CLASS_OBJ, -1);
	}

	function showHideLists()
	{
		showHideList(currentType, CLASS_OBJ);
		showHideList(currentType, WORKSHOP_OBJ);
		showHideList(currentType, STAFF_OBJ);
	}

	function showHideList(curType, compareType)
	{
		var id = '#' + idPrefix[compareType - 1] + 'Here';
		var pagerID = '#' + idPrefix[compareType - 1] + 'Pager';

		if (curType == compareType)
		{
			$(id).show();
			$(pagerID).show();
		}
		else
		{
			$(id).hide();
			$(pagerID).hide();
		}
	}

	function setButtonClass(newID)
	{
		$('#' + currentButtonID).removeClass('greenNoHoverButton');
		$('#' + currentButtonID).addClass('grayNoHoverButton');
		$('#' + newID).removeClass('grayNoHoverButton');
		$('#' + newID).addClass('greenNoHoverButton');
		currentButtonID = newID;
	}

	function viewWorkshopList(e)
	{
		setButtonClass('viewWorkshops');
		currentType = WORKSHOP_OBJ;
		$('#contentType').html('Workshops');
		$('#promoteThisTxt').html('&nbsp;workshop');
		showHideLists();
		session_mgr.setSession(SESSION_SET, 0, WORKSHOP_OBJ, -1);
	}

	function viewStaffList(e)
	{
		setButtonClass('viewTeachers');
		currentType = STAFF_OBJ;
		$('#contentType').html('Teachers');
		$('#promoteThisTxt').html('&nbsp;teacher');
		showHideLists();
		session_mgr.setSession(SESSION_SET, 0, STAFF_OBJ, -1);
	}
}
/*
function stripOutHtml(html)
{
	html = html.replace(/<style([\s\S]*?)<\/style>/gi, '');
	html = html.replace(/<script([\s\S]*?)<\/script>/gi, '');
	html = html.replace(/<\/div>/ig, '\n');
	html = html.replace(/<\/li>/ig, '\n');
	html = html.replace(/<li>/ig, '  *  ');
	html = html.replace(/<\/ul>/ig, '\n');
	html = html.replace(/<\/p>/ig, '\n');
	html = html.replace(/<br\s*[\/]?>/gi, "\n");
	html = html.replace(/<[^>]+>/ig, '');
	return html;
}
*/