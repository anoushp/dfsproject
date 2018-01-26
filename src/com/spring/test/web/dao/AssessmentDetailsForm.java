package com.spring.test.web.dao;

import java.util.List;

import javax.validation.Valid;


public class AssessmentDetailsForm {
	@Valid
	private AssessmentDetails assessmentDetails;
	
	private List<String> completionCriteria;

	public AssessmentDetails getAssessmentDetails() {
		return assessmentDetails;
	}

	public void setAssessmentDetails(AssessmentDetails assessmentDetails) {
		this.assessmentDetails = assessmentDetails;
	}

	public List<String> getCompletionCriteria() {
		return completionCriteria;
	}

	public void setCompletionCriteria(List<String> completionCriteria) {
		this.completionCriteria = completionCriteria;
	}

}
