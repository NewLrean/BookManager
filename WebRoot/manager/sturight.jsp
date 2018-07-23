<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.student.entiy.Student" %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link href="${ctx }/index/css/style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${ctx }/index/js/jquery.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.1.min.js"></script>
<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
<title>Insert title here</title>

<style type="text/css">
.form-control{
	height: 30px;
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
</style>

<script language="javascript">
$(function(){	
	//导航切换
	$(".imglist li").click(function(){
		$(".imglist li.selected").removeClass("selected")
		$(this).addClass("selected");
	})	
})	
</script>
<script type="text/javascript">
$(document).ready(function(){
  $(".click").click(function(){
  $(".tip3").fadeIn(200);
  });
  
   $(".click2").click(function(){
  $(".tip2").fadeIn(200);
  });
 
  
  $(".tiptop a").click(function(){
  $(".tip3").fadeOut(200);
  $(".tip2").fadeOut(200);
  });
  $(".massage a").click(function(){
  $(".massage").fadeOut(200);
  
});

  $(".sure").click(function(){
  $(".tip3").fadeOut(100);
  $(".tip2").fadeOut(200);
});

  $(".cancel").click(function(){
  $(".tip3").fadeOut(100);
  $(".tip2").fadeOut(200);
});

});


</script>
</head>
<body>

	<div class="place">
    <span>位置：</span>
    <ul class="placeul">
    <li><a href="#">首页</a></li>
    <li><a href="#">图片列表</a></li>
    </ul>
    </div>
    
    <div class="rightinfo">
    
    <div class="tools">
    
    	<ul class="toolbar">
        <a href="register.jsp"><li><span><img src="${ctx }/index/images/t01.png" /></span>添加</li></a>
        <li><span><img src="${ctx }/index/images/t02.png" /></span>修改</li>
        <li><span><img src="${ctx }/index/images/t03.png" /></span>删除</li>
        <li><span><img src="${ctx }/index/images/t04.png" /></span>统计</li>
        </ul>
        
        
        <ul class="toolbar1">
        <li><span><img src="${ctx }/index/images/t05.png" /></span>设置</li>
        </ul>
    
    </div>
    
    ${requestScope.massage}
    
    <table class="imgtable">
    
    <thead>
    <tr>
    <th width="100px;">学生姓名</th>
    <th>学生描述</th>
    <th>性别</th>
    <th>电话</th>
    <th>地址</th>
    <th>是否审核</th>
    <th>操作</th>
    </tr>
    </thead>
    
    <tbody>
    <c:forEach items="${requestScope.studentlist}" var="s">
    <tr height="30px">
    <td>${s.name}</td>
    <td>学生id<a href="#">${s.id }</a></td>
    <td>${s.sex}</td>
    <td>${s.phone }</td>
    <td class="address">${s.address}</td>
    <td>已审核</td>
    <td><a class="click" style="color: blue;cursor: pointer;">删除</a>
    
    <a class="click2" style="color: blue;cursor: pointer;">修改</a>
   
    </td>
    
    </tr>
    </c:forEach>
    
    
    </tbody>
    
    </table>
    
    <div class="tip tip3">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${ctx }/index/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的删除 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        
        <div class="tipbtn">
        <form action="${ctx }/StudentServlet" method="get">
       <div class="form-group" style="display: none">
    <input  class="form-control" value="delete" name="mothed" id="mothed" >
      <input  class="form-control" value="" name="id" id="id2" >
    
  </div>
        
        <input name="" type="submit"  class="sure" value="确定" />&nbsp;
        <input name="" type="button"  class="cancel" value="取消" />
        </form>
        
        </div>
    
    </div>
    
    
     <div class="tip tip2" style="height: 500px; position: absolute;top: 0px; ">
    	<div class="tiptop"><span>提示信息</span><a></a></div>
        
      <div class="tipinfo">
        <span><img src="${ctx }/index/images/ticon.png" /></span>
        <div class="tipright">
        <p>是否确认对信息的修改 ？</p>
        <cite>如果是请点击确定按钮 ，否则请点取消。</cite>
        </div>
        </div>
        <form style="padding: 50px 0px;" class="form-horizontal" action="${ctx }/StudentServlet?mothed=update" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label" >用户名</label>
    <div class="col-sm-6">
      <input  class="form-control" value="" name="name" id="name" pattern="[A-z0-9]{6,20}"  title="用户名不正确" placeholder="用户名" required="required" readonly="readonly">
    </div>
  </div>
  
  <div class="form-group" style="display: none">
    <label for="inputEmail3" class="col-sm-3 control-label" >用户id</label>
    <div class="col-sm-6">
      <input  class="form-control" value="" name="id" id="id" pattern="[A-z0-9]{6,20}"  title="用户名不正确" placeholder="用户名" required="required" readonly="readonly">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label" >性别</label>
    <div class="col-sm-9" style="margin-top: 6px;">
    	<div class="col-sm-3">
      		<input type="radio"  name="sex"  value="0" checked="checked">男
      	</div>
      	<div class="col-sm-6 col-sm-offset-1">
      		<input type="radio"  name="sex"  value="1">女
     	</div>
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">电话</label>
    <div class="col-sm-6">
      <input type="text" value=""  name="phone" class="form-control" id="phone" placeholder="密码" required="required"  title="电话不正确">
    </div>
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label">地址</label>
    <div class="col-sm-6">
    
      <input type="text"  name="address" id="address" class="form-control" id="address1" placeholder="地址" required="required" >
    </div>
  </div>
  

        <div class="tipbtn">
        <input type="submit" id="submit" value="确定" style="width: 96px;
    height: 35px;
    line-height: 35px;background: url(index/images/btnbg2.png) repeat-x;
    color: #000;
    font-weight: normal;    font-size: 14px;"/>&nbsp;
        
        <input name="" type="button"  class="cancel" value="取消" />
        </div>
    </form>
    </div>
    
    
   
    <div class="pagin">
    
    	<div class="message">共<i class="blue" style="font-size: 15px;">${sessionScope.entiy.pages }</i>页，当前显示第&nbsp;<i class="blue" style="font-size: 15px;">&nbsp;${sessionScope.entiy.pageNum+1}</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="${ctx }/PageServlet?pageNum=${sessionScope.entiy.pageNum-1}"><span class="pagepre"></span></a></li>
        <c:forEach var="i" begin="0" end="${sessionScope.entiy.pages -1}" step="1"> 
        <c:if test="${i<5}"> 
        <li class="paginItem"><a href="${ctx }/PageServlet?pageNum=${i}"><c:out value="${i+1}" /></a></li> 
			   </c:if>
			   <c:if test="${i >=5 &&i<10}">
        <li class="paginItem more"><a href="javascript:;">...</a></li>
        </c:if>
        <c:if test="${i >=10}">
        <li class="paginItem"><a href="javascript:;">10</a></li>
        	</c:if>
		   </c:forEach>
		
        
        
        
        <li class="paginItem"><a href="${ctx }/PageServlet?pageNum=${sessionScope.entiy.pageNum+1}"><span class="pagenxt"></span></a></li>
        
        <li class="paginItem" ><a style="width: 50px;" href="${ctx }/PageServlet?pageNum=${sessionScope.entiy.pages-1}">尾页</a></li> 
        
        
        <li style="float: left;">
        <form action="${ctx }/PageServlet" method="get">
        <input type="text" id="page" pattern="^[0-9]*[1-9][0-9]*$" title="只能输入正整数" name="pageNum" style="margin-left:5px; width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;" />
       
        <input type="submit" id="pageto"  value="跳转" style="width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;"/>
        </form> </li>
        </ul>
    </div>

</div>
    
    
    
    <script type="text/javascript">
    	$(function(){
    	
    	
    	$(".click2").on('click',function(){
    	$("#name").attr("value",$(this).parent().siblings().eq(0).text());
    	$("#id").attr("value",$(this).parent().siblings().eq(1).children().text());
    	$("#phone").attr("value",$(this).parent().siblings().eq(3).text());
    	$("#address").attr("value",$(this).parent().siblings().eq(4).text());
    	})
    	
    	$(".click").on('click',function(){
    	$("#id2").attr("value",$(this).parent().siblings().eq(1).children().text());
    	})
    	
    	
    	});
    </script>
</body>
</html>