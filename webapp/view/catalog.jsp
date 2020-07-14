<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ page import="com.epam.entity.Category" %>
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
    if (user == null) {
        response.sendRedirect(JspURL.LOGIN_PAGE);
    }

    request.setAttribute("categoryList", Category.values());
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

<br>
<center>
    <div class="card bg-dark text-white text-center" style="width:20%">
        <div class="card-body">
            <h2>Catalog</h2>
        </div>
    </div>
</center>
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary">
    <center>
        <div id="accordion" class="card bg-dark text-white" style="width:35%">
            <div class="card bg-dark text-white">
                <div class="card-header">
                    <a class="card-link text-white" data-toggle="collapse" href="#collapseOne">
                        <center><h4>Sorting (click)</h4></center>
                    </a>
                </div>
                <div id="collapseOne" class="collapse" data-parent="#accordion">
                    <div class="card-body bg-light text-dark">
                        <form action="${pageContext.request.contextPath}/sorted" method="post">
                            <select name="company" class="custom-select mb-3">
                                <option value="" selected>Company</option>
                                <option>Apple</option>
                                <option>Samsung</option>
                                <option>DELL</option>
                            </select>
                            <select name="price" class="custom-select mb-3">
                                <option value="" selected>Price</option>
                                <option value="larger">From larger to smaller</option>
                                <option value="smaller">From smaller to larger</option>
                            </select>

                            <select name="category" class="custom-select mb-3">
                                <option value="">Category</option>
                                <c:forEach items="${categoryList}" var="categories">
                                    <option>${categories}</option>
                                </c:forEach>
                            </select>
                            <button type="submit" class="btn btn-dark">Sort</button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </center>
    <br>
    <table class="table table-striped table-bordered" id="datatable">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Company</th>
            <th>Guarantee</th>
            <th>Category</th>
            <th>Price (UAH)</th>
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
                    <a href="${pageContext.request.contextPath}/productInformation?id=${product.id}">See</a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="jumbotron text-center" style="margin-bottom:0">
    <p>TechStore | Copyright 2020</p>
</div>

</body>
</html>