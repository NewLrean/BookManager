package com.student.dao;

import com.student.entiy.User;

public interface UserDao {
	/**
	 * 注册用户
	 * @param user 用户对象
	 * @return
	 */
	public boolean addUser(User user);
	
	public User login(String username,String password);
	
	
	public String findValidation(String username);
}
