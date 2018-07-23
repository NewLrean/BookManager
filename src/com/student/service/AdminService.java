package com.student.service;

import java.util.List;

import com.student.entiy.Admin;
import com.student.entiy.Function;
import com.student.entiy.Role;

public interface AdminService {
	public Admin login(Admin admin);
	
	public String getlogin_time();
	
	public List<Role> findAllRolesByAdmin(Admin admin);
	
	public List<Function> functionsByRole(Role role);
}
