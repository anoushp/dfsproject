<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<p />
<img src="<c:url value='/static/images/dfsproject.png' />"/>
<span class="message">Welcome ${userDetails}</span>
<table class="offers">
	<tr>
		<td>Name</td>
		<td>Email</td>
		<td>Offer</td>
	</tr>

	<c:forEach var="offer" items="${offers}">
		<tr>

			<td><c:out value="${offer.user.name}"></c:out></td>

			<td><a
				href="<c:url value='/message?uid=${offer.username}'></c:url>">contact</a></td>

			<td><c:out value="${offer.text}"></c:out></td>

		</tr>
	</c:forEach>
</table>



<script type="text/javascript">
	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);
		
	}
	function updatePage(){
		$.getJSON("<c:url value="/getmessages" />", updateMessageLink)
	}
	function onReady() {
		updatePage();
		window.setInterval(updatePage,5000);
	}
	$(document).ready(onReady);
</script>
