<%@ page language="java" import="java.util.*" pageEncoding="utf-8" isELIgnored="false"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>

    <title>猜数字游戏</title>
   
  </head>
  
  <body>
  <h3>猜数字游戏</h3>
  <font color="red">${message} ${timesStr}</font>
  <form action="${pageContext.request.contextPath}/GuessServlet" method="post">
    请输入一个你的幸运数字：<input type="text" name="lucyNo"/><br/>
    <input type = "hidden" name="times" value="${times}"/>
    <input type = "submit" value="开始竞猜"/>
    </form>
  </body>
</html>
