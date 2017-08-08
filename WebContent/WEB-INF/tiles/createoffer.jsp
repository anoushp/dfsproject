<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<script type="text/javascript">
	function onDeleteClick(event) {

		var doDelete = confirm("Are you sure you want to delete this offer?");
		if (doDelete == false) {
			event.preventDefault();
		}
	}
	function onReady() {

		$("#delete").click(onDeleteClick)
	}
	$(document).ready(onReady);
</script>

<sf:form method="post"
	action="${pageContext.request.contextPath}/docreate"
	commandName="offer">
	<sf:input type="hidden" name="id" path="id" />
	<table>
		<tr>
			<td>Text :</td>
			<td><sf:textarea path="text" name="text" type="text" /><br />
			<sf:errors path="text"></sf:errors></td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Save Advert" type="submit"></td>
		</tr>

		<c:if test="${offer.id != 0}">

			<tr>
				<td></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td></td>
				<td><input class="delete control" name="delete" id="delete"
					value="Delete Advert" type="submit"></td>
			</tr>
		</c:if>
	</table>
</sf:form>
