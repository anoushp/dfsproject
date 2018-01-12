package com.spring.test.web.dao;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="operation_sectors")
public class OperationSector {
	@Id
	@GeneratedValue
	private int id;

	
	@NotBlank
	@Size(min=5, max=45)
	private String sector=SectorType.DESIGN.getSectorType();

    public OperationSector(){
    	
    }
	public OperationSector(int id, String sector) {
		super();
		this.id = id;
		this.sector = sector;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((sector == null) ? 0 : sector.hashCode());
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
		OperationSector other = (OperationSector) obj;
		if (id != other.id)
			return false;
		if (sector == null) {
			if (other.sector != null)
				return false;
		} else if (!sector.equals(other.sector))
			return false;
		return true;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getSector() {
		return sector;
	}


	public void setSector(String sector) {
		this.sector = sector;
	}
	

}
