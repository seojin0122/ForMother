package com.seojin.equipment.service;

import com.seojin.equipment.dao.UserDao;
import com.seojin.equipment.dto.User;

public class UserService {

	private static UserService instance = new UserService();
	private UserDao userDao = UserDao.getInstance();

	private UserService() {
	}

	public static UserService getInstance() {
		return instance;
	}

	public User login(String id, String password) {
		if (id == null || id.trim().isEmpty()) {
			return null;
		}
		if (password == null || password.trim().isEmpty()) {
			return null;
		}
		return userDao.login(id, password);
	}
}