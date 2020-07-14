<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TECHSTORE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <style>
        .fakeimg {
            height: 200px;
            background: #aaa;
        }
    </style>
</head>
<body>
<%

    User user = (User) session.getAttribute("user");
    if(user == null){
        response.sendRedirect(JspURL.LOGIN_PAGE);
    }
%>
<nav class="navbar navbar-expand-md bg-dark navbar-dark">
    <a class="navbar-brand" href="#">MENU</a>

    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <div class="collapse navbar-collapse" id="collapsibleNavbar">
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/view/home.jsp">Head</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/checkAdmin">My profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/catalog">Catalog</a>
            </li>
        </ul>
    </div>
</nav>

<div class="jumbotron text-center" style="margin-bottom:0">
    <h1>Welcome to the tech store!</h1>
    <p>Here you can buy any technique for your taste.</p>
</div>

<div class="container p-3 my-3 bg-light text-dark" >

</div>

<div class="jumbotron text-center float-bottom" style="margin-bottom:0">
    <p>TechStore | Copyright 2020</p>
</div>

</body>
</html>