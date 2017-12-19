<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript">
   
    $(document).ready(function() {
    	
		var deleteLink = $("a:contains('Delete')");
      
		$(deleteLink).click(function(event) {
    	  alert("TEST"+$(event.target).attr("href"));
			$.ajax({
				url: $(event.target).attr("href"),
			  	type: "DELETE",
			  	
			  	beforeSend: function(xhr) {
			  		var token = $("meta[name='_csrf']").attr("content");
					var header = $("meta[name='_csrf_header']").attr("content");
					xhr.setRequestHeader(header, token);
			  		xhr.setRequestHeader("Accept", "application/json");
			  		xhr.setRequestHeader("Content-Type", "application/json");
			  	},
			  	
			  	success:  function(attribute) {
			  		var respContent = "";
			  		var rowToDelete = $(event.target).closest("tr");
			  		
			  		rowToDelete.remove();
			  		
			  		respContent += "<span class='success'>Attribute was deleted: [";
			  		respContent += attribute.id + " : ";
			  		respContent += attribute.name + "]</span>";
			  		
			  		$("#attributeFromResponse").html(respContent);   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   
</script>

<c:if test="${attributecreated != null}">
<div class="indicatormessage">${attributecreated}</div>
</c:if>
<c:if test="${error != null}">
<div class="errorblock">Error: Please check you filled all the fields required!</div>
</c:if>
<div id="attributeFromResponse"></div>
<table class="formtable">
<tr><td>Attribute Description</td><td>Category</td></tr>
<c:forEach var="ind" items="${attributes}">
<tr><td><c:out value="${ind.name}"></c:out></td>
<td>
	<c:forEach items="${ind.kpaCategories}" var="kpa" >
	<c:out value="${kpa.category}"></c:out>
	</c:forEach>
</td>

<td>
	<a href="${pageContext.request.contextPath}/attributes/editattribute/${ind.id}">Edit</a><br/>
	<a href="${pageContext.request.contextPath}/attributes/delete/${ind.id}.json">Delete</a><br/>
	<a href="${pageContext.request.contextPath}/attributes/${ind.id}/addindicator">AddIndicator</a><br/>
	<a href="${pageContext.request.contextPath}/attributes/${ind.id}/viewindicators">View Indicators</a><br/>
	</td>
</tr>
</c:forEach>
</table>

<sf:form method="post"
	action="${pageContext.request.contextPath}/docreateattribute"
	commandName="attribute">
	<sf:input type="hidden" name="id" path="id" />
	<sf:input type="hidden" name="weight" path="weight" value="0.1" />
	<table class="formtable">
		<tr>
			<td>Description :</td>
			<td><sf:input path="name" name="name" type="text" /><br />
			<div class="error"><sf:errors path="name"></sf:errors></div></td>
			<td>
			<sf:select path="kpaCategories" name="kpaCategories" items="${cat_list}" multiple="false" itemValue="id" itemLabel="category"/>
    		<div class="error"><sf:errors path="kpaCategories"></sf:errors></div>	
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Add Attribute" type="submit" class="submitButton"></td>
		</tr>

		
	</table>
</sf:form>