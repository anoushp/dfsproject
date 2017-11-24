package com.spring.test.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("dfstestsDao")
public class DfstestsDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	
	public Dfstest getDfstest(int id) {
		Criteria crit=session().createCriteria(Dfstest.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.idEq(id));
		return (Dfstest)crit.uniqueResult();
	
	}
	@SuppressWarnings("unchecked")
	public List<Dfstest> getDfstests(String username) {
		Criteria crit=session().createCriteria(Dfstest.class);
		crit.createAlias("user", "u");
		crit.add(Restrictions.eq("u.username", username));
		return crit.list();
	
	}
	public void saveOrUpdate(Dfstest dfstest) {
		session().saveOrUpdate(dfstest);
	}
}
