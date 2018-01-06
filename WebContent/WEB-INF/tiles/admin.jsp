<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<meta name="_csrf" content="${_csrf.token}" />
<!-- default header name is X-CSRF-TOKEN -->
<meta name="_csrf_header" content="${_csrf.headerName}" />
</head>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">
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
			  		
			  		respContent += "<span class='success'>User was deleted: [";
			  
			  		respContent += assessment.id.title + "]</span>";
			  		
			  		$("#userFromResponse").html(respContent);   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   
</script>
<h2>User Management</h2><br>
<div id="userFromResponse"></div>
<table class="table table-striped">
<thead>
    <tr>
    <th scope="col">Username</th>
    <th scope="col">Email</th>
    <th scope="col">Role</th>
    <th scope="col">Enabled</th>
     <th scope="col">Update</th>
      <th scope="col">Delete</th>
    </tr>
</thead>

<c:forEach var="user" items="${users}">
<tr><td><c:out value="${user.username}"></c:out></td>
<td><c:out value="${user.email} "></c:out></td>
<td><c:out value="${user.authority} "></c:out></td>
<td><c:out value="${user.enabled} "></c:out></td>
<td>
	<a href="${pageContext.request.contextPath}/updateuser/${user.username}">Update</a><br/>
</td>
<td>
	<a href="${pageContext.request.contextPath}/deleteuser/${user.username}.json">Delete</a><br/>
</td>	
</tr>
</c:forEach>
</table>
