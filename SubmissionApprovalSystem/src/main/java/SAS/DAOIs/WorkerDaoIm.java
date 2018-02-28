package SAS.DAOIs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import SAS.Beans.Worker;
import SAS.DAOs.*;
import SAS.Utils.*;

public class WorkerDaoIm implements WorkerDao {

	public Worker getDepHead(int wid) {
	}

	public boolean writeMessage(int rid, String message) {
		PreparedStatement ps = null;
		try (Connection conn = SASConnect.getConnection()) {
			String sql = ("UPDATE messages SET message =" + message + "WHERE reim_id=" + rid + "");
			ps = conn.prepareStatement(sql);
			ps.executeUpdate();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	public List<Worker> getAllWorkers(){
	PreparedStatement ps = null;
	ResultSet rs = null;
	List<Worker> workers = new ArrayList<Worker>();

	try(Connection conn = SASConnect.getConnection()) {
		String query = "SELECT * FROM workers";
		ps = conn.prepareStatement(query);
		rs = ps.executeQuery();
		
		if(rs.next()) {
			workers.add(new Worker (
					rs.getString(1);
					rs.getString(2);
					rs.getString(5);

					rs.getString(3);
					rs.getString(4);
					rs.getInt(6);
					rs.getInt(7);
					rs.getInt(8);
					rs.getDouble(9);
					rs.getInt(10);
					rs.getInt(11);
					);
			
			/* 
			 * u_id NUMBER(9),
    w_id NUMBER(9),
    w_super NUMBER(9) NOT NULL,
    w_department NUMBER(8) NOT NULL,
    w_adminstat NUMBER(2) NOT NULL,
    w_avalreim NUMBER(9,2) DEFAULT 1000.00,
    w_fname VARCHAR2(30) NOT NULL,
    w_lname VARCHAR2(30) NOT NULL,
    w_email VARCHAR2(60),
    username VARCHAR2(40) NOT NULL,
    password VARCHAR2(20) NOT NULL,
    
			 */
			
			
			
			
		}
	} catch(SQLException e) {
		logger.error(e.getMessage());
	} finally {
		close(ps);
		close(rs);
	}

	return workers;
	}

	public Worker getWorkerById(int wid) {
		List<Worker> works = getAllWorkers();
		ListIterator<Worker> it = works.listIterator(0);

		while (it.hasNext()) {
			if (it.next().getWorker_id() == wid) {
				return it.previous();
			}
		}
		return null;
	}

	}


	public int checkWorker(String username, String password) {
		PreparedStatement ps = null;

		try (Connection conn = SASConnect.getConnection()) {
			System.out.println("Database connection successful");

			String sql = "SELECT w_id, username, password FROM workers WHERE username = '" + username + " AND PASSWD="
					+ password;

			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int wid = 0;

			if (rs.next()) {
				wid = rs.getInt("w_id");
				rs.close();
				ps.close();
				return wid;
			} else {
				System.out.println("User does not exist. Please check your username or password");
				rs.close();
				ps.close();
				return 0;

			}

		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();

		}
		return 0;
	}

	public void addWorker(Worker w) {
		PreparedStatement ps = null;

		try {
			Connection conn = SASConnect.getConnection();
			String sql = "INSERT INTO workers VALUES(?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			ps.setString(1, w.getfName());
			ps.setString(2, w.getlName());
			ps.setString(3, w.getuName());
			ps.setString(4, w.getpWord());
			ps.setString(5, w.getEmail());
			ps.setInt(6, w.getW_id());
			ps.setInt(7, w.getU_id());
			ps.setInt(8, w.getSuperv());
			ps.setDouble(9, w.getR_aval());
			ps.setInt(10, w.getDepId());
			ps.setInt(11, w.getAdmin_stat());
			ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
	}

	public int getEmployeeType(int wid) {
		PreparedStatement ps = null;

		try (Connection conn = SASConnect.getConnection()) {
			System.out.println("Database connection successful");
			;

			String sql = "SELECT  FROM workers WHERE EMPLOYEEID = " + eid;

			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			int typeid = 0;
			if (rs.next()) {
				typeid = rs.getInt("EMPLOYEETYPEID");
				rs.close();
				ps.close();
				return typeid;
			}
			return 0;
		} catch (Exception ex) {
			ex.getMessage();
			ex.printStackTrace();

		}
		return 0;
	}
	public boolean updateAval(int wid, double cost) {
		PreparedStatement ps = null;

		try (Connection conn = ConnectionUtil.getConnection()) {
			System.out.println("Database connection successful");

			String sql = "UPDATE workers SET w_avalreim =" + (1000 - cost) + " WHERE w_id=" + wid;

			System.out.println(sql);
			ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();

			rs.close();
			ps.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	

}
