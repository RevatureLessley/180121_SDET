package com.userend;

import java.sql.SQLException;

import com.dao.BankAccountDao;
import com.dao.BankAccountDaoImp;;

public class BankAccountService {
	
	public static void addAccount(String FirstName, String LastName, String UserName, String Password){
		BankAccountDao dao = new BankAccountDaoImp();
		BankAccount user = new BankAccount (FirstName, LastName, UserName, Password, 0, 0);
		dao.insertBankAccount(user);
	}

	
	public static void Login(String UserName, String Password){
		BankAccountDao dao = new BankAccountDaoImp();
		try {
			BankAccount user = dao.selectBankAccountByUN(UserName, Password);
			if (user != null) {
				if (user.getAdminApproval() != 1) {
					System.out.println(" Need Admin Approval");
				}else {
				UserMenu menu = new UserMenu();
				menu.showMenu(user);
				}
			}
			else 
			{ System.out.println("Wrong username or password"); }
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void Withdraw(BankAccount user, int withdrawAmt){
		BankAccountDao dao = new BankAccountDaoImp();
		dao.withdrawFromAccount(user, withdrawAmt);
		
	}
	
	public static void Deposit(BankAccount user, int depositAmt){
		BankAccountDao dao = new BankAccountDaoImp();
		dao.depositToAccount(user, depositAmt);
		
	}
	
	public static int getBalance(BankAccount user) throws SQLException {
		BankAccountDao dao = new BankAccountDaoImp();
		int balance = 0;
		balance = dao.getAccountBalance(user);
		
		return balance;
	}
	
}
