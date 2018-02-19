package mainclass;

import java.sql.SQLException;

import com.revature.trms.service.EmployeeService;

public class mainclass {

	public static void main(String[] args) {
		String username = "kamel";
		String password = "kamel";
		try {
			if (EmployeeService.authentication(username, password)!=null) {
				System.out.println("<p>"+EmployeeService.authentication(username, password)+"</p>");
				
			}
		} catch (SQLException e) {
			
			System.out.println("<p>"+e.getMessage()+"</p>");
		}
	

	}

}
