package com.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entiy.PageEntiy;
import com.student.entiy.Student;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/PageServlet")
public class PageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentService studentService=new StudentServiceImpl();
       HttpSession session;
       private int pages=0;
       private int pagetotal;
       
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PageServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session=request.getSession();
		PageEntiy entiy=new PageEntiy();
		session.removeAttribute("entiy");
		pagetotal=studentService.findtotalstu();
		Integer pageN=null;
		if(pagetotal%entiy.getPageSize()==0){
			pages=pagetotal/entiy.getPageSize();
		}else{
			pages=pagetotal/entiy.getPageSize()+1;
		}
		entiy.setPages(pages);
		String pageNum = request.getParameter("pageNum");
		System.out.println(pageNum);
		if(pageNum==null||pageNum==""){
			
			List<Student> findAll = studentService.findAll(entiy);
			
			request.setAttribute("studentlist", findAll);
			session.setAttribute("entiy", entiy);
			request.getRequestDispatcher("/manager/sturight.jsp").forward(request, response);
		}else{
			try{
				pageN = Integer.valueOf(pageNum);
			}catch (NumberFormatException e) {
				// TODO: handle exception
				pageN=0;
			}
			if(pageN<0){
				pageN=0;
			}else if(pageN>=pages){
				pageN=pages-1;
			}
			entiy.setPageNum(pageN);
			List<Student> findAll = studentService.findAll(entiy);
			request.setAttribute("studentlist", findAll);
			session.setAttribute("entiy", entiy);
			request.getRequestDispatcher("/manager/sturight.jsp").forward(request, response);
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
