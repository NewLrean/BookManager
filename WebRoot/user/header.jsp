<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'header.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<style type="text/css">
ul{
	list-style: none;
	overflow: hidden;
	text-align: center;
	display: table;
margin:0 auto;
height:40px;
padding:0;
}
	ul li {
		float: left;
		margin-left: 50px;
	}
	
	ul li a{
		font-size: 15px;
	}
	
	h1{
text-align: center;
padding: 0 50px;
font-family: cursive !important;
}
h4{
text-align: center;
font-size: 16px;
color: red;}
</style>
  </head>
  
  <body>
    <h1 style="text-align: center;">丁丁图书商城</h1>
    <ul>
    	<li><a href="${ctx }/user/UserBookServlet?method=showBooks">主页</a></li>
    	<c:if test="${sessionScope.user ==null}">
    	<li><a href="${ctx }/user/login.jsp">登录</a></li>
    	<li><a href="${ctx }/user/register.jsp">注册</a></li>
    	</c:if>
    	<c:if test="${!empty sessionScope.user}">
    	<li><span style="font-size: 15px;">欢迎用户：${sessionScope.user.username}</span></li>
    	<li><a href="${ctx }/user/UserBookServlet?method=outlogin">退出登录</a></li>
    	</c:if>
    	
    	<li><a href="${ctx }/user/UserBookServlet?method=showOrders">我的订单</a> </li>
    	<li><a href="${ctx }/user/cart.jsp">购物车</a></li>
    </ul>
	<h4> ${requestScope.message }</h4>
  <hr/>
