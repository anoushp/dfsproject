<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update success</title>
<body>
<div class="generic-container">
    <div class="alert alert-success lead">
        ${success}
    </div>
     
    <span class="well floatRight">
        Go to <a href="${pageContext.request.contextPath}/attributes">Attribute List</a>
    </span>
</div>
</body>
</html>