package com.trms.dao;

import static com.trms.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.trms.beans.Application;
import com.trms.services.EmployeeService;
import com.trms.util.Connections;

public class FormDao {
	public int insertApplication(Application app) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "INSERT INTO applications (app_id, emp_id, firstname, lastname, email, address, city, state, tel,"
					+ "event_date, event_location, event_type, event_relation, event_cost, grading_format, description, status_code, submit_Date)"
					+ " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
			ps = conn.prepareStatement(sql);
			long id = getMaxAppId();
			ps.setLong(1, id + 1);
			ps.setLong(2, app.getEmpId());
			ps.setString(3, app.getFirstname());
			ps.setString(4, app.getLastname());
			ps.setString(5, app.getEmail());
			ps.setString(6, app.getAddress());
			ps.setString(7, app.getCity());
			ps.setString(8, app.getState());
			ps.setString(9, app.getTel());
			ps.setDate(10, app.getEventDate());
			ps.setString(11, app.getLocation());
			ps.setString(12, app.getType());
			ps.setString(13, app.getRelation());
			ps.setDouble(14, app.getCost());
			ps.setString(15, app.getGradingFormat());
			ps.setString(16, app.getDescription());
			ps.setString(17, app.getStatus());
			ps.setDate(18, app.getSubmitDate());

