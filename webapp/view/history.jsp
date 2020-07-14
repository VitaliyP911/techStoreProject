<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.epam.entity.History" %>
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

    List<History> historyList = (List<History>) request.getAttribute("historyList");

    if (historyList.isEmpty()) {
        request.setAttribute("clearButton", "hidden");
    } else {
        request.setAttribute("clearButton", "visible");
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
<br>
<center>
    <div class="card bg-dark text-white text-center" style="width:20%">
        <div class="card-body">
            <h1 class="">History</h1>
        </div>
    </div>
</center>
<div class="container p-3 my-3 bg-light text-dark border border-top-secondary">
    <table class="table table-striped table-bordered" id="datatable">
        <thead class="thead-dark">
        <tr>
            <th>Time</th>
            <th>Name order</th>
            <th>Company</th>
            <th>Price (UAH)</th>
            <th>Count</th>
            <th>Amount due</th>
        </tr>
        </thead>
        <c:forEach items="${historyList}" var="history">
            <tr>
                <td>${history.time}</td>
                <td>${history.nameProduct}</td>
                <td>${history.nameCompany}</td>
                <td>${history.price}</td>
                <td>${history.count}</td>
                <td>${history.amountDue}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>

</div>
<div class="container p-3 my-3">
    <a type="button" class="btn btn-dark btn-lg" style="visibility:${clearButton}"
       href="${pageContext.request.contextPath}/clearHistory">Clear history</a>
    <a type="button" class="btn btn-light btn-lg float-right" href="${pageContext.request.contextPath}/checkAdmin">Cancel</a>
</div>
<div class="jumbotron text-center" style="margin-bottom:0">
    <p>TechStore | Copyright 2020</p>
</div>

</body>
</html>