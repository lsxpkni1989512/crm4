<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>增加用户</title>
  </head>

  <body class="main">
  
  	<form:form action="${ctp}/user/update/${user.id}" id="" name="" method="post" modelAttribute="user" >
		<input type="hidden" name="_method" value="PUT">
	  		<span class="page_title">用户管理　&gt;　修改用户</span>
			<input id="id" name="id" type="hidden" value="${user.id}"/>
		
		
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.go(-1);">返回</button>
			<button class="common_button" onclick="document.forms[1].submit()">保存</button>
		</div>
		
		<table class="query_form_table" border="0" cellPadding="3" cellSpacing="0">
			<tr>
				<th class="input_title">用户名</th>
				<td class="input_content">
					<input id="name" name="name" type="text" value="${user.name }"/>
					<div id='divCheck'></div>
				</td>
				
				<th class="input_title">密码</th>
				<td class="input_content">
					<input id="password" name="password" type="password" value="${user.password }"/>
				</td>
			</tr>
			<tr>
				<th class="input_title">角色</th>
				<td class="input_content">
					<select id="role.id" name="role.id">
						<option value="3" 
							<c:if test="${user.role.id == 3 }">selected="selected"</c:if>
						>测试管理员
						</option>
						<option value="1"
							<c:if test="${user.role.id == 1 }">selected="selected"</c:if>
						>管理员</option>
						<option value="2"
							<c:if test="${user.role.id == 2 }">selected="selected"</c:if>
						>测试</option>
					</select>
				</td>
				<th class="input_title">状态</th>
				<td class="input_content">
					<span><input id="enabled1" name="enabled" type="radio" value="0" <c:if test="${user.enabled==0 }">checked="checked"</c:if>/><label for="enabled1">无效</label></span><span><input id="enabled2" name="enabled" type="radio" value="1" 
						<c:if test="${user.enabled==1 }">checked="checked"</c:if>
					/><label for="enabled2">有效</label></span>
				</td>
			</tr>
		</table>
	</form:form>
	
  </body>
</html>
