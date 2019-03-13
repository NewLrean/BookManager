<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

<title>Insert title here</title>

<style type="text/css">

h2{
text-align: center;
color: #ccc;
}

.ul1{
list-style: none;
	width: 1080px;
}

.ul1 li {
	float: left;
	width: 150px;
	margin-top: 50px;
}
</style>
	<link rel="stylesheet" type="text/css" href="${ctx }/css/bookstyle.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="${ctx }/bootstrap//bootstrap.min.js"></script>
	<link href="${ctx }/index/css/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="${ctx }/css/bootstrap.min.css"/>
</head>

<body>
<c:import url="/user/header.jsp"></c:import>
<h2>欢迎来到丁丁书城</h2>
<ul class="ul1">
<c:forEach items="${requestScope.bookslist }" var="b">
<li>
	<p style="height: 130px;"><img src="${ctx}${b.booksImages}"/></p>
	<p>${b.title }</p>
	<p>￥${b.unitPrice}</p>
	<p><a href="${ctx }/user/UserBookServlet?method=getCart&id=${b.id}">添加到购物车</a></p>
</li>
</c:forEach>
	
</ul>

<div class="rightinfo">
	<div class="pagin">
    
    	<div class="message">共<i class="blue" style="font-size: 15px;">${requestScope.entiy.pages}</i>页，当前显示第&nbsp;<i class="blue" style="font-size: 15px;">&nbsp;${requestScope.entiy.pageNum+1}</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="${ctx }/user/UserBookServlet?method=${requestScope.method}&pageNum=${requestScope.entiy.pageNum}"><span class="pagepre"></span></a></li>
        <c:forEach var="i" begin="0" end="${requestScope.entiy.pages-1 < 0 ? 0:requestScope.entiy.pages-1}" step="1"> 
       
        <li class="paginItem"><a href="${ctx }/user/UserBookServlet?method=${requestScope.method}&pageNum=${i+1}"><c:out value="${i+1}" /></a></li> 
			   
	
        
		   </c:forEach>
		
        
        
        
        <li class="paginItem"><a href="${ctx }/user/UserBookServlet?method=${requestScope.method}&pageNum=${requestScope.entiy.pageNum+2}"><span class="pagenxt"></span></a></li>
        
        <li class="paginItem" ><a style="width: 50px;" href="${ctx }/user/UserBookServlet?method=${requestScope.method}&pageNum=${requestScope.entiy.pages}">尾页</a></li> 
        
        
        <li style="float: left;">
        <form action="${ctx }/user/UserBookServlet" method="get">
        <input type="hidden" name="method" value="${requestScope.method}">
        <input type="text" id="page" pattern="^[0-9]*[1-9][0-9]*$" title="只能输入正整数" name="pageNum" value="" style="margin-left:5px; width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;" />
       
        <input type="submit" id="pageto"  value="跳转" style="width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;"/>
        </form> </li>
        </ul>
    </div>
   </div>
</body>
</html>