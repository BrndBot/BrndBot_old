/*  Be very careful what information gets set in the session from here.
 *  Setting the database ID with no security sounds scary.
 */
var SESSION_UNDEFINED = -1;
var SESSION_CHANNEL = SESSION_UNDEFINED;
var SESSION_CONTENT = SESSION_UNDEFINED;
var SESSION_DATABASE_ID = SESSION_UNDEFINED;
var SESSION_IMAGE_ID = SESSION_UNDEFINED;

// action= values
var SESSION_SET = "set";
var SESSION_CLEAR = "clear";
var SESSION_GET = "get";
var SESSION_BLOCKS = "blocks";
var SESSION_IMAGE = "image";

// Session data keys
var SESSION_CONTENT_KEY = "brndbotcontent";
var SESSION_CHANNEL_KEY = "brndbotchannel";
var SESSION_DATABASE_ID_KEY = "brndbotdbid";
var SESSION_IMAGE_ID_KEY = "brndbotimageid";
var SESSION_FUSED_IMAGE_ID_KEY = "brndbotfusedid";

function Session ()
{
}

// "blocks" is the blockStack array from the editor/builder pages
Session.prototype.storeBlocks = function(blocks, refreshCallback)
{
	var myJsonString = JSON.stringify(blocks);
	$('#hiddenBlocks').val(myJsonString);
	var args = '?action=' + SESSION_BLOCKS;
	$.ajax({
        type: 'POST',
        dataType: 'json',
        url: 'SetSessionServlet' + args,
        data: blockForm.serialize(), // serializes the form's elements.
		statusCode: {
			401: function() {
			  Session.redirectToLogin();
			}
		}
    }).done (function () {
    	if (refreshCallback)
    	{
    		refreshCallback();
    	}	
    });
};

Session.prototype.setImageID = function(image_id)
{
	if (image_id == 0)
	{
		alert('invalid image_id');
		return;
	}

	$.ajax({
        type: 'POST',
        dataType: 'json',
        url: 'SetSessionServlet?action=image&' + SESSION_IMAGE_ID_KEY + '=' + image_id,
        success: function(data)
        {
        	SESSION_IMAGE_ID = parseInt(data[SESSION_IMAGE_ID_KEY]);
        },
        error: function (xhr, ajaxOptions, thrownError)
        {
        	alert('The call to Brndbot server failed: ' + xhr.status);
        }
    });
};

Session.prototype.setSession = function(action, channel_type, content_type, database_id, refreshCallback)
{
	var args = '?';
	args += 'action=' + action;
	if (action === SESSION_SET)
	{
		if (channel_type)
		{
			args += '&' + SESSION_CHANNEL_KEY + '=' + channel_type;
		}
	
		if (content_type)
		{
			args += '&' + SESSION_CONTENT_KEY + '=' + content_type;
		}

		if (database_id)
		{
			args += '&' + SESSION_DATABASE_ID_KEY + '=' + database_id;
		}
	}
	$.ajax({
        type: 'POST',
        dataType: 'json',
        url: 'SetSessionServlet' + args,
        success: function(data)
        {
        	SESSION_CHANNEL = parseInt(data[SESSION_CHANNEL_KEY]);
        	SESSION_CONTENT = parseInt(data[SESSION_CONTENT_KEY]);
        	SESSION_DATABASE_ID = parseInt(data[SESSION_DATABASE_ID_KEY]);
        	if (refreshCallback)
        	{
        		refreshCallback(SESSION_DATABASE_ID);
        	}	
        },
		statusCode: {
			401: function() {
			  Session.redirectToLogin();
			}
		}
    }).fail ( function (jqXHR, textStatus, errorThrown) {
        	alert('The call to Brndbot server failed: ' + xhr.status);
    });
};

Session.redirectToLogin = function () {
	window.location.assign("index.jsp");
};

function validChannel()
{
	return !(SESSION_CHANNEL == SESSION_UNDEFINED);
}

function validContent()
{
	return !(SESSION_CONTENT == SESSION_UNDEFINED);
}