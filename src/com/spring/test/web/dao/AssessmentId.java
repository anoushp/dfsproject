package com.spring.test.web.dao;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotBlank;

@Embeddable
public class AssessmentId implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1198504985664783469L;
	@NotBlank
	@Column(name = "username")
	private String username;
	@NotBlank
	@Column(name = "title")
	private String title;

	public AssessmentId(){
		
	};
	public AssessmentId(String username, String title) {
		super();
		this.username = username;
		this.title = title;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
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
		AssessmentId other = (AssessmentId) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
