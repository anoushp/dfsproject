<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<h3>Reset Password</h3>
<sf:form method="post"
	action="${pageContext.request.contextPath}/reset-password"
	commandName="passwordResetForm">
<table class="formtable">
	<tr><td>Password:</td><td><input type='text' name="password" value="">
	<div class="error"><sf:errors path="password"></sf:errors></div></td></tr>
	<tr><td>Confirm Password:</td><td><input type='text' name="confirmPassword" value="">
	<tr><td colspan='2'><input name="submit" type="submit" value="Reset Password" class="submitButton"/></td></tr>
</table>
<input type="hidden" name="token" value="${token}"/>
</sf:form>
