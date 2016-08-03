<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
    <title>新建销售机会</title>
    <script type="text/javascript">
	    $(function(){
	    	$("#save").click(function(){
	    		$("#chanceForm").submit();
	    		return false;
	    	});
	    })
    </script>
    
  </head>
 <body class="main">
 	<span class="page_title">销售机会的分配</span>
	<div class="button_bar">
		<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
		<button class="common_button" id="save">保存</button>
	</div>
	
	<form:form id="chanceForm" action="${ctp}/chance/dispatch/${chance.id }" method="POST" modelAttribute="chance">
			<input type="hidden" name="_method" value="PUT"/>
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th>编号</th>
				<td>
					<form:input path="id" readonly="true"/>
					&nbsp;
				</td>
				<th>机会来源</th>
				<td><form:input path="source" readonly="true"/></td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td><form:input path="custName" readonly="true"/><span class="red_star">*</span></td>
				<th>成功机率（%）<br />填入数字，0~100</th>
				<td><form:input path="rate" readonly="true"/><span class="red_star">*</span></td>
			</tr>
			<tr>
				<th>概要</th>
				<td colspan="2"><form:input path="title" readonly="true"/><span class="red_star">*</span></td>
			</tr>
			<tr>
			<th>联系人</th>
			<td><form:input path="contact" readonly="true"/></td>
			<th>联系人电话</th>
			<td><form:input path="contactTel" readonly="true"/></td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3">
					<form:textarea path="description" rows="6" cols="50" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr>
				<th>创建人</th>
				<td>
					${user.name }(${user.role.name })
				</td>
				
				<th>创建时间</th>
				<td>
					<form:input path="createDate" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
			<tr></tr>
			<tr>
				<th>指派人</th>
				<td>
					<form:select path="designee.id" items="${userList }" itemLabel="name" itemValue="id"></form:select>
					<span class="red_star">*</span>
				</td>
				
				<th>指派时间</th>
				<td>
					<form:input path="designeeDate" readonly="true"/>
					<span class="red_star">*</span>
				</td>
			</tr>
		</table>
		<br /><br>
  	</form:form>
  </body>
</html>
