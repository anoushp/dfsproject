package com.spring.test.web.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "assessment_companies")
public class AssessmentCompany {
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	@Size(min=5, max=255)
	private String companyname;

	private Integer size;
	
	@NotBlank
	@Size(min=5, max=255)
	private String country;
	
	@NotBlank
	@Size(min=5, max=255)
	private String city;
	
	@NotEmpty(message="At least one sector should be selected")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "company_sector", 
             joinColumns = { @JoinColumn(name = "company_id") }, 
             inverseJoinColumns = { @JoinColumn(name = "sector_id") })
    private List<OperationSector> opSectors = new ArrayList<OperationSector>();
	
	
	public AssessmentCompany(){
		
	}
	public AssessmentCompany(int id, String companyname, Integer size, String country, String city) {
		super();
		this.id = id;
		this.companyname = companyname;
		this.size = size;
		this.country = country;
		this.city = city;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city == null) ? 0 : city.hashCode());
		result = prime * result + ((companyname == null) ? 0 : companyname.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + id;
		result = prime * result + ((size == null) ? 0 : size.hashCode());
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
		AssessmentCompany other = (AssessmentCompany) obj;
		if (city == null) {
			if (other.city != null)
				return false;
		} else if (!city.equals(other.city))
			return false;
		if (companyname == null) {
			if (other.companyname != null)
				return false;
		} else if (!companyname.equals(other.companyname))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (id != other.id)
			return false;
		if (size == null) {
			if (other.size != null)
				return false;
		} else if (!size.equals(other.size))
			return false;
		return true;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
	}

	public Integer getSize() {
		return size;
	}

	public void setSize(Integer size) {
		this.size = size;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public List<OperationSector> getOpSectors() {
		return opSectors;
	}

	public void setOpSectors(List<OperationSector> opSectors) {
		this.opSectors = opSectors;
	}
	
	
}
