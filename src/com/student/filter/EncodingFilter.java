package com.student.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {

	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		System.out.println("字符过滤器销毁");  
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		
		HttpServletResponse res=(HttpServletResponse) response;
		HttpServletRequest req=(HttpServletRequest) request;
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");
		MyHttpServletRequest request2=new MyHttpServletRequest(req);
		chain.doFilter(request2, res);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub
		System.out.println("字符过滤器构造");
		
	}
	
	
}
class MyHttpServletRequest extends HttpServletRequestWrapper{
	private HttpServletRequest request=null;
	/**
	 * 
	 */
	public MyHttpServletRequest(HttpServletRequest request) {
		super(request);
		// TODO Auto-generated constructor stub
		this.request=request;
	}

	@Override
	public String getParameter(String name) {
		// TODO Auto-generated method stub
		String method = request.getMethod();
		String names=null;
		names = super.getParameter(name);
		
		if("GET".equals(method)){
			
		try {
			if(names!=null){
			String 	bname = new String(names.getBytes("iso-8859-1"),"utf-8");
			return bname;
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		}
		
		return names;
	}
	
	
}

