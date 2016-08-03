<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<title>销售机会管理</title>
</head>

<body class="main">
	<form id="command" action="${ctp}/plan/list" method="post">
		<div class="page_title">
			销售机会管理
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="document.forms[1].submit();">
				查询
			</button>
		</div>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title"> 
					客户名称
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_custName" />
				</td>
				
				<th class="input_title">
					概要
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_title" />
				</td>
				
				<th class="input_title">
					联系人
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_contact" />
				</td>
			</tr>
		</table>
		<br />
		
		<!-- 列表数据 -->
		<c:if test="${empty page.content }">
			沒有任何數據.
		</c:if>
		<c:if test="${!empty page.content }">
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						客户名称
					</th>
					<th>
						概要
					</th>
					<th>
						联系人
					</th>
					<th>
						联系人电话
					</th>
					<th>
						创建时间
					</th>
					<th>
						状态
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach var="item" items="${page.content }">
					<tr>
						<td class="list_data_number">${item.id }</td>
						<td id="custName-${item.id }" class="list_data_text">${item.custName }</td>
						<td class="list_data_text">${item.title }</td>
						<td class="list_data_text">${item.contact }</td>
						<td class="list_data_text">${item.contactTel }</td>
						<td class="list_data_text">
							<fmt:formatDate value="${item.createDate }" pattern="yyyy-MM-dd"/>
						</td>
						<td class="list_data_text">
							<script type="text/javascript">
								switch('${item.status}')
								{
									case '2':
										document.write('开发中');
										break;
									case '3':
										document.write('开发成功');
										break;
									case '4':
									    document.write('开发失败');
									   	break;
								}
							</script>
						</td>
						<td class="list_data_op">
						<c:if test='${item.status==2}'>
								<img
									onclick="window.location.href='${ctp }/plan/make/${item.id}'"
									title="制定计划" src="${ctp }/static/images/bt_plan.gif" class="op_button" />
								<img
									onclick="window.location.href='${ctp }/plan/execution/${item.id}'"
									title="执行计划" src="${ctp }/static/images/bt_feedback.gif" class="op_button" />
								<img 
									onclick="window.location.href='${ctp }/chance/finish/${item.id}'"
									title="开发成功" src="${ctp }/static/images/bt_yes.gif" class="op_button" />
							</c:if>
							<c:if test='${item.status!="2"}'>
								<img
									onclick="window.location.href='${ctp }/chance/detail/${item.id}'"
									title="查看" src="${ctp }/static/images/bt_detail.gif" class="op_button" />
							</c:if>
							<input type="hidden" value="${item.id }"/>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:if>
	</form>

<div style="text-align:right; padding:6px 6px 0 0;">

	共 ${page.totalElements } 条记录 
	&nbsp;&nbsp;
	
	当前第 ${page.pageNo } 页/共 ${page.totalPages } 页
	&nbsp;&nbsp;

	<c:if test="${page.hasPrevPage }">
		<a href='${ctp}/plan/list?pageNo=1'>首頁</a>
		&nbsp;&nbsp;
		<a href='${ctp}/plan/list?pageNo=${page.prevPage}'>上一頁</a>
		&nbsp;&nbsp;
	</c:if>
	
	<c:if test="${page.hasNextPage }">
		<a href='${ctp}/plan/list?pageNo=${page.nextPage}'>下一頁</a>
		&nbsp;&nbsp;
		<a href='${ctp}/plan/list?pageNo=${page.totalPages}'>末頁</a>
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
			window.location.href = window.location.pathname + "?pageNo=" + pageNo2;
			
		});
	});
</script>
		
	
	
</body>
</html>
