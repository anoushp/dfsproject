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
@Component("indicatorsDao")
public class IndicatorsDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session session(){
		return sessionFactory.getCurrentSession();
	}

	
	@SuppressWarnings("unchecked")
	public List<Indicator> getIndicators() {
		Criteria crit=session().createCriteria(Indicator.class);
		return crit.list();
	}
	@SuppressWarnings("unchecked")
	public List<Indicator> getIndicators(Attribute attr) {

		Criteria crit=session().createCriteria(Indicator.class);
		crit.createAlias("attribute", "a");
		crit.add(Restrictions.eq("a.id", attr.getId()));
		return crit.list();

	}
	@SuppressWarnings("unchecked")
	public List<Indicator> getIndicators(int id) {

		Criteria crit=session().createCriteria(Indicator.class);
		crit.createAlias("attribute", "a");
		crit.add(Restrictions.eq("a.id", id));
		
		return crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();

	}
	public void saveOrUpdate(Indicator ind) {
		session().saveOrUpdate(ind);
	}
	public boolean delete(int id) {
		Query query=session().createQuery("delete from Indicator where id=:id");
		query.setLong("id",id);
		return (query.executeUpdate()==1);

	}
	public Indicator getIndicator(int id) {
		Criteria crit=session().createCriteria(Indicator.class);

		crit.add(Restrictions.idEq(id));
		Indicator ind=(Indicator)crit.uniqueResult();
		if (ind!=null){
		Attribute attr=ind.getAttribute();
		if (attr!=null){
			Hibernate.initialize(attr.getKpaCategories());
		}
		}
		return ind;

	}
	public boolean hasIndicators(int attr_id){
		Criteria crit=session().createCriteria(Indicator.class);
		crit.createAlias("attribute", "a");
		crit.add(Restrictions.eq("a.id", attr_id));
		if (crit.list().size()==0)
			return false;
		else return true;
		
		
		
	}

}
