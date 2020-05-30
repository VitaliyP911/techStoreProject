<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsf/core" %>--%>
<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@ page import="com.epam.entity.User" %>
<%@ page import="com.epam.constant.JspURL" %>
<html>
<head>
    <title>Login System</title>
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
    <!-- Brand -->
    <a class="navbar-brand" href="#">MENU</a>

    <!-- Toggler/collapsibe Button -->
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>

    <!-- Navbar links -->
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
                        <%--<h4><c:out value="${user.name}" /></h4>--%>
                        <h4>${user.name}</h4>
                    </div>
                </div>
                <br>
                <div class="row align-items-center">
                    <div class="col-4 bg-dark  text-white">
                        <h4>Surname</h4>
                    </div>
                    <div class="col-8 bg-light text-dark">
                       <%-- <h4> <c:out value="${user.surname}" /></h4>--%>
                        <h4>${user.surname}</h4>
                    </div>
                </div>
                <br>
                <div class="row align-items-center">
                    <div class="col-4 bg-dark  text-white">
                        <h4>Email</h4>
                    </div>
                    <div class="col-8 bg-light text-dark">
                        <%--<h4><c:out value="${user.email}" /></h4>--%>
                        <h4>${user.email}</h4>
                    </div>
                </div>
                <br>
                <div class="btn-group btn-group-lg" style="width:100%">
                    <a type="button" class="btn btn-dark"  href="${pageContext.request.contextPath}/view/changePassword.jsp">Change profile</a>
                    <a type="button" class="btn btn-light"  href="${pageContext.request.contextPath}/view/logout.jsp" >Log out</a>
                </div>
            </div>

        </div>
    </div>
</div>
</body>
</html>