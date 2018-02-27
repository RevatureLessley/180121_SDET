package Connections;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.*;
import org.junit.Test;

public class TuitionReimbursementConnection implements ClaimDao, EmployeeDao, ClaimApprovalDao {
	private final static Logger LOGGER = Logger.getLogger(TuitionReimbursementConnection.class.getName());
	@Test
	public void databaseInsert(Employee em) {
		Employee em1 = new Employee();

		assertEquals(em, em1);
	}
	
	@Test
	public void ifExist(Employee em) {
		assertTrue(em.getId() >0&& em.getClaim_max() > 0 && em.getPassword().length() > 0 && em.getUsername().length() > 0);
	}
	
	@Test
	public void ifExist(Claim claim) {
		assertTrue(claim.getClaim_employee() >0 && 
				claim.getClaim_grade_id() > 0 && 
				claim.getCost() > 0 && claim.getDate_of_event().length() > 0 &&
				claim.getEvent_type().length()>0);
	}

	
	@Override
	public void insertClaimApproval(ClaimApproval claimApproval) {
		// TODO Auto-generated method stub
        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

        	PreparedStatement ps = null;
    		
    		String sql = "INSERT INTO CLIENT_APPROVAL (claim_approval_claim_id, direct_supervisor_approval, claim_approval_employee_id, " + 
    				"direct_supervisor_message, department_head_approval, department_head_message, " + 
    				"benefit_coordinator_approval, benefit_coordinator_message) VALUES "+ 
    				" (?,?,?,?,?,?,?,?)";
    		
            ps.setInt(1, claimApproval.getClaim_approval_claim_id());
            ps.setInt(2, claimApproval.getDirect_supervisor_approval());
            ps.setInt(3, claimApproval.getClaim_approval_employee_id()); 
            ps.setString(4, claimApproval.getDirect_supervisor_message());
            ps.setInt(5, claimApproval.getDepartment_head_approval());
            ps.setString(6, claimApproval.getDepartment_head_message());
            ps.setInt(7, claimApproval.getBenefit_coordinator_approval());
            ps.setString(8, claimApproval.getBenefit_coordinator_message());

            System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");

        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
            
        }	
	}

	@Override
	public ClaimApproval getClaimApproval(Employee employee) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLConnection.getConnection()) {
            String sql = "SELECT * FROM CLAIM_APPROVAL WHERE " +
                    "claim_approval_employee_id='" + employee.getId() + "'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // result sets
            if(rs.next()) {
            	ClaimApproval claimApproval;
/*
 * int claim_approval_id, 
 * int claim_approval_claim_id, 
 * int direct_supervisor_approval,
 *  String direct_supervisor_message, 
 *  int department_head_approval, 
 *  String department_head_message,
 *	int benefit_coordinator_approval, 
 *String benefit_coordinator_message
 */
            	claimApproval = new ClaimApproval(
                        rs.getInt("claim_approval_id"),
                        rs.getInt("claim_approval_claim_id"),
                        rs.getInt("direct_supervisor_approval"),
                        rs.getString("direct_supervisor_message"),
                        rs.getInt("department_head_approval"),
                        rs.getString("department_head_message"),
                        rs.getInt("benefit_coordinator_approval"),
                        rs.getString("benefit_coordinator_message"));

                return  claimApproval;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return null;
	}

	@Override
	public ClaimApproval getClaimApproval(int id) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLConnection.getConnection()) {
            String sql = "SELECT * FROM CLAIM_APPROVAL WHERE " +
                    "claim_approval_employee_id='" + id + "'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // result sets
            if(rs.next()) {
            	ClaimApproval claimApproval;
/*
 * int claim_approval_id, 
 * int claim_approval_claim_id, 
 * int direct_supervisor_approval,
 *  String direct_supervisor_message, 
 *  int department_head_approval, 
 *  String department_head_message,
 *	int benefit_coordinator_approval, 
 *String benefit_coordinator_message
 */
            	claimApproval = new ClaimApproval(
                        rs.getInt("claim_approval_id"),
                        rs.getInt("claim_approval_claim_id"),
                        rs.getInt("direct_supervisor_approval"),
                        rs.getString("direct_supervisor_message"),
                        rs.getInt("department_head_approval"),
                        rs.getString("department_head_message"),
                        rs.getInt("benefit_coordinator_approval"),
                        rs.getString("benefit_coordinator_message"));

                return  claimApproval;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return null;
	}

	@Override
	public boolean updateClaimApprovalById(ClaimApproval claimApproval) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

            String sql = "UPDATE CLAIM_APPROVAL SET direct_supervisor_approval = ?, direct_supervisor_message = ?,  department_head_approval = ? " + 
            		"  department_head_message = ?, benefit_coordinator_approval=?,  benefit_coordinator_message = ? WHERE claim_approval_employee_id = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setInt(1, claimApproval.getDirect_supervisor_approval());
            ps.setString(2, claimApproval.getDirect_supervisor_message());
            ps.setInt(3, claimApproval.getDepartment_head_approval());
            ps.setString(4, claimApproval.getDepartment_head_message());
            ps.setInt(5, claimApproval.getBenefit_coordinator_approval());
            ps.setString(6, claimApproval.getBenefit_coordinator_message());
            ps.setInt(7, claimApproval.getClaim_approval_employee_id());

            System.out.println(ps.executeUpdate() + " RECORD(S) UPDATED!");

        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }

        return true;
	}

	@Override
	public void insertEmployee(Employee employee) {
		// TODO Auto-generated method stub
        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

        	PreparedStatement ps = null;
    		
    		String sql = "INSERT INTO EMPLOYEE (username, password, claim_max) VALUES "+ 
    				" (?,?,?)";
    		
            ps.setString(1, employee.getUsername());
            ps.setString(2, employee.getPassword());
            ps.setFloat(3, employee.getClaim_max()); 

            System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");

        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }	
		
	}

	@Override
	public Employee getEmployee(String username, String password) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLConnection.getConnection()) {
            String sql = "SELECT * From EMPLOYEE WHERE " +
                    "username='" + username + "' AND password='"  + password + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // result sets
            if(rs.next()) {
            	Employee current_employee;

            	current_employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("claim_max"));

                return  current_employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }
        
        return null;
	}

	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLConnection.getConnection()) {
            String sql = "SELECT * FROM EMPLOYEE WHERE employee_id = '" + id + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // result sets
            if(rs.next()) {
            	Employee current_employee;

            	current_employee = new Employee(
                        rs.getInt("employee_id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getInt("claim_max"));

                return  current_employee;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }
        
        return null;
	}

	@Override
	public boolean updateEmployeeByID(Employee employee) {
		// TODO Auto-generated method stub
		PreparedStatement ps = null;

        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

            String sql = "UPDATE EMPLOYEE SET claim_max = ? WHERE employee_id = ?";
            
            ps = conn.prepareStatement(sql);
            ps.setFloat(1, employee.getClaim_max());
            ps.setInt(2, employee.getId());

            System.out.println(ps.executeUpdate() + " RECORD(S) UPDATED!");

        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }

        return true;
	}

	@Override
	public void insertClaim(Claim claim) {
		// TODO Auto-generated method stub
        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

        	PreparedStatement ps = null;
    		
    		String sql = "INSERT INTO CLAIM (claim_employee_id, claim_grade_id, "
    				+ "date_of_event, time_of_event, location, description, cost, "
    				+ "event_type, attachement, missed_days) VALUES "+ 
    				" (?,?,?,?,?,?,?,?,?,?)";
    		
            ps.setInt(1, claim.getClaim_employee());
            ps.setInt(2, claim.getClaim_grade_id());
            ps.setString(3, claim.getDate_of_event()); 
            ps.setInt(4, claim.getTime_of_event());
            ps.setString(5, claim.getLocation());
            ps.setString(6, claim.getDescription());
            ps.setFloat(7, claim.getCost());
            ps.setString(8, claim.getEvent_type());
            ps.setBlob(7, claim.getData());
            ps.setInt(8, claim.getMissed_days());

            System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");

        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }	
	}

	@Override
	public Claim getClaim(Employee employee) {
		// TODO Auto-generated method stub
		Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLConnection.getConnection()) {
            String sql = "SELECT * FROM CLAIM WHERE " +
                    "claim_employee_id='" + employee.getId() + "'";
            
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // result sets
            if(rs.next()) {
            	Claim claim;
/*
 * int id, 
 * int claim_employee, 
 * int claim_grade_id, 
 * String date_of_event, 
 * int time_of_event,
			String location, 
			String description, 
			int cost, 
			String event_type, 
			ByteArrayInputStream data,
			int missed_days
 */
            	claim = new Claim(
                        rs.getInt("claim_id"),
                        rs.getInt("claim_employee"),
                        rs.getInt("claim_grade_id"),
                        rs.getString("date_of_event"),
                        rs.getInt("time_of_event"),
                        rs.getString("location"),
                        rs.getString("description"),
                        rs.getFloat("cost"),
                        rs.getString("event_type"),
                        null,
                        rs.getInt("missed_days"));

                return  claim;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            this.LOGGER.log(Level.SEVERE, e.getMessage());
        }
        return null;
	}

	@Override
	public Claim getClaim(int id) {
		// TODO Auto-generated method stub
				Statement stmt = null;
		        ResultSet rs = null;

		        try (Connection conn = SQLConnection.getConnection()) {
		            String sql = "SELECT * FROM CLAIM WHERE claim_employee_id = '" + id + "'";
		            stmt = conn.createStatement();
		            rs = stmt.executeQuery(sql);

		            // result sets
		            if(rs.next()) {
		            	Claim claim;

		            	claim = new Claim(
		            			rs.getInt("claim_id"),
		                        rs.getInt("claim_employee"),
		                        rs.getInt("claim_grade_id"),
		                        rs.getString("date_of_event"),
		                        rs.getInt("time_of_event"),
		                        rs.getString("location"),
		                        rs.getString("description"),
		                        rs.getFloat("cost"),
		                        rs.getString("event_type"),
		                        null,
		                        rs.getInt("missed_days"));

		                return  claim;
		            }
		        } catch (SQLException e) {
		            e.printStackTrace();
		            this.LOGGER.log(Level.SEVERE, e.getMessage());
		        }
		        
		        return null;
	}

	@Override
	public boolean updateClaimById(Claim claim) {
		// TODO Auto-generated method stub
				PreparedStatement ps = null;

		        try (Connection conn = SQLConnection.getConnection()) {
		            // First step in using the Statement object, is create a SQL query
		            // to
		            // use for the database;

		            String sql = "UPDATE CLAIM SET claim_grade_id = ?, cost = ? WHERE claim_employee_id = ?";
		            
		            ps = conn.prepareStatement(sql);
		            ps.setInt(1, claim.getClaim_grade_id());
		            ps.setFloat(2, claim.getCost());
		            ps.setInt(2, claim.getClaim_employee());

		            System.out.println(ps.executeUpdate() + " RECORD(S) UPDATED!");

		        } catch (SQLException e) {
		            e.printStackTrace();
		            this.LOGGER.log(Level.SEVERE, e.getMessage());
		        }

		        return true;
	}

}
