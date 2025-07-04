package com.tapfoods.DAO;

import java.util.List;

import com.tapfoods.Model.User;

public interface UserDAO {
	
	void addUser(User user);
	
	User getUser(int userId);
	
	void updateUser(User user);
	
	void deleteUser(int userId);
	
	List<User> getAllUsers();

}
