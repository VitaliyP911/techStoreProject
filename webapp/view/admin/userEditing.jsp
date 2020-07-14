<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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

    String status = (String) request.getAttribute("message");

    if (status == "warning") {
        request.setAttribute("statusMessage", "visible");
    } else {
        request.setAttribute("statusMessage", "hidden");
    }
%>
<center>
    <div class="alert alert-danger" style="width:25%; visibility:${statusMessage}">
        <strong>Warning!</strong> Failed to edit user information!
    </div>
</center>
<div class="container" style="width:35%">
    <div class="card bg-dark text-white text-center">
        <div class="card-body">
            <h1 class="">Edit user information</h1>
        </div>
    </div>
    <br>
    <div class="card bg-light text-dark">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/userEditing?email=${user.email}" method="post">
                <div class="container-fluid">
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Name</span>
                        </div>
                        <input type="name" class="form-control" id="name" placeholder="${user.name}" name="name">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Surname</span>
                        </div>
                        <input type="surname" class="form-control" id="surname" placeholder="${user.surname}"
                               name="surname">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Password</span>
                        </div>
                        <input type="password" class="form-control" id="pwd" placeholder="${user.password}"
                               name="password">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Email</span>
                        </div>
                        <input type="name" class="form-control" id="email" placeholder="${user.email}" name="email">
                    </div>
                    <center>
                        <div class="btn-group btn-group-lg" style="width:45%">
                            <input type="submit" value="Save" class="btn btn-dark"/>
                        </div>
                    </center>
                </div>
            </form>
        </div>
    </div>
</div>