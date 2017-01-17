package com.test.database;

import com.test.model.User;

public interface UserDatabase {
	public String saveUser(User user);
	public User readUser(String name);
}
