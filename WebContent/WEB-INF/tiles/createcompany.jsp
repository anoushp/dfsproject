<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>

<div class="container">
<sf:form method="post"
	action="${pageContext.request.contextPath}/docreatecompany"
	commandName="company" class="well form-horizontal" >
	<fieldset>

		<!-- Form Name -->
		<legend>
			
				<h2>
					<b>New Company</b>
				</h2>
			
		</legend>
		<br>
	<sf:input type="hidden" name="id" path="id" />
	
	<div class="form-group">
			<label class="control-label">Company/Design Office Name</label>
			<div class="inputGroupContainer">
				<div class="input-group">
					
					<sf:input path="companyname" name="name" placeholder="Company/Design Office Name"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="companyname"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		
			<div class="form-group">
			<label class="control-label">Company/Design Office Name</label>
			<div class="inputGroupContainer">
				<div class="input-group">
					
					<sf:input path="size" name="size" placeholder="Company/Design Office Size"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="size"></sf:errors>
					</div>

				</div>
			</div>
		</div>
			<div class="form-group">
			<label class="control-label">Country</label>
			<div class="selectContainer">
				<div class="input-group">
					
					<sf:select path="country" name="country" multiple="false"
						class="form-control selectpicker">
						<sf:option value="" />
						<sf:options items="${countries}" />
						<sf:errors path="country" class="error" />
					</sf:select>

				</div>
			</div>
		</div>
			<div class="form-group">
			<label class="control-label">Company/Design Office Name</label>
			<div class="inputGroupContainer">
				<div class="input-group">
					
					<sf:input path="city" name="city" placeholder="Company/Design Office City"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="city"></sf:errors>
					</div>

				</div>
			</div>
		</div>
	    
		<div class="form-group">
			<label class="control-label">Operation Sector</label>
			<div class="selectContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-list"></i></span>
					<sf:select path="opSectors" name="opSectors" items="${os_list}" multiple="true" itemValue="id" itemLabel="sector" class="form-control selectpicker"/>
						
				

				</div>
			</div>
		</div>
				<div class="form-group">
			<label class="control-label"></label>
			<div class="col-md-4">
				<br>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<button type="submit" class="btn btn-lg btn-primary btn-block">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAdd Company <span
						class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				</button>
			</div>
		</div>

	</fieldset>
	</sf:form>
</div>