<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'allbooks.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${ctx }/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="${ctx }/css/bookstyle.css">
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="${ctx }/bootstrap//bootstrap.min.js"></script>
	<link href="${ctx }/index/css/style.css" rel="stylesheet" type="text/css" />
	
	<style type="text/css">
		span{
			display: inline-block;
		}
		
		.massage{
background: #ddd;
font-size: 20px;
width: 500px;
margin: 0 auto;
padding: 0px 20px;
color: blueviolet;
font-family: cursive;

}

.massage a {
    display: block;
    background: url(${ctx }/index/images/close.png) no-repeat;
    width: 22px;
    height: 22px;
    float: right;
    margin-right: 7px;
    margin-top:5px;
    cursor: pointer;
}
	</style>
	
	
	
	<script type="text/javascript">
	window.onload = function(){
	$(".massage a").click(function(){
  $(".massage").fadeOut(200);
  
});

	
  var btn = document.getElementById('btn');
  var timer = null;
  var isTop = true;
  //获取页面可视区高度
  var clientHeight = document.documentElement.clientHeight;
 console.log(clientHeight)
   
  //滚动条滚动时触发
  window.onscroll = function() {
  //显示回到顶部按钮
    var osTop = document.documentElement.scrollTop || document.body.scrollTop;
    if (osTop >= clientHeight) {
      btn.style.display = "block";
    } else {
      btn.style.display = "none";
    };
  //回到顶部过程中用户滚动滚动条，停止定时器
    if (!isTop) {
      clearInterval(timer);
    };
    isTop = false;
 
  };
 
  btn.onclick = function() {
    //设置定时器
    timer = setInterval(function(){
      //获取滚动条距离顶部高度
      var osTop = document.documentElement.scrollTop || document.body.scrollTop;
      console.log('osTop '+osTop);
      var ispeed = Math.floor(-osTop / 7);
       console.log('ispeed '+ispeed);
      document.documentElement.scrollTop = document.body.scrollTop = osTop+ispeed;
      //到达顶部，清除定时器
      if (osTop == 0) {
        clearInterval(timer);
      };
      isTop = true;
       
    },30);
  };
};
	
	</script>
  </head>
  
  
  <body>
  
  <div style="position: fixed; bottom: 20px;left: 10px;z-index: 5; cursor: pointer;">
  	<span>当前位置 ： </span><br/>
  	<span>--》 ${requestScope.search } </span>
  	<div id="btn" style="color: red;font-size: 14px;">返回顶部</div>
  </div>
  
   ${requestScope.massage}
  
  <div class="search">
  <div class="tools" style="overflow: hidden;margin-top: 14px;">
    
    	<ul class="toolbar">
        
       	
       <button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
			添加书籍
		</button>
		<a href="${ctx }/book/OtherOpServlet?method=aboutpubl">
		<button class="btn btn-primary btn-lg">
			出版社操作
		</button>
		</a>
		
		<a href="${ctx }/book/OtherOpServlet?method=aboutcate">
		<button class="btn btn-primary btn-lg">
			书类别操作
		</button>
        </a>
        
        </ul>
        
        
        
						<form id="form" action="${ctx }/manager/ShowBooksServlet" method="get">
							<p class="searchform">
							<div class="col-md-9" >
							<div class="col-md-10">
								<input type="text"  style="font-size: 16px;" name="search" required="required" class="form-control" placeholder="输入查询内容">
								
							</div>
							<button type="submit" class="btn btn-primary">搜索</button>
							</div>
							</p>
						</form>
						<ul class="toolbar1">
        <li><span><img src="${ctx }/index/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
	</div>	
	
	
	<div class="catebox">
	<div class="outcate">
		<div class="col-md-2 catetxt">
			<div style="background: #dddddd; height: 100%;" >
				<p style="padding: 10px;color: #b0a59f">所有分类</p>
			</div>
		</div>
		<div class="srll">
		
		<div style="height: 20px;margin-left: 5px;margin-top: 5px;position: absolute;width: 100%;
    overflow: hidden;">
			<form  method="get" style="float: left;">
				<label>图书类别：</label>
				<input type="text" style="    border: 1px solid#dddddd;" id="Onecategory" name="Onecategory"/>
				
			</form>
			<span style="float: right;margin-right: 100px;"><a style="cursor: pointer;font-size: 14px;" id="showallcate">展开></a>
			<a style="cursor: pointer;font-size: 14px;display: none" id="outallcate">收起></a></span>
			
			</div>
			<div style="width: 100%;margin-top: 35px;" class="abuo" >
		<div class="col-md-10" style="height: 100%;margin-top: 5px;">
		
		<c:forEach items="${requestScope.categoriesList }" var="c">
			<a href="${ctx }/manager/ShowBooksServlet?categoryId=${c.id}"><div class="col-md-2 cateid">
				${c.name}
			</div></a>
		</c:forEach>
			
		</div>
		<div style="clear: both;"></div>
		</div>
	</div>
	</div>
	</div>
	<div class="catebox" style="border: 1px solid #d1ccc7;">
	<div class="outcate">
		<div class="col-md-2 catetxt">
			<div style="background: #dddddd; height: 100%;" >
				<p style="padding: 10px;color: #b0a59f">所有出版社分类</p>
			</div>
		</div>
		<div class="srll">
			<div style="height: 30px;margin-left: 5px;margin-top: 5px;position: absolute;width: 100%;
    overflow: hidden;">
			<form  method="get" style="float: left;">
				<label>出版社：</label>
				<input style="    border: 1px solid#dddddd;" type="text" id="Onepublisher" name="Onepublisher"/>
				
			</form>
			<span style="float: right;margin-right: 100px;">
			<a style="cursor: pointer;font-size: 14px;" id="showallpubli">展开></a>
			<a style="cursor: pointer;font-size: 14px;display: none" id="outallpubli">收起></a></span>
			
			</div>
			
			<div style="width: 100%;margin-top: 35px;" class="abuo" >
		<div class="col-md-10" style="height: 100%;margin-top: 5px;">
		<c:forEach items="${requestScope.publisherslist }" var="p">
			<a href="${ctx }/manager/ShowBooksServlet?publisherId=${p.id}"><div class="col-md-2 cateid">
				${p.name}
			</div></a>
		</c:forEach>
			</div>
			<div style="clear: both;"></div>
			</div>
			</div>
		</div>
	</div>
	</div>
  <div class="inoutbox">
  
    <div class="row">
    	<c:forEach items="${requestScope.bookslist }" var="b">
    		<div class="col-md-3 li" >
    			<div class="inbox_li">
    				<div class="bor">
    			<p style="height: 190px;"><img src="${b.booksImages }"/></p>
    			<p><span style="font-size: 18px;color: #ff0036"><em style="font-size: 12px;">￥</em>${b.unitPrice}</span></p>
    			<p>
    			<c:choose> 
   			 	<c:when test="${fn:length(b.title) > 15}"> 
     			<c:out value="${fn:substring(b.title, 0, 15)}......" /> 
    			</c:when> 
    			<c:otherwise> 
    			<c:out value="${b.title}" /> 
    			</c:otherwise> 
   				</c:choose> 
   				</p>
   				<p><span>月成交</span><span style="color: #b57c5b;font-size: 14px;font-weight: 700;">${b.souhuo}</span></p>
   				</div>
   				<p><span style="color: #b57c5b;font-size: 15px;"><a style="cursor: pointer;">点击进入查看详情</a>
   				<a data-toggle="modal" data-target="#updateModal" class="updateBook" style="cursor: pointer;margin-left: 5px">修改图书</a>
   				<a data-toggle="modal" data-target="#deleteModal" class="deleteBook" style="color:#acbd5f; cursor: pointer;margin-left: 5px">删除图书</a>
   				<a style="display: none">${b.id}</a>
   				</span></p>
   				
   				</div>
    		</div>
    	</c:forEach>
  		
		</div>
