
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"   prefix="c" %>
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <title>PersonList</title>
</head>
<body>


        <div style="padding:20px;">
            人员列表
        </div>
<div style="padding-left:40px;">
            <a href="/springmvc/mvc/person/add">新增</a>   <!-- 跳转路径 -->
            <a href="/springmvc/mvc/person/send">发送到订阅者</a>
        </div>
        <table border="1">
            <tr>
                <td>编号:</td>
                <td>姓名:</td>
                <td>年龄:</td>
                <td>薪水:</td>
                <td>操作:</td>
            </tr>

            <c:forEach items="${personlist}" var="p">
                <tr>
                    <td>${p.id}</td>
                    <td>${p.name}</td>
                    <td>${p.age}</td>
                    <td>${p.salary}</td>
                    <td>
                        <a href=/springmvc/mvc/person/update?id=${p.id}>修改</a>
                    </td>
                </tr>
            </c:forEach>

        </table>
    </form>

</body>
</html>