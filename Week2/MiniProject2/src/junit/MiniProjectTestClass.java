package junit;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import beans.User;
import functions.AdminFunctions;
import functions.CreateUser;
import functions.FetchUsers;
import functions.SerializeAndDeserialize;
import functions.UserLogIn;

public class MiniProjectTestClass {
	
	static Scanner scan;
	static FileOutputStream fos;
	static ObjectOutputStream oos;
	
	private static final String TESTUSERS = "TestUsers.txt";
	
	final static Logger logger = Logger.getLogger(MiniProjectTestClass.class);
	
	static List<User> users;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		scan = new Scanner(System.in);
		
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		scan.close();
	}

	@Before
	public void setUp() throws Exception {
		users = new ArrayList<>();
		User u1 = new User("Cats" , "9 lives" , 5 ,1 );
		User u2 = new User("USA" , "USA" , 1000 ,2);
		User u3 = new User("Hairy" , "Must Shave"  , 5,3 );
		User u4 = new User("Blob" , "Eat less" , 99 ,4);
		User u5 = new User("Food" , "yumm" ,25 ,5);
				
		users.add(u1);
		users.add(u2);
		users.add(u3);
		users.add(u4);
		users.add(u5);
		
		try {
			FileOutputStream fos = new FileOutputStream(TESTUSERS);
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			oos.writeObject(users);

			fos.close();
			oos.close();

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
		} catch (IOException e) {
			System.out.println("Error initializing stream");
		}

	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testDeleteUser() {
		User testUser = users.get(1);
		
		assertEquals(true, users.contains(testUser)); //list contains user
		AdminFunctions.deleteUser(scan, TESTUSERS, testUser.getUserName());
		
		List<User> users = SerializeAndDeserialize.deserializeUsers(TESTUSERS);
		assertEquals(false,users.contains(testUser));//list doesn't contain user
	}
	
	@Test
	@Ignore
	public void testChangeAmount() {
		assertEquals(50, UserLogIn.changeAmount(users, new User ("Susan", "Beans", 30), scan, TESTUSERS), 20);
	}
	
	
	@Test
	public void testUniqueID() {
		assertEquals(6, CreateUser.uniqueId(1,TESTUSERS));		

	}
	
	@Test
	public void testUsernameList() {
		List<String> testCodeList = new ArrayList<>();
		for(User u: users) {
			testCodeList.add(u.getUserName());
		}
		List<String> productionCodeList = FetchUsers.usernameList(TESTUSERS);
		for (int i = 0; i < testCodeList.size();i++) {
			assertEquals(productionCodeList.get(i),testCodeList.get(i));			
		}
		assertEquals(productionCodeList.size(),testCodeList.size());
	}
	
	@Test
	public void testAuthenticateUser() {
		
		assertEquals(false,users.get(0).isApproved());
		
		AdminFunctions.authenticateUser(scan, TESTUSERS, 0);
		
		List<User> users = SerializeAndDeserialize.deserializeUsers(TESTUSERS);
		assertEquals(true,users.get(0).isApproved());		
	}
	

}
