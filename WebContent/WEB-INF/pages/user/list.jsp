<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<script type="text/javascript">
		$(function(){
			$("img[id^='delete-']").click(function(){
				var id = this.id;
				id = id.split("-")[1];
				
				var userName = $(this).parents("tr").find("td").eq(1).text();
				
				var flag = confirm("是否要删除【"+ userName +"】的内容？");
				
				if(!flag){
					return ;
				}
				
				var url = "${ctp}/user/delete?id=" + id;
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
	<form action="${ctp}/user/list">
		<div class="page_title">
			用户管理
		</div>
		<div class="button_bar">
			<button class="common_button" id="new">新建</button>
			<button class="common_button" onclick="document.forms[1].submit();">
				查询
			</button>
		</div>
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
			<tr>
				<th class="input_title">
					用户名
				</th>
				<td class="input_content">
					<input type="text" name="search_LIKE_name" />
				</td>
				<th class="input_title">
					状态
				</th>
				<td class="input_content">
					<select name="search_EQ_enabled">
						<option value="">
							全部
						</option>
						<option value="1">
							正常
						</option>
						<option value="0">
							已删除
						</option>
					</select>
				</td>
			</tr>
		</table>
		<!-- 列表数据 -->
		<br />
		
		
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
				<tr>
					<th class="data_title" style="width: 40px;">
						编号
					</th>
					<th class="data_title" style="width: 50%;">
						用户名
					</th>
					<th class="data_title" style="width: 20%;">
						状态
					</th>
					<th class="data_title">
						操作
					</th>
				</tr>
					<c:forEach items="${userList }" var="user">
						<tr>
							<td class="data_cell" style="text-align: right; padding: 0 10px;">
							${user.id }
							</td>
							<td class="data_cell" style="text-align: center;">
							${user.name }
							</td>
							<td class="data_cell">
							${user.enabled==1?"有效":"无效" }
							</td>
							<td class="data_cell">
								<img id="delete-${user.id}" 
									title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
								<img onclick="window.location.href='toUpdate?id=${user.id}'" 
									class="op_button" src="${ctp}/static/images/bt_edit.gif" title="编辑" />
							</td>
						</tr>
					</c:forEach>
				
			</table>
			








<div style="text-align:right; padding:6px 6px 0 0;">

	

	共 6 条记录 
	&nbsp;&nbsp;
	
	当前第 1 页/共 2 页
	&nbsp;&nbsp;
	
	
	 
	
		<a href='?page=2&sortType=&&'>下一页</a>
		&nbsp;&nbsp;
		<a href='?page=2&sortType=&&'>末页</a>
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
			if(pageNo2 < 1 || pageNo2 > parseInt("2")){
				$(this).val("");
				alert("输入的页码不合法");
				return;
			}
			
			//查询条件需要放入到 class='condition' 的隐藏域中. 
			window.location.href = window.location.pathname
				+ "?page=" + pageNo2 + "&sortType=&&";
			
		});
	})
</script>
		
		
	</form>
</body>
</html>
    