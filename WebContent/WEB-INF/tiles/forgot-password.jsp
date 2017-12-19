<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<h3>Forgot Password?</h3>
<c:if test="${param.success != null }">
<p>You've successfully requested a new password reset!</p>
</c:if>
<sf:form method="post"
	action="${pageContext.request.contextPath}/forgot-password"
	commandName="forgotPasswordForm">
<table class="formtable">
	<tr><td>User Email:</td><td><input type='text' name="email" value="">
	<div class="error"><sf:errors path="email"></sf:errors></div></td></tr>
	
	<tr><td colspan='2'><input name="submit" type="submit" value="Reset Password" class="submitButton"/></td></tr>
</table>

</sf:form>





