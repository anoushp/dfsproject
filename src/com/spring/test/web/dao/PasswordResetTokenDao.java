package com.spring.test.web.dao;

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
@Component("passwordResetTokenDao")
public class PasswordResetTokenDao {
	@Autowired
	private SessionFactory sessionFactory;

	public Session session() {
		return sessionFactory.getCurrentSession();
	}

	public PasswordResetToken findByToken(String token) {
		Criteria crit = session().createCriteria(PasswordResetToken.class);
		crit.add(Restrictions.eq("token", token));
		return (PasswordResetToken) crit.uniqueResult();
	}
	public void save(PasswordResetToken tok){
		session().save(tok);
	}
	public boolean delete(int id) {
		Query query=session().createQuery("delete from PasswordResetToken where id=:id");
		query.setLong("id",id);
		return (query.executeUpdate()==1);
		
	}

}
