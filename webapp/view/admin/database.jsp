<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TECHSTORE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body>
<%

    User user = (User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect(JspURL.LOGIN_PAGE);
    }
%>
<br>
<center><div class="card bg-dark text-white text-center" style="width:20%">
    <div class="card-body">
        <h2 class="">Database</h2>
    </div>
</div>
</center>
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:35%">
    <center>
        <a type="button" class="btn btn-dark btn-lg"  href="${pageContext.request.contextPath}/users">Users</a>
        <a type="button" class="btn btn-dark btn-lg"  href="${pageContext.request.contextPath}/products">Products</a>
        <br><br>
        <a type="button" class="btn btn-outline-secondary btn-lg"  href="${pageContext.request.contextPath}/checkAdmin">Exit database</a>
    </center>
</div>


</body>
</html>