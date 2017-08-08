package com.spring.test.web.dao;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.spring.test.web.validation.ValidEmail;

@Entity
@Table(name="messages")
public class Message implements Serializable{
	@Override
	public String toString() {
		return "Message [id=" + id + ", subject=" + subject + ", content=" + content + ", name=" + name + ", email="
				+ email + ", username=" + username + "]";
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = -9136754850356336481L;
	@Id
	@GeneratedValue
	private int id;
	
	@NotBlank
	@Size(min=5, max=100)
	private String subject;
	
	@NotBlank
	@Size(min=5, max=1000)
	private String content;
	
	//name and email of sender
	@NotBlank
	@Size(min=5, max=60)
	private String name;
	
	@ValidEmail
	private String email;
	
	//send message to this user
	private String username;
	
	public Message(){
	
	
		
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Message(String subject, String content, String name, String email, String username) {
		
		this.subject = subject;
		this.content = content;
		this.name = name;
		this.email = email;
		this.username = username;
	}
	

}
