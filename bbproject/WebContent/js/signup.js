var yourPalette = [ "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4" ];
var suggestedPalette = [ "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4", "#E2E3E4" ];
var other_colors = new Array();
var maxColors = 7;

var colorsPicked = null;

// The currently selected palette id (1-4)
var currentYourPalette = null;

//The currently selected related palette id (1-50)
var currentAltPalette = null;

var currentMockup = null;

var currentSuggestedPalette = null;
var targetImage = null;

$(document).ready(function() 
{
	displayForLogo(toLogo);

	$("#helpFindUrls").kendoButton({
	});

	$("#createMyAccount").kendoButton({
		click: createMyAccount
	});

	$("#updateAccount").kendoButton({
		click: updateAccount
	});

	$("#updateSocials").kendoButton({
		click: updateSocials
	});

	$("#updateLogo").kendoButton({
		click: updateLogo
	});

	$("#skipPaletteButton").kendoButton({
		click: skipPalette
	});

	$("#viewPaletteButton").kendoButton({
		click: viewPalette
	});
	
	$("#unforkToPos").kendoButton({
		click: unforkToPos
	});

	$("#posContinue").kendoButton({
		click: posSelected
	});

	$("#signUpComplete").kendoButton({
		click: goHome
	});

	$("#industryList").kendoMultiSelect({
	  animation: false
	});

	function setPaletteBlockSize(id, height, width, setClass)
	{
		var base = '#' + id;
		// cache selectors for better performance
		var container = $(base);
		container.animate({width: width + 'px', height: height + 'px'},400);

//	opacity from 0 to 1	        wrapper.fadeTo('slow', 1);

		container.attr('class', setClass);
	}

	$('#mockup1').on('click', function(event)
	{
		changeLook('mockup1');
	});

	$('#mockup2').on('click', function(event)
	{
		changeLook('mockup2');
	});

	$('#mockup3').on('click', function(event)
	{
		changeLook('mockup3');
	});

	function changeLook(id)
	{
		if (currentMockup === id)
		{
			$('#' + id).attr('class', 'mockups');

			//			setPaletteBlockSize(currentMockup, 122, 170, 'mockups');
			currentMockup = null;
			return;
		}

		currentMockup = id;
		$('#' + id).attr('class', 'mockups-selected');
//		setPaletteBlockSize(currentMockup, 122, 170, 'mockups-selected');
	}	
	
	$('.paletteSquare').on('click', function(event) 
	{
		if (currentAltPalette && currentAltPalette.length > 0)
		{
			setPaletteBlockSize(currentAltPalette, 26, 39, 'paletteCol');
			currentAltPalette = null;
		}

		if (currentYourPalette === event.target.id)
		{
			setPaletteBlockSize(currentYourPalette, 50, 75, 'paletteSquare');
			currentYourPalette = null;
			return;
		}

		if (currentYourPalette && currentYourPalette.length > 0)
		{
			setPaletteBlockSize(currentYourPalette, 50, 75, 'paletteSquare');
		}
		currentYourPalette = event.target.id;
		setPaletteBlockSize(currentYourPalette, 60, 90, 'paletteSquare-selected');
	});

	$('#mindBodyPos').on('click', function(event) 
	{
		$('#posTitle').html("Connect to Mindbody, good.");
		$('#mindBodySpecific').show();
	});

    $("#files").kendoUpload({
        async: {
            saveUrl: "SaveImageServlet",
//            saveUrl: "http://pictaculous.com/api/1.0/",
            removeUrl: "RemoveLogoServlet",
            autoUpload: true
        },
        localization: {
            select: "upload logo",
            headerStatusUploaded: "Success!"
        },
        error: onError,
        success: onSuccess,
        multiple: false,
        select: onSelect,
        showFileList: false
    });
   
    function onSuccess(e)
    {
    	// Get the name for pictaculous/php all
    	var logoName = e.response.imageName;
    	files = e.files;

    	if (logoName.indexOf("localhost") > -1)
    	{
    		logoName = 'http://brndbot.com:8080/images/DemoLogo.jpg';
    	}

    	$.ajax({
            type: 'GET',
            dataType: 'json',
            url: PHP_SERVER_PAGE + '?name=' + encodeURIComponent(logoName),
            success: function(data)
            {
            	uploadStatus("Saving your palette, please wait...");

            	var json = jQuery.parseJSON(data);

            	var kuler = json.kuler_themes;
            	buildColorArray(other_colors, kuler);

            	var cl = json.cl_themes;
            	buildColorArray(other_colors, cl);

            	// Load the returned palette into the local arrays
                for (var i = 0; i < json.info.colors.length; i++)
                {
                	var c = '#' + json.info.colors[i].toLowerCase();
                	suggestedPalette[i] = c;
                	yourPalette[i] = c;
                }

                // Slow it down for effect
                setTimeout(function()
                {
                	$('#hiddenYourPalette').val(yourPalette);
                    $('#hiddenSuggestedPalette').val(suggestedPalette);

            		handleFiles(files);
            		$('#showActualLogo').show();
            		$('#logoUploadedSuccessfully').show(350);
            	    setTimeout(function() {
            	    	//$('#uploadRequest').hide();
                		uploadStatus("Great!");
            	    }, 1500);
                }, 2500);

                // Meanwhile, let's populate the color detail pane
                populateColorDetailPane();
            },
            error: function (xhr, ajaxOptions, thrownError)
            {
            	alert('Call to color processor failed: ' + xhr.status);
            	alert(thrownError);
            }
    	});
    }

    $("#swatchPicker").kendoColorPicker({
        value: "#ffffff",
        buttons: false,
        select: swatchColorSelect
    });

    function swatchColorSelect(e)
	{
		// Only do something if one of the palette slots is selected
		if (currentYourPalette && currentYourPalette.length > 0)
		{
			if (currentAltPalette && currentAltPalette.length > 0)
			{
				setPaletteBlockSize(currentAltPalette, 26, 39, 'paletteCol');
			}
			currentAltPalette = null;

			var container = $('#' + currentYourPalette);
			container.css('background-color', e.value);
			var title = getTitle(e.value, true);
			container.attr('title', title);

			// Update the palette form for the post
			var idxStr = currentYourPalette.split("-");
			var idx = parseInt(idxStr[1]);
			yourPalette[idx] = e.value;
        	$('#hiddenYourPalette').val(yourPalette);
		}
		else
			alert('First select a palette spot, then choose the replacement color.');
    }

    function buildColorArray(other_colors, color_parent)
    {
    	for (var i = 0; i < color_parent.length; i++)
    	{
    		for (var j = 0; j < color_parent[i].colors.length; j++)
    		{
    			other_colors.push('#' + color_parent[i].colors[j].toLowerCase());
    		}
    	}
    }

    function populateColorDetailPane()
    {
    	var count = (suggestedPalette.length > 3 ? 4 : suggestedPalette.length);
    	
    	// Set colors from logo into chosen palette blocks
    	for (var idx = 0; idx < count; idx++)
    	{
    		$('#palette-' + idx).css('background-color', suggestedPalette[idx]);
			var title = getTitle(suggestedPalette[idx], true);
    		$('#palette-' + idx).attr('title', title);
    	}

    	var html = '';
    	// Create an array of 10 x 5 of colors
    	var count = 0;
    	for (var i = 0; i < 5; i++)
    	{
    		html += '<div class="paletteRow">';
    		for (var j = 0; j < 10; j++)
    		{
    			var title = getTitle(other_colors[count]);

        		// title="This is my tooltip"
    			var unitType = (j < 9 ? 'float:left;' : 'float:none;');
    			html += '<div id="paletteCol-' + count + 
    				'" class="paletteCol" style="background-color: ' + 
    				other_colors[count++] + ';' + unitType + '"' + title + '></div>';
    		}
    		html += '</div>';
    	}
    	$('#otherColorsArray').html(html);
        $('.paletteCol').on('click', function(event) 
		{
			if (currentAltPalette === event.target.id)
			{
				setPaletteBlockSize(currentAltPalette, 26, 39, 'paletteCol');
				currentAltPalette = null;
				return;
			}

			// Only do something if one of the palette slots is selected
			if (currentYourPalette && currentYourPalette.length > 0)
			{
				if (currentAltPalette && currentAltPalette.length > 0)
				{
					setPaletteBlockSize(currentAltPalette, 26, 39, 'paletteCol');
				}
				currentAltPalette = event.target.id;
				setPaletteBlockSize(currentAltPalette, 28, 42, 'paletteCol-selected');

				var bgcol = $('#' + currentAltPalette).css('background-color');
				var container = $('#' + currentYourPalette);
				container.css('background-color', bgcol);

				// bgcol will be rgb(r, g, b) format.  Convert it to #ffffff-ish
				var rgb = bgcol.replace(/[^\d,]/g, '').split(',');
				bgcol = decimalRgbToHex(rgb[0], rgb[1], rgb[2]);
				container.attr('title', getTitle(bgcol, true));

				// Update the palette form for the post
				var idxStr = currentYourPalette.split("-");
				var idx = parseInt(idxStr[1]);
				yourPalette[idx] = bgcol;
	        	$('#hiddenYourPalette').val(yourPalette);
			}
			else
			{
//        				alert ('Please select a palette slot above before choosing a replacement.');
				return;
			}	
		});

    }


	function getTitle(color, omitPrefix)
	{
		var rgb = hexToRgb(color);
		var title = ' ';
		if (rgb)
		{
			if (!omitPrefix)
			{
    			title = 'title="';
			}
			title += color + '  rgb(' + 
				rgb.r + ', ' + rgb.g + ', ' + rgb.b + ')';
			if (!omitPrefix)
			{
    			title += '"';
			}
		}
		return title;
	}

});  // document.ready(

