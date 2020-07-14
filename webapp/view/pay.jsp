<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.entity.User" %>
<%@ page import="com.epam.constant.JspURL" %>
<html>
<head>
    <title>TECHSTORE</title>
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
    if (user == null) {
        response.sendRedirect(JspURL.LOGIN_PAGE);
    }

    String status = (String) request.getAttribute("status");

    if (status == "warning") {
        request.setAttribute("statusText", "visible");
    } else {
        request.setAttribute("statusText", "hidden");
    }
%>
<center>
    <div class="alert alert-danger" style="width:25%; visibility:${statusText}">
        <strong>Warning!</strong> Operation not performed!
    </div>
</center>
<br>
<center>
    <div class="card bg-dark text-white text-center" style="width:20%">
        <div class="card-body">
            <h2>Making an order</h2>
        </div>
    </div>
</center>
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:40%">
    <table class="table table-striped table-bordered" id="datatable">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Company</th>
            <th>Price</th>
            <th>Count</th>
        </tr>
        </thead>
        <c:forEach items="${basket}" var="product">
            <tr>
                <td>${product.name}</td>
                <td>${product.nameCompany}</td>
                <td>${product.price}</td>
                <td>${product.count}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:40%">
    <h2>Information</h2>
    <div class="container-fluid">
        <div class="row">
            <div class="col-4 bg-dark  text-white">
                <h4>Name</h4>
            </div>
            <div class="col-8 bg-light text-dark">
                <h4>${user.name}</h4>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-4 bg-dark  text-white">
                <h4>Surname</h4>
            </div>
            <div class="col-8 bg-light text-dark">
                <h4>${user.surname}</h4>
            </div>
        </div>
        <br>
        <div class="row">
            <div class="col-4 bg-dark  text-white">
                <h4>Email</h4>
            </div>
            <div class="col-8 bg-light text-dark">
                <h4>${user.email}</h4>
            </div>
        </div>
    </div>
</div>

<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:40%">
    <h2>Delivery</h2>
    <div class="container-fluid" style="width:50%">
        <div class="row align-items-center">
            <select name="price" class="custom-select mb-3">
                <option value="" selected>Choose a delivery method</option>
                <option value="courier">Delivery by courier</option>
                <option value="store">Pick up from the store</option>
                <option value="mail">Delivery by mail</option>
            </select>
        </div>
    </div>
</div>

<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:40%">
    <h2>Payment</h2>
    <div class="container-fluid" style="width:50%">
        <div class="row align-items-center">
            <select name="price" class="custom-select mb-3">
                <option value="" selected>Choose a payment method</option>
                <option value="сash">Cash payment</option>
                <option value="card">Transfer money to the card</option>
            </select>
        </div>
    </div>
</div>

<div class="container text-dark " style="width:40%">
    <h3>Amount due: ${amountDue}UAH</h3>
    <br>
    <div style="margin-bottom:50px">
        <a type="button" class="btn btn-dark btn-lg" href="${pageContext.request.contextPath}/history">I
            confirm the order</a>
        <div class="float-right">
            <a type="button" class="btn btn-light btn-lg" href="${pageContext.request.contextPath}/basket">Cancel</a>
        </div>
    </div>
</div>
</body>
</html>