import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;
public class Bank {

String url = "jdbc:oracle:thin:@localhost:8080:xe";
String user = "system";
String password = "Zwinky12345";
		
private Connection connection() {
	Connection connection = null;;
	try {
		connection = DriverManager.getConnection(url, user, password);
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return connection;
	
}
int addAccount(String firstname, String lastname, String SSN, String DOB, AccountType AccountType, double balance) {
	int userId = -1;
	int accountId = -1;
	Connection connection = connection();
	try {
		connection.setAutoCommit(false);
		//add user
		String addUserSql = "insert into users (firstname, lastname, SSN) values(1, 2, 3)";
		PreparedStatement addUser = connection.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
		addUser.setString(1, firstname);
		addUser.setString(2, lastname);
		addUser.setString(3, SSN);
		addUser.setString(4, DOB);
		addUser.executeUpdate();
		ResultSet addUserResults = addUser.getGeneratedKeys();
		if (addUserResults.next()) {
			userId = addUserResults.getInt(1);
			
		}
		//add Account
		String addAccountSql = "insert into accounts(Type, balance)values(?, ?)";
		PreparedStatement addAccount = connection.prepareStatement(addAccountSql, Statement.RETURN_GENERATED_KEYS);
		addAccount.setString(1, AccountType.name());
		addAccount.setDouble(2, balance);
		addAccount.executeUpdate();
		ResultSet addAccountResults = addAccount.getGeneratedKeys();
		
		//link user to account
		if (userId > 0 && accountId > 0 ) {
			String linkAccountSql = "insert into mappings (UserId, AccountId) values (?, ?";
			PreparedStatement linkAccount = connection.prepareStatement(linkAccountSql, Statement.RETURN_GENERATED_KEYS);
			linkAccount.setInt(1, userId);
			linkAccount.setInt(2, accountId);
			linkAccount.executeUpdate();
			connection.commit();
		}else {
			connection.rollback();
		}
		connection.close();
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return accountId;
	
	
	
}
















	}

