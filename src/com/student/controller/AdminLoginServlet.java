package com.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;
import org.springframework.util.DigestUtils;

import com.student.entiy.Admin;
import com.student.service.AdminService;
import com.student.service.StudentService;
import com.student.service.impl.AdminServiceImpl;
import com.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/servlet/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	AdminService adminService=new AdminServiceImpl();
	StudentService studentService=new StudentServiceImpl();
	
	
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Admin admin=new Admin();
		HttpSession session=request.getSession();
		try {
			BeanUtils.populate(admin, request.getParameterMap());
			System.out.println(admin);
			String remember = request.getParameter("remember");
			String rememberpass=request.getParameter("rememberpass");
			System.out.println(remember);
			admin.setPassword(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
			System.out.println(DigestUtils.md5DigestAsHex(admin.getPassword().getBytes()));
			Cookie cookie=null;
			Cookie cookie2=null;
			Cookie cookie4=null;
			Cookie[] cookies = request.getCookies();
			Admin admin2 = adminService.login(admin);
			System.out.println(admin2);
			if(admin2!=null){
				
				String time=adminService.getlogin_time();
				session.setAttribute("login_time", time);
				session.setAttribute("admin", admin2);
				if(remember!=null){
					if(rememberpass!=null){
						cookie4=new Cookie("rememberpass", rememberpass);
						cookie4.setMaxAge(60*60*24);
						cookie4.setPath(request.getContextPath());
						response.addCookie(cookie4);
						
					}
					cookie=new Cookie("remember", remember);
					 cookie2=new Cookie("user", admin.getName()+"//_//"+admin.getPassword());
					 String contextPath = getServletContext().getContextPath();
					 response.setContentType("text/html; charset=utf-8");
					 	cookie.setPath(contextPath);
						cookie2.setPath(contextPath);
						cookie.setMaxAge(60*60*24);
						cookie2.setMaxAge(60*60*24);
						response.addCookie(cookie);
						response.addCookie(cookie2);
						
				}else{
					for (Cookie cookie3 : cookies) {
						if("remember".equals(cookie3.getName())||"user".equals(cookie3.getName())||"rememberpass".equals(cookie3.getName())){
							cookie3.setMaxAge(0);;
							String contextPath = getServletContext().getContextPath();
							cookie3.setPath(contextPath);
							response.addCookie(cookie3);
							System.out.println("执行");
						}
					}
				}
				/*response.getWriter().append("true");*/
				response.sendRedirect(getServletContext().getContextPath()+"/manager/main.jsp");
				
			}else{
				/*response.getWriter().append("false");*/
				for (Cookie cookie3 : cookies) {
					if("remember".equals(cookie3.getName())||"user".equals(cookie3.getName())||"rememberpass".equals(cookie3.getName())){
						cookie3.setMaxAge(0);;
						String contextPath = getServletContext().getContextPath();
						cookie3.setPath(contextPath);
						response.addCookie(cookie3);
						System.out.println("执行");
					}
				}
				request.setAttribute("error", "提示：        您输入密码或用户名有误");
				
				request.getRequestDispatcher("/manager/relogin.jsp").forward(request, response);
				return;
			}
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
