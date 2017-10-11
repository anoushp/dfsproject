<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createoffer">Edit or
			remove your current offer</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath}/createoffer">Add new
			offer</a>
	</c:otherwise>
</c:choose>
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/attributes">Create new Attribute</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/updateuser">Update your account details</a>
</sec:authorize>

<%-- <sec:authorize access="!isAuthenticated()">
<p><a href="<c:url value='/login'/>">Log In</a></p>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<p><c:url var="logoutUrl" value="/logout"/><form action="${logoutUrl}" id="logout" method="post"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></form><a href="#" onclick="document.getElementById('logout').submit();">Logout</a></p>
</sec:authorize> --%>
&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin</a>
</sec:authorize>
&nbsp;

<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/messages'/>">Messages(<span
		id=numberMessages>0</span>)
	</a>
</sec:authorize>