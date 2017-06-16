package com.spring.test.web.test.tests;

import static org.junit.Assert.*;

import java.util.List;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.spring.test.web.dao.User;
import com.spring.test.web.dao.UsersDao;

@ActiveProfiles("dev")
@ContextConfiguration(locations={"classpath:com/spring/test/web/config/dao-context.xml",
"classpath:com/spring/test/web/config/security-context.xml",
"classpath:com/spring/test/web/test/config/datasource.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class UserDaoTests {
	
	@Autowired
	private UsersDao usersDao;
	
	@Autowired
	private DataSource dataSource;
	
	@Before
	public void init(){
		JdbcTemplate jdbc=new JdbcTemplate(dataSource);
		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
		
		
		
	}
	@Test
	public void testUsers(){
		User user=new User("aaa", "hello", "Anush", true, "user", "aa@tt.com");
		
		assertTrue("user creation should return true", usersDao.create(user));
		List<User> users = usersDao.getAllUsers();
		assertEquals("number of users should be 1", 1, users.size());
		assertTrue("user should exists",usersDao.exists(user.getUsername()));
		assertEquals("created user should be identical to retrieved user", user, users.get(0) );
	}

}