</div>

<div class="rightinfo">
	<div class="pagin">
    
    	<div class="message">共<i class="blue" style="font-size: 15px;">${requestScope.entiy.pages}</i>页，当前显示第&nbsp;<i class="blue" style="font-size: 15px;">&nbsp;${requestScope.entiy.pageNum+1}</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="${ctx }/manager/ShowBooksServlet?objfan=${requestScope.entiy.objfan}&pageNum=${requestScope.entiy.pageNum}"><span class="pagepre"></span></a></li>
        <c:forEach var="i" begin="0" end="${requestScope.entiy.pages-1<0?0:requestScope.entiy.pages-1}" step="1"> 
       
        <li class="paginItem"><a href="${ctx }/manager/ShowBooksServlet?objfan=${requestScope.entiy.objfan}&pageNum=${i+1}"><c:out value="${i+1}" /></a></li> 
			   
		<%-- <c:if test="${requestScope.entiy.pages>i&&i >=5}">
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        </c:if> --%>
        
		   </c:forEach>
		
        
        
        
        <li class="paginItem"><a href="${ctx }/manager/ShowBooksServlet?objfan=${requestScope.entiy.objfan}&pageNum=${requestScope.entiy.pageNum+2}"><span class="pagenxt"></span></a></li>
        
        <li class="paginItem" ><a style="width: 50px;" href="${ctx }/manager/ShowBooksServlet?objfan=${requestScope.entiy.objfan}&pageNum=${requestScope.entiy.pages}">尾页</a></li> 
        
        
        <li style="float: left;">
        <form action="${ctx }/manager/ShowBooksServlet" method="get">
        <input type="hidden" name="objfan" value="${requestScope.entiy.objfan}">
        <input type="text" id="page" pattern="^[0-9]*[1-9][0-9]*$" title="只能输入正整数" name="pageNum" value="" style="margin-left:5px; width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;" />
       
        <input type="submit" id="pageto"  value="跳转" style="width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;"/>
        </form> </li>
        </ul>
    </div>
   </div>
   
   
   
   
   
   
   
   <!-- ***************************书籍添加**************************** -->
   <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					书籍的添加
				</h4>
			</div>
			<div class="modal-body">
			
	<form role="form" action="${ctx }/book/BookServlet" method="post" enctype="multipart/form-data">
	<div class="form-group">
		<label for="name">书籍名称</label>
		<input type="text" class="form-control" name="title" required="required"
			   placeholder="请输入书籍名称">
	</div>
	<div class="form-group">
		<label for="name">书的作者</label>
		<input type="text" class="form-control" name="author" required="required"
			   placeholder="请输入书的作者">
	</div>
	<div class="form-group">
		<label for="name">选择书的出版社</label>
		<div class="col-lg-6">
				<div class="input-group">
				<input type="hidden" class="form-control" readonly="readonly" id="publisherId" name="publisherId" required="required">
					<input type="text" class="form-control" readonly="readonly" id="publisherName" required="required">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							选择图书出版社
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right" style="overflow: auto;height: 300px;">
						<c:forEach items="${requestScope.publisherslist }" var="p">
			<li>
				<a style="cursor: pointer;width: 100%" class="addpubl">${p.name}</a>
				<a style="cursor: pointer; display: none;">${p.id}</a>
			</li>
		</c:forEach>
							
						</ul>
					</div><!-- /btn-group -->
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
	</div>
	<div class="form-group">
		<label for="name">出版时间</label>
		<input type="date" class="form-control"  name="publishDate" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">国际书号</label>
		<input type="text" class="form-control"  name="isbn" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">页数</label>
		<input type="number" class="form-control"  name="wordsCount" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">书的单价</label>
		<input type="number" class="form-control" name="unitPrice" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">内容描述</label>
		<textarea class="form-control"  rows="3" name="contentDescription"></textarea>
	</div>
	<div class="form-group">
		<label for="name">作者描述</label>
		<textarea  rows="3" class="form-control" name="aurhorDescription"></textarea>

	</div>
	
	
	<div class="form-group">
		<label for="name">本书概述</label>
		<textarea  rows="3" class="form-control"  name="editorComment"></textarea>
	</div>
	
	<div class="form-group">
		<label for="name">本书目录</label>
		<textarea  rows="3" class="form-control"  name="TOC"></textarea>
	</div>
	<div class="form-group">
		<label for="name">选择书的种类</label>
		<div class="col-lg-6">
				<div class="input-group">
				<input type="hidden" class="form-control" readonly="readonly" id="categoryId" name="categoryId" required="required">
					<input type="text" class="form-control" readonly="readonly" id="categoryName" required="required">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							选择图书种类
							<span class="caret"></span>
						</button>
						
						
						<ul class="dropdown-menu pull-right" style="overflow: auto;height: 300px;">
						<c:forEach items="${requestScope.categoriesList }" var="c">
			<li>
				<a style="cursor: pointer;width: 100%" class="addcate">${c.name}</a>
				<a style="cursor: pointer; display: none;">${c.id}</a>
			</li>
		</c:forEach>
							
						</ul>
					</div><!-- /btn-group -->
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
	</div>
	<div class="form-group">
		<label for="inputfile">图片输入</label>
		<input type="file" id="inputfile" name="booksImages" required="required">
		
	</div>
	
	<div class="form-group">
		<label for="inputfile">图书现有库存</label>
		<input type="text" class="form-control"  
			   placeholder="请输入名称" name="quantity" required="required">
		
	</div>
	
	<div class="form-group">
		<label for="name">送货地址</label>
		<input type="text" class="form-control"  
			   placeholder="请输入名称" name="address" required="required">
	</div>
	<div class="form-group">
		<label>
			 是否包邮,请选择
		</label>
		<input type="radio" name="baoyou" value="0" checked="checked">不包邮
		<input type="radio" name="baoyou" value="1">包邮
	</div>
	<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="submit" class="btn btn-primary">
					提交
				</button>
			</div>
