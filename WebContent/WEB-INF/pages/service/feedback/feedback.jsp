<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>      
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>处理客户服务</title>
	<script type="text/javascript">
		$(function(){
			$("#save").click(function(){
				
				alert("保存成功！")
			})
		}) 
	</script>
</head>

<body class="main">

	<span class="page_title">处理客户服务</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button id="save" class="common_button" onclick="document.forms[1].submit();">
			保存
		</button>
	</div>

	<form:form action="${ctp}/service/feedback" method="post" modelAttribute="cs">
		<input type="hidden" name="id" value="${cs.id }"/>
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
					<form:input path="serviceDeal" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					处理人
				</th>
				<td>
					${user.name }
					<span class="red_star">*</span>
				</td>
				<th>
					处理时间
				</th>
				<td>
					<form:input path="dealDate" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
		<br />
		
		<table class="query_form_table">
			<tr>
				<th>处理结果</th>
				<td><form:textarea path="dealResult" rows="6" cols="50"/><span class="red_star">*</span></td>
				<th>满意度</th>
				<td>
					<!-- <select name="satisfy">
						<option value="">请选择...</option>
						<option value="☆☆☆☆☆">☆☆☆☆☆</option>
						<option value="☆☆☆☆">☆☆☆☆</option>
						<option value="☆☆☆">☆☆☆</option>
						<option value="☆☆">☆☆</option>
						<option value="☆">☆</option>
					</select> -->
					<form:select path="satisfy" items="${optionMap }" ></form:select>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
		
	</form:form>
</body>
</html>
