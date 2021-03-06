package com.spring.test.web.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
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
		crit.addOrder(Order.desc("score"));
        List<Assessment> aslist=crit.list();
        for (Assessment assessment:aslist){
			 AssessmentCompany ac=assessment.getCompany();
			if (ac!=null){
				Hibernate.initialize(ac.getOpSectors());
			}
		}
		return aslist;

	}
	@SuppressWarnings("unchecked")
	public List<Assessment> getAllAssessments(){

		List<Assessment> assessments =session().createQuery("from Assessment a order by a.ascore DESC").list();
		for (Assessment assessment:assessments){
			 AssessmentCompany ac=assessment.getCompany();
			if (ac!=null){
				Hibernate.initialize(ac.getOpSectors());
			}
		}
		return assessments;

	}
	@SuppressWarnings("unchecked")
	public Assessment getAssessment(String name,String title){

		Criteria crit=session().createCriteria(Assessment.class);

		crit.add(Restrictions.eq("id.username", name));
		crit.add(Restrictions.eq("id.title", title));
        Assessment assessment=(Assessment)crit.uniqueResult();
        AssessmentCompany ac=assessment.getCompany();
        if (ac!=null){
			Hibernate.initialize(ac.getOpSectors());
		}
		return assessment;

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
