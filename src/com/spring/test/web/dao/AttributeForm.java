package com.spring.test.web.dao;
import java.util.List;

import javax.validation.Valid;

public class AttributeForm {
	@Valid
	List<Attribute> attributes;

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public void setAttributes(List<Attribute> attributes) {
		this.attributes = attributes;
	}
	

}
