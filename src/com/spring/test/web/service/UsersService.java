package com.spring.test.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.User;
import com.spring.test.web.dao.UsersDao;

@Service("usersService")
public class UsersService {
	private UsersDao usersDao;
	
	@Autowired
	public void setUsersDao(UsersDao offersDao) {
		this.usersDao = offersDao;
	}



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

}
