package com.student.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FilenameUtils;

import com.google.gson.Gson;
import com.student.entiy.Book;
import com.student.service.BookService;
import com.student.service.impl.BookServiceImpl;
import com.student.util.IdGenrtor;

/**
 * Servlet implementation class BookServlet
 */
@WebServlet("/book/BookServlet")
public class BookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	final long MAX_SIZE = 5 * 1024 * 1024;
	BookService bookService=new BookServiceImpl();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		System.out.println(method);
		switch (method) {
		
		case "findBook":
			String id = request.getParameter("id");
			Book book2 = bookService.findOneBook(id);
			Gson gson=new Gson();
			String json = gson.toJson(book2);
			response.getWriter().append(json);
			break;
			
		case "deleteBook":
			String bookid = request.getParameter("bookid");
			if(bookService.deleteByBookId(bookid)){
				request.setAttribute("massage", "<div  class='form-control massage'>图书删除成功！<a></a></div>");
				request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
				return;
			}else{
				request.setAttribute("massage", "<div  class='form-control massage'>图书删除失败！<a></a></div>");
				request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
			}
			break;
		default:
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		boolean isMulti = ServletFileUpload.isMultipartContent(request);
		if(!isMulti){
			response.getWriter().append("您无法进行文件上传");
			request.getRequestDispatcher("/manager/showBooksServlet").forward(request, response);
			return;
		}
		String parameter = request.getParameter("method");
		
		DiskFileItemFactory factory=new DiskFileItemFactory();
		
		ServletFileUpload fileUpload=new ServletFileUpload(factory);
		fileUpload.setHeaderEncoding("utf-8");
		List<FileItem> items;
		Book book = new Book();
		try {
			items = fileUpload.parseRequest(request);
			
			for (FileItem item : items) {
				if(item.isFormField()){
					String name = item.getFieldName();
					String truename = FilenameUtils.getName(name);
				
					String string = item.getString("utf-8");
					BeanUtils.setProperty(book, truename,string);
					
				}else{
					try{
						
					
					fileUpload.setFileSizeMax(MAX_SIZE);
					}catch (Exception e) {
						response.getWriter().append("您上传的文件太大，不能超过3M");
					}
					response.setCharacterEncoding("utf-8");
					String name = item.getName();
					String Filename = FilenameUtils.getName(name);
					if(Filename==null||Filename==""){
						response.getWriter().append("您还没有传文件");
					}else{
						SimpleDateFormat dateFormat=new SimpleDateFormat("YYYY-MM-dd");
						String nowTime = dateFormat.format(new Date());
						ServletContext context=request.getServletContext();
						String realPath = context.getRealPath("/file/"+nowTime);
						
						File file=new File(realPath);
						if(!file.exists()){
							file.mkdirs();
						}
						
						factory.setSizeThreshold(4096);
						factory.setRepository(new File(realPath));
						InputStream in=item.getInputStream();
						int len=0;
						byte[] b=new byte[1024];
						String guid = IdGenrtor.getGUID();
						OutputStream os=new FileOutputStream(new File(realPath,guid+Filename));
						while((len=in.read(b))!=-1){
							os.write(b,0,len);
							os.flush();
						}
						
						
						
						in.close();
						os.close();
						book.setBooksImages("/file/"+nowTime+"/"+guid+Filename);
					}
					
					
				}
				item.delete();
			}
			
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		
		}catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		System.out.println(book);
		if("updateBook".equals(parameter)){
			if(bookService.updateByBookId(book)){
				request.setAttribute("massage", "<div  class='form-control massage'>图书修改成功！<a></a></div>");
				request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
				
			}else{
				request.setAttribute("massage", "<div  class='form-control massage'>图书修改失败！<a></a></div>");
				request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
			}
			return;
		}
		book.setId(IdGenrtor.getGUID());
		
		
		
		if(bookService.addBook(book)){
			request.setAttribute("massage", "<div  class='form-control massage'>图书添加成功！<a></a></div>");
			request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
		}else{
			request.setAttribute("massage", "<div  class='form-control massage'>图书添加失败！<a></a></div>");
			request.getRequestDispatcher("/manager/ShowBooksServlet").forward(request, response);
		}
	}

}
