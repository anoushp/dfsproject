package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.KPACategory;
import com.spring.test.web.dao.KPACategoryDao;




@Service("kpaCategoryService")
public class KPACategoryService {
	private KPACategoryDao kpaCategoryDao;
	
	@Autowired
	public void setKPACategoryDao(KPACategoryDao kpaCategoryDao) {
		this.kpaCategoryDao = kpaCategoryDao;
	}

	public void saveOrUpdate(KPACategory kpacat) {
		 kpaCategoryDao.saveOrUpdate(kpacat);
	}

	public void delete(int id) {
		kpaCategoryDao.delete(id);
		
	}
	
   public List<KPACategory> getKPACategory() {
		
		List<KPACategory> kpas=kpaCategoryDao.getKPACategory();
		if (kpas.size()==0) return null;
		return kpas;
	}
public int getKPACategoryId(String category){
	return kpaCategoryDao.getKPACategoryId(category).getId();
}
public KPACategory findById(int id) {
	return kpaCategoryDao.findById(id);
	
}

}
