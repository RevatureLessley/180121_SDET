package SAS.DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import SAS.Beans.Worker;
import SAS.Utils.SASConnect;

public interface WorkerDao {

	public  Worker getWorkerById(int wid);
	public boolean writeMessage(int rid, String message);
	public Worker getDepHead(int wid);
	public  List<Worker> getAllWorkers();
	public  int getEmployeeType(int wid);
	public  boolean updateAval(int wid, double cost);
	public  void addWorker(Worker w);
}


/*
"UPDATE claimsRe SET approv_stat = "+  cost + "
"UPDATE claimsRe SET claim_approved=" +  cost + "
"UPDATE claimsRe SET claim_grade =" +  cost + "
"UPDATE claimsRe SET claim_passgrade=" +  cost + ""
*/

