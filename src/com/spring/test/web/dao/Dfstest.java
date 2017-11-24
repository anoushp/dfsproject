package com.spring.test.web.dao;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.spring.test.web.validation.ValidEmail;
@Entity
@Table(name="dfstests")

public class Dfstest {
	@Id
	@GeneratedValue
	private int id;
	

	@ManyToOne
	@JoinColumn(name="username")
	private User user;
	
	@ManyToOne
	@JoinColumn(name="attributes_id", referencedColumnName = "id")
	private Attribute attribute;
	
	@NotNull
	@Min(value=1, message="Please select one of the indicators in the list below")
	@Column(name="matlevel")
	private int matlevel;
	
	public Dfstest(){
		this.attribute=new Attribute();
		this.user=new User();
	}
	public Dfstest(User user, Attribute attribute, int matlevel) {
		super();
		this.user = user;
		this.attribute = attribute;
		this.matlevel = matlevel;
	}

	@Override
	public String toString() {
		return "Dfstest [id=" + id + ", user=" + user + ", attribute=" + attribute + ", matlevel=" + matlevel + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((attribute == null) ? 0 : attribute.hashCode());
		result = prime * result + id;
		result = prime * result + matlevel;
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		Dfstest other = (Dfstest) obj;
		if (attribute == null) {
			if (other.attribute != null)
				return false;
		} else if (!attribute.equals(other.attribute))
			return false;
		if (id != other.id)
			return false;
		if (matlevel != other.matlevel)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}


	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Attribute getAttribute() {
		return attribute;
	}

	public void setAttribute(Attribute attribute) {
		this.attribute = attribute;
	}

	public int getMatlevel() {
		return matlevel;
	}

	public void setMatlevel(int matlevel) {
		this.matlevel = matlevel;
	}

}
