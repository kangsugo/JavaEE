<%@ page language="java" import="java.util.*,gz.itcast.entity.*" pageEncoding="utf-8"%>
<%--导入核心标签库 --%>
<%--
	uri: 表示需要导入的标签库的uri名称。每个标签库都会有一个tld后缀名的标签声明文件，在tld文件中都有唯一的uri的名称。这个uri的名称就是当前标签库的名称。
	prefix: 使用标签库的前缀，通用和tld文件的short-name名称相同
 --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>核心标签库</title>  
  </head>
  
  <body>
    <%--
    1) <c:set/>  给域对象赋值   
     --%>
     <%
     	//String name = "eric";
     	//pageContext.setAttribute("name",name);
      %>
      <%--
      	var: 数据的名称
      	value： 保存的数据值
      	scope: 保存到哪个域。
      		page-》page域
      		requset->request域
      		session->session域
      		application->application域
       --%>
      <%--<c:set var="name" value="jacky" scope="request"/>--%>
      
      <%--
      2) <c:out/>   获取域对象的数据
       --%>
      
      ${name } <br/>
      <%--
      	value: 代表获取域中的某个名称内容。如果数据在域中，必须使用EL语法去获取
      	default: 默认值。当前需要获取的内容为null，那么使用默认值代替
      	escapeXml: 默认情况下为true，out标签会把输入的内容进行转义。如果不需要转义，则为false既可！
       --%>
      <c:out value="${name}" default="<h3>标题3</h3>" escapeXml="false"></c:out>
    
    <hr/>
    	<%--
    	3) <c:if/> 单条件判断
    	 --%>
    	 <%--
    	 test: 条件表达式。返回true的时候，就会执行if标签体内容，否则，不执行。
    	  --%>
    	 <c:if test="${10>8}">
    	 	<input type="submit" value="提交"/>
    	 </c:if>
    	<hr/>
    	
    	<%--
    		4) <c:choose/>+<c:when/>+<c:otherwise/>  多条件判断
    		
    		登录之后，一定会把数据存放到session域，user名称
    			session.setAttribute("user",user);
    	 --%>
    	 <%--模拟登录 --%>
    	 <c:set var="user" value="eric" scope="session"></c:set>
    	 
    	 <c:choose>
    	 	<c:when test="${!empty sessionScope.user}">
    	 		欢迎回来，你的用户名是 ：eric，<a href="">【退出登录】</a>
    	 	</c:when>
    	 	<c:otherwise>
				 请先<a href="">注册</a>或<a href="">登录</a>  	 		
    	 	</c:otherwise>
    	 </c:choose>
    
    	<hr/>
    	<%--
    	5) <c:forEach/>   用于迭代或循环
    	 --%>
    	 <%
    	 //List
        List<Student> list = new ArrayList<Student>();
        list.add(new Student("eric","123456"));
        list.add(new Student("lily","123456"));
        list.add(new Student("maxwell","123456"));
        pageContext.setAttribute("list",list);
    	 
    	 %>
    	 <hr/>
    	 <%--
    	 begin: 从哪个元素开始遍历，从0开始
    	 end: 到哪个元素位置
    	 step: 增加步长。默认step为 1
    	 items: 需要遍历的数据。（数组|List集合|Map集合） 如果是获取域数据，那么使用EL表达式获取
    	 var: 每个元素名称
    	 varStatus: 当前状态对象。该对象封装当前元素状态信息。  例如 count属性: 表示当前遍历的是哪个元素，从1开始
    	  --%>
    	 List集合： <br/>
    	<c:forEach items="${list}" var="student" varStatus="varSta">
    		序号：${varSta.count }   姓名：${student.name1 } - 密码： ${student.password }<br/>
    	</c:forEach>
    	<hr/>
    	<% 
    		Map<String,Student> map = new HashMap<String,Student>();
          	map.put("001",new Student("eric","123456"));
          	map.put("002",new Student("jacky","123456"));
          	map.put("003",new Student("rose","123456"));
          	pageContext.setAttribute("map",map);
    	%>
    	
    	 Map集合：<br/>
    	 <%--
    	 	注意： forEach标签遍历Map集合时，把每个Map的对象使用Entry封装，
    	 		Entry封装键对象和值对象，通过getKey()获取键对象，通过getValue()获取值对象
    	  --%>
    	 <c:forEach items="${map}" var="entry">
    	 	 编号： ${entry.key } - 姓名:${entry.value.name1 } - 密码： ${entry.value.password }<br/>
    	 </c:forEach>
    	 <hr/>
    	 <%--
    	 6)  <c:forToekens/>   遍历特殊字符串
    	  --%>
    	  <c:set var="str" value="java-net-php-平面设计"></c:set>
    	 <%
    	 	   String str= (String)pageContext.getAttribute("str");
    	 	   String[] strs = str.split("-");
    	 	   for(int i=0;i<strs.length;i++){
    	 	   	out.write(strs[i]+",");
    	 	   }
    	  %>
    	  <br/>
    	  <%--
    	  items: 需要遍历的字符串
    	  delims: 指定分割符号
    	  var: 每个内容的名称
    	   --%>
    	  <c:forTokens items="${str}" delims="-" var="s">
    	  	    ${s },
    	  </c:forTokens>
    	  <hr/>
    	  <%--
    	  7) <c:rediect/>  重定向标签
    	   --%>
    	   <%
    	   	//response.sendRedirect(request.getContextPath()+"/03.el3.jsp");
    	    %>
    	    <c:redirect url="/03.el3.jsp"></c:redirect>
  </body>
</html>
