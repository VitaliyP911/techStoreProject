<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Catalog</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/v/bs4/dt-1.10.20/datatables.min.css"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
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
    if (user == null) {
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


<div class="container p-3 my-3 bg-light text-dark">
    <center>
        <div class="card bg-dark text-white text-center" style="width:50%">
            <div class="card-body">
                <h1 class="">Product information</h1>
            </div>
        </div>
    </center>
    <br>

    <ul class="nav nav-tabs" role="tablist">
        <li class="nav-item">
            <a class="nav-link active text-dark" data-toggle="tab" href="#home">About the product</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-dark" data-toggle="tab" href="#menu1">Features</a>
        </li>
        <li class="nav-item">
            <a class="nav-link text-dark" data-toggle="tab" href="#menu2">Reviews</a>
        </li>
    </ul>

    <!-- Tab panes -->
    <div class="tab-content">
        <div id="home" class="container tab-pane active"><br>
            <h3>${product.nameCompany} ${product.name}</h3>
            <h4>Price: ${product.price}</h4>
            ${error}
            <form action="${pageContext.request.contextPath}/addProductToBasket?id=${product.id}" method="post">
                <select name="count" class="custom-select mb-3" style="width:15%">
                    <option value="1" selected>Count</option>
                    <option>1</option>
                    <option>2</option>
                    <option>3</option>
                    <option>4</option>
                    <option>5</option>
                    <option>6</option>
                    <option>7</option>
                    <option>8</option>
                    <option>9</option>
                    <option>10</option>
                </select>
                <br>
                <button type="submit" class="btn btn-outline-dark btn-lg">Add to basket</button>
                <div class="float-right">
                    <a type="button" class="btn btn-light btn-lg" href="${pageContext.request.contextPath}/catalog">Cancel</a>
                </div>
            </form>
        </div>
        <div id="menu1" class="container tab-pane fade"><br>
            <h3>Menu 1</h3>

        </div>
        <div id="menu2" class="container tab-pane fade"><br>
            <h3>Menu 2</h3>

        </div>
    </div>

</div>

<div class="jumbotron text-center" style="margin-bottom:0">
    <p>TechStore | Copyright 2020</p>
</div>

</body>
</html>
