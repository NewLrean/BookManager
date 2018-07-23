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
	form{
		width: 500px;
		margin:  50px auto;
		padding: 50px;
		 border: 2px solid red;
	}
	
	.form-group{
		height: 34px!important;
	}
	
	.form-group input[type="radio"] {
	
}
h1{
text-align: center;
padding: 0 50px;
font-family: cursive !important;
}

h5{
text-align: center;
color: #ccc;
}
</style>

<link rel="stylesheet" type="text/css" href="${ctx }/css/bootstrap.min.css"/>
<script type="text/javascript" src="${ctx }/js/jquery-1.7.1.min.js"></script>
</head>

<body>
<c:import url="/user/header.jsp"></c:import>
<h1>注册页面</h1>
<h5>*为必填内容</h5>
<form class="form-horizontal" action="${ctx }/user/UserBookServlet?method=register" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label">用户名*</label>
    <div class="col-sm-9">
      <input  class="form-control" name="username" id="username" pattern="[A-z_0-9]{6,20}" required="required" title="由字母、数字或_组成，长度不小于6位，不多于20位" placeholder="Username"><span id="span1"></span> 
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">设置密码*</label>
    <div class="col-sm-9">
      <input type="password"  name="password" pattern=".{6,}" title="不小于6位字符" required="required"  class="form-control" id="inputPassword" placeholder="Password">
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">确认密码*</label>
    <div class="col-sm-9">
      <input type="password"  name="truepwd" class="form-control" required="required" title="密码不一致" id="truePassword" placeholder="TruePassword">
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
    <label for="inputPassword3" class="col-sm-3 control-label">手机号</label>
    <div class="col-sm-9">
      <input type="text"  name="phone" class="form-control" id="phone" placeholder="Inputphone">
    </div>
  </div>
  
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">地址</label>
    <div class="col-sm-9">
      <input type="text"  name="address" class="form-control" id="address" placeholder="Inputphone">
    </div>
  </div>
  
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    	<div class="col-sm-6">
      <button type="submit" class="btn btn-default">注册</button>
      </div>
     <!--  <div class="col-sm-6">
      <a class="btn btn-default" href="login.jsp">返回登录</a>
      </div> -->
      
    </div>
    
  </div>
</form>

<script type="text/javascript">
	$(function(){
		$("#username").change(function(){
		var usern= $(this).val();
			$.post("${ctx}/user/UserBookServlet", { 'method':'vailtion','username': usern },
   				function(data){
     $("#span1").html(data);
   });
		})
	})

</script>
</body>
</html>