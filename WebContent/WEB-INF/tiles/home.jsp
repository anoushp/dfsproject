<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<table class="offers">
<tr><td>Name</td><td>Email</td><td>Offer</td></tr>

<c:forEach var="offer" items="${offers}">
<tr>

<td><c:out value="${offer.user.name}"></c:out></td>

<td><a href="<c:url value='/message?uid=${offer.username}'></c:url>">contact</a></td>

<td><c:out value="${offer.text}"></c:out></td>
   
</tr>
</c:forEach>
</table>
<p/>
<c:choose>
<c:when test="${hasOffer}">
<p><a href="${pageContext.request.contextPath}/createoffer">Edit or remove your current offer</a></p>
</c:when>
<c:otherwise><p><a href="${pageContext.request.contextPath}/createoffer">Add new offer</a></p></c:otherwise>
</c:choose>
<p><a href="${pageContext.request.contextPath}/offers">Show cuurent offers</a></p>


<%-- <sec:authorize access="!isAuthenticated()">
<p><a href="<c:url value='/login'/>">Log In</a></p>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<p><c:url var="logoutUrl" value="/logout"/><form action="${logoutUrl}" id="logout" method="post"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></form><a href="#" onclick="document.getElementById('logout').submit();">Logout</a></p>
</sec:authorize> --%>
<sec:authorize access="hasRole('ROLE_ADMIN')">
<p><a href="<c:url value='/admin'/>">Admin</a></p>
</sec:authorize>
