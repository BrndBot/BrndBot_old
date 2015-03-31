/* homecontent.js, formerly content.js
 * 
 * 
 * OBSOLETE -- I think
 * 
 * In spite of its name, this is for home.jsp, not for the editor.
 * It has no connection to content.jsp. */

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