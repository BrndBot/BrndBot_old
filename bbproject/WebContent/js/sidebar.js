var lastSideBarID = '';
var lastEmailID = '';

function clickSideBar(startID)
{
	if (startID === lastSideBarID)
	{
		return;
	}

	if (lastSideBarID !== '')
	{
		collapseSideBar(lastSideBarID);
	}

	// Clear the email submenu choice
	if (lastEmailID !== '')
	{
		$('#' + lastEmailID).removeClass('isActiveOrange');
	}

	if (startID === 'homeLine')
	{
//		$('#homeLine').switchClass('isInactive homeInactive', 'isActive homeActive', { duration: 250 });
		$('#homeLine').switchClass('isInactive homeInactive', 'isActive homeActive');
	}
	else if (startID === 'emailLine')
	{
//		$(this).animate({height:'200px'});
//		$('#emailPs').fadeIn();
		$('#emailPs').show();
//		$('#emailLine').switchClass('isInactive emailInactive', 'isActive emailActive', { duration: 250 });
		$('#emailLine').switchClass('isInactive emailInactive', 'isActive emailActive');
	}
	else if (startID === 'socialLine')
	{
//		$('#socialLine').switchClass('isInactive socialInactive', 'isActive socialActive', { duration: 250 });
		$('#socialLine').removeClass('isInactive socialInactive').addClass('isActive socialActive');
	}
	else if (startID === 'autoLine')
	{
//		$('#autoLine').switchClass('isInactive autoInactive', 'isActive autoActive', { duration: 250 });
		$('#autoLine').removeClass('isInactive autoInactive').addClass('isActive autoActive');
	}
	else if (startID === 'acctLine')
	{
//		$('#acctLine').switchClass('isInactive acctInactive', 'isActive acctActive', { duration: 250 });
		$('#acctLine').removeClass('isInactive acctInactive').addClass('isActive acctActive');
	}
	else
	{
		alert('Forgot something');
		return;
	}
	lastSideBarID = startID;
}

function collapseSideBar(startID)
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
}

function emailTypeClicked(id)
{
	if (lastEmailID === id)
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
		if (lastEmailID !== '')
		{
			$('#' + lastEmailID).removeClass('isActiveOrange');
		}
		lastEmailID = id;
	}
	else
		alert('Wrong email option.');
}
