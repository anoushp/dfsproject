package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.Indicator;
import com.spring.test.web.dao.IndicatorsDao;




@Service("indicatorsService")
public class IndicatorsService {
private IndicatorsDao indicatorsDao;
	
	@Autowired
	public void setOffersDao(IndicatorsDao indicatorsDao) {
		this.indicatorsDao = indicatorsDao;
	}

	public List<Indicator> getIndicators(){
		return indicatorsDao.getIndicators();
		
	}
	public Indicator getIndicator(int id){
		return indicatorsDao.getIndicator(id);
	}
	public boolean hasIndicator(int id) {
		if (id<=0) return false;
		List<Indicator> indicators=indicatorsDao.getIndicators(id);
		if (indicators.size()==0){
			return false;
		}
		return true;
	}

	public List<Indicator> getIndicators(int id) {
		if (id<=0)
			return null;
		List<Indicator> indicators=indicatorsDao.getIndicators(id);
		if (indicators.size()==0) return null;
		return indicators;
	}

	public void saveOrUpdate(Indicator ind) {
		 indicatorsDao.saveOrUpdate(ind);
	}

	public void delete(int id) {
		indicatorsDao.delete(id);
		
	}
  
}
