<%@ tag language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="page" required="true" rtexprvalue="true" type="com.atguigu.crm.orm.Page" %>
<%@ attribute name="queryString" required="false" rtexprvalue="true" type="java.lang.String" %>
<%-- 
	this.queryString = queryString;
	pageContext.setAttribute("queryString", queryString);
--%>

<c:set var="ctp" value="${pageContext.request.contextPath }"></c:set>
<% 
	if(queryString != null && !queryString.trim().equals("")){
		queryString = "&" + queryString;
		getJspContext().setAttribute("queryString ", queryString);
	}
%>

<div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalElements } 条记录
	&nbsp;&nbsp;
	
	当前第 ${page.pageNo } 页/共 ${page.totalPages } 页
	&nbsp;&nbsp;

	<c:if test="${page.hasPrevPage }">
		<a href='?pageNo=1${queryString}'>首頁</a>
		&nbsp;&nbsp;
		<a href='?pageNo=${page.prevPage}${queryString}'>上一頁</a>
		&nbsp;&nbsp;
	</c:if>
	
	<c:if test="${page.hasNextPage }">
		<a href='?pageNo=${page.nextPage}${queryString}'>下一頁</a>
		&nbsp;&nbsp;
		<a href='?pageNo=${page.totalPages}${queryString}'>末頁</a>
		&nbsp;&nbsp;
	</c:if>
	
	
	转到 <input id="pageNo" size='1'/> 页
	&nbsp;&nbsp;

</div>

<script type="text/javascript" src="${ctp }/static/jquery/jquery-1.9.1.min.js"></script>
<script type="text/javascript">

	$(function(){
		
		$("#pageNo").change(function(){
			var pageNo = $(this).val();
			var reg = /^\d+$/;
			if(!reg.test(pageNo)){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			var pageNo2 = parseInt(pageNo);
			if(pageNo2 < 1 || pageNo2 > parseInt("${page.totalPages}")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname + "?pageNo=" + pageNo2 + "${queryString}";
			
		});
	})
</script>