</form>
			</div>
			
			
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- ***************************书籍修改**************************** -->
<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					书籍的修改
				</h4>
			</div>
			<div class="modal-body">
			
	<form role="form" action="${ctx }/book/BookServlet?method=updateBook" method="post" enctype="multipart/form-data">
	
	
		
	<div class="form-group">
		<label for="name">书籍id</label><br/>
		<label for="name" id="bookid2"></label>
		<input type="hidden" class="form-control" id="bookid4"  name="id" required="required"
			   placeholder="请输入书籍名称">
	</div>
	
	<div class="form-group">
		<label for="name">书籍名称</label>
		<input type="text" class="form-control" id="title2" name="title" required="required"
			   placeholder="请输入书籍名称">
	</div>
	<div class="form-group">
		<label for="name">书的作者</label>
		<input type="text" class="form-control" id="author2" name="author" required="required"
			   placeholder="请输入书的作者">
	</div>
	<div class="form-group">
		<label for="name">选择书的出版社</label>
		<div class="col-lg-6">
				<div class="input-group">
				<input type="hidden" class="form-control" readonly="readonly" id="publisherId2" name="publisherId" required="required">
					<input type="text" class="form-control" readonly="readonly" id="publisherName2" required="required">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							选择图书出版社
							<span class="caret"></span>
						</button>
						<ul class="dropdown-menu pull-right" style="overflow: auto;height: 300px;">
						<c:forEach items="${requestScope.publisherslist }" var="p">
			<li>
				<a style="cursor: pointer;width: 100%" class="addpubl2">${p.name}</a>
				<a style="cursor: pointer; display: none;">${p.id}</a>
			</li>
		</c:forEach>
							
						</ul>
					</div><!-- /btn-group -->
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
	</div>
	<div class="form-group">
		<label for="name">出版时间</label>
		<input type="text" class="form-control" id="publishDate2"  name="publishDate" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">国际书号</label>
		<input type="text" class="form-control" id="isbn2" name="isbn" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">页数</label>
		<input type="number" class="form-control" id="wordsCount2"  name="wordsCount" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">书的单价</label>
		<input type="text" class="form-control" id="unitPrice2" name="unitPrice" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">内容描述</label>
		<textarea class="form-control"  rows="3" id="contentDescription2" name="contentDescription"></textarea>
	</div>
	<div class="form-group">
		<label for="name">作者描述</label>
		<textarea  rows="3" class="form-control" id="aurhorDescription2" name="aurhorDescription"></textarea>

	</div>
	
	
	<div class="form-group">
		<label for="name">本书概述</label>
		<textarea  rows="3" class="form-control" id="editorComment2" name="editorComment"></textarea>
	</div>
	
	<div class="form-group">
		<label for="name">本书目录</label>
		<textarea  rows="3" class="form-control" id="TOC2"  name="TOC"></textarea>
	</div>
	<div class="form-group">
		<label for="name">选择书的种类</label>
		<div class="col-lg-6">
				<div class="input-group">
				<input type="hidden" class="form-control" readonly="readonly" id="categoryId2" name="categoryId" required="required">
					<input type="text" class="form-control" readonly="readonly" id="categoryName2" required="required">
					<div class="input-group-btn">
						<button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
							选择图书种类
							<span class="caret"></span>
						</button>
						
						
						<ul class="dropdown-menu pull-right" style="overflow: auto;height: 300px;">
						<c:forEach items="${requestScope.categoriesList }" var="c">
			<li>
				<a style="cursor: pointer;width: 100%" class="addcate2">${c.name}</a>
				<a style="cursor: pointer; display: none;">${c.id}</a>
			</li>
		</c:forEach>
							
						</ul>
					</div><!-- /btn-group -->
				</div><!-- /input-group -->
			</div><!-- /.col-lg-6 -->
	</div>
	<div class="form-group">
		<label for="inputfile">现有图片</label>
		<input type="text" class="form-control" id="booksImagesold" name="booksImages" readonly="readonly" />
		<input type="button" class="btn" value="添加新的图片" id="addnewimg"/>
		<input type="file" class="form-control" id="booksImagesnew" style="display: none">
		
	</div>
	
	<div class="form-group">
		<label for="inputfile">图书现有库存</label>
		<input type="text" class="form-control"  
			   placeholder="请输入名称" name="quantity" id="quantity2" required="required">
		
	</div>
	
	<div class="form-group">
		<label for="name">送货地址</label>
		<input type="text" class="form-control"  
			   placeholder="请输入名称" name="address" id="address2" required="required">
	</div>
	<div class="form-group">
		<label>
			 是否包邮,请选择
		</label>
		<input type="radio" name="baoyou" class="baoyou3" value="0">不包邮
		<input type="radio" name="baoyou" class="baoyou3" value="1">包邮
	</div>
	<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="submit" class="btn btn-primary">
					提交
				</button>
			</div>
