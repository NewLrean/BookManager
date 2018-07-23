package com.student.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class MainServlet
 */
@WebServlet("/MainServlet")
public class MainServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HttpSession session;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MainServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		session=request.getSession();
		
		switch (method) {
		case "goout":
			session.invalidate();
			Cookie[] cookies = request.getCookies();
			for (Cookie cookie3 : cookies) {
				if("remember".equals(cookie3.getName())||"user".equals(cookie3.getName())||"rememberpass".equals(cookie3.getName())){
					cookie3.setMaxAge(0);;
					String contextPath = getServletContext().getContextPath();
					cookie3.setPath(contextPath);
					response.addCookie(cookie3);
					System.out.println("执行");
				}
			}
			response.sendRedirect(request.getContextPath()+"/manager/relogin.jsp");
			break;

		default:
			break;
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
