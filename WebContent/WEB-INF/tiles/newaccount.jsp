<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />

<h2>Create New Account</h2>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<table class="formtable">
<tr><td>User Name: </td> <td><sf:input path="username" name="username" type="text"/><div class="error"><sf:errors path="username"></sf:errors></div></td></tr>
<tr><td>Name: </td> <td><sf:input path="name" name="name" type="text"/><div class="error"><sf:errors path="name"></sf:errors></div></td></tr>
<tr><td>Email: </td> <td><sf:input path="email" name="email" type="text"/><div class="error"><sf:errors path="email"></sf:errors></div></td></tr>
<tr><td>Password: </td> <td><sf:input id="password" path="password" name="password" type="text"/><div class="error"><sf:errors path="password"></sf:errors></div></td></tr>
<tr><td>Confirm Password: </td> <td><input id="confirmpass" name="confirmpass" type="text"/><div id="matchpass"></div></td></tr>
<tr><td></td><td><input value="Create Account" type="submit" class="submitButton"></td></tr>
</table>
</sf:form>
