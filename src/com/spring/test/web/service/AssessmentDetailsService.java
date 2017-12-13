package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.web.dao.AssessmentDetailsDao;
import com.spring.test.web.dao.AssessmentDetails;


@Service("assessmentDetailsService")
@Transactional
public class AssessmentDetailsService {
	private AssessmentDetailsDao assessmentDetailsDao ;
	@Autowired
	public void setAssessmentDetailsDao(AssessmentDetailsDao dfstestsDao) {
		this.assessmentDetailsDao = dfstestsDao;
	}
	@Secured({"ROLE_USER","ROLE_ADMIN"})
	public void create(AssessmentDetails dfstest) {
		assessmentDetailsDao.saveOrUpdate(dfstest);

	}

	public boolean hasDfstest(String name, String title) {
		if (name==null) return false;
		List<AssessmentDetails> dfstests=assessmentDetailsDao.getAssessmentDetails(name, title);
		if (dfstests==null){
			return false;
		}
		return true;
	}

	public List<AssessmentDetails> getAssessmentDetails(String username, String title) {
		if (username==null)
			return null;
		List<AssessmentDetails> dfstests=assessmentDetailsDao.getAssessmentDetails(username, title);
		if (dfstests==null) return null;
		return dfstests;
	}

	public void saveOrUpdate(AssessmentDetails dfstest) {
		assessmentDetailsDao.saveOrUpdate(dfstest);
	}
	public void delete(int id){
		assessmentDetailsDao.delete(id);
	}
}
