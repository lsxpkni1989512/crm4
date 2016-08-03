<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>     
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>处理客户服务</title>
</head>

<body class="main">

	<span class="page_title">处理客户服务</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
	</div>

	<form:form action="${ctp}/service/deal" method="post" modelAttribute="cs">
		<input type="hidden" name="id" value="1441"/>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					编号
				</th>
				<td><form:input path="id" readonly="true"/></td>
				<th>
					服务类型
				</th>
				<td><form:input path="serviceType" readonly="true"/></td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3"><form:input path="serviceTitle" readonly="true"/></td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td><form:input path="customer.name" readonly="true"/></td>
				<th>
					状态
				</th>
				<td>
					<form:input path="serviceState" readonly="true"/>
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3"><form:input path="serviceRequest" readonly="true"/></td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td><form:input path="createdby.name" readonly="true"/></td>
				<th>
					创建时间
				</th>
				<td>
					<form:input path="createDate" readonly="true"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>

				<th>
					分配给
				</th>
				<td><form:input path="allotTo.name" readonly="true"/></td>
				<th>
					分配时间
				</th>
				<td>
					<form:input path="allotDate" readonly="true"/>
				</td>
			</tr>
		</table>
		
		<br />
		<table class="query_form_table">
			<tr>
				<th>
					服务处理
				</th>
				<td colspan="3">
					<form:textarea path="serviceDeal" rows="6" cols="50" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					处理人
				</th>
				<td>
					<%-- <form:input path="allotTo.name" readonly="true"/> --%>
					${user.name }
					<span class="red_star">*</span>
				</td>
				<th>
					处理时间
				</th>
				<td>
					<!-- <input name="dealDate" readonly="readonly" id="dealDate" />  -->
					<form:input path="dealDate" readonly="true"/>
					<%-- <fmt:formatDate value="${cs.dealDate}" pattern="yyyy-MM-dd" /> --%>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
		<br />
		
		<table class="query_form_table">
			<tr>
				<th>处理结果</th>
				<td><form:input path="dealResult" readonly="true"/><span class="red_star">*</span></td>
				<th>满意度</th>
				<td>
					<form:input path="satisfy" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
		