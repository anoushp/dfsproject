package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.Dfstest;
import com.spring.test.web.dao.DfstestsDao;


@Service("dfstestsService")
public class DfstestsService {
	private DfstestsDao dfstestsDao;
	
	@Autowired
	public void setOffersDao(DfstestsDao dfstestsDao) {
		this.dfstestsDao = dfstestsDao;
	}

	
    @Secured({"ROLE_USER","ROLE_ADMIN"})
	public void create(Dfstest dfstest) {
		dfstestsDao.saveOrUpdate(dfstest);
		
	}

	public boolean hasDfstest(String name) {
		if (name==null) return false;
		List<Dfstest> dfstests=dfstestsDao.getDfstests(name);
		if (dfstests==null){
			return false;
		}
		return true;
	}

	public List<Dfstest> getDfstests(String username) {
		if (username==null)
			return null;
		List<Dfstest> dfstests=dfstestsDao.getDfstests(username);
		if (dfstests==null) return null;
		return dfstests;
	}

	public void saveOrUpdate(Dfstest dfstest) {
		 dfstestsDao.saveOrUpdate(dfstest);
	}

}
