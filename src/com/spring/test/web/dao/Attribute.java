package com.spring.test.web.dao;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import javax.validation.constraints.Size;
import javax.persistence.JoinColumn;


import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;


@Entity
@Table(name="attributes")
public class Attribute {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	@Size(min=5, max=255)
	private String name;
	
	@Range(min = 0, max = 100, message="Please input percentage 0<= weight <=100")
	private int weight;
	
	@Range(min = 0, max = 100, message="Please input percentage 0<= weight <=100")
	private int sector_weight;
	
	
	@NotEmpty(message="At least one category should be selected")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "attribute_category", 
             joinColumns = { @JoinColumn(name = "attributes_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "kpa_categories_id") })
    private List<KPACategory> kpaCategories = new ArrayList<KPACategory>();


	@OneToMany(mappedBy = "attribute",fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Indicator> indicators=new ArrayList<Indicator>();
	

	

	public Collection<Indicator> getIndicators() {
		return indicators;
	}

	public void setIndicators(Collection<Indicator> indicators) {
		this.indicators = indicators;
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

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public int getSector_weight() {
		return sector_weight;
	}

	public void setSector_weight(int sector_weight) {
		this.sector_weight = sector_weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((indicators == null) ? 0 : indicators.hashCode());
		result = prime * result + ((kpaCategories == null) ? 0 : kpaCategories.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + sector_weight;
		result = prime * result + weight;
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
		if (indicators == null) {
			if (other.indicators != null)
				return false;
		} else if (!indicators.equals(other.indicators))
			return false;
		if (kpaCategories == null) {
			if (other.kpaCategories != null)
				return false;
		} else if (!kpaCategories.equals(other.kpaCategories))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (sector_weight != other.sector_weight)
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}
	

}
