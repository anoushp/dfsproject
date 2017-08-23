<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<sf:form method="post"
	action="${pageContext.request.contextPath}/doupdateuser"
	commandName="user">
	<sf:input type="hidden" name="username" path="username" />
	<sf:input type="hidden" name="password" path="password" />
	<sf:input type="hidden" name="enabled" path="enabled" />
	<sf:input type="hidden" name="authority" path="authority" />
	
	<table>
		<tr>
			<td>Name :</td>
			<td><sf:input path="name" name="name" type="text" /><br />
			<sf:errors path="name"></sf:errors></td>
		</tr>
		<tr>
			<td>Email :</td>
			<td><sf:input path="email" name="email" type="text" /><br />
			<sf:errors path="email"></sf:errors></td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Save Details" type="submit"></td>
		</tr>
		

		<c:if test="${user.username!= ''}">

			<tr>
				<td></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				
			</tr>
		</c:if>
	</table>
</sf:form>