package com.services;

import java.sql.SQLException;

import com.dao.AssociateAccountDao;
import com.dao.AssociateAccountDaoImp;
import com.userend.AssociateAccount;

public class LoginAssociate {
	
	public static AssociateAccount validate(String usernamein, String passwordin) throws SQLException{
	
		AssociateAccountDao dao = new AssociateAccountDaoImp();
		
		AssociateAccount user = new AssociateAccount(usernamein, passwordin);
		
		if(dao.getUN_PW(user)!= null){
			
			return dao.getUN_PW(user);
		
		}else{
			
			return null;
		}
		
	}
}
