<c:choose>
	<c:when test="${templateEnum == 1}">
	<%
		Client client = (com.brndbot.promo.Client) session.getAttribute("brndbotClient");
		if (client == null) {
			client = Client.getClient (session);
			SessionUtils.saveSessionData (request, SessionUtils.CLIENT, client);
		}
	%>

	<%= 	benchHelper.insertPromotion (client) %>
	</c:when>
</c:choose>