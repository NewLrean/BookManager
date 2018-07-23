package com.student.service.impl;

import com.student.dao.UserDao;
import com.student.dao.impl.UserDaoImpl;
import com.student.entiy.User;
import com.student.service.UserService;

public class UserServiceImpl implements UserService {

	UserDao userDao=new UserDaoImpl();
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		return userDao.login(username, password);
	}
	@Override
	public String findValidation(String username) {
		// TODO Auto-generated method stub
		return userDao.findValidation(username);
	}

}
