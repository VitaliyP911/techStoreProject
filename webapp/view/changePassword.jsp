<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 07.05.2020
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сhange password system</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
<body>
<%
    String status = (String) request.getAttribute("status");

    if(status == "Incorrect email"){
        request.setAttribute("statusText", "<div class=\"alert alert-danger\">\n" +
                "            <strong>Warning!</strong> Incorrect email!\n" +
                "        </div>");
    }
%>
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary " style="width:25%">
    <form action="${pageContext.request.contextPath}/changePassword" method="post">
        <p>${statusText}</p>
        <h2>Change password</h2>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Email</span>
            </div>
            <input  type="email" class="form-control" id="email" name="email">
        </div>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">New password</span>
            </div>
            <input  type="password" class="form-control" id="new pwd" name="newPassword">
        </div>
        <input type="submit" value="Change password" class="btn btn-dark"/>
        <div class="float-right">
            <a type="button" class="btn btn-light"  href="${pageContext.request.contextPath}/view/login.jsp" >Back to login page</a>
        </div>
    </form>
</div>
</body>
</html>