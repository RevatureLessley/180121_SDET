package com.miniproject.dao;

import static com.miniproject.util.CloseStreams.close;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.miniproject.beverages.BeverageBrand;
import com.miniproject.util.Connections;

public class BeverageBrandDaoImpl implements BeverageBrandDao {

	@Override
	public String getBrand(int inBrandId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		String brandName = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT brand_name FROM beverage_brands WHERE brand_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inBrandId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				brandName = rs.getString(1);
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		
		return brandName;
	}

	@Override
	public BeverageBrand getBeverageBrand(int inBrandId) {
		PreparedStatement ps = null;
		ResultSet rs = null;
		BeverageBrand bb = null;
		
		try(Connection conn = Connections.getConnection()){
			String sql = "SELECT * FROM beverage_brands WHERE brand_id = ?";
			ps = conn.prepareStatement(sql);
			ps.setInt(1, inBrandId);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				bb = new BeverageBrand(rs.getInt(1), rs.getString(2), rs.getString(3));
			}
		} catch(SQLException e) {
			e.printStackTrace();
		} finally {
			close(ps);
			close(rs);
		}
		return bb;
	}

}
