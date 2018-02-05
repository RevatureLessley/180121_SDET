package com.bank.MockitoJunit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import com.bank.beans.User;
import com.bank.dao.UserDao;
import com.bank.dao.UserDaoImp;
import com.bank.services.UserService;

@RunWith(MockitoJUnitRunner.class)
public class MethodTest {

	@Mock
	private List<String[]> mockList;
	

	@Test
	// Insert Test User
	public void InsetUsertest() {
		UserDao d = new UserDaoImp();
		User u = new User("Test","Test",null);
		assertEquals(true, d.insertUser(u));
	}
	
	@Test
	// NonUser test
	//Mockito for NonUser login
	public void NonUserTest() {
		String[] s = {"Test", "Test"};
		when(mockList.get(0)).thenReturn(s);
		try {
			assertEquals(2, UserService.getUserStatus(mockList.get(0)[0],mockList.get(0)[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	@Test
	// User test
	//Mockito for User login
	public void UserTest() {
		String[] s = {"Harish", "Kumar"};
		when(mockList.get(0)).thenReturn(s);
		try {
			assertEquals(1, UserService.getUserStatus(mockList.get(0)[0],mockList.get(0)[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	@Test
	// SuperUser test
	//Mockito for SuperUser login
	public void superUserTest() {
		String[] s = {"Admin", "Admin"};
		when(mockList.get(0)).thenReturn(s);
		try {
			assertEquals(0, UserService.getUserStatus(mockList.get(0)[0],mockList.get(0)[1]));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Test
	// Delete Test User
	public void DeleteUsertest() {
		UserDao d = new UserDaoImp();
		User u = new User("Test","Test",null);
		assertFalse(!d.deleteUser(u));
	}

}
