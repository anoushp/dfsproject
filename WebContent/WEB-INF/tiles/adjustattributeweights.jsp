<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<c:if test="${adjusted != null}"><b>Attribute weights were successfully adjusted</b><br>
  </c:if>
<sf:form method="post"
	action="${pageContext.request.contextPath}/doadjustattributesweights"
	commandName="attributeForm">
	
	<div class="ss-form-entry">
	<table>
	<tr><td><b>Attribute Name</b></td><td><b>Attribute Categories</b></td><td><b>Attribute weight by category</b></td><td><b>Attribute weight</b></td></tr>
	<c:forEach items="${attributeForm.attributes}" var="attribute" varStatus="status">
	
	<sf:input type="hidden" name="attributes[${status.index}].id" path="attributes[${status.index}].id" />
	<sf:input type="hidden" name="attributes[${status.index}].name" path="attributes[${status.index}].name" value="${attribute.name}" />
	<tr>
	<td><c:out value="${attribute.name}"></c:out></td>
	<td>
	<c:forEach items="${ attribute.kpaCategories}" var="kpa" >
	<c:out value="${kpa.category}"></c:out>
	</c:forEach>
	</td>
	<td><sf:input path="attributes[${status.index}].sector_weight" name="attributes[${status.index}].sector_weight" type="text" style="width:30px;" /><br />
	<div class="error"><sf:errors path="attributes[${status.index}].sector_weight"></sf:errors></div></td>
	<td><sf:input path="attributes[${status.index}].weight" name="attributes[${status.index}].weight" type="text" style="width:30px;" /><br />
	<div class="error"><sf:errors path="attributes[${status.index}].weight"></sf:errors></div></td>
	
	<sf:select path="attributes[${status.index}].kpaCategories" name="attributes[${status.index}].kpaCategories" items="${cat_list}" multiple="true" itemValue="id" itemLabel="category" style="display: none"/>
	</c:forEach>
	
		<tr>
		
			<td><input value="Adjust Weights" type="submit" class="submitButton"></td>
		</tr>

		
	</table>
	</div>
</sf:form>