package com.test.controller;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
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
	public User readUser(@PathParam(value = "name") String name){
		return service.readUser(name);
	}
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public User updateUser(@PathParam(value = "name") String name, @PathParam(value = "updatedName") String updatedName){
		return service.updateUser(name, updatedName);
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.DELETE)
	public String deleteUser(@PathParam(value = "name") String name){
		return service.deleteUser(name);
	}
}
