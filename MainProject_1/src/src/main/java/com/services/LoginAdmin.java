package com.services;

import java.sql.SQLException;

import com.adminend.SupervisorAccount;
import com.dao.SupervisorAccountDao;
import com.dao.SupervisorAccountDaoImp;

public class LoginAdmin {

	public static SupervisorAccount validateSuper(String usernamein, String passwordin) throws SQLException{
			
			SupervisorAccountDao dao = new SupervisorAccountDaoImp();
			
			SupervisorAccount visor = new SupervisorAccount(usernamein, passwordin);
			
			if(dao.getUN_PW(visor)!= null){
				
				return dao.getUN_PW(visor);
			
			}else{
				
				return null;
			}
			
		
	}

}
