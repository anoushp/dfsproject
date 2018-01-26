package com.spring.test.web.dao;

import java.util.List;

import javax.validation.Valid;

public class AssessmentForm {
	@Valid
	String title;

	@Valid
	AssessmentCompany assessmentCompany;

	@Valid
	private List<AssessmentDetailsForm> assessmentDetailsForm;

	public List<AssessmentDetailsForm> getAssessmentDetailsForm() {
		return assessmentDetailsForm;
	}

	public void setAssessmentDetailsForm(List<AssessmentDetailsForm> assessmentDetailsForm) {
		this.assessmentDetailsForm = assessmentDetailsForm;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public AssessmentCompany getAssessmentCompany() {
		return assessmentCompany;
	}

	public void setAssessmentCompany(AssessmentCompany assessmentCompany) {
		this.assessmentCompany = assessmentCompany;
	}


}
