package com.spring.test.web.dao;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;


@Entity
@Table(name="attributes")
public class Attribute {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	@Size(min=5, max=255)
	private String name;
	
	
	@NotEmpty
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "attribute_category", 
             joinColumns = { @JoinColumn(name = "attributes_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "kpa_categories_id") })
    private List<KPACategory> kpaCategories = new ArrayList<KPACategory>();


	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Attribute other = (Attribute) obj;
		if (id != other.id)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

	public Attribute(){
		
	}

	public List<KPACategory> getKpaCategories() {
		return kpaCategories;
	}

	public void setKpaCategories(List<KPACategory> kpaCategories) {
		this.kpaCategories = kpaCategories;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Attribute(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
