<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
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
			  	
			  	success:  function(attribute) {
			  		var respContent = "";
			  		var rowToDelete = $(event.target).closest("tr");
			  		
			  		rowToDelete.remove();
			  		
			  		respContent += "<div class='alert alert-success' role='alert' id='success_message'>Attribute was deleted: [";
			  		respContent += attribute.id + " : ";
			  		respContent += attribute.name + "]</div>";
			  		
			  		$("#attributeFromResponse").html(respContent);   		
			  	}
			});
  
			event.preventDefault();
		});
       
});   
</script>

<c:if test="${attributecreated != null}">
 <div class="alert alert-success" role="alert" id="success_message">
			<i class="glyphicon glyphicon-thumbs-up"></i>${attributecreated}
 </div>

</c:if>
<c:if test="${error != null}">
<div class="errorblock">Error: Please check you filled all the fields required!</div>
</c:if>
<div id="attributeFromResponse"></div>
<table class="table table-striped">
<thead>
    <tr>
    <th scope="col">Attribute Description</th>
    <th scope="col">Category</th>
    <th scope="col">Action</th>
    
    </tr>
</thead>

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
	<a href="${pageContext.request.contextPath}/attributes/${ind.id}/addindicator">Add/Edit Indicator</a><br/>
	
	</td>
</tr>
</c:forEach>
</table>
<div class="container">
<sf:form method="post"
	action="${pageContext.request.contextPath}/docreateattribute"
	commandName="attribute" class="well form-horizontal" >
	<fieldset>

		<!-- Form Name -->
		<legend>
			
				<h2>
					<b>New Attribute</b>
				</h2>
			
		</legend>
		<br>
	<sf:input type="hidden" name="id" path="id" />
	<sf:input type="hidden" name="weight" path="weight" value="0.1" />
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
					&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbspAdd Attribute <span
						class="glyphicon glyphicon-send"></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				</button>
			</div>
		</div>

	</fieldset>
	</sf:form>
</div>
<%-- 	<table class="formtable">
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
</sf:form> --%>