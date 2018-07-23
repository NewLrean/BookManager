<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'cart.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.1.min.js"></script>	
<style type="text/css">
	a{
		t
	}
</style>
  </head>
  
  <body>
  <c:import url="/user/header.jsp"></c:import>
    
    <c:if test="${empty sessionScope.cart.map}">
    	<center> 您的购物车还是空的！<a href="${ctx }/user/UserBookServlet?method=showBooks">去选购</a></center>
    </c:if>
    
    <c:if test="${!empty sessionScope.cart.map}">
    <table align="center" cellpadding="0" cellspacing="0" border="1px" width="638px">
    
    		<tr>
    			<td>图书名称</td>
    			<td>图书单价</td>
    			<td>图书数量</td>
    			<td>图书小计</td>
    			<td>操作</td>
    		</tr>
    	<c:forEach items="${sessionScope.cart.map }" var="maps">
    		<tr>
    			<td style="display: none">${maps.value.id}</td>
    			<td>${maps.value.name}</td>
    			<td>${maps.value.price}</td>
    			<td><input type="text" style="width: 50px" value="${maps.value.number}" class="changeNum"/></td>
    			<td>${maps.value.subtotal}</td>
    			<td><a href="${ctx }/user/UserBookServlet?method=delCateItem&id=${maps.value.id}">删除</a></td>
    		</tr>
    	</c:forEach>
    	<tr>
    		<td colspan="4" align="right">总数：${sessionScope.cart.totalNum}&nbsp;总价：${sessionScope.cart.totlemoney}</td>
    		<td><a href="${ctx }/user/UserBookServlet?method=topayment">结算</a></td>
    	</tr>
    </table>
    </c:if>
    
    
    <script type="text/javascript">
    $(function(){
    var myReg = /^[0-9]*[1-9][0-9]*$/;  
    $(".changeNum").change(function(){
    if(myReg.test($(this).val()))
    	window.location.href="${ctx }/user/UserBookServlet?method=changeNum&num="+$(this).val()+"&id="+$(this).parent().siblings().eq(0).text();
    	
    })
    
    })
    
    	
    </script>
  </body>
</html>
