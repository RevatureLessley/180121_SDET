package dao;

import java.util.List;

import beans.User;

public interface UserDao {
	public List<User> getAllUser();
	public static void insertUser() {};

}
