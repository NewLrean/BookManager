package com.student.filter;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entiy.Admin;
import com.student.entiy.Function;
import com.student.entiy.Role;
import com.student.service.AdminService;
import com.student.service.impl.AdminServiceImpl;

public class RestrictFilter implements Filter{
	private HttpSession session;
	private AdminService adminService=null;
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("销毁权限过滤器");
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse response=(HttpServletResponse) res;
		HttpServletRequest request=(HttpServletRequest) req;
		session=request.getSession();
		Admin admin = (Admin) session.getAttribute("admin");
		
		if(request.getRequestURI().endsWith("relogin.jsp")||request.getRequestURI().endsWith("gologin.jsp")){
			chain.doFilter(request, response);
			return;
		}
		if(admin==null){
			response.sendRedirect(request.getContextPath()+"/manager/relogin.jsp");
			return;
		}
		
		List<Role> roleslist = adminService.findAllRolesByAdmin(admin);
		Set<String> functions=new HashSet<String>();
		for (Role role : roleslist) {
			List<Function> functionsByRole = adminService.functionsByRole(role);
			for (Function function : functionsByRole) {
				functions.add(function.getUri());
			}
		}
		
		
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		
		uri=uri.replace(request.getContextPath(),"");
		boolean flag=false;
		for (String string : functions) {
			
			if(uri.equals(string)||(uri+"?"+queryString).equals(string)){
				flag=true;
				break;
			}
		}
		if(queryString!=null&&queryString.contains("&")){
			queryString=queryString.split("&")[0];
		}
		
		if(uri.equals("/book/OtherOpServlet")){
			if(!flag){
				request.setAttribute("massage", "<div  class='form-control massage'>您没有操作该功能的权限！<a></a></div>");
				request.getRequestDispatcher((String)session.getAttribute("uri")+session.getAttribute("queryString")).forward(request, response);
				return;
			}
		}
		session.setAttribute("uri",uri);
		session.setAttribute("queryString", "?"+queryString);
		if(!flag){
			request.setAttribute("massage", "<div  class='form-control massage'>您没有操作该功能的权限！<a></a></div>");
			request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
			return;
		}
		
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("构造权限过滤器");
		if(adminService==null){
			adminService=new AdminServiceImpl();
		}
	}
	
}
