package com.connection;

public interface UserDao {
    public void insertUser(User user);
    public User getUser(String username, String password);
    public int deleteUserById();
    public int updateUserById(User emp);
}
