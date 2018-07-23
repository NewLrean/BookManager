<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'pay.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <form action="${pageContext.request.contextPath }/user/PaymentServlet" method="post">
    	<table>
    		<tr>
    		<td>支付金额：</td>
    		<td><input type="text" value="0.01" name="money"/>元</td>
    		</tr>
    		<tr>
    			<td>支付方式:</td>
    			
    		</tr>
    		<tr>
    		<td></td>
    		<td>&nbsp;&nbsp;<input type="radio" value="1000000-NET" name="pd_FrpId" checked="checked"/>易宝会员支付</td>
    		<td>&nbsp;&nbsp;<input type="radio" value="ICBC-NET-B2C" name="pd_FrpId"/>工商银行</td>
    		</tr>
    		<tr>
    		<td></td>
    		<td>&nbsp;&nbsp;<input type="radio" value="CMBCHINA-NET-B2C" name="pd_FrpId"/>招商银行</td>
    		<td>&nbsp;&nbsp;<input type="radio" value="ABC-NET-B2C" name="pd_FrpId"/>中国农业银行</td>
    		
    		</tr>
    		<tr><td colspan="3"><input type="submit" value="支付"/></td></tr>
    	</table>
    </form>
  </body>
</html>
