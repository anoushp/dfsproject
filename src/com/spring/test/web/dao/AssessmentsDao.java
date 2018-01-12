package com.spring.test.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
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
@Component("assessmentsDao")
public class AssessmentsDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Session session(){
		return sessionFactory.getCurrentSession();
	}
	@Transactional
	public void create(Assessment assessment) {

		session().persist(assessment);

	}
	@Transactional
	public void update(Assessment assessment) {

		session().saveOrUpdate(assessment);

	}
	@SuppressWarnings("unchecked")
	public List<Assessment> getAssessments(String name){

		Criteria crit=session().createCriteria(Assessment.class);

		crit.add(Restrictions.eq("id.username", name));

		return crit.list();

	}
	@SuppressWarnings("unchecked")
	public List<Assessment> getAllAssessments(){

		List<Assessment> assessments =session().createQuery("from Assessment").list();
		
		return assessments;

	}
	@SuppressWarnings("unchecked")
	public Assessment getAssessment(String name,String title){

		Criteria crit=session().createCriteria(Assessment.class);

		crit.add(Restrictions.eq("id.username", name));
		crit.add(Restrictions.eq("id.title", title));

		return (Assessment)crit.uniqueResult();

	}
	public void saveOrUpdate(Assessment assessment) {
		session().saveOrUpdate(assessment);

	}
	public boolean delete(String username, String title) {
		Query query=session().createQuery("delete from Assessment a where a.id.username=:username and a.id.title=:title");
		query.setString("username", username);
		query.setString("title", title);
		
		return (query.executeUpdate()==1);
		/*MapSqlParameterSource params = new MapSqlParameterSource("id", id);

		return jdbc.update("delete from offers where id=:id", params) == 1;*/
	}
}
