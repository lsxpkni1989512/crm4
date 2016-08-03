<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="atguigu" %>      
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
</head>
<body>
 
	<div class="page_title">
		客户服务管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="">
			新建
		</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/service/feedback/list" method="get">
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceType" />
				</td>
				<th>
					概要
				</th>
				<td>
					<input type="text" name="search_LIKES_serviceTitle" />
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<input type="text" name="search_LIKES_customerName" />
				</td>
				<th>
					创建时间
				</th>
				<td>
					<input type="text" name="search_GED_minCreateDate" size="10" />
					-
					<input type="text" name="search_LED_maxCreateDate" size="10" />
				</td>
			</tr>
		</table>
		
		<!-- 列表数据 -->
		<br />
		<c:if test="${empty page.content }">
			没有数据！
		</c:if>
		<c:if test="${!empty page.content }">
		
			<table class="data_list_table" border="0" cellPadding="3" cellSpacing="0">
				<tr>
					<th>
						编号
					</th>
					<th>
						服务类型
					</th>
					<th>
						概要
					</th>
					<th>
						客户
					</th>
					<th>
						创建人
					</th>
					<th>
						创建时间
					</th>
					<th>
						操作
					</th>
				</tr>
				<c:forEach items="${page.content }" var="cs">
					<tr>
						<td class="list_data_number">
							${cs.id }
						</td>
						<td class="list_data_text">
							${cs.serviceType }
						</td>
						<td class="list_data_ltext">
							${cs.serviceTitle }
						</td>
	
						<td class="list_data_text">
							${cs.customer.name }
						</td>
						<td class="list_data_text">
							${cs.createdby.name }
						</td>
						<td class="list_data_text">
							<fmt:formatDate value="${cs.createDate}" pattern="yyyy-MM-dd" />
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/service/feedback?id=${cs.id }'" 
								title="反馈" src="${ctp}/static/images/bt_feedback.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
			<atguigu:page page="${page }" queryString="${queryString }"></atguigu:page>
		</c:if>

	</form>
</body>
</html>

