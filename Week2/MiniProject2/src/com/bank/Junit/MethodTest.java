package com.bank.Junit;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import com.bank.beans.User;
import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImp;
import com.bank.services.UserService;

public class MethodTest {
		
	
	@Test
	// Insert Test User
	public void InsetUsertest() {
		UserDao d = new UserDaoImp();
		User u = new User("Test","Test",null);
		assertEquals(true, d.insertUser(u));
	}
	
	@Test
	// Administrator Login
	public void getUserStatustest() {
		String userName = "Admin";
		String password = "Admin";
		try {
			assertEquals(0, UserService.getUserStatus(userName, password));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// Delete Test User
	public void DeleteUsertest() {
		UserDao d = new UserDaoImp();
		User u = new User("Test","Test",null);
		assertEquals(true, d.deleteUser(u));
	}

}
