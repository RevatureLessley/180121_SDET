package com.adminend;

import java.sql.SQLException;

import com.dao.AdminAccountDao;
import com.dao.AdminAccountDaoImp;



public class AdminAccountService {
	
	public static void Login(String Password){
		AdminAccountDao dao = new AdminAccountDaoImp();
		try {
			AdminAccount admin = dao.selectAdminAccountByUN(Password);
			if (admin != null) {
				AdminMenu menu = new AdminMenu();
				menu.showMenu(admin);
			}
			else 
			{ System.out.println("Wrong password"); }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void AproveAccount(String username){
		AdminAccountDao dao = new AdminAccountDaoImp();
		dao.ApproveUserAccount(username);
		
	}
}
