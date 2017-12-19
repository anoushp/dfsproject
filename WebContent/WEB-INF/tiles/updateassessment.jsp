<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<h1>DESIGN for SAFETY CAPABILITY MATURITY INDICATOR</h1>

<p class="ss-par">The DfS-CMI tool is an assessment tool to enable
	architectural and design firms to improve their DfS capability.</p>
<sf:form method="post"
	action="${pageContext.request.contextPath}/updateDfSAssessment/${savedTitle}"
	commandName="dfsassessform">
  <c:if test="${formError != null}"><div class="errorblock">Please make sure you have selected an indicator for each attribute below</div>
  </c:if>
   <table class="formtable">
   <tr><td>Assessment Title: </td>
   <td><sf:input path="title" name="title" type="text"/><div class="error"><sf:errors path="title"></sf:errors></div></td></tr>
   </table>
   <c:set var="counter" value="0" />
   <c:forEach var="cat_dfstst" items="${dfsmap}" varStatus="loopmain">
    <h4 class="ss-attr-desc">${cat_dfstst.key}</h4>
	<c:forEach var="dfstst" items="${cat_dfstst.value}" varStatus="loop1">
		<sf:input type="hidden" name="assessmentDetails[${ counter}].id"
			path="assessmentDetails[${ counter}].id" />
		<sf:input type="hidden" name="assessmentDetails[${ counter}].attribute"
			path="assessmentDetails[${ counter}].attribute.id"
			value="${dfstst.key.id}" />
		
		<h4 class="ss-attr-desc">Attribute ${counter+1}:
			${dfstst.key.name}</h4>
		<div class="ss-form-entry">
			<table>
				<tbody>
				
				<spring:bind path="assessmentDetails[${ counter}].matlevel">
                 
             <c:if test="${status.error}">
            <tr><div class="errorblock"><sf:errors path="assessmentDetails[${ counter}].matlevel"></sf:errors></div></tr>
             </c:if>
                </spring:bind>
				
			
					<c:forEach var="ind" items="${dfstst.value }" varStatus="loop">

						<tr>
							<td nowrap><sf:radiobutton
									path="assessmentDetails[${ counter}].matlevel"
									value="${loop.index+1}" />&nbsp ${loop.index+1} &nbsp</td>
									
							<td><c:if test="${ind != null}">${ind.text}
    </c:if></td>
						</tr>


					</c:forEach>
				</tbody>
			</table>
		</div>
		<br />
		<c:set var="counter" value="${counter+1 }"	/>	
</c:forEach>
</c:forEach>
<input value="Update your self-assessment" type="submit" class="submitButton">
</sf:form>
