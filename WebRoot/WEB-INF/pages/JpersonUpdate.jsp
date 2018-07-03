<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>PersonCreate</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
  </head>
  
  <body>
    <form action="mvc/person/updatePersonList" method="POST">
    <div style="padding:20px;">
        更新人员信息
    </div>

    <table>
    
        <tr>
            <td>编号(请输入正确的编号):</td>
            <td><input type="text" name="id" value=""/></td>
        </tr>
    
        <tr>
            <td>姓名:</td>
            <td><input type="text" name="name" value=""/></td>
        </tr>
        
        <tr>
            <td>年龄:</td>
            <td><input type="text" name="age" value=""/></td>
        </tr>
        
        <tr>
            <td>薪水:</td>
            <td><input type="text" name="salary" value=""/></td>
        </tr>
        <tr>
            <td colspan="2"><input type="submit" name="btnOK" value="提交"/></td>
        </tr>
    </table>
</form>
  </body>
</html>
