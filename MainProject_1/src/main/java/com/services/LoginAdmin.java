package com.services;

import java.sql.SQLException;

import com.adminend.BenCoAccount;
import com.adminend.DeptHeadAccount;
import com.adminend.SupervisorAccount;
import com.dao.AdminAccountDao;
import com.dao.AdminAccountDaoImp;

public class LoginAdmin {

	public static SupervisorAccount validateSuper(String usernamein, String passwordin) throws SQLException{
			
		AdminAccountDao dao = new AdminAccountDaoImp();
			
			SupervisorAccount visor = new SupervisorAccount(usernamein, passwordin);
			
			if(dao.getUN_PW(visor)!= null){
				
				return dao.getUN_PW(visor);
			
			}else{
				
				return null;
			}
			
		
	}

	public static DeptHeadAccount validateDept(String usernamein, String passwordin) throws SQLException {
		
		AdminAccountDao dao = new AdminAccountDaoImp();
		
		DeptHeadAccount dept = new DeptHeadAccount(usernamein, passwordin);
		
		if(dao.getUN_PW(dept)!= null){
			
			return dao.getUN_PW(dept);
		
		}else{
			
			return null;
		}
	}

	public static BenCoAccount validateBenCo(String usernamein, String passwordin) throws SQLException{
		
		AdminAccountDao dao = new AdminAccountDaoImp();
		
		BenCoAccount benco = new BenCoAccount(usernamein, passwordin);
		
		if(dao.getUN_PW(benco)!= null){
			
			return dao.getUN_PW(benco);
		
		}else{
			
			return null;
		}
	}

}
