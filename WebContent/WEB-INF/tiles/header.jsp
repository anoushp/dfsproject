<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<img id="img_logo" src="<c:url value='/static/images/dfsproject.png' />"/>
<a class="title" href="<c:url value='/'/>">DfS-CMI Web Tool</a>

<sec:authorize access="!isAuthenticated()">
<a class="login" href="<c:url value='/login'/>">Log In</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
<span class="login">Welcome ${userDetails}</span>
<c:url var="logoutUrl" value="/logout"/><form action="${logoutUrl}" id="logout" method="post"> <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/></form><a class="login" href="#" onclick="document.getElementById('logout').submit();">Logout</a>
</sec:authorize>