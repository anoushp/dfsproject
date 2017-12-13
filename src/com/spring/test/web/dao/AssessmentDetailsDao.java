package com.spring.test.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
@Component("assessmentDetailsDao")
public class AssessmentDetailsDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	@Transactional
	public void create(Assessment assessment) {

		session().persist(assessment);

	}

	public AssessmentDetails getAssessmentDetail(int id) {
		Criteria crit = session().createCriteria(AssessmentDetails.class);

		crit.add(Restrictions.idEq(id));
		return (AssessmentDetails) crit.uniqueResult();

	}

	@SuppressWarnings("unchecked")
	public List<AssessmentDetails> getAssessmentDetails(String username, String title) {
		Criteria crit = session().createCriteria(AssessmentDetails.class);
		crit.createAlias("assessment", "a");
		crit.add(Restrictions.eq("a.id.username", username));
		crit.add(Restrictions.eq("a.id.title", title));
		return crit.list();

	}

	public void saveOrUpdate(AssessmentDetails dfstest) {
		session().saveOrUpdate(dfstest);
	}

	public boolean delete(int id) {

		Query query = session().createQuery("delete from AssessmentDetails where id=:id");
		query.setLong("id", id);
		return (query.executeUpdate() == 1);
	}
}
