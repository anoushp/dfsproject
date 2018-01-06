package com.spring.test.web.dao;


import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
@Component("usersDao")
public class UsersDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
    public Session session(){
    	return sessionFactory.getCurrentSession();
    }

	@Transactional
	public void create(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		/*MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("username", user.getUsername());
		params.addValue("password", passwordEncoder.encode(user.getPassword()));
		params.addValue("name", user.getName());
		params.addValue("email", user.getEmail());
		params.addValue("enabled", user.isEnabled());
		params.addValue("authority", user.getAuthority());
		return jdbc.update("insert into users (username, authority, name, password, enabled, email) values (:username, :authority, :name, :password, :enabled, :email)", params)==1*/
		session().save(user);
		
	}


	public boolean exists(String username) {
		
		return (getUser(username)!=null);
		
	}

@SuppressWarnings("unchecked")
	public List<User> getAllUsers() {
		return session().createQuery("from User").list();
		//return jdbc.query("select * from users", BeanPropertyRowMapper.newInstance(User.class));
	}
	

public User getUser(String username){
	
	Criteria crit=session().createCriteria(User.class);
	crit.add(Restrictions.eq("username", username));
	//or crit.add(Restrictions.ideq(username));
	
	return (User)crit.uniqueResult();
	
}
public User findByEmail(String email){
	
	Criteria crit=session().createCriteria(User.class);
	crit.add(Restrictions.eq("email", email));
	
	
	return (User)crit.uniqueResult();
	
}
public void updateUser(User user) {
/*	Query q = session().createQuery("from User where username = :username ");
	q.setParameter("username", user.getUsername());
	User u = (User)q.list().get(0);

	u.setName(user.getName());
	u.setEmail(user.getEmail());*/
	
	session().saveOrUpdate(user);
}
public void updatePassword(String password, String username) {
	Query q = session().createQuery("from User where username = :username ");
	q.setParameter("username", username);
	User u = (User)q.list().get(0);

	u.setPassword(passwordEncoder.encode(password));
	
	
	session().update(u);
}
public boolean delete(String username) {
	Query query=session().createQuery("delete from User where username = :username");
	query.setParameter("username", username);
	return (query.executeUpdate()==1);
	
}
}

