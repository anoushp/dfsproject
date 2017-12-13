<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
			  	
			  	success:  function(assessment) {
			  		var respContent = "";
			  		var rowToDelete = $(event.target).closest("tr");
			  		
			  		rowToDelete.remove();
			  		
			  		respContent += "<span class='success'>Assessment was deleted: [";
			  
			  		respContent += assessment.id.title + "]</span>";
			  		
			  		$("#assessmentFromResponse").html(respContent);   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   
</script>
<c:if test="${empty assessment}">
<div class="indicatormessage">You have no existing assessments. Please proceed and create a new one</div>
</c:if>
<c:if test="${assessment!=null}">
List of existing assessments 
<div id="assessmentFromResponse"></div>
<table class="formtable">
<tr><td>Assessment Title</td></tr>
<c:forEach var="assmt" items="${dfsassessments}">
<tr><td><c:out value="${assmt.id.title}"></c:out></td>

<td>
	<a href="${pageContext.request.contextPath}/dfsassessments/update/${assmt.id.title}">Update</a><br/>
	<a href="${pageContext.request.contextPath}/delete/assessment/${assmt.id.title}.json">Delete</a><br/>
	
	</td>
</tr>
</c:forEach>
</table>
</c:if>
<p><a href="<c:url value="/newassessment"/>">Create new assessment</a></p>
