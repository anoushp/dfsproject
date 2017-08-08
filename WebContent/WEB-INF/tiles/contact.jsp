<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />

<h2>Send Message</h2>
<sf:form method="post" commandName="message">
	<input type="hidden" name="_flowExecutionKey"
		value="${flowExecutionKey}" />
	<input type="hidden" name="_eventId" value="send" />
	<table class="formtable">
		<tr>
			<td>Your Name</td>
			<td><sf:input class="control" path="name" type="text" value="${fromName}"/><br/>
				<div class="error">
					<sf:errors path="name"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Your Email</td>
			<td><sf:input path="email" type="text" value="${fromEmail}" /><br/>
				<div class="error">
					<sf:errors path="email"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Subject</td>
			<td><sf:input class="control" path="subject" type="text" /><br/>
				<div class="error">
					<sf:errors path="subject"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Your Message</td>
			<td><sf:textarea class="control" path="content" type="text" /><br/>
				<div class="error">
					<sf:errors path="content"></sf:errors>
				</div></td>
		</tr>


		<tr>
			<td></td>
			<td><input value="Send Message" type="submit"></td>
		</tr>
	</table>
</sf:form>