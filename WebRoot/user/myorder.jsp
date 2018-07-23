<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'myorder.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<style type="text/css">
h3{
text-align: center;
color: #ccc;
}
</style>
  </head>
  
  <body>
    <c:import url="/user/header.jsp"></c:import>
    <h3>我的订单</h3>
    
     <c:if test="${empty requestScope.orderslist}">
    	<center> 您还没有订单！！<a href="${ctx }/user/UserBookServlet?method=showBooks">去选购</a></center>
    </c:if>
    
    <c:if test="${!empty requestScope.orderslist}">
    <table align="center" cellpadding="0" cellspacing="0" border="1px" width="938px">
    		<tr><td colspan="5"><span style="color: red;">以下是待处理订单！！！</span></td></tr>
    		<tr>
    			<td>订单号</td>
    			<td>商品数量</td>
    			<td>支付总价</td>
    			<td>下单时间</td>
    			<td>操作</td>
    		</tr>
    		
    	<c:forEach items="${requestScope.orderslist }" var="o">
    		<c:if test="${o.status==0 }">
    		<tr>
    			<td>${o.ordernum}</td>
    			<td>${o.totalNum}</td>
    			<td>${o.totalmoney}</td>
    			<td>${o.ordertime}</td>
    			<td><a href="${ctx }/user/UserBookServlet?method=orderdetails&id=${o.id}">订单详情</a><a href="${ctx }/user/pay.jsp">付款</a></td>
    		</tr>
    		</c:if>
    	</c:forEach>
    	
    </table>
    </c:if>
    <table>
    
    </table>
  </body>
</html>
