<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<p />
<img src="<c:url value='/static/images/dfsproject.png' />" />
<span class="message">Welcome ${userDetails}</span>
<%-- <table class="offers">
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
 --%>
<h3>Background and Purpose</h3>
Background and purpose of research It is well established that design is
a significant contributor to occupational injuries and illnesses in
construction. Consequently, design for safety (DfS), also called
“prevention through design” or “design risk management” is increasingly
becoming prominent in construction worldwide. DfS requires that
designers (e.g. architects and engineers) give careful consideration to
how their design decisions would affect the health and safety (H&S) of
builders, maintenance workers, and users of built assets. In the UK, DfS
is buttressed by the Construction (Design and Management) (CDM)
Regulations 2015 which seek to integrate H&S into the design and
management of construction works from the inception stage. CDM 2015
stipulates that designers (organisations/individuals), when preparing or
modifying designs, should eliminate, reduce or control foreseeable risks
that may arise during the construction, maintenance and use of built
assets. Consequently and understandably, CDM 2015 also requires that the
appointment of organisations with design responsibilities should be
based on their capability. This brings to the fore the important issue
of design firms having adequate maturity in terms of DfS capability.
Whilst some design firms may have attained some appreciable maturity in
terms of DfS capability, others would also be deficient. However, there
is lacking a robust systematic mechanism for profiling the DfS
capability maturity of construction organisations with design
responsibilities in order to pave way for improvements in DfS
capability. 



<script type="text/javascript">
	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);

	}
	function updatePage() {
		$.getJSON("<c:url value="/getmessages" />", updateMessageLink)
	}
	function onReady() {
		updatePage();
		window.setInterval(updatePage, 5000);
	}
	$(document).ready(onReady);
</script>
