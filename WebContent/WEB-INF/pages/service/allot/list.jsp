<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/commons/common.jsp" %>    
<%@ taglib tagdir="/WEB-INF/tags" prefix="atguigu" %>      
    
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
	<title>客户服务管理</title>
	<script type="text/javascript">
		$(function(){
			$("select[id^='allotTo-']").change(function(){
				var val = $(this).val();
				if(val != ""){
					var label = $(this).find("option:selected").text();
					var flag = confirm("确定要分配给" + label + "吗?");
					
					if(flag){
						var $tr = $(this).parent().parent();
						
						var id = $(this).prev(":hidden").val();
						var url = "${ctp}/service/allot";
						var args = {"id":id, "allotTo.id":val,"_method":"PUT","time":new Date()};
						$.post(url, args, function(data){
							if(data == "1"){
								alert("分配成功!");
								$tr.remove();
							}else{
								alert("分配失败!");
							}
						});
					}else{
						$(this).find("option[value='']").attr("selected", "selected");
					}
				}
			});
		})
	</script>
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
	
	<form action="${ctp}/service/allot/list">
		<table class="query_form_table" border="0" cellPadding="3"
			cellSpacing="0">
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
			<table class="data_list_table" border="0" cellPadding="3"
				cellSpacing="0">
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
						分配给
					</th>
					<th>
						操作
					</th>
				</tr>
				<%-- <c:forEach items="${customerServiceList }" var="cs"> --%>
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
	
						<td class="list_data_text">
							<input type="hidden" name="id" value="${cs.id }"/>
							<select name="allotTo" id="allotTo-${cs.id }">
								<option value="">
									未指定
								</option>
									<c:forEach items="${userList }" var="user">
										<option value="${user.id }">${user.name }</option>
									</c:forEach>
									
								
							</select>
							<%-- <form:select path="allotTo" items="${userList }"   /> --%>
						</td>
						<td class="list_data_op">
							<img onclick="window.location.href='${ctp}/service/delete?id=1441'" 
								title="删除" src="${ctp}/static/images/bt_del.gif" class="op_button" />
						</td>
					</tr>
				</c:forEach>
			</table>
			
			<atguigu:page page="${page }" queryString="${queryString }"></atguigu:page>
			</c:if>

	</form>
</body>
</html>
