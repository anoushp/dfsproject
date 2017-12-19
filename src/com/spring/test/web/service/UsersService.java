package com.spring.test.web.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.Message;
import com.spring.test.web.dao.MessagesDao;
import com.spring.test.web.dao.User;
import com.spring.test.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	@Autowired
	private UsersDao usersDao;

	@Autowired
	private MessagesDao messagesDao;

	/*
	 * @Autowired public void setUsersDao(UsersDao offersDao) { this.usersDao =
	 * offersDao; }
	 */

	public void create(User user) {
		usersDao.create(user);

	}

	public boolean exists(String username) {

		return usersDao.exists(username);
	}

	@Secured("ROLE_ADMIN")
	public List<User> getAllUsers() {

		return usersDao.getAllUsers();
	}

	public void sendMessage(Message message) {
		messagesDao.saveOrUpdate(message);
	}
	
	public void updatePassword(String password, String username){
		usersDao.updatePassword(password, username);
	}
	public User getUser(String username){
		return usersDao.getUser(username);
		
	}
	public User findByEmail(String email){
		return usersDao.findByEmail(email);
		
	}

	public List<Message> getMessages(String username) {
		return messagesDao.getMessages(username);
	}

	public void saveOrUpdate(User user) {
		usersDao.updateUser(user);
		
	}
}
