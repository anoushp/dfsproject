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
@Component("kpaCategoryDao")
public class KPACategoryDao {
	@Autowired
	private SessionFactory sessionFactory;
	 public Session session(){
	    	return sessionFactory.getCurrentSession();
	    }


	   @SuppressWarnings("unchecked")
		public List<KPACategory> getKPACategory(){
		
			Criteria crit=session().createCriteria(KPACategory.class);
			
			
			return (List<KPACategory>)crit.list();
			
		}
	   @SuppressWarnings("unchecked")
	 		public KPACategory getKPACategoryId(String category){
	 		
	 			Criteria crit=session().createCriteria(KPACategory.class);
	 			crit.add(Restrictions.eq("category", category));
	 			
	 			return (KPACategory)crit.uniqueResult();
	 			
	 		}
		
		
		public void saveOrUpdate(KPACategory kpa_category) {
			session().saveOrUpdate(kpa_category);
		}
			
		
		public boolean delete(int id) {
			Query query=session().createQuery("delete from KPACategory where id=:id");
			query.setLong("id",id);
			return (query.executeUpdate()==1);
			
		}


		public KPACategory findById(int id) {
			Criteria crit=session().createCriteria(KPACategory.class);
			crit.add(Restrictions.idEq(id));
			KPACategory cat=(KPACategory)crit.uniqueResult();
			return cat;
		}

		
}
