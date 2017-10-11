package com.spring.test.web.dao;

public enum CategoryType {
   PROCESS ("Process"),
   PEOPLE ("People"),
   TECHNOLOGY ("Technology"),
   SYSTEMS ("Systems"),
   POLICY ("Policy"),
   RESOURCES ("Resources");
   
     
    String categoryType;

	public String getCategoryType() {
		return categoryType;
	}

	private CategoryType(String categoryType) {
		this.categoryType = categoryType;
	}

}
