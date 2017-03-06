<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户列表</title>
    ${sessionScope.user }
  </head>
  
  <body>
  
  <c:choose>
  		<c:when test="${empty sessionScope.user}">
  		 请先<a href="${pageContext.request.contextPath }/login.jsp">登录</a>
  		 </c:when>
  		 
  		 <c:otherwise>
  		 欢迎回来，你的用户名是：${sessionScope.user }
  		 </c:otherwise>
  </c:choose>
 
  <center><h3>用户列表</h3></center>
    <table>
    	<tr>
    		<th>编号</th>
    		<th>姓名</th>
    		<th>密码</th>
    	</tr>
    	<c:forEach items="${userList }" var="user">
	    	<tr>
	    		<td>${user.id} }</td>
	    		<td>${user.name }</td>
	    		<td>${user.password }</td>
	    	</tr>
    	</c:forEach>
    	
    </table>
  </body>
</html>
