<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link href="${pageContext.request.contextPath}/static/css/main.css"
	rel="stylesheet" type="text/css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="${pageContext.request.contextPath}/static/script/jquery.js"></script>
<script type="text/javascript">
function onLoad(){
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	
	$("#details").submit(canSubmit);

}
function canSubmit(){
	var password=$("#password").val();
	var confirmpass=$("#confirmpass").val();
	
	if (password!=confirmpass) {return false;
	alert("<fmt:message key='UnmatchedPasswords.user.password'/>")
	}
	return true;
}
function checkPasswordsMatch() {
	var password=$("#password").val();
	var confirmpass=$("#confirmpass").val();
	if (password.length >3 || confirmpass.length>3) {
	
	if (password==confirmpass){
		$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password'/>")
		
	} else {
		$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password'/>")
		
	}
	}
	
}
$(document).ready(onLoad);
</script>

<title>Insert title here</title>
</head>
<body>
<h2>Create New Account</h2>
<sf:form id="details" method="post" action="${pageContext.request.contextPath}/createaccount" commandName="user">
<table class="formtable">
<tr><td>User Name: </td> <td><sf:input path="username" name="username" type="text"/><div class="error"><sf:errors path="username"></sf:errors></div></td></tr>
<tr><td>Email: </td> <td><sf:input path="email" name="email" type="text"/><div class="error"><sf:errors path="email"></sf:errors></div></td></tr>
<tr><td>Password: </td> <td><sf:input id="password" path="password" name="password" type="text"/><div class="error"><sf:errors path="password"></sf:errors></div></td></tr>
<tr><td>Confirm Password: </td> <td><input id="confirmpass" name="confirmpass" type="text"/><div id="matchpass"></div></td></tr>
<tr><td></td><td><input value="create Account" type="submit"></td></tr>
</table>
</sf:form>
</body>
</html>