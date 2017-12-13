package com.spring.test.web.dao;

import java.util.Collections;
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
@Component("attributesDao")
public class AttributesDao {
	@Autowired
	private SessionFactory sessionFactory;
	public Session session(){
    	return sessionFactory.getCurrentSession();
    }
	@Transactional
	public void create(Attribute attribute) {
		
		session().persist(attribute);
		
	}
	@SuppressWarnings("unchecked")
	public List<Attribute> getAllAttributes() {
		List<Attribute> attrs =session().createQuery("from Attribute").list();
		for (Attribute attr:attrs){
			if (attr!=null){
				Hibernate.initialize(attr.getKpaCategories());
			}
		}
		Collections.sort(attrs, new AttributeSortByCategoryComparator());
		return attrs;
		
	}
	public Attribute getAttribute(int id) {
		Criteria crit=session().createCriteria(Attribute.class);
		crit.add(Restrictions.idEq(id));
		Attribute attr=(Attribute)crit.uniqueResult();
		if (attr!=null){
			Hibernate.initialize(attr.getKpaCategories());
		}
		return attr;
		}
	public Attribute getAttribute(String name){
		
		Criteria crit=session().createCriteria(Attribute.class);
		crit.add(Restrictions.eq("name", name));
		Attribute attr=(Attribute)crit.uniqueResult();
		if (attr!=null){
			Hibernate.initialize(attr.getKpaCategories());
		}
		return attr;
		
	}
	public void saveOrUpdate(Attribute ind) {
		session().persist(ind);
	
	}
	public boolean delete(int id) {
		Criteria crit=session().createCriteria(Attribute.class);
		crit.add(Restrictions.idEq(id));
		Attribute attr=(Attribute)crit.uniqueResult();
		if (attr!=null){
			Hibernate.initialize(attr.getIndicators());
		}
		Query query=session().createQuery("delete from Attribute where id=:id");
		query.setLong("id",id);
		return (query.executeUpdate()==1);
		/*MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id=:id", params) == 1;*/
	}

}
