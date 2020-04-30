package com.app.practice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.practice.dao.UserDao;
import com.app.practice.model.User;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;
	
	@Override
	public int add(User user) {
		return userDao.add(user);
	}

	@Override
	public int edit(User user, int userId) {
		return userDao.edit(user, userId);
	}

	@Override
	public String delete(int userId) {
		return userDao.delete(userId);
	}

	@Override
	public User find(int userId) {
		return userDao.find(userId);
	}

	@Override
	public List<User> findAll() {
		return userDao.findAll();
	}
	
	
}
