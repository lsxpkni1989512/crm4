<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>角色管理</title>
	<script type="text/javascript">
		$(function(){
			$("img[id^='delete-']").click(function(){
				var id = this.id;
				id = id.split("-")[1];
				
				var roleName = $(this).parents("tr").find("td").eq(1).text();
				
				var flag = confirm("是否要删除【"+ roleName +"】的内容？");
				
				if(!flag){
					return ;
				}
				
				var url = "${ctp}/role/delete/" + id;
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
			return false;
		});
	
	
	</script>
</head>

<body class="main">

	<div class="page_title">
		角色管理
	</div>
	
	<div class="button_bar">
		<button class="common_button" onclick="window.location.href='${ctp}/role/toAddRole'">
			新建
		</button>
	</div>
	
	<form action="${ctp}/role/delete2">
		<!-- <input type="hidden" name="_method" value="delete"> -->
		<!-- 列表数据 -->
		<br />
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" >
						编号
					</th>
					<th class="data_title" >
						角色名
					</th>
					<th class="data_title" >
						角色描述
					</th>
					<th class="data_title">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
					<c:forEach items="${roleList }" var="role">
						<tr>
							<td class="data_cell" style="text-align:right;padding:0 10px;">${role.id }</td>
							<td class="data_cell" style="text-align:center;">${role.name }</td>
							<td class="data_cell" style="text-align:left;">${role.description }</td>
							<td class="data_cell" style="text-align:center;">${role.enabled?"可用":"不可用" }</td>
							<td class="data_cell">
								<img onclick="window.location.href='${ctp }/role/assign/${role.id}'" title="分配权限" src="${ctp}/static/images/bt_linkman.gif" class="op_button" />
								<%-- <img onclick="document.forms[1].submit();" title="删除" id="delete-${role.id }" src="${ctp}/static/images/bt_del.gif" class="op_button" /> --%>
								<img id="delete-${role.id }" title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
							</td>
						</tr>
					</c:forEach>
				
			</table>
			








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 3 条记录 
	&nbsp;&nbsp;
	
	当前第 1 页/共 1 页
	&nbsp;&nbsp;
	
	
	 
	
	
	转到 <input id="pageNo" size='1'/> 页
	&nbsp;&nbsp;

</div>

<script type="text/javascript" src="${ctp}/static/jquery/jquery-1.9.1.min.js"></script>
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
			if(pageNo2 < 1 || pageNo2 > parseInt("1")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "&sortType=&&";
			
		});
	});
</script>
		
		
		
		
	</form>
</body>
</html>
