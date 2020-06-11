<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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


<div class="container p-3 my-3 bg-light text-dark" >
    <center><div class="card bg-dark text-white text-center" style="width:35%">
        <div class="card-body">
            <h1 class="">Catalog</h1>
        </div>
    </div>
    </center>
    <br>
    <table class = "table table-striped table-bordered" id="datatable">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Company</th>
            <th>Guarantee</th>
            <th>Category</th>
            <th>Price</th>
            <th>Information</th>
        </tr>
        </thead>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.nameCompany}</td>
                <td>${product.guarantee}</td>
                <td>${product.category}</td>
                <td>${product.price}</td>
                <td>
                    <a href = "${pageContext.request.contextPath}/">See</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>
</html>