<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
  <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
	<c:set var="ctx" value="${pageContext.request.contextPath }"></c:set>
<!DOCTYPE html>
<html>
<head>
<title>登录界面</title>
<link href="${ctx }/index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/index/js/jquery.js"></script>
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<script type="text/javascript">
$(function(){	
	//顶部导航切换		
	$(".nav li a").click(function(){
		$(".nav li a.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
</head>

<body style="background:url(${ctx }/index/images/topbg.gif) repeat-x;">

    <div class="topleft">
    <a href="main.jsp" target="_parent"><img src="${ctx }/index/images/logo.png" title="系统首页" /></a>
    </div>
        
    <ul class="nav">
    <li><a href="${ctx }/index.jsp" target="rightFrame" class="selected"><img src="${ctx }/index/images/icon01.png" title="工作台" /><h2>工作台</h2></a></li>
    <li><a href="${ctx }/index.jsp" target="rightFrame"><img src="${ctx }/index/images/icon02.png" title="模型管理" /><h2>模型管理</h2></a></li>
    <li><a href="${ctx }/index.jsp"  target="rightFrame"><img src="${ctx }/index/images/icon03.png" title="模块设计" /><h2>模块设计</h2></a></li>
    <li><a href="${ctx }/index.jsp"  target="rightFrame"><img src="${ctx }/index/images/icon04.png" title="常用工具" /><h2>常用工具</h2></a></li>
    <li><a href="${ctx }/index.jsp" target="rightFrame"><img src="${ctx }/index/images/icon05.png" title="文件管理" /><h2>文件管理</h2></a></li>
    <li><a href="${ctx }/index.jsp"  target="rightFrame"><img src="${ctx }/index/images/icon06.png" title="系统设置" /><h2>系统设置</h2></a></li>
    </ul>
            
    <div class="topright">    
    <ul>
    <li><span><img src="${ctx }/index/images/help.png" title="帮助"  class="helpimg"/></span><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    <li><a href="${ctx }/MainServlet?method=goout" target="_parent">退出</a></li>
    </ul>
     
    <div class="user">
    <span>${sessionScope.user}</span>
    <i>消息</i>
    <b>5</b>
    </div>    
    
    </div>
    </body>
</html>