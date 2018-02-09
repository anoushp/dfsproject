package com.spring.test.web.service;

import java.util.List;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.web.dao.Attribute;
import com.spring.test.web.dao.AttributesDao;



@Service("attributesService")
@Transactional
public class AttributesService {

	private AttributesDao attributesDao;

	@Autowired
	public void setOffersDao(AttributesDao attributesDao) {
		this.attributesDao = attributesDao;
	}


	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public void create(Attribute ind) {
		attributesDao.saveOrUpdate(ind);

	}
   public void updateAttribute(Attribute attr) {
	   Attribute attribute=attributesDao.getAttribute(attr.getId());
	   if (attribute!=null){
		   attribute.setName(attr.getName());
		   attribute.setKpaCategories(attr.getKpaCategories());
	   }
   }
   public void updateAttributeWeight(Attribute attr) {
	   Attribute attribute=attributesDao.getAttribute(attr.getId());
	   if (attribute!=null){
		   attribute.setWeight(attr.getWeight());
		   attribute.setSector_weight(attr.getSector_weight());
	   }
   }
	public void saveOrUpdate(Attribute ind) {
		attributesDao.saveOrUpdate(ind);
	}

	public void delete(int id) {
		attributesDao.delete(id);

	}

	public Attribute getAttribute(int id){
		return attributesDao.getAttribute(id);

	}
	public Attribute getAttribute(String name){
		return attributesDao.getAttribute(name);

	}
	public List<Attribute> getAllAttributes(){
		return attributesDao.getAllAttributes();

	}


}
