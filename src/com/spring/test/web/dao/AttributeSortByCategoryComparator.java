package com.spring.test.web.dao;

import java.util.Comparator;

public class AttributeSortByCategoryComparator implements Comparator<Attribute> {

	@Override
	public int compare(Attribute o1, Attribute o2) {
		
		if(o1.getKpaCategories().isEmpty() || o2.getKpaCategories().isEmpty())
			return -1;
		String category1 = o1.getKpaCategories().get(0).getCategory();
		String category2 = o2.getKpaCategories().get(0).getCategory();
	
		return category1.compareTo(category2);
		
	}

}
