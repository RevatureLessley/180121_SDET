package com.company;

import java.sql.*;

public class BankConnection implements UserDao {
    @Override
    public void insertUser(User user) {
        PreparedStatement ps = null;

        try (Connection conn = Connections.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

            String sql = "INSERT INTO CLIENT (client_name, client_passord, client_bank_id, client_amount_id) VALUES(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setInt(3, 1);
            ps.setInt(3, user.getId());

            System.out.println(ps.executeUpdate() + " RECORD(S) INSERTED!");

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public User getUser(String username, String password) {
        Statement stmt = null;
        ResultSet rs = null;
        User user = new User();

        /*
         * Try-With-Resources will close any streams you create within the
         * parenthesis' of the try block, once the try-catch is finished.
         */
        try (Connection conn = Connections.getConnection()) {
            // First step in using the Statement object, is create a SQL query
            // to
            // use for the database;

            String sql = "SELECT * FROM employees WHERE username =" +
                    username + " AND password =" + password;
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql); // Executing queries, brings back
            // resultsets

            if(rs.next()) {
                user = new User(
                        rs.getString("client_password"),
                        rs.getFloat("client_amount"),
                        rs.getString("client_name"),
                        rs.getInt("client_id"));
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
    public int updateUserById(User emp) {
        return 0;
    }
}
