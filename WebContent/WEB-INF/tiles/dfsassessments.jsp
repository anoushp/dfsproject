<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
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
<h2>Assessment management</h2>
<div id="assessmentFromResponse"></div>
<table class="table table-striped">
<thead>
    <tr>
    <th scope="col">Assessment Title</th>
    <th scope="col">Company Name</th>
    <th scope="col">Company Size</th>
    <th scope="col">Sector</th>
    <th scope="col">Score</th>
    <th scope="col">Actions/<th>
  </tr>
</thead>

<c:forEach var="assmt" items="${dfsassessments}">
<tr><td><c:out value="${assmt.id.title}"></c:out></td>
<td><c:out value="${assmt.company.companyname}"></c:out></td>
<td><c:out value="${assmt.company.size}"></c:out></td>
<td><c:forEach items="${assmt.company.opSectors}" var="opSector" >
	<c:out value="${opSector.sector}"></c:out><br>
	</c:forEach></td>
<td>
<c:if test="${empty assmt.score}">
N/A
</c:if>
<c:if test="${not empty assmt.score}">
<c:out value="${assmt.score}"></c:out>
</c:if>
</td>

<td>
	<a href="${pageContext.request.contextPath}/dfsassessments/update/${assmt.id.title}">Update</a><br/>
	<a href="${pageContext.request.contextPath}/delete/assessment/${assmt.id.title}.json">Delete</a><br/>
	<a href="${pageContext.request.contextPath}/dfsassessments/assessment/${username}/${assmt.id.title}">View Results</a><br/>
	
	</td>
</tr>
</c:forEach>
</table>
</c:if>
<p><a href="<c:url value="/newassessment"/>">Create new assessment</a></p>