			return ps.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return 0;
	}

	public long getMaxAppId() {
		Statement stmt = null;
		ResultSet rs = null;
		int max = -1;

		try (Connection conn = Connections.getConnection()) {
			String sql = "SELECT Max(app_id) FROM applications";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				max = rs.getInt("MAX(app_id)");
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Get Max app_id Failed.");
			return 0;
		} finally {
			close(stmt);
			close(rs);
		}

		return max;
	}

	public List<Application> getFormsByEmpId(long id) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> apps = new ArrayList<Application>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM ApplicationView WHERE emp_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				apps.add(new Application(rs.getLong("app_id"), rs
						.getLong("emp_id"), rs.getString("firstname"), rs
						.getString("lastname"), rs.getString("email"), rs
						.getString("address"), rs.getString("city"), rs
						.getString("state"), rs.getString("tel"), rs
						.getDate("event_date"), rs.getString("event_location"),
						rs.getString("event_type"), rs
								.getString("event_relation"), rs
								.getDouble("event_cost"), rs
								.getString("grading_format"), rs
								.getString("description"), rs
								.getString("status"), rs
								.getString("sv_decision"), rs
								.getString("sv_comment"), rs
								.getString("dh_decision"), rs
								.getString("dh_comment"), rs
								.getString("bc_decision"), rs
								.getString("bc_comment"), rs
								.getDate("submit_date")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return apps;
	}

	public List<Application> getSupervisorForms(String username) {

		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> apps = new ArrayList<Application>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT A.* FROM APPLICATIONVIEW A "
					+ "LEFT JOIN EMPLOYEES B " + "ON B.EMP_ID = A.EMP_ID "
					+ "WHERE B.SUPERVISOR_ID = ? AND A.STATUS_CODE = 1 "
					+ "ORDER BY A.SUBMIT_DATE DESC";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, EmployeeService.getEmpByUsername(username).getEmpId());
			rs = ps.executeQuery();
			while (rs.next()) {
				apps.add(new Application(rs.getLong("app_id"), rs
						.getLong("emp_id"), rs.getString("firstname"), rs
						.getString("lastname"), rs.getString("email"), rs
						.getString("address"), rs.getString("city"), rs
						.getString("state"), rs.getString("tel"), rs
						.getDate("event_date"), rs.getString("event_location"),
						rs.getString("event_type"), rs
								.getString("event_relation"), rs
								.getDouble("event_cost"), rs
								.getString("grading_format"), rs
								.getString("description"), rs
								.getString("status"), rs
								.getString("sv_decision"), rs
								.getString("sv_comment"), rs
								.getString("dh_decision"), rs
								.getString("dh_comment"), rs
								.getString("bc_decision"), rs
								.getString("bc_comment"), rs
								.getDate("submit_date")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return apps;
	}

	public boolean updateSvAction(Long appId, String svDecision,
			String svComment) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "UPDATE Applications SET sv_decision = ?, sv_Comment = ? WHERE app_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, svDecision);
			ps.setString(2, svComment);
			ps.setLong(3, appId);
			ps.executeUpdate();

			if (svDecision.equals("Approve")) {
				sql = "UPDATE Applications SET status_code = 2 WHERE app_id = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql);
				ps2.setLong(1, appId);
				ps2.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return false;
	}

	public Application getFormByAppId(long id) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		Application app = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM ApplicationView WHERE app_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setLong(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				app = new Application(rs.getLong("app_id"),
						rs.getLong("emp_id"), rs.getString("firstname"),
						rs.getString("lastname"), rs.getString("email"),
						rs.getString("address"), rs.getString("city"),
						rs.getString("state"), rs.getString("tel"),
						rs.getDate("event_date"),
						rs.getString("event_location"),
						rs.getString("event_type"),
						rs.getString("event_relation"),
						rs.getDouble("event_cost"),
						rs.getString("grading_format"),
						rs.getString("description"), rs.getString("status"),
						rs.getString("sv_decision"),
						rs.getString("sv_comment"),
						rs.getString("dh_decision"),
						rs.getString("dh_comment"),
						rs.getString("bc_decision"),
						rs.getString("bc_comment"), rs.getDate("submit_date"));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return app;
	}

	public List<Application> getDHForms(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> apps = new ArrayList<Application>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM APPLICATIONVIEW "
					+ "WHERE STATUS_CODE = 2 "
					+ "ORDER BY SUBMIT_DATE DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				apps.add(new Application(rs.getLong("app_id"), rs
						.getLong("emp_id"), rs.getString("firstname"), rs
						.getString("lastname"), rs.getString("email"), rs
						.getString("address"), rs.getString("city"), rs
						.getString("state"), rs.getString("tel"), rs
						.getDate("event_date"), rs.getString("event_location"),
						rs.getString("event_type"), rs
								.getString("event_relation"), rs
								.getDouble("event_cost"), rs
								.getString("grading_format"), rs
								.getString("description"), rs
								.getString("status"), rs
								.getString("sv_decision"), rs
								.getString("sv_comment"), rs
								.getString("dh_decision"), rs
								.getString("dh_comment"), rs
								.getString("bc_decision"), rs
								.getString("bc_comment"), rs
								.getDate("submit_date")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return apps;
	}

	public List<Application> getBCForms(String username) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> apps = new ArrayList<Application>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM APPLICATIONVIEW "
					+ "WHERE STATUS_CODE = 3 "
					+ "ORDER BY SUBMIT_DATE DESC";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				apps.add(new Application(rs.getLong("app_id"), rs
						.getLong("emp_id"), rs.getString("firstname"), rs
						.getString("lastname"), rs.getString("email"), rs
						.getString("address"), rs.getString("city"), rs
						.getString("state"), rs.getString("tel"), rs
						.getDate("event_date"), rs.getString("event_location"),
						rs.getString("event_type"), rs
								.getString("event_relation"), rs
								.getDouble("event_cost"), rs
								.getString("grading_format"), rs
								.getString("description"), rs
								.getString("status"), rs
								.getString("sv_decision"), rs
								.getString("sv_comment"), rs
								.getString("dh_decision"), rs
								.getString("dh_comment"), rs
								.getString("bc_decision"), rs
								.getString("bc_comment"), rs
								.getDate("submit_date")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return apps;
	}

	public boolean updateDhAction(Long appId, String decision, String comment) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "UPDATE Applications SET dh_decision = ?, dh_Comment = ? WHERE app_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, decision);
			ps.setString(2, comment);
			ps.setLong(3, appId);
			ps.executeUpdate();

			if (decision.equals("Approve")) {
				sql = "UPDATE Applications SET status_code = 3 WHERE app_id = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql);
				ps2.setLong(1, appId);
				ps2.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return false;
		
	}

	public boolean updateBcAction(Long appId, String bcDecision, String bcComment) {
		PreparedStatement ps = null;

		try (Connection conn = Connections.getConnection()) {

			String sql = "UPDATE Applications SET bc_decision = ?, bc_Comment = ? WHERE app_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, bcDecision);
			ps.setString(2, bcComment);
			ps.setLong(3, appId);
			ps.executeUpdate();

			if (bcDecision.equals("Approve")) {
				sql = "UPDATE Applications SET status_code = 4 WHERE app_id = ?";
				PreparedStatement ps2 = conn.prepareStatement(sql);
				ps2.setLong(1, appId);
				ps2.executeUpdate();
			}

			return true;

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return false;
		
	}

	public List<Application> getAllForms() {
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Application> apps = new ArrayList<Application>();

		try (Connection conn = Connections.getConnection()) {

			String sql = "SELECT * FROM ApplicationView";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while (rs.next()) {
				apps.add(new Application(rs.getLong("app_id"), rs
						.getLong("emp_id"), rs.getString("firstname"), rs
						.getString("lastname"), rs.getString("email"), rs
						.getString("address"), rs.getString("city"), rs
						.getString("state"), rs.getString("tel"), rs
						.getDate("event_date"), rs.getString("event_location"),
						rs.getString("event_type"), rs
								.getString("event_relation"), rs
								.getDouble("event_cost"), rs
								.getString("grading_format"), rs
								.getString("description"), rs
								.getString("status"), rs
								.getString("sv_decision"), rs
								.getString("sv_comment"), rs
								.getString("dh_decision"), rs
								.getString("dh_comment"), rs
								.getString("bc_decision"), rs
								.getString("bc_comment"), rs
								.getDate("submit_date")));

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
		}
		return apps;
	}
}
