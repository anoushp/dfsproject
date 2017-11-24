<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
<b> Attribute : ${attribute.name}</b>
<sf:form method="post"
	action="${pageContext.request.contextPath}/attributes/${attribute.id}/docreateindicator"
	commandName="indicatorForm">
	
	
	<table class="formtable">
	<c:forEach items="${indicatorForm.indicators}" var="indicator" varStatus="status">
	
	<sf:input type="hidden" name="indicators[${status.index}].id" path="indicators[${status.index}].id" />
	<sf:input type="hidden" name="indicators[${status.index}].attribute" path="indicators[${status.index}].attribute.id" value="${model.attribute.id}"/>
		<tr>
			<td>Maturity level :</td>
			<td><sf:input path="indicators[${status.index}].matlevel" name="indicators[${status.index}].matlevel" value="${status.index+1}" type="text" /><br />
				<sf:errors path="indicators[${status.index}].matlevel"></sf:errors></td>
		</tr>
		<tr>
			<td>Text :</td>
			<td><sf:textarea path="indicators[${status.index}].text" name="indicators[${status.index}].text" type="text" /><br />
				<sf:errors path="indicators[${status.index}].text"></sf:errors></td>
		</tr>
		</c:forEach>
		<tr>
			<td></td>
			<td><input value="Save Indicator" type="submit" class="submitButton"></td>
		</tr>

		<c:if test="${indicator.id != 0}">

			<tr>
				<td></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td><input class="delete control" name="delete" id="delete"
					value="Delete Indicator" type="submit" class="submitButton"></td>
			</tr>
		</c:if> 
	</table>
</sf:form>
