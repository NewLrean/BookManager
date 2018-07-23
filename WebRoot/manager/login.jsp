<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/bootstrap-paginator.js"></script>
<title>Insert title here</title>
<style type="text/css">
	form{
		width: 500px;
		margin:  50px auto;
		padding: 50px;
		 border: 2px solid red;
	}
	
	h1{
text-align: center;
padding: 0 50px;
font-family: cursive !important;

}
</style>

<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
</head>
<body>
<h1>登陆页面</h1>
<form class="form-horizontal" action="" method="post">
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-3 control-label" >用户名</label>
    <div class="col-sm-9">
      <input  class="form-control" name="name" id="inputEmail3" title="用户名不正确" placeholder="用户名" required="required">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-3 control-label">密码</label>
    <div class="col-sm-9">
      <input type="password"  name="pwd" class="form-control" id="inputPassword3" placeholder="密码" required="required" pattern=".{6,}" title="密码不正确">
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <div class="checkbox">
        <label>
          <input type="checkbox">自动登录
        </label>
      </div>
    </div>
  </div>
  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
    	<div class="col-sm-6">
      <button type="submit" class="btn btn-default">登录</button>
      </div>
      <div class="col-sm-6">
      <a class="btn btn-default" href="register.jsp">注册</a>
      </div>
      
    </div>
    
  </div>
</form>
</body>
</html>