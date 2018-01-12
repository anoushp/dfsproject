package com.spring.test.web.dao;

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
@Component("assessmentCompanyDao")
public class AssessmentCompanyDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Session session(){
    	return sessionFactory.getCurrentSession();
    }
	@Transactional
	public void create(AssessmentCompany acompany) {
		
		session().persist(acompany);
		
	}
	public AssessmentCompany getAssessmentCompany(int id) {
		Criteria crit=session().createCriteria(AssessmentCompany.class);
		crit.add(Restrictions.idEq(id));
		AssessmentCompany ac=(AssessmentCompany)crit.uniqueResult();
		if (ac!=null){
			Hibernate.initialize(ac.getOpSectors());
		}
		return ac;
		}
	
	public void saveOrUpdate(AssessmentCompany ac) {
		session().saveOrUpdate(ac);
	
	}
	public boolean delete(int id) {
		Criteria crit=session().createCriteria(AssessmentCompany.class);
		crit.add(Restrictions.idEq(id));
		AssessmentCompany ac=(AssessmentCompany)crit.uniqueResult();
		
		Query query=session().createQuery("delete from AssessmentCompany where id=:id");
		query.setLong("id",id);
		return (query.executeUpdate()==1);
		/*MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id=:id", params) == 1;*/
	}

}
