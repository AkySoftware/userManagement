package com.app.practice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.app.practice.model.User;
import com.app.practice.service.UserService;

@RestController
public class UserController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/")
	public String test() {
		return "Welcome";
	}

	/**
	@GetMapping(value = "/users")
	public List<User> allUsers() {
		return userService.findAll();
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String addUser(@RequestBody User newuser) {
		userService.add(newuser);
		return "User Added Sucessfull";
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.POST)
	public String deleteUser(@PathVariable int id) {
		return userService.delete(id);
	}

	@RequestMapping(value = "/user", method = RequestMethod.PUT)
	public int updateUser(@RequestBody User user) {
		return userService.edit(user, user.getId());
	}
   */
	
	@GetMapping(value = "/users")
	public ResponseEntity<List<User>> allUsers() {
		HttpHeaders headers = new HttpHeaders();
		List<User> userList = userService.findAll();
		if (userList == null) {
			return new ResponseEntity<List<User>>(HttpStatus.NOT_FOUND);
		}
		headers.add("Number Of Records Found", String.valueOf(userList.size()));
		return new ResponseEntity<List<User>>(userList, headers, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
	public ResponseEntity<User> getEmployee(@PathVariable("id") Integer userId) {
		User user = userService.find(userId);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<User>(user, HttpStatus.OK);
	}

	@RequestMapping(value = "/user/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<User> deleteEmployee(@PathVariable("id") Integer userId) {
		HttpHeaders headers = new HttpHeaders();
		User user = userService.find(userId);
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		}
		userService.delete(userId);
		headers.add("User Deleted - ", String.valueOf(userId));
		return new ResponseEntity<User>(user, headers, HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/user", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<User> createEmployee(@RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		if (user == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		userService.add(user);
		headers.add("User Created  - ", String.valueOf(user.getId()));
		return new ResponseEntity<User>(user, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/user/{id}", method = RequestMethod.PUT)
	public ResponseEntity<User> updateEmployee(@PathVariable("id") Integer userId, @RequestBody User user) {
		HttpHeaders headers = new HttpHeaders();
		User isExist = userService.find(userId);
		if (isExist == null) {
			return new ResponseEntity<User>(HttpStatus.NOT_FOUND);
		} else if (user == null) {
			return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
		}
		userService.edit(user, userId);
		headers.add("User Updated  - ", String.valueOf(userId));
		return new ResponseEntity<User>(user, headers, HttpStatus.OK);
	}
}
