package com.student.service;

import com.student.entiy.User;

public interface UserService {

	/**
	 * 注册用户
	 * @param user 用户对象
	 * @return
	 */
	public boolean addUser(User user);
	/**
	 * 用户登录
	 * @param username 用户名
	 * @param password 密码 注：需要加密验证
	 * @return
	 */
	public User login(String username,String password);
	
	public String findValidation(String username);
}
