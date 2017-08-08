package com.spring.test.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
@Repository
@Transactional
@Component("messagesDao")
public class MessagesDao {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}
    public Session session(){
    	return sessionFactory.getCurrentSession();
    }

   @SuppressWarnings("unchecked")
	public List<Message> getMessages() {
		Criteria crit=session().createCriteria(Message.class);
		
		return crit.list();

		//return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true", new OfferRowMapper());

		
	}
   @SuppressWarnings("unchecked")
	public List<Message> getMessages(String username) {

		//return jdbc.query("select * from offers, users where offers.username=users.username and users.enabled=true and offers.username=:username", new MapSqlParameterSource("username", username), new OfferRowMapper());
		Criteria crit=session().createCriteria(Message.class);
	
		crit.add(Restrictions.eq("username", username));
		return crit.list();
		
	}
	
	/*public void update(Offer offer) {
		session().update(offer);
		/*BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("update offers set text=:text where id=:id", params) == 1;*/
	//}*/
	
	public void saveOrUpdate(Message message) {
		System.out.println(message);
		session().saveOrUpdate(message);
		
		/*BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(offer);
		
		return jdbc.update("insert into offers (username, text) values (:username, :text)", params) == 1;*/
	}
	
//	@Transactional
/*	public int[] create(List<Offer> offers) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(offers.toArray());
		
		return jdbc.batchUpdate("insert into offers (username, text) values (:username, :text)", params);
	}
	*/
	public boolean delete(int id) {
		Query query=session().createQuery("delete from Message where id=:id");
		query.setLong("id",id);
		return (query.executeUpdate()==1);
		/*MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from offers where id=:id", params) == 1;*/
	}

	public Message getMessage(int id) {
		Criteria crit=session().createCriteria(Message.class);

		crit.add(Restrictions.idEq(id));
		return (Message)crit.uniqueResult();
		/*MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from offers, users where offers.username=users.username and users.enabled=true and id=:id", params,
				new OfferRowMapper());*/
		

				
	}
	
}

