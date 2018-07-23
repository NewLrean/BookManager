<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

<link rel="stylesheet" type="text/css" href="${ctx }/css/bootstrap.min.css">
<meta charset="utf-8"> 
	<link href="${ctx }/index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/index/js/jquery.js"></script>
	<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
	<script src="${ctx }/bootstrap//bootstrap.min.js"></script>

<style type="text/css">
	img{
		vertical-align: middle;
	}
	.tools .toolbar li span{
		margin-top: 5px;
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

a{
 cursor: pointer;
}

h2{
	text-align: center;
}

table tr td{
overflow: hidden;
}
</style>


<script type="text/javascript">
	$(document).ready(function(){
	$(".click").click(function(){
  $(".tip3").fadeIn(200);
  });
  
  
  $(".tiptop a").click(function(){
  $(".tip3").fadeOut(200);
  });
  $(".massage a").click(function(){
  $(".massage").fadeOut(200);
  
});

  $(".sure").click(function(){
  $(".tip3").fadeOut(100);
});

  $(".cancel").click(function(){
  $(".tip3").fadeOut(100);
});

	
	})
</script>
  </head>
  
  <body>
 <h2>${requestScope.about}的操作页面</h2>
 <a href="${ctx }/manager/ShowBooksServlet">
 <button class="btn btn-primary btn-lg">
	返回
</button>
</a>
<button class="btn btn-primary btn-lg" data-toggle="modal" data-target="#myModal">
	添加${requestScope.about}
</button>

<a href="${ctx }/book/OtherOpServlet?method=${requestScope.toOthor}">
 <button class="btn btn-primary btn-lg">
	操作${requestScope.anothor}
</button>
</a>
${requestScope.massage}
<table cellpadding="0" align="center" class="imgtable">
    
    <thead>
    <tr>
    <th width="150px">id</th>
    <th width="250px">名称</th>
    <th>描述</th>
    <th width="150px">操作</th>
    </tr>
    </thead>
    
    <tbody >
   	<c:forEach items="${requestScope.aboutlists }" var="a">
   	<tr height="30px;">
   		<td>${a.id }</td>
   		<td>${a.name}</td>
   		<td>${a.description}</td>
   		<td>
   			<a class="update" data-toggle="modal" data-target="#updateModal">修改</a>
   			<a class="click" data-toggle="modal" data-target="#deleteModal" style="margin-left: 30px">删除</a>
   		</td>
   	</tr>
   	
   	</c:forEach>
   	</tbody>
    
    </table>

<!-- ***************************删除**************************** -->
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
			
	<form role="form" action="${ctx }/book/OtherOpServlet" method="get">
	
	<div class="form-group">
		<label for="inputfile">执行删除--》</label>
		<input type="hidden"  name="method" value="${requestScope.deleteabout}" required="required">
		
	</div>
	<div class="form-group">
		<label for="inputfile">图书ID</label>
		<input type="text" readonly="readonly" name="id" id="AboutId2" required="required">
		
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




<div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					${requestScope.about}的修改
				</h4>
			</div>
			<div class="modal-body">
			<form action="${ctx }/book/OtherOpServlet" method="get">
			
			<input type="hidden" name="method" value="${requestScope.updateAbout }">
			<div class="form-group">
		<label for="name">${requestScope.about}id</label>
		<input type="text" readonly="readonly" class="form-control" id="updateid"  name="id" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">${requestScope.about}名称</label>
		<input type="text" class="form-control" name="name" id="updatename" required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">${requestScope.about}描述</label>
		<textarea class="form-control"  rows="3" id="updatedescription" name="description"></textarea>
	</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="submit" class="btn btn-primary">
					提交更改
				</button>
			</div>
			</form>
			</div>
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" 
						aria-hidden="true">×
				</button>
				<h4 class="modal-title" id="myModalLabel">
					${requestScope.about}的添加
				</h4>
			</div>
			<div class="modal-body">
				<form action="${ctx }/book/OtherOpServlet" method="post">
			
			<input type="hidden" name="method" value="${requestScope.addAbout }">
			
	<div class="form-group">
		<label for="name">${requestScope.about}名称</label>
		<input type="text" class="form-control" name="name"  required="required"
			   placeholder="请输入名称">
	</div>
	<div class="form-group">
		<label for="name">${requestScope.about}描述</label>
		<textarea class="form-control"  rows="3"  name="description"></textarea>
	</div>
			<div class="modal-footer">
				<button type="button" class="btn btn-default" 
						data-dismiss="modal">关闭
				</button>
				<button type="submit" class="btn btn-primary">
					提交更改
				</button>
			</div>
			</form>
			</div>
			
		</div><!-- /.modal-content -->
	</div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<script type="text/javascript">
	$(function(){
	$(".click").on('click',function(){
	
		$("#AboutId2").prop("value",$(this).parent().siblings().eq(0).text());
	})
	
	$(".update").on('click',function(){
		$("#updateid").prop("value",$(this).parent().siblings().eq(0).text());
		$("#updatename").prop("value",$(this).parent().siblings().eq(1).text());
		$("#updatedescription").text($(this).parent().siblings().eq(2).text());
	})
	
	})
</script>
  </body>
</html>