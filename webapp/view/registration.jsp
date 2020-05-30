<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration system</title>
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

    User user = (User) session.getAttribute("user");
    if(user != null){
        response.sendRedirect(JspURL.HOME_PAGE);
    }


    String status = (String) request.getAttribute("status");

    if(status == "Incorrect email"){
        request.setAttribute("statusText", "<div class=\"alert alert-danger\">\n" +
                "            <strong>Warning!</strong> Failed, account cannot be created!\n" +
                "        </div>");
    }
    if(status == "Success"){
        request.setAttribute("statusText", "<div class=\"alert alert-success\">\n" +
                "            <strong>Success!</strong> Account created!\n" +
                "        </div>");
    }
%>
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:25%">
    <form action="${pageContext.request.contextPath}/registration" method="post">
        <p>${statusText}</p>
        <h2>Registration</h2>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Name</span>
            </div>
            <input type="name" class="form-control" id="name" name="name">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Surname</span>
            </div>
            <input  type="surname" class="form-control" id="surname" name="surname">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Email</span>
            </div>
            <input  type="email" class="form-control" id="email" name="email">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Password</span>
            </div>
            <input  type="password" class="form-control" id="pwd" name="password">
        </div>
        <input type="submit" value="Sing in" class="btn btn-dark"/>
        <div class="float-right">
            <input type="button" value="Go to login page" class="btn btn-link" href="${pageContext.request.contextPath}/view/login.jsp" />
        </div>
    </form>
</div>
</body>
</html>