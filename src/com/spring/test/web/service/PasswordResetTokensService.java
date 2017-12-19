package com.spring.test.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.spring.test.web.dao.PasswordResetToken;
import com.spring.test.web.dao.PasswordResetTokenDao;

@Service("passwordResetTokensService")
public class PasswordResetTokensService {
	private PasswordResetTokenDao passwordResetTokenDao;
	
	@Autowired
	public void setPasswordResetTokenDao(PasswordResetTokenDao passwordResetTokenDao){
		this.passwordResetTokenDao=passwordResetTokenDao;
	}
	
	public PasswordResetToken findByToken(String token){
		PasswordResetToken tok= passwordResetTokenDao.findByToken(token);
		return tok;
	}
	public void save(PasswordResetToken token){
		passwordResetTokenDao.save(token);
	}
	public void delete(int id){
		passwordResetTokenDao.delete(id);
	}

}