function hexToRgb(hex) {
    var result = /^#?([a-f\d]{2})([a-f\d]{2})([a-f\d]{2})$/i.exec(hex);
    return result ? {
        r: parseInt(result[1], 16),
        g: parseInt(result[2], 16),
        b: parseInt(result[3], 16)
    } : null;
}

// We forward back to ourselves as easiest way to reinitialize the
//  kendo widgets.  The form data should be in the session.
// If toLogo is true, it keeps session and goes to logo.
function startOver(toLogo)
{
	$("body").css("cursor", "progress"); // hourglass cursor
    $.ajax({
        type: 'POST',
        url: 'RemoveLogoServlet',
        success: function(data)
        {
        	window.location = 'signup.jsp' +
        		(toLogo ? '?toLogo=1' : '');
        }
      });
}

function goHome()
{
	window.location = "home.jsp";
}

function skipPalette()
{
	document.getElementById('brndbotHeader').scrollIntoView();
	$('#paletteForkPane').hide();
	$('#posSelectPane').show();
	savePalette();
}

function viewPalette()
{
	document.getElementById('brndbotHeader').scrollIntoView();
	$('#paletteForkPane').hide();
	$('#palettePane').show();
}

function unforkToPos()
{
	document.getElementById('brndbotHeader').scrollIntoView();
	$('#palettePane').hide();
	$('#posSelectPane').show();
	savePalette();
}

