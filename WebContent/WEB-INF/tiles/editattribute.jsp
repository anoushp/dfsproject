<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
<sf:form method="post"
	action="${pageContext.request.contextPath}/attributes/editattribute"
	commandName="attribute" class="well form-horizontal" >
	<sf:input type="hidden" name="id" path="id" />
	<sf:input type="hidden" name="weight" path="weight" value="0.1" />
	<fieldset>

		<!-- Form Name -->
		<legend>
			
				<h2>
					<b>Amend Attribute</b>
				</h2>
			
		</legend>
		<br>
		<div class="form-group">
			<label class="control-label">Description</label>
			<div class="inputGroupContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-pencil"></i></span>
					<sf:input path="name" name="name" placeholder="Attribute Name"
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="name"></sf:errors>
					</div>

				</div>
			</div>
		</div>
	    
		<div class="form-group">
			<label class="control-label">Category</label>
			<div class="selectContainer">
				<div class="input-group">
					<span class="input-group-addon"><i
						class="glyphicon glyphicon-list"></i></span>
					<sf:select path="kpaCategories" name="kpaCategories" items="${cat_list}" multiple="false" itemValue="id" itemLabel="category" class="form-control selectpicker"/>
						
				

				</div>
			</div>
		</div>
				<div class="form-group">
			<label class="control-label"></label>
			<div class="col-md-4">
				<br>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<button type="submit" class="btn btn-lg btn-primary btn-block">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSave <span
						class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				</button>
			</div>
		</div>

	</fieldset>
	
</sf:form>
</div>