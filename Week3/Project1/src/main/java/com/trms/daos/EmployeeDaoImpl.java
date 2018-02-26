package com.trms.daos;

import static com.trms.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.trms.beans.Employee;
import com.trms.util.Connections;

/**
 * The Data Access Object that connects with the Oracle database and get information related to the employees table
 * @author Lynda
 *
 */
public class EmployeeDaoImpl implements EmployeeDao {
	final static Logger logger = Logger.getLogger(EmployeeDaoImpl.class);

	//=======GET METHODS=======
	
	/**
	 * Retrieves all the information about an employee into an Employee bean
	 * @param empId	The primary key of the employee being retrieved
	 * @return An Employee with all relevent information
	 */
	@Override
	public Employee getEmployee(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee emp = null;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT a.emp_fname, a.emp_lname, a.emp_reportsto, b.depart_name, c.title, a.emp_availreim,"
					+ " a.emp_addr, a.emp_city, a.emp_state, a.emp_zip"
					+ " FROM employees a, empdepartments b, employeetitles c"
					+ " WHERE emp_id = ? AND a.emp_department = b.depart_id AND a.emp_title_id = c.title_id";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			if (rs.next()) {
				emp = new Employee(empId, rs.getString(1), rs.getString(2), rs.getInt(3), rs.getString(4),
						rs.getString(5), rs.getFloat(6), rs.getString(7), rs.getString(8), rs.getString(9),
						rs.getInt(10));
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}

		return emp;
	}
	
	/**
	 * Retrieves the department and title ids of a specified employee from the employees table
	 * @param empId The primary key of the employee being accessed
	 * @return An Employee object with the department and title id set
	 */
	@Override
	public Employee getDepartAndTitle(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Employee e = new Employee();
		
		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_department, emp_title_id FROM employees WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				e.setDepartmentId(rs.getInt(1));
				e.setTitleId(rs.getInt(2));
			}
		} catch(SQLException er) {
			logger.error(er.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return e;
	}

	/**
	 * Retrieves just the title id of a specified employee from the employees table
	 * @param empId The primary key of the employee being accessed
	 * @return An int with the title id
	 */
	@Override
	public int getTitle(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int titleNum = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_title_id FROM employees WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			if (rs.next()) {
				titleNum = rs.getInt(1);
			}
		} catch (SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return titleNum;
	}

	/**
	 * Retrieves the number of subordinates under a specified employee from the employees table
	 * @param empid The primary key of the employee being accessed
	 * @return An int with the count of employees
	 */
	@Override
	public int getSubordinates(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int subNum = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT count(emp_reportsto) FROM employees WHERE emp_reportsto = ?"; // TODO modify to db query
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();

			if (rs.next()) {
				subNum = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		return subNum;
	}

	/**
	 * Retrieves the employee id of the employee that the specified employee reports to
	 * @param empId The primary key of the employee being accessed
	 * @return An int with the employee id
	 */
	@Override
	public int getReportsTo(int empId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int rt = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_reportsto FROM employees WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, empId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				rt = rs.getInt(1);
			}
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
			close(rs);
		}
		
		return rt;
	}

	/**
	 * Retrieves the employee id of the department head by the specified department id
	 * @param departId The id for the department that will be checked
	 * @return An int that represents the employee's id
	 */
	@Override
	public int getDepartmentHeadIdBy(int departId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		int departHeadId = -1;
		
		try(Connection conn = Connections.getConnection()) {
			String sql = "SELECT emp_id FROM employees WHERE emp_department = ? AND (emp_title_id = 50 OR emp_title_id = 0)";
			ps = conn.prepareStatement(sql);
			logger.info("getDepartmentHeadIdBy() : departId=" + departId);
			ps.setInt(1, departId);
			rs = ps.executeQuery();
			if(rs.next()) {
				departHeadId = rs.getInt(1);
			}
			logger.info("departHeadId=" + departHeadId);
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return departHeadId;
	}

	//=======UPDATE METHODS=======
	
	/**
	 * Updates the total available reimbursement for a specified employee based on the project reimbursement from the reimbursement specified
	 * @param rId The primary key for the specified reimbursement
	 * @param empId The employee id for the employee to be updated
	 * @return An int with the number of records updated
	 */
	@Override
	public int updateAvailReimb(int rId, int empId) {
		PreparedStatement ps = null;
		int result = -1;
		// TODO make a check ensure that available reimburse is enough to cover the projected amount
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE employees SET emp_availreim = "
					+ "(-(SELECT reimburse_projreimb FROM reimbursements WHERE reimburse_id = ?) + emp_availreim) "
					+ "WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, rId);
			ps.setInt(2, empId);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}

	/**
	 * Updates all employees available reimbursement amounts to 1000
	 * @return An int with the number of tables that were updated
	 */
	@Override
	public int resetAllAvailReimb() {
		PreparedStatement ps = null;
		int result = -1;
		// TODO make a check ensure that available reimburse is enough to cover the projected amount
		try(Connection conn = Connections.getConnection()) {
			String sql = "UPDATE employees SET emp_availreim = 1000, reset_timestamp = SYSTIMESTAMP "
					+ "WHERE EXTRACT(YEAR FROM reset_timestamp) < EXTRACT(YEAR FROM SYSTIMESTAMP)";
			ps = conn.prepareStatement(sql);
			result = ps.executeUpdate();
		} catch(SQLException e) {
			logger.error(e.getMessage());
		} finally {
			close(ps);
		}
		
		return result;
	}
}
