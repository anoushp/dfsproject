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
	
	private User user1 = new User("johnwpurcell", "hellothere", "John Purcell",
			true, "ROLE_USER","john@caveofprogramming.com");
	private User user2 = new User("richardhannay",  "the39steps","Richard Hannay",
			true, "ROLE_ADMIN", "richard@caveofprogramming.com");
	private User user3 = new User("suetheviolinist",  "iloveviolins","Sue Black",
			true, "ROLE_USER", "sue@caveofprogramming.com");
	private User user4 = new User("rogerblake", "liberator","Rog Blake"
			, false, "user","rog@caveofprogramming.com");

	@Before
	public void init() {
		JdbcTemplate jdbc = new JdbcTemplate(dataSource);

		jdbc.execute("delete from offers");
		jdbc.execute("delete from users");
	}
	
	@Test
	public void testCreateRetrieve() {
		usersDao.create(user1);
		
		List<User> users1 = usersDao.getAllUsers();
		
		assertEquals("One user should have been created and retrieved", 1, users1.size());
		
		assertEquals("Inserted user should match retrieved", user1, users1.get(0));
		
		usersDao.create(user2);
		usersDao.create(user3);
		usersDao.create(user4);
		
		List<User> users2 = usersDao.getAllUsers();
		
		assertEquals("Should be four retrieved users.", 4, users2.size());
	}

	//todo
	
	public void testUsers(){
		User user=new User("aaa", "hello", "Anush", true, "user", "aa@tt.com");
		
	    usersDao.create(user);
		List<User> users = usersDao.getAllUsers();
		assertEquals("number of users should be 1", 1, users.size());
		assertTrue("user should exists",usersDao.exists(user.getUsername()));
		assertFalse("user should not exists",usersDao.exists("GRRRRR"));
		assertEquals("created user should be identical to retrieved user", user, users.get(0) );
	}

}
