package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.model.User;
import com.test.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public String saveUser(@RequestBody User user){
		return service.saveUser(user);
	}
	
	@RequestMapping(value = "/read", method = RequestMethod.GET)
	public User readUser(@PathVariable(name = "name") String name){
		return service.readUser(name);
	}
}
