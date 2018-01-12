package com.spring.test.web.dao;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "assessments")
public class Assessment {
	@EmbeddedId
	private AssessmentId id;

	@OneToOne
	@JoinColumn(name = "assessment_companies_id")
	private AssessmentCompany company;

	public Assessment() {

	}

	public Assessment(AssessmentId id, AssessmentCompany company) {
		super();
		this.id = id;
		this.company = company;
	}

	public AssessmentId getId() {
		return id;
	}

	public void setId(AssessmentId id) {
		this.id = id;
	}

	public AssessmentCompany getCompany() {
		return company;
	}

	public void setCompany(AssessmentCompany company) {
		this.company = company;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((company == null) ? 0 : company.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assessment other = (Assessment) obj;
		if (company == null) {
			if (other.company != null)
				return false;
		} else if (!company.equals(other.company))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
