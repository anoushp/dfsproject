package com.spring.test.web.dao;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.spring.test.web.validation.ValidEmail;
@Entity
@Table(name="users")
public class User {
	
	@NotBlank(message="Username cannot be blank", groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Size(min=5, max=15,groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Pattern(regexp="^\\w{5,}$", message="username can consist only of numbers, letters and the underscore character",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Id
	private String username;
	
	@NotBlank(message="Password cannot be blank",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Pattern(regexp="^\\S+$", message="Password cannot contain spaces",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Size(min=5, max=15, message="Password must be between 5 and 15 characters long",groups={FormValidationGroup.class})
	private String password;
	
	@NotBlank(message="Name cannot be blank",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	@Size(min=5, max=60,groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	private String name;
	private boolean enabled=false;
	private String authority;
	@ValidEmail(message="this is not a valid email ",groups={PersistenceValidationGroup.class,FormValidationGroup.class})
	private String email;
	
	public User(){
		
	}
	
	public User(String username, String password, String name, boolean enabled, String authority, String email) {
		
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.name=name;
		this.email = email;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", name=" + name + ", enabled=" + enabled + ", authority=" + authority
				+ ", email=" + email + "]";
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
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + (enabled ? 1231 : 1237);
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
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
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (enabled != other.enabled)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
