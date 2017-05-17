package com.spring.test.web.dao;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.spring.test.web.validation.ValidEmail;

public class User {
	@NotBlank(message="Username cannot be blank")
	@Size(min=5, max=15)
	@Pattern(regexp="^\\w{5,}$", message="username can consist only of numbers, letters and the underscore character")
	private String username;
	@NotBlank(message="Password cannot be blank")
	@Pattern(regexp="^\\S+$", message="Password cannot contain spaces")
	@Size(min=5, max=15, message="Password must be between 5 and 15 characters long")
	private String password;
	private boolean enabled=false;
	private String authority;
	@ValidEmail(message="this is not a valid email ")
	private String email;
	
	public User(){
		
	}
	
	public User(String username, String password, boolean enabled, String authority, String email) {
		
		this.username = username;
		this.password = password;
		this.enabled = enabled;
		this.authority = authority;
		this.email = email;
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

}
