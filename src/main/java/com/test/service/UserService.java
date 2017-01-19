package com.test.service;

import com.test.model.User;

public interface UserService {
	public String saveUser(User user);
	public User readUser(String name);
	public User updateUser(String name, String updatedName);
	public String deleteUser(String name);
}
