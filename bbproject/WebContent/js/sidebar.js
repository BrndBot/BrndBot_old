var sidebar = {

lastSideBarID: '',
lastEmailID: '',

clickSideBar: function (startID)
{
	if (startID === sidebar.lastSideBarID)
	{
		return;
	}

	if (sidebar.lastSideBarID !== '')
	{
		sidebar.collapseSideBar(sidebar.lastSideBarID);
	}

	// Clear the email submenu choice
	if (sidebar.lastEmailID !== '')
	{
		$('#' + sidebar.lastEmailID).removeClass('isActiveOrange');
	}

	if (startID === 'homeLine')
	{
		$('#homeLine').switchClass('isInactive homeInactive', 'isActive homeActive');
	}
	else if (startID === 'emailLine')
	{
		$('#emailPs').show();
		$('#emailLine').switchClass('isInactive emailInactive', 'isActive emailActive');
	}
	else if (startID === 'socialLine')
	{
		$('#socialLine').removeClass('isInactive socialInactive').addClass('isActive socialActive');
	}
	else if (startID === 'autoLine')
	{
		$('#autoLine').removeClass('isInactive autoInactive').addClass('isActive autoActive');
	}
	else if (startID === 'acctLine')
	{
		$('#acctLine').removeClass('isInactive acctInactive').addClass('isActive acctActive');
	}
	else if (startID === 'imagesLine')
	{
		window.location = 'images.jsp';
		return;
	}
	else
	{
		alert('Forgot something');
		return;
	}
	sidebar.lastSideBarID = startID;
},

collapseSideBar: function (startID)
{
	if (startID === 'homeLine')
	{
		$('#homeLine').removeClass('isActive homeActive').addClass('isInactive homeInactive');
	}
	else if (startID === 'emailLine')
	{
//		$('#emailPs').fadeOut();
		$('#emailPs').hide();
		$('#emailLine').removeClass('isActive emailActive').addClass('isInactive emailInactive');
	}
	else if (startID === 'socialLine')
	{
		$('#socialLine').removeClass('isActive socialActive').addClass('isInactive socialInactive');
	}
	else if (startID === 'autoLine')
	{
		$('#autoLine').removeClass('isActive autoActive').addClass('isInactive autoInactive');
	}
	else if (startID === 'acctLine')
	{
		$('#acctLine').removeClass('isActive acctActive').addClass('isInactive acctInactive');
	}
	else
	{
		alert('Forgot something');
		return;
	}
},

emailTypeClicked: function(id)
{
	if (sidebar.lastEmailID === id)
	{
		return;
	}

	if (id === 'emailAll' ||
			id === 'emailScheduled' ||
			id === 'emailDrafts' ||
			id === 'emailSent' ||
			id === 'emailTrash')
	{
		$('#' + id).addClass('isActiveOrange');
		if (sidebar.lastEmailID !== '')
		{
			$('#' + sidebar.lastEmailID).removeClass('isActiveOrange');
		}
		sidebar.lastEmailID = id;
	}
	else
		alert('Wrong email option.');
}
};