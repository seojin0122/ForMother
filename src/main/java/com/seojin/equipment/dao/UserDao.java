package com.seojin.equipment.dao;

import java.util.ArrayList;
import java.util.List;

import com.seojin.equipment.dto.User;

public class UserDao {

	private static UserDao instance = new UserDao();
	private List<User> users = new ArrayList<>();

	private UserDao() {
		users.add(new User("admin", "1234", "관리자", "ADMIN"));
	}

	public static UserDao getInstance() {
		return instance;
	}

	public User login(String id, String password) {
		for (User user : users) {
			if (user.getId().equals(id) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}
}