function posSelected()
{
	document.getElementById('brndbotHeader').scrollIntoView();
	$('#posSelectPane').hide();
	$('#lookPane').show();
	$('#lookPane2').show();
}

function handleFiles(files) 
{
	var imageType = /image.*/;
	var file = files[0].rawFile;

	if (file.type.match(imageType)) 
	{
		var reader = new FileReader();
		reader.onload = function(event) 
		{
			var tag = "<img class=\"target-image\" src=\"" + event.target.result + "\" alt=\"\" width=\"220\"></img>";
			$('#actualLogo').prepend(tag);
			targetImage = $('.target-image');
		};
		reader.readAsDataURL(file);
	} 
	else 
	{
		alert('File must be a supported image type.');
	}
}

function onSelect(e)
{
	$('#imageTarget').hide();
	$('#filesHome').hide();
	$('uploadRequest').hide();
	$('#uploadStatus').show();
	uploadStatus("Uploading logo to server...");
}

function uploadStatus(stat)
{
	$('#uploadStatus').html(stat);
}

function showContinue()
{
	alert('now button');
}

// Convert RGB to hex color and add padding
function componentToHex(c) 
{
    var hex = c.toString(16);
    return hex.length == 1 ? "0" + hex : hex;
}

// decimal to hex string
function rgbToHex(r, g, b) 
{
	var ret = "#" + componentToHex(r) + componentToHex(g) + componentToHex(b);
    return ret.toLowerCase() ;
}

// String decimal to hex string
function decimalRgbToHex(r, g, b) 
{
    return rgbToHex(parseInt(r), parseInt(g), parseInt(b));
}

function escapeRegExp(string) 
{
    return string.replace(/([.*+?^=!:${}()|\[\]\/\\])/g, "\\$1");
}

/* probably unused
 
function replaceAll(find, rep, str) 
{
	  return str.replace(new RegExp(escapeRegExp(find), 'g'), rep);
}
*/

function onError(e) 
{
	alert('There was an unexpected error processing the logo.  The maximum file sie is ' + MAX_LOGO_SIZE + '.  Was your file too big?');
	window.location = 'signup.jsp?toLogo=1';
}

function onRemove(e) 
{
//gone	destroyColorPalette();
	alert('implement onRemove()');
}

/*function getFileInfo(e)
{
    return $.map(e.files, function(file) 
    {
        var info = file.name;

        // File size is not available in all browsers
        if (file.size > 0) {
            info  += " (" + Math.ceil(file.size / 1024) + " KB)";
        }
        return info;
    }).join(", ");
}
*/

function onLogoUploadSuccess(e) 
{
    // Array with information about the uploaded files
    var files = e.logoFileInput;

    if (e.operation == "upload") 
    {
        alert("Successfully uploaded " + files.length + " files.");
    }
}

