package com.student.service.impl;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.util.List;

import com.student.dao.AdminDao;
import com.student.dao.impl.AdminDaoImpl;
import com.student.entiy.Admin;
import com.student.entiy.Function;
import com.student.entiy.Role;
import com.student.proxy.DynamicProxy;
import com.student.service.AdminService;



public class AdminServiceImpl implements AdminService {

	AdminDao adminDao=new AdminDaoImpl();
	InvocationHandler handler=new DynamicProxy(adminDao);
	
	AdminDao dao=(AdminDao) Proxy.newProxyInstance(handler.getClass().getClassLoader(), adminDao.getClass().getInterfaces(),
			handler);
	
	
	@Override
	public Admin login(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.login(admin);
	}


	@Override
	public String getlogin_time() {
		// TODO Auto-generated method stub
		return adminDao.getlogin_time();
	}


	@Override
	public List<Role> findAllRolesByAdmin(Admin admin) {
		// TODO Auto-generated method stub
		return adminDao.findAllRolesByAdmin(admin) ;
	}


	@Override
	public List<Function> functionsByRole(Role role) {
		// TODO Auto-generated method stub
		return adminDao.functionsByRole(role);
	}

}
