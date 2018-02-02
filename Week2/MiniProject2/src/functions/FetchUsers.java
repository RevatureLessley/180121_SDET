package functions;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import beans.User;

public class FetchUsers {

	final static Logger logger = Logger.getLogger(CreateUser.class);
		
	//returns a List of String usernames
	public static List<String> usernameList(String textfile){
		
		List<String> usernames = new ArrayList<String>();
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);
		
		for (User u : users) {
			usernames.add(u.getUserName());
		}				
		logger.debug("Returned username list");
		return usernames;
	}
	
	//prints all Users
	public static void showAll(String textfile) {
		List<User> users = SerializeAndDeserialize.deserializeUsers(textfile);

		for (User u : users) {
			System.out.println(u);
		}
	}
}
