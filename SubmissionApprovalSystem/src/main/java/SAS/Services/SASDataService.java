package SAS.Services;
import SAS.*;
import java.sql.*;
public class SASDataService {

public static void insertWorker (String fname, String lname, String username, String password, String email, int w_id, int superv, int r_aval, int depId, int admin_stat, int amount) {
		

	}
	w_id, superv, dep_hed, r_aval, depId, admin_stat);
}
	

	
public static int insertClaim(int claimreid, int claim_event_id, int claim_cost, int claim_projreimb, int claim_w_id,
			int grade_formid, int claim_grade, int claim_passgrade, String claim_reason, Date claim_date,
			Date timenowis, int claim_urgent, int approv_stat, int claim_approved) {


Claim claim = new Claim(claimreid, claim_event_id, claim_cost, claim_projreimb, claim_w_id, 
		grade_formid, claim_grade, claim_passgrade, claim_reason, claim_date, timenowis, claim_urgent, approv_stat, claim_approved);, 

return claimreid;
}
}
