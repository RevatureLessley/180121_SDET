import java.util.*;
public class Bank {

	ArrayList<User> users = new ArrayList<User>();

	public static void addUser(User user) {
		users.add(user);
	}

    ArrayList<User> getUser() {
		// TODO Auto-generated method stub
		return null;
	}

	User getUser(int account) {
		return users.get(account);
	}
	
	ArrayList<User> getusers() {
		return users;
	}

	
		
	}

