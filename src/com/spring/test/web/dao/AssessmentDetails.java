package com.spring.test.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.spring.test.web.validation.ValidMatlevelCompletion;

@Entity
@Table(name = "assessment_details")
@ValidMatlevelCompletion
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

	
	@Column(name="matlevel")
	private int matlevel;
	
	@Column(name="score")
	private Double score;

	
	@Column(name="matlevels")
	@NotNull
	@Size(min=1, message="Please select at least one of the checkboxes")
	
	@Convert(converter = StringListConverter.class)
	private List<String> matlevels;
	
	@Convert(converter = StringListConverter.class)
	private List<String> completion_criteria;
	
	public AssessmentDetails(){
		this.attribute=new Attribute();
		this.assessment=new Assessment();
		this.matlevels=new ArrayList<String>();
		this.completion_criteria=new ArrayList<String>();
	}

	

	public AssessmentDetails(int id, Attribute attribute, Assessment assessment, int matlevel, List<String> matlevels,List<String> completion_criteria, Double score) {
		super();
		this.id = id;
		this.attribute = attribute;
		this.assessment = assessment;
		this.matlevel = matlevel;
		this.matlevels = matlevels;
		this.completion_criteria = completion_criteria;
		this.score=score;
	}

	public List<String> getMatlevels() {
		return matlevels;
	}

	public void setMatlevels(List<String> matlevels) {
		this.matlevels = matlevels;
	}

	public List<String> getCompletion_criteria() {
		return completion_criteria;
	}

	public void setCompletion_criteria(List<String> completion_criteria) {
		this.completion_criteria = completion_criteria;
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

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((assessment == null) ? 0 : assessment.hashCode());
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + ((completion_criteria == null) ? 0 : completion_criteria.hashCode());
		result = prime * result + id;
		result = prime * result + matlevel;
		result = prime * result + ((matlevels == null) ? 0 : matlevels.hashCode());
		result = prime * result + ((score == null) ? 0 : score.hashCode());
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
		if (completion_criteria == null) {
			if (other.completion_criteria != null)
				return false;
		} else if (!completion_criteria.equals(other.completion_criteria))
			return false;
		if (id != other.id)
			return false;
		if (matlevel != other.matlevel)
			return false;
		if (matlevels == null) {
			if (other.matlevels != null)
				return false;
		} else if (!matlevels.equals(other.matlevels))
			return false;
		if (score == null) {
			if (other.score != null)
				return false;
		} else if (!score.equals(other.score))
			return false;
		return true;
	}

	
	
	
	
	
}
