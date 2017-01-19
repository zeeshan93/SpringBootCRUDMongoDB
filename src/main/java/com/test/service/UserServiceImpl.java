package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.database.UserDatabase;
import com.test.model.User;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDatabase database;

	public String saveUser(User user) {
		return database.saveUser(user);
	}

	public User readUser(String name) {
		return database.readUser(name);
	}

	@Override
	public User updateUser(String name, String updatedName) {
		return database.updateUser(name, updatedName);
	}

	@Override
	public String deleteUser(String name) {
		return database.deleteUser(name);
	}

}
