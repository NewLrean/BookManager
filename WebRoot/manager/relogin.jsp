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
<meta name="viewport" content="width=device-width,initial-scale=1.0,user-scalable=no">
<link rel="stylesheet" type="text/css" href="${ctx }/img/style.css"/>
<link rel="stylesheet" type="text/css" href="${ctx}/css/bootstrap.min.css">
	<script type="text/javascript" src="${ctx }/js/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${ctx }/js/bootstrap-paginator.js"></script>
	<style type="text/css">
	body{color:#fff; font-family:"微软雅黑"; font-size:14px;}
	.wrap1{position:absolute; top:0; right:0; bottom:0; left:0; margin:auto }/*把整个屏幕真正撑开--而且能自己实现居中*/
	.main_content{background:url(${ctx}/img/main_bg.png) repeat; margin-left:auto; margin-right:auto; text-align:left; float:none; border-radius:8px;}
	.form-group{position:relative;}
	.login_btn{display:block; background:#3872f6; color:#fff; font-size:15px; width:100%; line-height:50px; border-radius:3px; border:none; }
	.login_input{width:100%; border:1px solid #3872f6; border-radius:3px; line-height:40px; padding:2px 5px 2px 30px; background:none;}
	.icon_font{position:absolute; bottom:15px; left:10px; font-size:18px; color:#3872f6;}
	.font16{font-size:16px;}
	.mg-t20{margin-top:20px;}
	@media (min-width:200px){.pd-xs-20{padding:20px;}}
	@media (min-width:768px){.pd-sm-50{padding:50px;}}
	#grad {
	  background: -webkit-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Safari 5.1 - 6.0 */
	  background: -o-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Opera 11.1 - 12.0 */
	  background: -moz-linear-gradient(#4990c1, #52a3d2, #6186a3); /* Firefox 3.6 - 15 */
	  background: linear-gradient(#4990c1, #52a3d2, #6186a3); /* 标准的语法 */
	}
	</style>
</head>

<body style="background:url(${ctx}/img/bg.jpg) no-repeat;">
	<div class="container wrap1" style="height:450px;">
            <h2 class="mg-b20 text-center">后台登录页面</h2>
            <div class="col-sm-8 col-md-5 center-auto pd-sm-50 pd-xs-20 main_content">
                <p class="text-center font16">管理员登录</p>
                <%
    String name ="";
    String pass="";
    String rem=null;
    String rempass=null;
    //获取所有Cookie
    Cookie[] cookies = request.getCookies();
    //如果浏览器中存在Cookie
    if (cookies != null && cookies.length > 0) {
        //遍历所有Cookie
        for(Cookie cookie: cookies) {
            //找到name为city的Cookie
            if (cookie.getName().equals("user")) {
                //使用URLDecode.decode()解码,防止中文乱码
               
                name = java.net.URLDecoder.decode(cookie.getValue().split("//_//")[0], "utf-8");
              	pass= java.net.URLDecoder.decode(cookie.getValue().split("//_//")[1], "utf-8");
              	
            }
            else if(cookie.getName().equals("remember")){
            	rem = cookie.getValue();
                 }
                 else if("rememberpass".equals(cookie.getName())){
                 	rempass=cookie.getValue();
                 }
        }
    }%>
                <form id="form" action="${ctx}/servlet/AdminLoginServlet"  method="post">
                    <div class="form-group mg-t20" style="margin-bottom:-15px!important;">
                        <i class="icon-user icon_font"></i>
                        <input type="text" value="<%=name %>" class="login_input" name="name" pattern="[A-z_0-9]{6,15}" title="请输入六位以上的正确用户名" required="required" id="Email1" placeholder="请输入用户名" />
                         
                    </div>
                    <div style="width: 100%;height: 30px;line-height: 30px;margin-top: 15px;">
                    	<p style="font-size: 15px;color: orange;margin-left: 10px;">${requestScope.error}</p>
                    </div>
                    <div class="form-group">
                        <i class="icon-lock icon_font"></i>
                        <input type="password" value="<%=pass %>" class="login_input" name="password" pattern="[A-z_0-9]{6,}" required="required" title="请输入六位以上的正确用户名" id="Password" placeholder="请输入密码" />
                       
                    </div>
                    <div class="checkbox mg-b25">
                        <label>
                            <input type="checkbox" <%=rem!=null?"checked":"" %>  name="remember"/>记住密码
                        </label>
                        <label style="margin-left: 50px;">
                            <input type="checkbox" <%=rempass!=null?"checked":"" %> name="rememberpass"/>自动登录
                        </label>
                    </div>
                    <button type="submit" id="submit" class="login_btn">登录</button>
               </form>
        </div><!--row end-->
    </div><!--container end-->
    
    <script type="text/javascript">
    $(function(){	
    	/* $("#submit").on('click',function(){
    	
    		$.ajax({
        			url: "${ctx}/AdminLoginServlet",
        			type: "POST",
        			async: true,
			        data: $("#form").serialize(),
					dataType: "text",
			        success:function(data){
          		 		if(data="true")
          		 		alert(data)
						window.location.href = "${ctx}/main.jsp";
				
          			}
    	}) 
    	})*/
    	 
  
    });
    </script>
</body>
</html>