<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<sf:form class="well form-horizontal" method="post"
	action="${pageContext.request.contextPath}/doupdateuser"
	commandName="user">
	<sf:input type="hidden" name="username" path="username" />
	<sf:input type="hidden" name="password" path="password" />
	<sf:input type="hidden" name="enabled" path="enabled" />
	<sf:input type="hidden" name="authority" path="authority" />
	<fieldset>

		<!-- Form Name -->
		<legend>
			<center>
				<h2>
					<b>Registration Form</b>
				</h2>
			</center>
		</legend>
		<br>

		<!-- Text input-->

		<div class="form-group">
			<label class="col-md-4 control-label">First Name</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span>
					<sf:input path="fname" name="fname" placeholder="First Name"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="fname"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Last Name</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-user"></i></span>
					<sf:input path="lname" name="lname" placeholder="Last Name"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="lname"></sf:errors>
					</div>

				</div>
			</div>
		</div>


		<div class="form-group">
			<label class="col-md-4 control-label">Email Address</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-envelope"></i></span>
					<sf:input path="email" name="email" placeholder="Email Address"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="email"></sf:errors>
					</div>

				</div>
			</div>
		</div>

		<div class="form-group">
			<label class="col-md-4 control-label">Country</label>
			<div class="col-md-4 selectContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-list"></i></span>
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
			<label class="col-md-4 control-label">Job Role</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="job_role" name="job_role" placeholder="Job Role"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="job_role"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Years of Experience in
				Role</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="years_role" name="years_role"
						placeholder="Years of Experience in Role" class="form-control"
						type="text" />
					<div class="error">
						<sf:errors path="years_role"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Years of Experience in
				Construction</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="years_construction" name="years_construction"
						placeholder="Years of Experience in Construction"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="years_construction"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Professional
				Qualifications</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="prof_qualification" name="prof_qualification"
						placeholder="Professional Qualifications" class="form-control"
						type="text" />
					<div class="error">
						<sf:errors path="prof_qualification"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<div class="form-group">
			<label class="col-md-4 control-label">Sector of your
				operation/work in construction</label>
			<div class="col-md-4 inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="work_sector" name="work_sector"
						placeholder="Sector of your operation/work in construction"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="work_sector"></sf:errors>
					</div>

				</div>
			</div>
		</div>
		<!-- Button -->
		<div class="form-group">
			<label class="col-md-4 control-label"></label>
			<div class="col-md-4">
				<br>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<button type="submit" class="btn btn-lg btn-primary btn-block">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspUpdate Account <span
						class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				</button>
			</div>
		</div>

	</fieldset>
	<%-- <table>
		<tr>
			<td>FirstName :</td>
			<td><sf:input path="fname" name="fname" type="text" /><br />
			<sf:errors path="fname"></sf:errors></td>
		</tr>
		<tr>
			<td>Last Name:</td>
			<td><sf:input path="lname" name="lname" type="text" />
				<div class="error">
					<sf:errors path="lname"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Email :</td>
			<td><sf:input path="email" name="email" type="text" /><br />
			<sf:errors path="email"></sf:errors></td>
		</tr>
		<tr>
			<td>Country:</td>
			<td><sf:select path="country" name="country"
					items="${countries}" multiple="false" /></td>
		</tr>
		<tr>
			<td>Job Role:</td>
			<td><sf:input path="job_role" name="job_role" type="text" />
				<div class="error">
					<sf:errors path="job_role"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Years of Experience in Role:</td>
			<td><sf:input path="years_role" name="years_role" type="text" />
				<div class="error">
					<sf:errors path="years_role"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Years of Experience in Construction:</td>
			<td><sf:input path="years_construction"
					name="years_construction" type="text" />
				<div class="error">
					<sf:errors path="years_construction"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Professional Qualification:</td>
			<td><sf:input path="prof_qualification"
					name="prof_qualification" type="text" />
				<div class="error">
					<sf:errors path="prof_qualification"></sf:errors>
				</div></td>
		</tr>
		<tr>
			<td>Sector of your operation/work in construction:</td>
			<td><sf:input path="work_sector" name="work_sector" type="text" />
				<div class="error">
					<sf:errors path="work_sector"></sf:errors>
				</div></td>
		</tr>
		
		<tr>
			<td></td>
			<td><input value="Save Details" type="submit" class="submitButton"></td>
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
	</table> --%>
</sf:form>