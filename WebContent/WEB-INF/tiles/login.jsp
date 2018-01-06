<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<script type="text/javascript" src="webjars/jquery/2.1.1/jquery.min.js"></script>


	<script type="text/javascript">$(document).ready(function(){
		document.f.username.focus();
	})
	</script>
	
<c:if test="${param.resetSuccess != null }">
<p>You have successfully updated your password</p>
</c:if>
<h3>Login with Username and Password</h3>
<c:if test="${param.error != null }">
<p>Login failed check your username and password</p>
</c:if>
<form name='f' action='${pageContext.request.contextPath}/login' method='POST' class="form-signin">
      
        <h2 class="form-signin-heading">Please login</h2>
      <input type="text" class="form-control" name="username" placeholder="Username" autofocus="" />
      <input type="password" class="form-control" name="password" placeholder="Password" />      
      <label class="checkbox">
        <input type="checkbox" name='remember-me' checked="checked"> Remember me
      </label>
      <button class="btn btn-lg btn-primary btn-block" type="submit">Login</button>   
   

<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
</form>
<p><a href="<c:url value="/newaccount"/>">Create new account</a></p>
<p><a href="<c:url value="/forgot-password"/>">Forgotten Password?</a></p>

