package com.connection;

import java.sql.*;

public class BankConnection implements UserDao {

    @Override
    public void insertUser(User user) {
        PreparedStatement ps = null;

        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

            String sql = "INSERT INTO client (client_name, client_password, client_bank_id) VALUES(?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, 1);

            System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public User getUser(String username, String password) {
        Statement stmt = null;
        ResultSet rs = null;

        try (Connection conn = SQLConnection.getConnection()) {
            /*
            String sql = "SELECT * FROM client WHERE client_name ='" +
                    username + "' AND client_password ='" + password + "'";
                    */
            String sql = "SELECT * From amount JOIN client ON client_amount_id = amount.amount_id " +
                    "AND client_name='" + username + "' AND client_password='"  + password + "'";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);

            // result sets
            if(rs.next()) {
                User user;
                /*
                user = new User(
                        rs.getString("client_password"),
                        rs.getString("client_name"),
                        rs.getInt("client_id"));
                        */
                user = new User(
                        rs.getString("client_password"),
                        rs.getFloat("amount"),
                        rs.getString("client_name"),
                        rs.getInt("client_id"));

                if(user.getAmount() == null){
                    user.setAmount(0f);
                    System.out.println("null");
                }
                return  user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public int deleteUserById() {
        return 0;
    }

    @Override
    public int updateUserById(User user) {
        PreparedStatement ps = null;

        try (Connection conn = SQLConnection.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

            String sql = "UPDATE amount SET amount = ? WHERE amount_id = ?";
            ps = conn.prepareStatement(sql);
            ps.setFloat(1, user.getAmount());
            ps.setInt(2, user.getId());

            System.out.println(ps.executeUpdate() + " RECORD(S) UPDATED!");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return 0;
    }
}
