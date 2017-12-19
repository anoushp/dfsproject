package com.spring.test.web.dao;

public enum CategoryType {
	   COMPETENCE ("Competence"),
	   STRATEGY ("Strategy"),
	   INFRASTRUCTURE ("Infrastructure"),
	   SYSTEMS ("Systems"),
	   COLLABORATION ("Collaboration"),
	   RESOURCES ("Resources");
     
    String categoryType;

	public String getCategoryType() {
		return categoryType;
	}

	private CategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

}
