package com.spring.test.web.dao;

import java.util.List;

import javax.validation.Valid;

public class AssessmentForm {
	@Valid
	String title;
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Valid
	private List<AssessmentDetails> assessmentDetails;

	public List<AssessmentDetails> getAssessmentDetails() {
		return assessmentDetails;
	}

	public void setAssessmentDetails(List<AssessmentDetails> assessmentDetails) {
		this.assessmentDetails = assessmentDetails;
	}
	

}
