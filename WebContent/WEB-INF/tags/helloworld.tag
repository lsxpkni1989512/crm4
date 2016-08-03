<%@ tag import="java.util.Date"%>
<%@ tag language="java" pageEncoding="UTF-8"%>
<!--  
name: 属性名
type: 属性的类型
required: 属性是否为必须的.
rtexprvalue: rt: runtime, expr: expression, value: value. 属性是否可以接收运行时表达式的值. 即是否可以接收 EL. 
-->
<%@ attribute name="count" required="true" type="java.lang.Integer" rtexprvalue="true" %>

Hello: <%= new Date() %>
<br><br>

HelloWorld!
<br><br>

<%--
	getJspBody().invoke(null);
--%>

<jsp:doBody></jsp:doBody>
<br><br>

<%= count %>