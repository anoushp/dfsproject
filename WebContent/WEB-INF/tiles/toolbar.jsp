<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<!-- Latest compiled and minified CSS -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-full" style='background-color: #428bca'>
    <!--   <a class="navbar-brand" href="#">Navbar</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>-->

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item active">
            <a class="nav-link" href="${pageContext.request.contextPath}/">Home <span class="sr-only">(current)</span></a>
          </li>
        
           <sec:authorize access="isAuthenticated()">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Your Account</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/updateuser">Update your account details</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/dfsassessments">Manage Your DfS Assessments</a>
              <a class="dropdown-item" href="${pageContext.request.contextPath}/adjustattributeweights">Adjust Attribute Weights</a>
             
            </div>
          </li>
          </sec:authorize>
          <sec:authorize access="hasRole('ROLE_ADMIN')">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="${pageContext.request.contextPath}/" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Admin area</a>
            <div class="dropdown-menu" aria-labelledby="dropdown01">
              <a class="dropdown-item" href="${pageContext.request.contextPath}/attributes">Manage Attributes</a>
              <a class="dropdown-item" href="<c:url value='/admin'/>">Manage Users</a>
             
            </div>
          </li>
          </sec:authorize>
        </ul>
        
      </div>
    </nav>

<!--<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="${pageContext.request.contextPath}/attributes">View Attributes List</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/adjustattributeweights">Adjust attribute weights</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/updateuser">Update your account details</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/dfstest">Start Dfs Test</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	<a href="${pageContext.request.contextPath}/dfsassessments">Assessments</a>
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
</sec:authorize>-->
&nbsp;

<%-- <sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/messages'/>">Messages(<span
		id=numberMessages>0</span>)
	</a>
</sec:authorize> --%>