package com.spring.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.web.dao.AssessmentCompany;
import com.spring.test.web.dao.AssessmentCompanyDao;



@Service("assessmentCompanyService")
@Transactional
public class AssessmentCompanyService {
	
	private AssessmentCompanyDao assessmentCompanyDao; 
	
	@Autowired
	public void setAssessmentCompanyDao(AssessmentCompanyDao assessmentCompanyDao) {
		this.assessmentCompanyDao = assessmentCompanyDao;
	}
	public void create(AssessmentCompany ac) {
		assessmentCompanyDao.saveOrUpdate(ac);

	}
   public void updateAssessmentCompany(AssessmentCompany ac) {
	   AssessmentCompany acompany=assessmentCompanyDao.getAssessmentCompany(ac.getId());
	   if (acompany!=null){
		   acompany.setCompanyname(ac.getCompanyname());
		   acompany.setOpSectors(ac.getOpSectors());
	   }
   }

	public void saveOrUpdate(AssessmentCompany ac) {
		assessmentCompanyDao.saveOrUpdate(ac);
	}

	public void delete(int id) {
		assessmentCompanyDao.delete(id);

	}

	public AssessmentCompany getAssessmentCompany(int id){
		return assessmentCompanyDao.getAssessmentCompany(id);

	}

}
