<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>My JSP 'login.jsp' starting page</title> 
	
  </head>
  
  <body>
   
    <font color="red">${requestScope.msg }</</font>
    <form action="${pageContext.request.contextPath }/LoginServlet"  method="post">
    	用户名：<input type="text" name="name"/><br/>
   		密码：    <input type="password" name="password"/>
   		<input type="submit" value="登录"/>
    </form>
  </body>
</html>
