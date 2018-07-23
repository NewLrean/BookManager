<div class="rightinfo">
	<div class="pagin">
    
    	<div class="message">共<i class="blue" style="font-size: 15px;">${requestScope.entiy.pages}</i>页，当前显示第&nbsp;<i class="blue" style="font-size: 15px;">&nbsp;${requestScope.entiy.pageNum+1}</i>页</div>
        <ul class="paginList">
        <li class="paginItem"><a href="${ctx }/ShowBooksServlet?pageNum=${requestScope.entiy.pageNum-1}"><span class="pagepre"></span></a></li>
        <c:forEach var="i" begin="0" end="" step="1"> 
        <c:if test="${i<5}"> 
        <li class="paginItem"><a href="${ctx }/PageServlet?ShowBooksServlet=${i}"><c:out value="${i+1}" /></a></li> 
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
        <input type="text" id="page" name="pageNum" value="" style="margin-left:5px; width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;" />
       
        <input type="submit" id="pageto"  value="跳转" style="width: 50px;height: 28px;border: 1px solid #a5a5a5;border-radius: 5px;"/>
        </form> </li>
        </ul>
    </div>
   </div>
  </body>
</html>
