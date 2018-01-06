<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
<script type="text/javascript">
	function onDeleteClick(event) {

		var doDelete = confirm("Are you sure you want to delete this indicator?");
		if (doDelete == false) {
			event.preventDefault();
		}
	}
	function onReady() {

		$("#delete").click(onDeleteClick)
	}
	$(document).ready(onReady);
</script>
<h2> Indicators for Attribute : ${attribute.name}</h2>
<div class="container">
<sf:form method="post"
	action="${pageContext.request.contextPath}/attributes/${attribute.id}/docreateindicator"
	commandName="indicatorForm" class="well form-horizontal">
	<fieldset>
	<c:forEach items="${indicatorForm.indicators}" var="indicator" varStatus="status">
	
	<sf:input type="hidden" name="indicators[${status.index}].id" path="indicators[${status.index}].id" />
	<sf:input type="hidden" name="indicators[${status.index}].attribute" path="indicators[${status.index}].attribute.id" value="${model.attribute.id}"/>
	<div class="form-group">
			<label class="control-label">Maturity Level</label>
			<div class="inputGroupContainer">
				<div class="input-group">
					<sf:input path="indicators[${status.index}].matlevel" name="indicators[${status.index}].matlevel" value="${status.index+1}" 
						class="form-control" type="text" readonly="true"/>
					<div class="error">
						<sf:errors path="indicators[${status.index}].matlevel"></sf:errors>
					</div>

				</div>
			</div>
		</div>
			<div class="form-group">
			<label class="control-label">Text</label>
			<div class="inputGroupContainer">
				<div class="input-group">
					<sf:textarea path="indicators[${status.index}].text" name="indicators[${status.index}].text" 
						class="form-control" type="text" />
					<div class="error">
						<sf:errors path="indicators[${status.index}].text"></sf:errors>
					</div>

				</div>
			</div>
		</div>

		</c:forEach>
				<div class="form-group">
			<label class="control-label"></label>
			<div class="col-md-4">
				<br>
				&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				<button type="submit" class="btn btn-lg btn-primary btn-block">
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspSave Indicators
				</button>
			</div>
		</div>

</fieldset>

</sf:form>
</div>
