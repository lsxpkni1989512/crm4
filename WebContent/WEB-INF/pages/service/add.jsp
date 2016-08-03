<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>        
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>新建客户服务</title>
	<script type="text/javascript">
		$(function(){
			$("#save").click(function(){
				alert("保存成功！")
			})
			
		});
	</script>
</head>

<body class="main">

	<span class="page_title">新建客户服务</span>
	
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">
			返回
		</button>
		<button id="save" class="common_button" onclick="document.forms[1].submit();">
			保存
		</button>
	</div>
	
	<form:form  action="${ctp}/service/create" method="post" modelAttribute="cs">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					服务类型
				</th>
				<td>
					<!-- <select name="serviceType">
						<option value="">
							未指定
						</option>
						
							<option value="咨询">咨询</option>
						
							<option value="投诉">投诉</option>
						
							<option value="建议">建议</option>
						
					</select> -->
					<form:select path="serviceType" items="${serviceTypeList }" ></form:select>
					<span class="red_star">*</span>
				</td>
				<th>&nbsp;</th>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<th>
					概要
				</th>
				<td colspan="3">
					<!-- <input type="text" name="serviceTitle" size="50" /> -->
					<form:input path="serviceTitle" size="50"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					客户
				</th>
				<td>
					<!-- <select name="customer.id">
						<option value="">
							未指定
						</option>
						
							<option value="141">联想移动</option>
						
							<option value="154">魅族科技</option>
						
							<option value="2">大连重工</option>
						
							<option value="3">恒大电脑</option>
						
							<option value="4">阿拉灯</option>
						
							<option value="5">北京培黎师范学院</option>
						
							<option value="6">新浪</option>
						
							<option value="7">腾讯</option>
						
					</select> -->
					<form:select path="customer.id" items="${customerList }" itemValue="id" itemLabel="name" ></form:select>
					<span class="red_star">*</span>
				</td>
				<th>
					状态
				</th>
				<td>
					新创建 <!-- <input type="hidden" name="serviceState" value="新创建"/> -->
				</td>
			</tr>
			<tr>
				<th>
					服务请求
				</th>
				<td colspan="3">
					<!-- <textarea name="serviceRequest" rows="6" cols="50"></textarea> -->
					<form:textarea path="serviceRequest" rows="6" cols="50"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>
					创建人
				</th>
				<td>
					${user.name }(${user.role.name })
					<%-- <input type="hidden" name="createdby" value="${user }"> --%>
					<%-- <form:input path="createdby"/> --%>
					<span class="red_star">*</span>
				</td>
				<th>
					创建时间
				</th>
				<td>
					<form:input path="createDate" readonly="true" />
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
	</form:form>
</body>
</html>
