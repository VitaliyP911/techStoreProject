<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
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


<div class="container p-3 my-3 bg-light text-dark">
    <center>
        <div class="card bg-dark text-white text-center" style="width:35%">
            <div class="card-body">
                <h1 class="">Products</h1>
            </div>

        </div>
        <br>
        <a type="button" style="width:30%" class="btn btn-dark btn-lg"
           href="${pageContext.request.contextPath}/view/admin/addNewProduct.jsp">Add product</a>
    </center>
    <br>
    <table class="table table-striped table-bordered" id="datatable">
        <thead class="thead-dark">
        <tr>
            <th>Company</th>
            <th>Name</th>
            <th>Price (UAH)</th>
            <th>Guarantee</th>
            <th>Category</th>
            <th>Setting</th>
        </tr>
        </thead>
        <c:forEach items="${productList}" var="product">
            <tr>
                <td>${product.nameCompany}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.guarantee}</td>
                <td>${product.category}</td>

                <td>
                    <form action="${pageContext.request.contextPath}/deleteProduct?id=${product.id}" method="post">

                        <a class="btn btn-link"
                           href="${pageContext.request.contextPath}/dataProduct?id=${product.id}">Edit</a>
                        <input type="submit" value="Delete" class="btn btn-link"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <a type="button" class="btn btn-light btn-lg" hidden>Cancel</a>
    <div class="float-right">
        <a type="button" class="btn btn-light btn-lg" href="${pageContext.request.contextPath}/view/admin/database.jsp">Cancel</a>
    </div>
</div>


</body>
</html>