package com.adminend;

import java.sql.SQLException;
import java.util.List;

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
			{ System.out.println("WRONG PASSWORD"); }
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public static void AproveAccount(String username){
		AdminAccountDao dao = new AdminAccountDaoImp();
		dao.ApproveUserAccount(username);
		
	}
	
	
	public static void displayAllTransactions(){
		AdminAccountDao dao = new AdminAccountDaoImp();		
		List<Transactions> trans = dao.getAllTransactions();
		
		System.out.println("		----- LIST OF ALL TRANSACTIONS ----- \n");
		
		System.out.println("Transaction ID    |" + " Account ID    |" + " UserName    |" + " TransactionType    |" + " Amount    |");
		for(Transactions t: trans){
			System.out.println(t.getTransaction_ID() + "    	  | " + t.getAccount_ID() + "    	  | " 
							   + t.getUserName() + "   | " + 
								t.getTransaction_Type() + "    	     | " + 
							   t.getAmount()+ "    	 | ");
		
		}
	}
}
