
<%@ page import="com.epam.entity.User" %>
<%@ page import="com.epam.constant.JspURL" %>
<html>
<head>
    <title>My profile</title>
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
                <a class="nav-link" href="${pageContext.request.contextPath}/view/profile.jsp">My profile</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/catalog">Catalog</a>
            </li>
        </ul>
    </div>
</nav>
<br>
<div class="container" style="width:35%">
    <div class="card bg-dark text-white text-center ">
        <div class="card-body">
            <h1 class="">My profile</h1>
        </div>
    </div>
    <br>
    <div class="card bg-light text-dark">
        <div class="card-body">

            <div class="container-fluid">

                <div class="row align-items-center">
                    <div class="col-4 bg-dark  text-white">
                        <h4>Name</h4>
                    </div>
                    <div class="col-8 bg-light text-dark">
                        <h4>${user.name}</h4>
                    </div>
                </div>
                <br>
                <div class="row align-items-center">
                    <div class="col-4 bg-dark  text-white">
                        <h4>Surname</h4>
                    </div>
                    <div class="col-8 bg-light text-dark">
                        <h4>${user.surname}</h4>
                    </div>
                </div>
                <br>
                <div class="row align-items-center">
                    <div class="col-4 bg-dark  text-white">
                        <h4>Email</h4>
                    </div>
                    <div class="col-8 bg-light text-dark">
                        <h4>${user.email}</h4>
                    </div>
                </div>
                <br>
                <center>
                <div class="btn-group btn-group-lg" style="width:65%">
                    <a type="button" class="btn btn-dark"  href="${pageContext.request.contextPath}/view/profileSettings.jsp">Profile settings</a>
                    <a type="button" class="btn btn-light"  href="${pageContext.request.contextPath}/view/logout.jsp" >Log out</a>
                </div>
                </center>
            </div>

        </div>
    </div>
</div>
<div class="jumbotron text-center" style="margin-bottom:0">
    <p>TechStore | Copyright 2020</p>
</div>
</body>
</html>