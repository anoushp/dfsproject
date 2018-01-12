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
@Component("operationSectorDao")
public class OperationSectorDao {
	@Autowired
	private SessionFactory sessionFactory;
	 public Session session(){
	    	return sessionFactory.getCurrentSession();
	    }


	   @SuppressWarnings("unchecked")
		public List<OperationSector> getOperationSector(){
		
			Criteria crit=session().createCriteria(OperationSector.class);
			
			
			return (List<OperationSector>)crit.list();
			
		}
	   @SuppressWarnings("unchecked")
	 		public OperationSector getOperationSectorId(String sector){
	 		
	 			Criteria crit=session().createCriteria(OperationSector.class);
	 			crit.add(Restrictions.eq("sector", sector));
	 			
	 			return (OperationSector)crit.uniqueResult();
	 			
	 		}
		
		
		public void saveOrUpdate(OperationSector operation_sector) {
			session().saveOrUpdate(operation_sector);
		}
			
		
		public boolean delete(int id) {
			Query query=session().createQuery("delete from OperationSector where id=:id");
			query.setLong("id",id);
			return (query.executeUpdate()==1);
			
		}


		public OperationSector findById(int id) {
			Criteria crit=session().createCriteria(OperationSector.class);
			crit.add(Restrictions.idEq(id));
			OperationSector sector=(OperationSector)crit.uniqueResult();
			return sector;
		}
}
