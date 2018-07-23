package com.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import com.student.entiy.Category;
import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;
import com.student.service.CategoryService;
import com.student.service.PublisherService;
import com.student.service.impl.CategoryServiceImpl;
import com.student.service.impl.PublisherServiceImpl;
import com.student.util.IdGenrtor;

/**
 * Servlet implementation class OtherOpServlet
 */
@WebServlet("/book/OtherOpServlet")
public class OtherOpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      CategoryService categoryService=new CategoryServiceImpl();
      
      PublisherService publisherService=new PublisherServiceImpl();
      PageEntiy entiy=new PageEntiy();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public OtherOpServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method = request.getParameter("method");
		
		if("aboutcate".equals(method)){
			findCategory(request,response);
		}else if("aboutpubl".equals(method)){
			findPublisher(request,response);
		}else if("deleteCate".equals(method)){
			deleteCate(request,response);
		}else if("deletePubl".equals(method)){
			deletePubl(request,response);
		}else if("updateCate".equals(method)){
			updateCate(request,response);
		}else if("updatePubl".equals(method)){
			updatePubl(request,response);
		}else if("addCate".equals(method)){
			addCate(request,response);
		}else if("addPubl".equals(method)){
			addPubl(request,response);
		}
	}
	
	private void addPubl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Publisher publisher=new Publisher();
		try {
			BeanUtils.populate(publisher, request.getParameterMap());
			publisher.setId(IdGenrtor.getGUID()+"ca");
			boolean addPublisher = publisherService.addPublisher(publisher);
			if(addPublisher){
				request.setAttribute("massage", "<div  class='form-control massage'>出版社添加成功！<a></a></div>");
				request.getRequestDispatcher("/book/OtherOpServlet?method=aboutpubl").forward(request, response);
			}else{
				request.setAttribute("massage", "<div  class='form-control massage'>出版社添加失败！<a></a></div>");
				request.getRequestDispatcher("/book/OtherOpServlet?method=aboutpubl").forward(request, response);
				
			}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	private void addCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		Category category=new Category();
		try {
			BeanUtils.populate(category, request.getParameterMap());
			category.setId(IdGenrtor.getGUID()+"pu");
			boolean addCategory = categoryService.addCategory(category);
		if(addCategory){
			request.setAttribute("massage", "<div  class='form-control massage'>图书类别添加成功！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutcate").forward(request, response);
		}else{
			request.setAttribute("massage", "<div  class='form-control massage'>图书类别添加失败！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutcate").forward(request, response);
			
		}
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void updatePubl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Publisher publisher=new Publisher();
		publisher.setId(request.getParameter("id"));
		publisher.setName(request.getParameter("name"));
		publisher.setDescription(request.getParameter("description"));
		
		boolean updateByPublisherId = publisherService.updateByPublisherId(publisher);
		if(updateByPublisherId){
			request.setAttribute("massage", "<div  class='form-control massage'>出版社更新成功！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutpubl").forward(request, response);
		}else{
			request.setAttribute("massage", "<div  class='form-control massage'>出版社更新失败！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutpubl").forward(request, response);
			
		}
	}

	private void updateCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Category category=new Category();
		category.setId(request.getParameter("id"));
		category.setName(request.getParameter("name"));
		category.setDescription(request.getParameter("description"));			
		boolean updateByCategoryId = categoryService.updateByCategoryId(category);
		if(updateByCategoryId){
			request.setAttribute("massage", "<div  class='form-control massage'>图书类别更新成功！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutcate").forward(request, response);
		}else{
			request.setAttribute("massage", "<div  class='form-control massage'>图书类别更新失败！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutcate").forward(request, response);
			
		}
	}

	private void deletePubl(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		boolean deleteByPublisherId = publisherService.deleteByPublisherId(id);
		if(deleteByPublisherId){
			request.setAttribute("massage", "<div  class='form-control massage'>出版社删除成功！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutpubl").forward(request, response);
		}else{
			request.setAttribute("massage", "<div  class='form-control massage'>出版社删除失败！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutpubl").forward(request, response);
			
		}
	}

	private void deleteCate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		boolean deleteByCategoryId = categoryService.deleteByCategoryId(id);
		if(deleteByCategoryId){
			request.setAttribute("massage", "<div  class='form-control massage'>图书种类删除成功！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutcate").forward(request, response);
		}else{
			request.setAttribute("massage", "<div  class='form-control massage'>图书种类删除失败！<a></a></div>");
			request.getRequestDispatcher("/book/OtherOpServlet?method=aboutcate").forward(request, response);
			
		}
	}

	/**
	 * 找到所有的出版社
	 */

	private void findPublisher(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		List<Publisher> aboutlists = publisherService.findAllPublisher(entiy);
		request.setAttribute("about", "出版社");
		request.setAttribute("anothor", "图书种类");
		request.setAttribute("deleteabout", "deletePubl");
		request.setAttribute("updateAbout", "updatePubl");
		request.setAttribute("addAbout", "addPubl");
		request.setAttribute("toOthor", "aboutcate");
		request.setAttribute("aboutlists", aboutlists);
		request.getRequestDispatcher("/manager/upaboutBooks.jsp").forward(request, response);
	}

	
	/**
	 * 找到所有的图书种类
	 */
	private void findCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		List<Category> aboutlists = categoryService.findAllCategory(entiy);
		request.setAttribute("about", "图书种类");
		request.setAttribute("anothor", "出版社");
		request.setAttribute("toOthor", "aboutpubl");
		request.setAttribute("deleteabout", "deleteCate");
		request.setAttribute("updateAbout", "updateCate");
		request.setAttribute("addAbout", "addCate");
		request.setAttribute("aboutlists", aboutlists);
		request.getRequestDispatcher("/manager/upaboutBooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
