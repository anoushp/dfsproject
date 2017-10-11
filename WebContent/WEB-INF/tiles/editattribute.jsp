<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<sf:form method="post"
	action="${pageContext.request.contextPath}/attributes/editattribute"
	commandName="attribute">
	<sf:input type="hidden" name="id" path="id" />
	<table>
		<tr>
			<td>Description :</td>
			<td><sf:input path="name" name="name" type="text" /><br />
			<div class="error"><sf:errors path="name"></sf:errors></div></td>
			<td>
			<sf:select path="kpaCategories" name="kpaCategories" items="${cat_list}" multiple="true" itemValue="id" itemLabel="category"/>
    			
			</td>
		</tr>
		<tr>
			<td></td>
			<td><input value="Save changes" type="submit"></td>
		</tr>

		
	</table>
</sf:form>