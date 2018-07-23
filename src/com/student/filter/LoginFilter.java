package com.student.filter;

import java.io.IOException;
import java.util.Set;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.dao.AdminDao;
import com.student.dao.impl.AdminDaoImpl;
import com.student.entiy.Admin;
import com.student.service.AdminService;
import com.student.service.impl.AdminServiceImpl;

public class LoginFilter implements Filter {

	HttpServletRequest req;
	
	HttpServletResponse res;
	
	AdminService adminService=new AdminServiceImpl();
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("登录过滤器销毁"); 
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		req=(HttpServletRequest) request;
		res=(HttpServletResponse) response;
		String path = req.getContextPath();
		String indexPath = req.getScheme()+"://"+req.getServerName()+":"+req.getServerPort()+path+ "/manager/gologin.jsp";
		
		
		HttpSession session = req.getSession();
		 Admin admin = (Admin) session.getAttribute("admin");
		 Cookie cookie=null;
		 Cookie cookie3=null;
		 if(admin==null){
			 
			 Cookie[] cookies = req.getCookies();
			 if(cookies!=null){
				 for (Cookie cookie2 : cookies) {
					if("user".equals(cookie2.getName())){
						cookie=cookie2;
					}else if("rememberpass".equals(cookie2.getName())){
						cookie3=cookie2;
					}
				}
			 }
			 if(cookie3!=null){
				 
			 if(cookie!=null){
				 
				 String[] split = cookie.getValue().split("//_//");
				 Admin admin2=new Admin();
				 admin2.setName(split[0]);
				 admin2.setPassword(split[1]);
				 Admin admin3 = adminService.login(admin2);
				 if(admin3!=null){
					 String time=adminService.getlogin_time();
					session.setAttribute("login_time", time);
					 session.setAttribute("admin", admin3);
					 System.out.println("执行自动登录");
					 if(req.getRequestURI().endsWith("/"))
					    {
						 System.out.println("cookie里有存值跳转");
						 res.sendRedirect(req.getContextPath()+"/manager/main.jsp");  
					      return;
					    }
				 }
				 if(req.getRequestURI().endsWith("relogin.jsp")) 
				    {
					 System.out.println("cookie里有存值跳转");
					 res.sendRedirect(req.getContextPath()+"/manager/main.jsp");  
				      return;
				    }
				 
				 chain.doFilter(req, res);
				 return;
			 }
			 }
			 if(req.getRequestURI().endsWith("relogin.jsp")) 
			    {
				 
				 System.out.println("未登录且cookie没值跳转");
				 chain.doFilter(req, res);  
			      return;
			    }
			 System.out.println("no user in LoginInterceptor!!!");
			  res.sendRedirect(indexPath);
		      return;
		  }
		 if(req.getRequestURI().endsWith("relogin.jsp")) 
		    { 
			 System.out.println("已登录跳转");
			 res.sendRedirect(req.getContextPath()+"/manager/main.jsp");  
		      return;
		    }
				chain.doFilter(req, res);
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("登录过滤器构造");
	}

}
