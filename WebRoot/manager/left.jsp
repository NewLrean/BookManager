<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="${ctx }/index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/index/js/jquery.js"></script>

<script type="text/javascript">
$(function(){	
	//导航切换
	$(".menuson li").click(function(){
		$(".menuson li.active").removeClass("active")
		$(this).addClass("active");
	});
	
	$('.title').click(function(){
		var $ul = $(this).next('ul');
		$('dd').find('ul').slideUp();
		if($ul.is(':visible')){
			$(this).next('ul').slideUp();
		}else{
			$(this).next('ul').slideDown();
		}
	});
})	


</script>

<style type="text/css">
* {
    font-size: 9pt;
    border: 0;
    margin: 0;
    padding: 0;
}

div{
display: block;
}
</style>
</head>
<body>
<body style="background:#f0f9fd;">
	<div class="lefttop"><span></span>通讯录</div>
    
    <dl class="leftmenu">
        
    <dd>
    <div class="title">
    <span><img src="${ctx }/index/images/leftico01.png" /></span>管理信息
    </div>
    	<ul class="menuson">
        <li class="active"><cite></cite><a href="${ctx }/manager/index.jsp" target="rightFrame">首页模版</a><i></i></li>
        <li><cite></cite><a href="${ctx }/PageServlet" target="rightFrame">学生列表</a><i></i></li>
        <li><cite></cite><a href="${ctx }/manager/ShowBooksServlet" target="rightFrame">图书列表</a><i></i></li>
        <li><cite></cite><a href="${ctx }/register.jsp" target="rightFrame">添加编辑</a><i></i></li>
        <li><cite></cite><a  target="rightFrame">图片列表</a><i></i></li>
        <li><cite></cite><a  target="rightFrame">自定义</a><i></i></li>
        <li><cite></cite><a  target="rightFrame">常用工具</a><i></i></li>
        <li><cite></cite><a  target="rightFrame">信息管理</a><i></i></li>
        <li><cite></cite><a  target="rightFrame">Tab页</a><i></i></li>
        <li><cite></cite><a  target="rightFrame">404页面</a><i></i></li>
        </ul>    
    </dd>
        
    
    <dd>
    <div class="title">
    <span><img src="${ctx }/index/images/leftico02.png" /></span>其他设置
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">编辑内容</a><i></i></li>
        <li><cite></cite><a href="#">发布信息</a><i></i></li>
        <li><cite></cite><a href="#">档案列表显示</a><i></i></li>
        </ul>     
    </dd> 
    
    
    <dd>
    <div class="title">
    <span><img src="${ctx }/index/images/leftico03.png" /></span>编辑器
    </div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>    
    </dd>  
    
    
    <dd>
    <div class="title"><span><img src="${ctx }/index/images/leftico04.png" /></span>日期管理</div>
    <ul class="menuson">
        <li><cite></cite><a href="#">自定义</a><i></i></li>
        <li><cite></cite><a href="#">常用资料</a><i></i></li>
        <li><cite></cite><a href="#">信息列表</a><i></i></li>
        <li><cite></cite><a href="#">其他</a><i></i></li>
    </ul>
    
    </dd>   
    
    </dl>
</body>
</html>