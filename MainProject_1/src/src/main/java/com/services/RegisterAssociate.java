package com.services;

import java.sql.SQLException;

import com.dao.AssociateAccountDao;
import com.dao.AssociateAccountDaoImp;
import com.userend.AssociateAccount;

public class RegisterAssociate {

	public static boolean addAccount(Integer AssociateId, String FirstName, String LastName, String UserName, String Password, 
								 String Phone, String Email, Integer SupervisorRef) throws SQLException{
		
		AssociateAccountDao dao = new AssociateAccountDaoImp();
		AssociateAccount user = new AssociateAccount (AssociateId ,FirstName, LastName, UserName, Password, Phone, Email, SupervisorRef);
		
		
		if (dao.insertAssociateAccount(user) != 0)
			return true;
		else 
			return false;
		
	}
}
