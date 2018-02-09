<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet"
	href="//netdna.bootstrapcdn.com/font-awesome/3.2.1/css/font-awesome.min.css">

<h1>DESIGN for SAFETY CAPABILITY MATURITY INDICATOR</h1>

<script>
	$(document).ready(function() {
		$('[data-toggle="tooltip"]').tooltip();
	});
</script>
<style>
.tooltip>.tooltip-inner {
	border: 1px solid;
	padding: 10px;
	max-width: 450px;
	color: black;
	text-align: left;
	background-color: #D3D3D3;
	background: #D3D3D3;
	
}

.tooltip>.tooltip-arrow {
	border-bottom-color: black;
}


</style>
<p class="ss-par">The DfS-CMI tool is an assessment tool to enable
	architectural and design firms to improve their DfS capability.</p>


<sf:form method="post"
	action="${pageContext.request.contextPath}/updateDfSAssessment/${savedTitle}"
	commandName="dfsassessform">
	<c:if test="${formError != null}">
		<div class="errorblock">Please make sure you have selected
			indicator/indicators for each attribute below correctly</div>
	</c:if>
	<sf:input type="hidden" name="assessmentCompany.id"
		path="assessmentCompany.id" />
	<div class="form-group">
		<label>Assessment Title</label>
		<sf:input path="title" name="title" class="form-control" type="text" />
		<div class="error">
			<sf:errors path="title"></sf:errors>
		</div>

	</div>

	<div class="form-group">
		<label>Company/Design Office Name</label>
		<sf:input path="assessmentCompany.companyname"
			name="assessmentCompany.companyname" class="form-control" type="text" />
		<div class="error">
			<sf:errors path="assessmentCompany.companyname"></sf:errors>
		</div>

	</div>

	<div class="form-group">
		<label>Company/Design Office Size</label>
		<sf:input path="assessmentCompany.size" name="assessmentCompany.size"
			class="form-control" type="text" />
		<div class="error">
			<sf:errors path="assessmentCompany.size"></sf:errors>
		</div>

	</div>
	<div class="form-group">
		<label>Company/Design Office Country</label>
		<sf:select path="assessmentCompany.country"
			name="assessmentCompany.country" multiple="false"
			class="form-control" style="width: 250px;">
			<sf:option value="" />
			<sf:options items="${countries}" />
			<sf:errors path="assessmentCompany.country" class="error" />
		</sf:select>
		<div class="error">
			<sf:errors path="assessmentCompany.country"></sf:errors>
		</div>

	</div>
	<div class="form-group">
		<label>Company/Design Office City</label>
		<sf:input path="assessmentCompany.city" name="assessmentCompany.city"
			class="form-control" type="text" />
		<div class="error">
			<sf:errors path="assessmentCompany.city"></sf:errors>
		</div>

	</div>
	<div class="form-group">
		<label>Operations Sector</label>
		<div class="selectContainer">
			<div class="input-group" style="width: 250px;">
				<sf:select path="assessmentCompany.opSectors"
					name="assessmentCompany.opSectors" items="${os_list}"
					multiple="true" itemValue="id" itemLabel="sector"
					class="form-control" style="width: 250px;" />
				<div class="error">
					<sf:errors path="assessmentCompany.opSectors"></sf:errors>
				</div>
			</div>
		</div>

	</div>

	<br>
	<c:set var="counter" value="0" />
	<c:forEach var="cat_dfstst" items="${dfsmap}" varStatus="loopmain">

		<h4 class="ss-attr-desc">${cat_dfstst.key}</h4>
		<c:forEach var="dfstst" items="${cat_dfstst.value}" varStatus="loop1">
			<sf:input type="hidden"
				name="assessmentDetailsForm[${ counter}].assessmentDetails.id"
				path="assessmentDetailsForm[${ counter}].assessmentDetails.id" />
			<sf:input type="hidden"
				name="assessmentDetailsForm[${ counter}].assessmentDetails.attribute"
				path="assessmentDetailsForm[${ counter}].assessmentDetails.attribute.id"
				value="${dfstst.key.id}" />

			<h4 class="ss-attr-desc">Attribute: ${dfstst.key.name}</h4>
			<div class="ss-form-entry">
				<table>
					<tbody>

						<spring:bind
							path="assessmentDetailsForm[${ counter}].assessmentDetails.*">

							<c:if test="${status.error}">

								<tr>
									<div class="errorblock">
										<c:forEach items="${status.errorMessages}" var="error">
        Error code: <c:out value="${error}" />
											<br>
										</c:forEach>
									</div>
								</tr>
							</c:if>
						</spring:bind>


						<c:forEach var="ind" items="${dfstst.value }" varStatus="loop">

							<tr>
								<td nowrap><sf:checkbox
										path="assessmentDetailsForm[${ counter}].assessmentDetails.matlevels"
										value="${loop.index+1}" />&nbsp ML${loop.index+1} &nbsp</td>
								<td><sf:select
										path="assessmentDetailsForm[${ counter}].assessmentDetails.completion_criteria[${loop.index}]"
										name="assessmentDetailsForm[${ counter}].assessmentDetails.completion_criteria[${loop.index}]"
										multiple="false">
										<sf:option value="" />
										<sf:options items="${mat_compliance}" />
									</sf:select></td>
								<td><c:if test="${ind != null}"><c:out value="${ind.text}" escapeXml="false"/> <a  href="#" data-toggle="tooltip" data-placement="top" data-html="true" title="${ind.example}" >Indicator Example</a>   
									</c:if></td>
							</tr>


						</c:forEach>
					</tbody>
				</table>
			</div>
			<br />
			<c:set var="counter" value="${counter+1 }" />
		</c:forEach>

	</c:forEach>
	<input value="Update your self-assessment" type="submit"
		class="submitButton">
</sf:form>
