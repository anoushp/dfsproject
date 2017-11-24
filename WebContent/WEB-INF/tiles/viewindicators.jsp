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
			  	
			  	success:  function(indicator) {
			  		var respContent = "";
			  		var rowToDelete = $(event.target).closest("tr");
			  		
			  		rowToDelete.remove();
			  		
			  		respContent += "<span class='success'>Indicator was deleted: [";
			  		respContent += indicator.inddesc + "]</span>";
			  		
			  		$("#indicatorFromResponse").html(respContent);   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   
</script>
<a href="${pageContext.request.contextPath}/attributes">View List of Attributes</a>
<br></br>
List of indicators for attribute "${attribute.name}"
<div id="indicatorFromResponse"></div>
<table class="formtable">
<tr><td>Maturity Level</td><td>Additional Notes</td></tr>
<c:forEach var="ind" items="${indicators}">
<tr><td><c:out value="${ind.matlevel}"></c:out></td>
<td><c:out value="${ind.text}"></c:out></td>

<td>
	<a href="${pageContext.request.contextPath}/attributes/${attribute.id}/editattribute/${ind.id}">Edit</a><br/>
	<a href="${pageContext.request.contextPath}/indicators/delete/${ind.id}.json">Delete</a><br/>
	
	</td>
</tr>
</c:forEach>
</table>
