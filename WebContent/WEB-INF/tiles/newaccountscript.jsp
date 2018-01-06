<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/fmt" prefix = "fmt" %>

<script type="text/javascript">
function onLoad(){
	$("#password").keyup(checkPasswordsMatch);
	$("#confirmpass").keyup(checkPasswordsMatch);
	
	$("#reg_details").submit(canSubmit);

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