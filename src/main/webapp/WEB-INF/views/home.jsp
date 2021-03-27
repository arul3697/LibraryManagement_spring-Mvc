<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Leno
  Date: 11/2/2020
  Time: 3:08 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>Home</title>
    <link rel="stylesheet" href="../../assets/css/bootstrap.min.css" >
    <link rel="stylesheet" href="../../assets/css/font-awesome.min.css" >
    <style>
        body {
            margin:0;
            padding:0;
            background-image:url("../../assets/images/login2.jpg");
            background-size: cover;
        }
    </style>
</head>

<nav class="navbar navbar-light bg-dark">
    <form class="form-inline">
            <c:choose>
            <c:when test="${sessionScope.currentUser.role.id=='1'}">
            <h6>
                <a class="nav-link" href="/user/admin" style="color:white" title="Admin"><i class="fa fa-user-plus" aria-hidden="true" style="font-size:20px;color:blue;"></i></a>
            </h6>
            </c:when>
                <c:when test="${sessionScope.currentUser.role.id=='2'}">
                   <h6>
                       <a class="nav-link" href="/user/librarian" style="color:white" title="librarian"><i class="fa fa-user-circle-o" aria-hidden="true" style="font-size:20px;color:blue;"></i></a>
                   </h6>
                </c:when>
                <c:when test="${sessionScope.currentUser.role.id=='3'}">
                    <h6>
                        <a class="nav-link" href="/user/user" style="color:white" title="user"><i class="fa fa-user-circle-o" aria-hidden="true" style="font-size:20px;color:blue;"></i></a>
                    </h6>
                </c:when>
            </c:choose>
    </form>
    <form class="form-inline">
        <div align="center" style="color: lightblue">
            <h4>Welcome <c:out value="${sessionScope.currentUser.name}"/> !!</h4>
        </div>
    </form>
    <form class="form-inline my-2 my-lg-0">
        <h6>
            <a href="/logout" class="nav-link" style="color:white" title="Logout"><i class="fa fa-sign-out" aria-hidden="true" style="font-size:20px;color:red;"></i></a>
        </h6>
    </form>
</nav>
<br>
<%--<h6 align="center" style="color:greenyellow">${loginmsg}</h6>--%>
</body>
</html>
