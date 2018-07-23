package com.student.controller;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.student.cartbean.Cart;
import com.student.cartbean.CartItem;
import com.student.entiy.Book;
import com.student.entiy.OrderItem;
import com.student.entiy.Orders;
import com.student.entiy.PageEntiy;
import com.student.entiy.User;
import com.student.service.BookService;
import com.student.service.OrderService;
import com.student.service.UserService;
import com.student.service.impl.BookServiceImpl;
import com.student.service.impl.OrderServiceImpl;
import com.student.service.impl.UserServiceImpl;
import com.student.util.SecurityUtils;

import sun.print.PSPrinterJob.EPSPrinter;


/**
 * Servlet implementation class UserBookServlet
 */
@WebServlet("/user/UserBookServlet")
public class UserBookServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BookService bookService=new BookServiceImpl();
     private  PageEntiy entiy=new PageEntiy();
     HttpSession session=null;
     private UserService userService=new UserServiceImpl();
     private OrderService orderService=new OrderServiceImpl();
     
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserBookServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String method = request.getParameter("method");
		
		switch (method) {
		case "showBooks":
			findAllBooks(request,response);
			break;
		case "getCart":
			getCart(request,response);
			break;
			
		case "delCateItem":
			delCateItem(request,response);
			break;
		case "changeNum":
			changeNum(request,response);
			break;
			
		case "topayment":
			topayment(request,response);
			break;
			
		case "outlogin":
			outlogin(request,response);
			break;
			
		case "showOrders":
			showOrders(request,response);
			break;
		case "orderdetails":
			orderdetails(request,response);
			break;
		default:
			break;
		}
		
		
	}

	private void orderdetails(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		// TODO Auto-generated method stub
		String orderid = request.getParameter("id");
		
	}

	private void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session=request.getSession();
		User user = (User) session.getAttribute("user");
		if(user==null){
			request.setAttribute("message", "您还没有进行登录！！！");
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);
			return;
		}
		
		List<Orders> orderslist = orderService.findAllOrders(user.getId());
		request.setAttribute("orderslist", orderslist);
		request.getRequestDispatcher("/user/myorder.jsp").forward(request, response);
	}

	private void outlogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		session=request.getSession();
		session.removeAttribute("user");
		response.sendRedirect(request.getContextPath()+"/user/login.jsp");
	}

	private void topayment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		// TODO Auto-generated method stub
		session=request.getSession();
		
		User user = (User) session.getAttribute("user");
		if(user==null){
			request.setAttribute("message", "您还没有进行登录！！！");
			request.getRequestDispatcher("/user/login.jsp").forward(request, response);;
			return;
		}
		Cart cart = (Cart) session.getAttribute("cart");
		List<OrderItem> items=new ArrayList<OrderItem>();
		if(cart==null){
			
			request.setAttribute("message", "您的购物车已清空！！！");
			request.getRequestDispatcher("/user/cart.jsp").forward(request, response);
			return;
			
		}
		
		Map<String, CartItem> map = cart.getMap();
		Set<String> keySet = map.keySet();
		for (String string : keySet) {
			CartItem cartItem = map.get(string);
			OrderItem item=new OrderItem();
			item.setBookid(cartItem.getId());
			item.setName(cartItem.getName());
			item.setNumber(cartItem.getNumber());
			item.setSubprice(cartItem.getSubtotal());
			items.add(item);
		}
		
		Orders orders=new Orders();
		orders.setTotalNum(cart.getMap().keySet().size());
		orders.setUserid(user.getId());
		orders.setTotalNum(cart.getTotalNum());
		orders.setTotalmoney(cart.getTotlemoney());
		orders.setItems(items);
		Date date=new Date();
		SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String format2 = format.format(date);
		orders.setOrdertime(format2);
		boolean addOrder = orderService.addOrder(orders);
		if(addOrder){
			session.removeAttribute("cart");
			List<Orders> orderslist = orderService.findAllOrders(user.getId());
			request.setAttribute("orderslist", orderslist);
			request.getRequestDispatcher("/user/myorder.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/user/payerror.jsp");
		}
		
		
	}

	private void changeNum(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String num=request.getParameter("num");
		int number = 1;
		if(num!=null&&Integer.valueOf(num)>0){
			
			number=Integer.valueOf(num);
			
		}
		String id = request.getParameter("id").trim();
		Book book = bookService.findOneBook(id);
		Cart cart = (Cart) session.getAttribute("cart");
		Set<String> keySet2 = cart.getMap().keySet();
		for (String key : keySet2) {
			
			if(key.equals(id)){
				cart.getMap().get(key).setPrice(book.getUnitPrice());
				cart.getMap().get(key).setNumber(number);
				break;
			}
		}
		Cart cart2=new Cart(cart.getMap());
		Set<String> keySet = cart2.getMap().keySet();
		total(cart2, keySet);
		cart=null;
		session.setAttribute("cart", cart2);
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
		
	}

	private void delCateItem(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String id = request.getParameter("id");
		session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart.getMap().keySet().contains(id))
		cart.getMap().remove(id);
		Cart cart2=new Cart(cart.getMap());
		Set<String> keySet = cart2.getMap().keySet();
		total(cart2,keySet);
		
		cart=null;
		session.setAttribute("cart", cart2);
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
	}

	private void getCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		Map<String, CartItem> map=null;
		if(cart==null){
			map=new HashMap<String, CartItem>();
		}else{
			map=cart.getMap();
		}
		
		String id = request.getParameter("id");
		Book book = bookService.findOneBook(id);
		CartItem cartItem=new CartItem();
		cartItem.setId(id);
		cartItem.setName(book.getTitle());
		cartItem.setPrice(book.getUnitPrice());
		
		Set<String> keySet = map.keySet();
		
		
		if(keySet.contains(cartItem.getId())){
			CartItem cartItem2 = map.get(cartItem.getId());
			Integer number = cartItem2.getNumber();
			cartItem.setNumber(number+1);
			map.put(cartItem.getId(), cartItem);
		}else{
			map.put(cartItem.getId(), cartItem);
		}
		cart=new Cart(map);
		total(cart,keySet);
		
		session.setAttribute("cart", cart);
		response.sendRedirect(request.getContextPath()+"/user/cart.jsp");
	}
	
	private void total(Cart cart,Set<String> keySet){
		float totlemoney=0;
		Map<String, CartItem> map=cart.getMap();
		for (String string : keySet) {
			CartItem cartItem2 = map.get(string);
			
				totlemoney=totlemoney+cartItem2.getSubtotal();
		}
		int totalNum=0;
		for (String string : keySet) {
			CartItem cartItem2 = map.get(string);
			
				totalNum=totalNum+cartItem2.getNumber();
		}
		cart.setTotalNum(totalNum);
		cart.setTotlemoney(totlemoney);
	}

	/**
	 * 查询出所有图书
	 */
	private void findAllBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// TODO Auto-generated method stub
		
		String pageNum2 = request.getParameter("pageNum");
		Integer pageNum=0;
		if(pageNum2!=null){
			pageNum = Integer.valueOf(pageNum2)-1;
		}
		
		
		entiy.setPageSize(20);
		int pages=0;
		List<Book> bookslist=null;
		
		int findtotalbooks = bookService.findtotalbooks();
		pages=findtotalbooks%entiy.getPageSize()==0?findtotalbooks/entiy.getPageSize():findtotalbooks/entiy.getPageSize()+1;
		if(pageNum<0){
			pageNum=0;
		}else if(pageNum>pages-1){
			pageNum=pages-1;
		}
		entiy.setPageNum(pageNum);
		entiy.setPages(pages);
		bookslist = bookService.findSomeBook(entiy);
		request.setAttribute("method", "showBooks");
		request.setAttribute("entiy", entiy);
		request.setAttribute("bookslist",bookslist);
		request.getRequestDispatcher("/user/index.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String method = request.getParameter("method");
		
		if("register".equals(method)){
			toregister(request,response);
		}else if("tologin".equals(method)){
			tologin(request,response);
		}else if("vailtion".equals(method)){
			tovailtion(request,response);
		}
			
		
	}

	private void tovailtion(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		if(userService.findValidation(username)!=null){
			response.getWriter().append("该用户名已被注册");
		}else{
			response.getWriter().append("该用户名可以使用！");
			}
	}

	private void tologin(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		session=request.getSession();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		User user = userService.login(username, password);
		if(user!=null){
			session.setAttribute("user", user);
			request.setAttribute("message", "登陆成功！！！");
			response.sendRedirect(request.getContextPath()+"/user/UserBookServlet?method=showBooks");
		}else{
			request.setAttribute("message", "登陆失败！！！");
			response.sendRedirect(request.getContextPath()+"/user/login.jsp");
		}
	}

	private void toregister(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		User user=new User();
		try {
			
			BeanUtils.populate(user, request.getParameterMap());
			user.setPassword(SecurityUtils.MD5(user.getPassword()));
			if(userService.addUser(user)){
				response.getWriter().print("注册成功！");
				response.sendRedirect(request.getContextPath()+"/user/login.jsp");
			}else{
				response.getWriter().print("注册失败！");
				response.sendRedirect(request.getContextPath()+"/user/register.jsp");
			}
			System.out.println(user);
			
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