function createMyAccount(e)
{
	document.getElementById('brndbotHeader').scrollIntoView();

	var scrollTo = 'signUpDiv';
	// Validate email and password
	if (!validateEntryMade('#userEmail', 'Email Address') ||
		!validateEntryMade('#userPassword', 'Password') ||
		!validateEntryMade('#userConfirmPassword', 'Confirmation Password'))
	{
		dataEntryError('You must enter all fields to continue with your account creation.', scrollTo);
		return;
	}

	if (!validateEmail($('#userEmail').val()))
	{
		dataEntryError('Email address is not defined correctly, please try again.', scrollTo);
		return;
	}

	if ($('#userPassword').val() != $('#userConfirmPassword').val())
	{
		dataEntryError('Confirmation password does not match the password, please try again.', scrollTo);
		$('#userPassword').val('');
		$('#userConfirmPassword').val('');
		$('#userPassword').focus();
		return;
	}

	// Check to see if the email address exists
	$('#hiddenEmail').val($('#userEmail').val());
    $.ajax({
        type: 'POST',
        url: 'EmailExistServlet',
        data: $('#userDataForm').serialize(), // serializes the form's elements.
        success: function(data)
        {
        	$('#registerNewUser').hide();
        	$('#tellUsAboutYourself').show();
        	$('#errorMsg').html('');
        	$('#errorMsg').hide();
        },
        error: function (xhr, ajaxOptions, thrownError)
        {
    		dataEntryError('This email address already exists, please enter a different address.');
        }
    });
}

// Test the structure of the email address
function validateEmail(email) 
{
	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(email))  
	{  
		return true;
	}
	return false;
}

function validateEntryMade(id, prompt)
{
	if ($(id).val().length == 0)
	{
		dataEntryError('You must enter a value in the ' + prompt + ' to continue with the sign up.');
		return false;
	}
	return true;
}

function dataEntryError(errorMsg, scrollTo)
{
	$('#errorMsg').html(errorMsg);
	$('#errorMsg').show();
	if (scrollTo)
	{
		document.getElementById(scrollTo).scrollIntoView();
	}
}

function updateAccount(e)
{
	document.getElementById('brndbotHeader').scrollIntoView();

	$('#registerNewUser').hide();
	$('#tellUsAboutYourself').hide();
	$('#talkColor').hide();
	$('#areYouASocialite').show();
}

function updateSocials(e)
{
	document.getElementById('brndbotHeader').scrollIntoView();

	// order of these 2 calls and error handling not very good
	displayForLogo(true);
	doSubmitForUser();
}

function displayForLogo(forLogo)
{
	if (forLogo)
	{
		$('#talkColor').show();
		$('#registerNewUser').hide();
	}
	else
	{
		$('#talkColor').hide();
		$('#registerNewUser').show();
	}
	$('#tellUsAboutYourself').hide();
	$('#areYouASocialite').hide();
}

function updateLogo(e)
{
	document.getElementById('brndbotHeader').scrollIntoView();

	$('#talkColor').hide();
	$('#paletteForkPane').show();
}

function savePalette()
{
    $.ajax({
        type: 'POST',
        url: 'SavePaletteServlet',
        data: $('#paletteForm').serialize(), // serializes the form's elements.
        success: function(data)
        {
        },
        error: function (xhr, ajaxOptions, thrownError)
        {
        	alert('Temp debug output: ' + xhr.status);
//        	alert(thrownError);
        }
	});
}

function doSubmitForUser()
{
	// Load data into the hidden form
	$('#hiddenEmail').val($('#userEmail').val());
	$('#hiddenPassword').val($('#userPassword').val());
	$('#hiddenConfirmPassword').val($('#userConfirmPassword').val());
	$('#hiddenCompany').val($('#companyName').val());
	$('#hiddenUrl').val($('#companyUrl').val());
	$('#hiddenAddress').val($('#companyAddress').val());
	$('#hiddenFacebookUrl').val($('#facebookUrl').val());
	$('#hiddenTwitterHandle').val($('#twitterHandle').val());
	$('#hiddenLinkedIn').val($('#linkedIn').val());
	$('#hiddenYouTube').val($('#youtubeUrl').val());
	$('#hiddenInstagram').val($('#instagramUrl').val());

    $.ajax({
        type: 'POST',
        url: 'SaveUserServlet',
        data: $('#userDataForm').serialize(), // serializes the form's elements.
        success: function(data)
        {
        	brndBotUserID = data[signUpUserKey];
        	if (brndBotUserID == 0) alert(brndBotUserID);
//        	alert('successfully saved.');
        },
        error: function (xhr, ajaxOptions, thrownError)
        {
//        	alert('Call to Brndbot server failed: ' + xhr.status);
        	alert(thrownError);
        }
    });
}

/*
function getFacilities()
{
	var multiselect = $("#facilities").data("kendoMultiSelect");

	// get data items for the selected options.
	var dataItems = multiselect.dataItems();
	var str = '';
	for (var i=0; i < dataItems.length; i++)
	{
        var dataItem = dataItems[i];
        if (str.length > 0)
        {
        	str += ',';
        }
        str += dataItem.FacilityID;
	}
	return str;
}
*/
