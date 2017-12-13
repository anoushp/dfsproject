package com.spring.test.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "assessment_details")
public class AssessmentDetails {
	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne
	@JoinColumn(name="attributes_id", referencedColumnName = "id")
	private Attribute attribute;
	
	
	@ManyToOne
    @JoinColumns({
        @JoinColumn(
            name = "username",
            referencedColumnName = "username"),
        @JoinColumn(
            name = "title",
            referencedColumnName = "title")
    })
	private Assessment assessment;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@NotNull
	@Min(value=1, message="Please select one of the indicators in the list below")
	@Column(name="matlevel")
	private int matlevel;
	
	public AssessmentDetails(){
		this.attribute=new Attribute();
		this.assessment=new Assessment();
	}

	public AssessmentDetails(Attribute attribute, Assessment assessment, int matlevel) {
		super();
		this.attribute = attribute;
		this.assessment = assessment;
		this.matlevel = matlevel;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Assessment getAssessment() {
		return assessment;
	}

	public void setAssessment(Assessment assessment) {
		this.assessment = assessment;
	}

	public int getMatlevel() {
		return matlevel;
	}

	public void setMatlevel(int matlevel) {
		this.matlevel = matlevel;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + id;
		result = prime * result + matlevel;
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
		AssessmentDetails other = (AssessmentDetails) obj;
		if (assessment == null) {
			if (other.assessment != null)
				return false;
		} else if (!assessment.equals(other.assessment))
			return false;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (id != other.id)
			return false;
		if (matlevel != other.matlevel)
			return false;
		return true;
	}
	
	
}
