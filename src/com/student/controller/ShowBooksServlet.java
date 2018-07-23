package com.student.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.student.entiy.Book;
import com.student.entiy.Category;
import com.student.entiy.PageEntiy;
import com.student.entiy.Publisher;
import com.student.service.BookService;
import com.student.service.CategoryService;
import com.student.service.PublisherService;
import com.student.service.impl.BookServiceImpl;
import com.student.service.impl.CategoryServiceImpl;
import com.student.service.impl.PublisherServiceImpl;

/**
 * Servlet implementation class ShowBooksServlet
 */
@WebServlet("/manager/ShowBooksServlet")
public class ShowBooksServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private BookService bookService=new BookServiceImpl();
	private CategoryService categoryService=new CategoryServiceImpl();
	private PublisherService publisherService=new PublisherServiceImpl();
     private  PageEntiy entiy=new PageEntiy();
     HttpSession session=null;
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ShowBooksServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		entiy.setPageNum(0);
		session=request.getSession();
		String search = request.getParameter("search");
		String categoryId=request.getParameter("categoryId");
		if(categoryId!=null)
		System.out.println(categoryId.length());
		String publisherId = request.getParameter("publisherId");
		
		String objfan = request.getParameter("objfan");
		String pageNum = request.getParameter("pageNum");
		Integer pagen =null;
		String search2=null;
		
		
		if(pageNum!=null){
			if(pageNum.trim().equals("")){
				pagen= Integer.valueOf(0);
			}else{
				try{
					pagen= Integer.parseInt(pageNum)-1;
				}catch(NumberFormatException n){
					pagen=0;
				}
			}
		}
		entiy.setPageSize(20);
		List<Book> bookslist = null;
		int findtotalbooks=0;
		int pages=0;
		if(objfan!=null){
			
		switch (objfan.trim()) {
		case "allbooks":
			
			
			findtotalbooks = bookService.findtotalbooks();
			pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
			if(pagen<0){
				pagen=0;
			}else if(pagen>=pages-1){
				pagen=pages-1;
			}
			entiy.setPageNum(pagen);
			entiy.setObjfan("allbooks");
			entiy.setPages(pages);
			bookslist = bookService.findSomeBook(entiy);
			request.setAttribute("search", "所有书籍");
			request.setAttribute("entiy", entiy);
			request.setAttribute("bookslist",bookslist);
			break;

			case "titlebooks":
				search2 = (String) session.getAttribute("search");
				if(search2!=null){
				findtotalbooks = bookService.findTitletotal(search2, entiy);
				pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
				if(pagen<0){
					pagen=0;
				}else if(pagen>=pages-1){
					pagen=pages-1;
				}
				entiy.setPageNum(pagen);
				entiy.setObjfan("titlebooks");
				entiy.setPages(pages);
				session.setAttribute("search", search2);
				request.setAttribute("entiy", entiy);
				request.setAttribute("search", search);
				bookslist = bookService.findTitleBook(search2, entiy);
				request.setAttribute("bookslist",bookslist);
				}
				break;
			case "category":
				search2 = (String) session.getAttribute("categoryId");
				if(search2!=null){
					findtotalbooks = categoryService.findtotalCategories(search2);
					pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
					if(pagen<0){
						pagen=0;
					}else if(pagen>=pages-1){
						pagen=pages-1;
					}
					entiy.setPageNum(pagen);
					entiy.setObjfan("category");
					entiy.setPages(pages);
					session.setAttribute("categoryId", search2);
					request.setAttribute("entiy", entiy);
					Category oneCategory = categoryService.findOneCategory(search2, entiy);
					bookslist = oneCategory.getListBooks();
					request.setAttribute("bookslist",bookslist);
				}
				break;
				
			case "publisher":
				search2 = (String) session.getAttribute("publisherId");
				if(search2!=null){
					findtotalbooks = publisherService.findtotalPublishers(search2);
					pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
					if(pagen<0){
						pagen=0;
					}else if(pagen>=pages-1){
						pagen=pages-1;
					}
					entiy.setPageNum(pagen);
					entiy.setObjfan("publisher");
					entiy.setPages(pages);
					session.setAttribute("publisherId", search2);
					request.setAttribute("entiy", entiy);
					Publisher publisher= publisherService.findOnePublisher(search2, entiy);
					bookslist = publisher.getListBooks();
					request.setAttribute("bookslist",bookslist);
				}
				break;
		default:
			break;
		}
		
		}
			
			
		
		
		List<Category> categoriesList =categoryService.findAllCategory(entiy);
		request.setAttribute("categoriesList", categoriesList);
		if(categoryId!=null&&categoryId.length()>0&&bookslist==null){
			Category oneCategory = categoryService.findOneCategory(categoryId,entiy);
			findtotalbooks = categoryService.findtotalCategories(categoryId);
			pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
			entiy.setObjfan("category");
			entiy.setPages(pages);
			session.setAttribute("categoryId", categoryId);
			request.setAttribute("entiy", entiy);
			request.setAttribute("search", oneCategory.getName());
			bookslist = oneCategory.getListBooks();
			request.setAttribute("bookslist",bookslist);
		}
		
		
		List<Publisher> publisherslist =publisherService.findAllPublisher(entiy);
		request.setAttribute("publisherslist", publisherslist);
		
		
		if(publisherId!=null&&publisherId.length()>0&&bookslist==null){
			Publisher publisher= publisherService.findOnePublisher(publisherId, entiy);
			findtotalbooks = publisherService.findtotalPublishers(publisherId);
			pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
			entiy.setObjfan("publisher");
			entiy.setPages(pages);
			session.setAttribute("publisherId", publisherId);
			request.setAttribute("entiy", entiy);
			request.setAttribute("search", publisher.getName());
			bookslist = publisher.getListBooks();
			request.setAttribute("bookslist",bookslist);
		}
		
		if(search==null&&bookslist==null){
			bookslist = bookService.findSomeBook(entiy);
			findtotalbooks = bookService.findtotalbooks();
			pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
			entiy.setObjfan("allbooks");
			entiy.setPages(pages);
			request.setAttribute("entiy", entiy);
			request.setAttribute("search", "所有书籍");
			request.setAttribute("bookslist",bookslist);
			
		}
		if(search!=null&&bookslist==null){
				bookslist = bookService.findTitleBook(search, entiy);
				findtotalbooks = bookService.findTitletotal(search, entiy);
				session.setAttribute("search", search);
				pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
				entiy.setObjfan("titlebooks");
				entiy.setPages(pages);
				request.setAttribute("entiy", entiy);
				request.setAttribute("search", search);
				request.setAttribute("bookslist",bookslist);
		}
		request.getRequestDispatcher("/manager/allbooks.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
