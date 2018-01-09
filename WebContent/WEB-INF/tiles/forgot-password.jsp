<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<h3>Forgot Password? Enter your email address and we will send you a link to reset your password</h3>
<c:if test="${param.success != null }">
  <div class="alert alert-success" role="alert" id="success_message">
			You've successfully requested a new password reset!
   </div>

</c:if>
<div class="container">
<sf:form method="post" class="well form-horizontal"
	action="${pageContext.request.contextPath}/forgot-password"
	commandName="forgotPasswordForm">
	<fieldset>
		
	<div class="form-group">
			<label class="col-md-4 control-label">Email</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-envelope"></i></span>
					<input type='text' name="email" class="form-control" value="">
					<div class="error">
						<sf:errors path="email"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-4">
				<br>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<button type="submit" class="btn btn-lg btn-primary btn-block">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSend Password Reset Email<span
						class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				</button>
			</div>
		</div>
		</fieldset>
		


</sf:form>

</div>



