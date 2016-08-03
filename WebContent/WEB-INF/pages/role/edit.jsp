<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理 - 分配权限</title>
	<script type="text/javascript">
		$(function(){
			$(".parentAuthority").each(function(){
				var id = $(this).prop("id");
				var flag = ($(".subAuthority-" + id + ":checked").length == $(".subAuthority-" + id).length);
				$(this).prop("checked", flag);
			});
			
			$(".parentAuthority").click(function(){
				var flag = $(this).prop("checked");
				var id = $(this).prop("id");
				$(".subAuthority-" + id).prop("checked", flag);
			});
			
			$(":checkbox[class^='subAuthority-']").click(function(){
				var classVal = $(this).prop("class");
				var flag = ($("." + classVal + ":checked").length == $("." + classVal).length);
				
				var id = classVal.split("-")[1];
				$("#" + id).prop("checked", flag);
			});
		})
	</script>
</head>

<body class="main">
	
	<form:form action="${ctp }/role/${role.id }" method="POST" modelAttribute="role">
		<input type="hidden" name="_method" value="PUT"/>
		
		<div class="page_title">
			角色管理 &gt; 分配权限
		</div>
		
		<div class="button_bar">
			<button class="common_button" onclick="javascript:history.back(-1);">
				返回
			</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				保存
			</button>
		</div>

		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title" width="10%">
					角色名
				</th>
				<td class="input_content" width="20%">
					${role.name }
				</td>
				<th class="input_title" width="10%">
					角色描述
				</th>
				<td class="input_content" width="20%">
					${role.description }
				</td>
				<th class="input_title" width="10%">
					状态
				</th>
				<td class="input_content" width="20%">
					${role.enabled ? '正常' : '禁用' }
				</td>
			</tr>
			<tr>
				<th class="input_title">
					权限
				</th>
				<td class="input_content" colspan="5" valign="top">
					<c:forEach items="${parentAuthorities }" var="pa">
						<input type="checkbox" class="parentAuthority" id="${pa.id }"/> ${pa.displayName }
						<br>
						
						&nbsp;&nbsp;&nbsp;
						
						<form:checkboxes items="${pa.subAuthorities }" cssClass="subAuthority-${pa.id }" 
							path="authorities2" itemLabel="displayName" itemValue="id" delimiter="<br>&nbsp;&nbsp;&nbsp;&nbsp;"/>
						<br><br>
					</c:forEach>
				</td>
			</tr>
		</table>
	</form:form>
	
</body>
</html>
