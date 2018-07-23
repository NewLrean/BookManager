package com.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.student.entiy.PageEntiy;
import com.student.entiy.Student;
import com.student.service.StudentService;
import com.student.service.impl.StudentServiceImpl;

/**
 * Servlet implementation class StudentServlet
 */
@WebServlet("/StudentServlet")
public class StudentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	StudentService studentService=new StudentServiceImpl();
	HttpSession session=null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public StudentServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String mothed = request.getParameter("mothed");
		
		System.out.println(mothed);
		session=request.getSession();
		
		switch (mothed) {
		case "delete":
			String ss = request.getParameter("id");
			int id=Integer.valueOf(ss);
			if(studentService.deleteById(id)){
				PageEntiy entiy = (PageEntiy) session.getAttribute("entiy");
				List<Student> findAll = studentService.findAll(entiy);
				request.setAttribute("studentlist", findAll);
				request.setAttribute("massage", "<div  class='form-control massage'>删除成功！<a></a></div>");
				request.getRequestDispatcher("/manager/sturight.jsp").forward(request, response);
			}
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
		
		String parameter = request.getParameter("mothed");
		System.out.println(parameter);
		Student student=new Student();
		session=request.getSession();
		switch (parameter) {
		case "update":
			
			try {
				BeanUtils.populate(student, request.getParameterMap());
				
				System.out.println(student);
				
				if(studentService.updateById(student)){
					PageEntiy entiy = (PageEntiy) session.getAttribute("entiy");
					List<Student> findAll = studentService.findAll(entiy);
					request.setAttribute("studentlist", findAll);
					request.setAttribute("massage", "<div  class='form-control massage'>修改成功！<a></a></div>");
					request.getRequestDispatcher("/manager/sturight.jsp").forward(request, response);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			break;

		case "register":
			try {
				
				BeanUtils.populate(student, request.getParameterMap());
				if(studentService.addstu(student)){
					request.setAttribute("massage",	"添加成功！");
					request.getRequestDispatcher("/manager/success.jsp").forward(request, response);
				}else{
					request.setAttribute("massage",	"添加失败！");
					request.getRequestDispatcher("/manager/success.jsp").forward(request, response);
				}
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		default:
			break;
		}
		
	}
}