</form>
			</div>
			
			
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<!-- ***************************书籍删除**************************** -->
<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true">
					&times;
				</button>
				<h4 class="modal-title" id="myModalLabel">
					确认是否删除该书籍？？？
				</h4>
			</div>
			<div class="modal-body">
			
	<form role="form" action="${ctx }/book/BookServlet" method="get">
	
	<div class="form-group">
		<label for="inputfile">执行删除--》</label>
		<input type="hidden"  name="method" value="deleteBook" required="required">
		
	</div>
	<div class="form-group">
		<label for="inputfile">图书ID</label>
		<input type="text" readonly="readonly" name="bookid" id="bookid" required="required">
		
	</div>
	
	<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="submit" class="btn btn-primary">
					确认
				</button>
			</div>
</form>
			</div>
			
			
			
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->



<script type="text/javascript">
	$(function(){
		var add=0;
		$("#addnewimg").on('click',function(){
		
		if(add==0){
			$("#booksImagesold").removeAttr("name");
			$("#booksImagesnew").css('display','block');
			$("#booksImagesnew").prop('name','booksImages');
			$(this).prop('value','取消');
			add=1;
		}else{
		
			$("#booksImagesnew").removeAttr("name");
			$("#booksImagesnew").css('display','none');
			$("#booksImagesold").prop('name','booksImages');
			$(this).prop('value','添加新的图片');
			add=0;
			}
			
		})
	
		$("#showallcate").on('click',function(){
			$(this).css("display","none");
			$("#outallcate").css("display","block");
			$(this).parents(".outcate").css("height","250px");
		})
		
		$("#outallcate").on('click',function(){
			$(this).css("display","none");
			$("#showallcate").css("display","block");
			$(this).parents(".outcate").css("height","70px");
		})
		
		$("#showallpubli").on('click',function(){
			$(this).css("display","none");
			$("#outallpubli").css("display","block");
			$(this).parents(".outcate").css("height","250px");
		})
		
		$("#outallpubli").on('click',function(){
			$(this).css("display","none");
			$("#showallpubli").css("display","block");
			$(this).parents(".outcate").css("height","70px");
		})
	
		$(".addcate").on('click',function(){
			$("#categoryName").prop("value",$(this).text());
			$("#categoryId").prop("value",$(this).next().text());
			
		})
		
		$(".addpubl").on('click',function(){
			$("#publisherName").prop("value",$(this).text());
			$("#publisherId").prop("value",$(this).next().text());
			
		})
		
		$(".addcate2").on('click',function(){
			$("#categoryName2").prop("value",$(this).text());
			$("#categoryId2").prop("value",$(this).next().text());
			
		})
		
		$(".addpubl2").on('click',function(){
			$("#publisherName2").prop("value",$(this).text());
			$("#publisherId2").prop("value",$(this).next().text());
			
		})
		
		$(".deleteBook").on('click',function(){
			$("#bookid").prop("value",$(this).next().text());
			
		})
		
		
		$(".updateBook").on('click',function(){
			$.ajax({
				type: "GET",
				url: "${ctx}/book/BookServlet?method=findBook",
				data:{
					'id':$(this).siblings().last().text()
				},
				dataType: "text",
            contentType: 'application/xml;charset=gb2312;',
				
				success:function(data){
					
					var obj= $.parseJSON(data);
					$("#bookid2").text(obj.id);
					$("#bookid4").prop("value",obj.id);
					$("#title2").prop("value",obj.title);
					$("#author2").prop("value",obj.author);
					$("#publisherId2").prop("value",obj.publisher.id);
					$("#publisherName2").prop("value",obj.publisher.name);
					$("#publishDate2").prop("value",obj.publishDate);
					$("#isbn2").attr("value",obj.isbn);
					$("#wordsCount2").prop("value",obj.wordsCount);
					$("#unitPrice2").prop("value",obj.unitPrice);
					if(obj.contentDescription!=null){
					$("#contentDescription2").text(obj.contentDescription);
					}else{
					$("#contentDescription2").text("");
					}
					
					if(obj.aurhorDescription!=null){
					$("#aurhorDescription2").text(obj.aurhorDescription);
					}else{
					$("#aurhorDescription2").text("");
					}
					if(obj.editorComment){
					$("#editorComment2").text(obj.editorComment);
					}else{
					$("#editorComment2").text("");
					}
					
					if(obj.TOC!=null){
					$("#TOC2").text(obj.TOC);
					}else{
					$("#TOC2").text("");
					}
					$("#categoryId2").prop("value",obj.category.id);
					
					$("#categoryName2").prop("value",obj.category.name);
					
					if(obj.booksImages!=null){
					$("#booksImagesold").prop("value",obj.booksImages);
					}else{
					$("#booksImagesold").prop("value","");
					}
					$("#quantity2").prop("value",obj.quantity);
					$("#address2").prop("value",obj.address);
					var a="";
					if(obj.baoyou=="不包邮"){
						a="0";
					}else if(obj.baoyou=="包邮"){
						a="1";
					}
					
					if(a!=""){
					$(".baoyou3").each(function(){
						
						if($(this).val()==a){
							
							
							$(this).prop('checked','checked');
							
						}
					})
					}else{
						$(".baoyou3").last().prop('checked','checked');
					}
				}
			})
		})
	});
	
</script>
  </body>
</html>
