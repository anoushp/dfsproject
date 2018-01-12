package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.test.web.dao.Assessment;
import com.spring.test.web.dao.AssessmentsDao;



@Service("assessmentsService")
@Transactional
public class AssessmentsService {
	private AssessmentsDao assessmentsDao;

	@Autowired
	public void setAssessmentsDao(AssessmentsDao assessmentsDao) {
		this.assessmentsDao = assessmentsDao;
	}

	public void create(Assessment assessment) {
		assessmentsDao.create(assessment);

	}

	public boolean hasAssessment(String name) {
		if (name==null) return false;
		List<Assessment> dfstests=assessmentsDao.getAssessments(name);
		if (dfstests==null){
			return false;
		}
		else if (dfstests.size()==0)
			return false;

	
		return true;
	}
	public boolean hasAssessment(String name, String title) {
		if (name==null) return false;
		Assessment dfstest=assessmentsDao.getAssessment(name, title);
		if (dfstest==null){
			return false;
		}
		
	
		return true;
	}

	public List<Assessment> getAssessments(String username) {
		if (username==null)
			return null;
		List<Assessment> dfstests=assessmentsDao.getAssessments(username);
		if (dfstests==null) return null;
		return dfstests;
	}
	
	public List<Assessment> getAllAssessments() {
		
		List<Assessment> dfstests=assessmentsDao.getAllAssessments();
		if (dfstests==null) return null;
		return dfstests;
	}
	
	public Assessment getAssessment(String username, String title) {
		if (username==null)
			return null;
		Assessment dfstest=assessmentsDao.getAssessment(username, title);
		if (dfstest==null) return null;
		return dfstest;
	}

	public void saveOrUpdate(Assessment dfstest) {
		assessmentsDao.saveOrUpdate(dfstest);
	}

	public void delete(String username, String title){
		assessmentsDao.delete(username, title);
	}

}
