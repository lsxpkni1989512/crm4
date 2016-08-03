<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="atguigu" %> 
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>产品查询</title>
	<script type="text/javascript">
	
	$(function(){
		
		$("img[id^='delete-']").click(function(){
			var id = this.id;
			id = id.split("-")[1];
			
			var proName = $(this).parents("tr").find("td").eq(1).text();
			
			var flag = confirm("是否要删除【"+ proName +"】的内容？");
			
			if(!flag){
				return ;
			}
			
			var url = "${ctp}/product/delete/" + id;
			var params = {
				"time":new Date(),
				"id":id,
				"_method":"delete"
			};
			var callBack = function(data){
				if(data == "1"){
					
					
					alert("删除成功！");
				}else{
					alert("删除失败！");
				};
			};
			$.post(url,params,callBack);
			//自己删除自己
			$(this).parents("tr").remove();
		});
	});
	</script>
</head>
<body>
	
	<div class="page_title">
		产品管理
	</div>
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/product/toAddProduct'">
			产品添加
		</button>
		<button class="common_button" onclick="document.forms[1].submit();">
			查询
		</button>
	</div>
	
	<form action="${ctp}/product/list" method="post">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th>
					名称
				</th>
				<td>
					<input type="text" name="search_LIKES_name" />
				</td>
				<th>
					型号
				</th>
				<td>
					<input type="text" name="search_LIKES_type" />
				</td>
				<th>
					批次
				</th>
				<td>
					<input type="text" name="search_LIKES_batch" />
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
	</form>	
	<c:if test="${empty page.content}">
			没有任何数据！
	</c:if>	
	<c:if test="${!empty page.content}">	
	<table class="data_list_table" border="0" cellPadding="3"
		cellSpacing="0">
		<tr>
			<th>
				编号
			</th>
			<th>
				名称
			</th>
			<th>
				型号
			</th>
			<th>
				等级/批次
			</th>
			<th>
				单位
			</th>
			<th>
				单价（元）
			</th>
			<th>
				备注
			</th>
			<th>
				操作
			</th>
		</tr>
		
			<c:forEach items="${page.content }" var="pro">
			<tr>
				<td class="list_data_number">
					${pro.id }
				</td>
				<td class="list_data_ltext">
					${pro.name }
				</td>
				<td class="list_data_text">
					${pro.type }
				</td>
				<td class="list_data_text">
					${pro.batch}
				</td>
	
				<td class="list_data_text">
					${pro.unit }
				</td>
				<td class="list_data_number">
					${pro.price }
				</td>
				<td class="list_data_ltext">
					${pro.memo }
				</td>
				<td class="list_data_op">
					<img onclick="window.location.href='${ctp}/product/toUpdate?id=${pro.id }'" 
						title="编辑" src="${ctp}/static/images/bt_edit.gif" class="op_button" />
					<img id="delete-${pro.id }" 
						title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
				</td>
			</tr>
			</c:forEach>
			<atguigu:page page="${page }" queryString="${queryString }"></atguigu:page>
		</table>
	</c:if>
	
</body>
</html>