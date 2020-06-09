
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
                <a class="nav-link" href="#"></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Head</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">My profile</a>
            </li>
        </ul>
    </div>
</nav>
<br>
<div class="container" style="width:35%">
    <div class="card bg-dark text-white text-center">
        <div class="card-body">
            <h1 class="">Profile settings</h1>
        </div>
    </div>
    <br>
    <div class="card bg-light text-dark">
        <div class="card-body">
            <form action="${pageContext.request.contextPath}/profileSettings" method="post">
                <div class="container-fluid">

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Name</span>
                        </div>
                        <input  type="name" class="form-control" id="name" placeholder="${user.name}" name="name">
                    </div>
                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Surname</span>
                        </div>
                        <input  type="surname" class="form-control" id="surname" placeholder="${user.surname}" name="surname">
                    </div>

                    <div class="input-group mb-3">
                        <div class="input-group-prepend">
                            <span class="input-group-text">Password</span>
                        </div>
                        <input  type="password" class="form-control" id="pwd" placeholder="${user.password}" name="password">
                    </div>
                    <div class="btn-group btn-group-lg">
                        <input type="submit" value="Save" class="btn btn-dark"/>
                    </div>
                    <form action="${pageContext.request.contextPath}/deleteAccount" method="post">
                        <div class="float-right btn-group btn-group-lg">
                            <input type="submit" value="Delete" class="btn btn-danger"/>
                        </div>
                    </form>
                </div>
            </form>

        </div>
    </div>
</div>
</body>
</html>