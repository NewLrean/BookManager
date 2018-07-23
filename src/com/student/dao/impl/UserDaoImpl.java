package com.student.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.student.dao.UserDao;
import com.student.entiy.User;
import com.student.util.Dbconn;
import com.student.util.IdGenrtor;
import com.student.util.SecurityUtils;

public class UserDaoImpl implements UserDao {
	QueryRunner runner =new QueryRunner(Dbconn.getDataSource());
	@Override
	public boolean addUser(User user) {
		// TODO Auto-generated method stub
		try {
			int update = runner.update("insert into user(id,username,password,phone,address) value (?,?,?,?,?)",
					IdGenrtor.getGUID()+"_uid",user.getUsername(),user.getPassword(),user.getPhone(),user.getAddress());
			if(update>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	@Override
	public User login(String username, String password) {
		// TODO Auto-generated method stub
		try {
			User user = runner.query("select * from user where username=? and password = ?", new BeanHandler<User>(User.class),username,SecurityUtils.MD5(password));
			if(user!=null){
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	@Override
	public String findValidation(String username) {
		// TODO Auto-generated method stub
		try {
			String query = runner.query("select username from user where username= ?", new BeanHandler<String>(String.class), username);
			if(query!=null){
				return query;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
