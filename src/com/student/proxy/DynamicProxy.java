package com.student.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

import com.student.dao.AdminDao;
import com.student.util.Utils;


public class DynamicProxy implements InvocationHandler{

	private AdminDao admindao;
	
	public DynamicProxy(AdminDao adminDao) {
		// TODO Auto-generated constructor stub
		this.admindao=adminDao;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		
		
		
	
		boolean invoke = (boolean) method.invoke(admindao, args);
		if(invoke){
			Utils.getTime();
		}
		
		return invoke;
	}

}
