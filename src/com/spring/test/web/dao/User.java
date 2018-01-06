package com.spring.test.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;

import com.spring.test.web.validation.ValidEmail;

@Entity
@Table(name = "users")
public class User implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6999373167529034746L;

	@NotBlank(message = "Username cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 15, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	@Pattern(regexp = "^\\w{5,}$", message = "username can consist only of numbers, letters and the underscore character", groups = {
			PersistenceValidationGroup.class, FormValidationGroup.class })
	@Id
	private String username;

	@NotBlank(message = "Password cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Pattern(regexp = "^\\S+$", message = "Password cannot contain spaces", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 15, message = "Password must be between 5 and 15 characters long", groups = {
			FormValidationGroup.class })
	private String password;

	@NotBlank(message = "First name cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String fname;
	private boolean enabled = false;

	@NotBlank(message = "Last name cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String lname;

	@NotBlank(message = "Country field cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String country;

	@NotBlank(message = "Job role cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String job_role;

	@Range(min=1, max=60, message = "Incorrect range")
	private Integer years_role;
	@Range(min=1, max=60)
	private Integer years_construction;

	@NotBlank(message = "Professional qualification cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String prof_qualification;

	@NotBlank(message = "Work Sector cannot be blank", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	@Size(min = 5, max = 60, groups = { PersistenceValidationGroup.class, FormValidationGroup.class })
	private String work_sector;

	private String authority;
	@ValidEmail(message = "this is not a valid email ", groups = { PersistenceValidationGroup.class,
			FormValidationGroup.class })
	private String email;

	public User() {

	}

	public User(String username, String password, String fname, boolean enabled, String lname, String country,
			String job_role, Integer years_role, Integer years_construction, String prof_qualification, String work_sector,
			String authority, String email) {
		super();
		this.username = username;
		this.password = password;
		this.fname = fname;
		this.enabled = enabled;
		this.lname = lname;
		this.country = country;
		this.job_role = job_role;
		this.years_role = years_role;
		this.years_construction = years_construction;
		this.prof_qualification = prof_qualification;
		this.work_sector = work_sector;
		this.authority = authority;
		this.email = email;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getJob_role() {
		return job_role;
	}

	public void setJob_role(String job_role) {
		this.job_role = job_role;
	}

	public Integer getYears_role() {
		return years_role;
	}

	public void setYears_role(Integer years_role) {
		this.years_role = years_role;
	}

	public Integer getYears_construction() {
		return years_construction;
	}

	public void setYears_construction(Integer years_construction) {
		this.years_construction = years_construction;
	}

	public String getProf_qualification() {
		return prof_qualification;
	}

	public void setProf_qualification(String prof_qualification) {
		this.prof_qualification = prof_qualification;
	}

	public String getWork_sector() {
		return work_sector;
	}

	public void setWork_sector(String work_sector) {
		this.work_sector = work_sector;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((authority == null) ? 0 : authority.hashCode());
		result = prime * result + ((country == null) ? 0 : country.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((fname == null) ? 0 : fname.hashCode());
		result = prime * result + ((job_role == null) ? 0 : job_role.hashCode());
		result = prime * result + ((lname == null) ? 0 : lname.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((prof_qualification == null) ? 0 : prof_qualification.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((work_sector == null) ? 0 : work_sector.hashCode());
		result = prime * result + ((years_construction == null) ? 0 : years_construction.hashCode());
		result = prime * result + ((years_role == null) ? 0 : years_role.hashCode());
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
		User other = (User) obj;
		if (authority == null) {
			if (other.authority != null)
				return false;
		} else if (!authority.equals(other.authority))
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (fname == null) {
			if (other.fname != null)
				return false;
		} else if (!fname.equals(other.fname))
			return false;
		if (job_role == null) {
			if (other.job_role != null)
				return false;
		} else if (!job_role.equals(other.job_role))
			return false;
		if (lname == null) {
			if (other.lname != null)
				return false;
		} else if (!lname.equals(other.lname))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (prof_qualification == null) {
			if (other.prof_qualification != null)
				return false;
		} else if (!prof_qualification.equals(other.prof_qualification))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (work_sector == null) {
			if (other.work_sector != null)
				return false;
		} else if (!work_sector.equals(other.work_sector))
			return false;
		if (years_construction == null) {
			if (other.years_construction != null)
				return false;
		} else if (!years_construction.equals(other.years_construction))
			return false;
		if (years_role == null) {
			if (other.years_role != null)
				return false;
		} else if (!years_role.equals(other.years_role))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + ", fname=" + fname + ", enabled=" + enabled
				+ ", lname=" + lname + ", country=" + country + ", job_role=" + job_role + ", years_role=" + years_role
				+ ", years_construction=" + years_construction + ", prof_qualification=" + prof_qualification
				+ ", work_sector=" + work_sector + ", authority=" + authority + ", email=" + email + "]";
	}

	

	
	
}
