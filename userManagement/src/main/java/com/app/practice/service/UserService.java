package com.app.practice.service;

import java.util.List;

import com.app.practice.model.User;

public interface UserService {
	public int add(User user);
	 
    public int edit(User user, int userId);
 
    public String delete(int userId);
 
    public User find(int userId);
 
    public List <User> findAll();
}
