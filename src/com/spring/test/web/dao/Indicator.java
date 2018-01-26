package com.spring.test.web.dao;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name="indicators")
public class Indicator {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	private String text;
	
	private String example;
	
	@NotBlank
	private int matlevel;
	
	@ManyToOne
	@JoinColumn(name="attributes_id", referencedColumnName = "id")
	private Attribute attribute;
	
	public Indicator(){
		this.attribute=new Attribute();
	}
	
	public Indicator(int matlevel, String text, String example, Attribute attribute) {
		super();
		this.matlevel = matlevel;
		this.text = text;
		this.attribute = attribute;
		this.example=example;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public Indicator(int id,int matlevel, String text, String example, Attribute attribute) {
		super();
		this.id=id;
		this.matlevel = matlevel;
		this.text = text;
		this.example=example;
		this.attribute = attribute;
	}
	
	public String getAttrName(){
		return attribute.getName();
	}

	public int getMatlevel() {
		return matlevel;
	}

	public void setMatlevel(int matlevel) {
		this.matlevel = matlevel;
	}

	public String getExample() {
		return example;
	}

	public void setExample(String example) {
		this.example = example;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + ((example == null) ? 0 : example.hashCode());
		result = prime * result + id;
		result = prime * result + matlevel;
		result = prime * result + ((text == null) ? 0 : text.hashCode());
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
		Indicator other = (Indicator) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (example == null) {
			if (other.example != null)
				return false;
		} else if (!example.equals(other.example))
			return false;
		if (id != other.id)
			return false;
		if (matlevel != other.matlevel)
			return false;
		if (text == null) {
			if (other.text != null)
				return false;
		} else if (!text.equals(other.text))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Indicator [id=" + id + ", text=" + text + ", example=" + example + ", matlevel=" + matlevel
				+ ", attribute=" + attribute + "]";
	}



	

}
