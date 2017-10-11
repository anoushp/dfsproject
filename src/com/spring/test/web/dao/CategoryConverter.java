package com.spring.test.web.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.spring.test.web.service.KPACategoryService;
@Component
public class CategoryConverter implements Converter<Object, KPACategory> {
	private KPACategoryService kpaCategoryService;
	@Autowired
    public CategoryConverter(KPACategoryService kpaCategoryService){
		this.kpaCategoryService=kpaCategoryService;
	}
 
    /**
     * Gets UserProfile by Id
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public KPACategory convert(Object element) {
        int id = Integer.parseInt((String)element);
        KPACategory cat= kpaCategoryService.findById(id);
        System.out.println("Category: "+cat);
        return cat;
    }
     
}
