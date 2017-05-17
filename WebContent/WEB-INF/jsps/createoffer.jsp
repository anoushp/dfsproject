<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<sf:form method="post" action="${pageContext.request.contextPath}/docreate" commandName="offer">
<table>
<tr><td>Name: </td> <td><sf:input path="name" name="name" type="text"/><br/><sf:errors path="name"></sf:errors></td></tr>
<tr><td>Email: </td> <td><sf:input path="email" name="email" type="text"/><br/><sf:errors path="email"></sf:errors></td></tr>
<tr><td>Text : </td> <td><sf:textarea path="text" name="text" type="text"/><br/><sf:errors path="text"></sf:errors></td></tr>
<tr><td></td><td><input value="create Advert" type="submit"></td></tr>
</table>
</sf:form>
</body>
</html>