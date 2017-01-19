package com.test.database;

import com.test.model.User;

public interface UserDatabase {
	public String saveUser(User user);
	public User readUser(String name);
	public User updateUser(String name, String updatedName);
	public String deleteUser(String name);
}
