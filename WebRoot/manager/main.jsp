<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<html>
<head>
<script type="text/javascript">
    /* String.prototype.queryString= function(name) {
    var reg=new RegExp("[\?\&]" + name+ "=([^\&]+)","i"),r = this.match(reg);
    return r!==null?unescape(r[1]):null;
};
    window.onload=function(){
        var last=location.href.queryString("_v");
        document.body.innerHTML+=last||"";
        if(location.href.indexOf("?")==-1){
            location.href=location.href+"?_v="+(new Date().getTime());
        }else{
            var now=new Date().getTime();
            if(!last){
                location.href=location.href+"&_v="+(new Date().getTime());
            }else if(parseInt(last)<(now-500)){
                location.href=location.href.replace("_v="+last,"_v="+(new Date().getTime()));
            }
        }
    }; */
    </script>
<frameset rows="88,*" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frameset cols="187,*" frameborder="no" border="0" framespacing="0">
    <frame src="left.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
    <frame src="index.jsp" name="rightFrame" id="rightFrame" title="rightFrame" />
  </frameset>
</frameset>
</head>
<noframes>

<body>


</body></noframes>
</html>