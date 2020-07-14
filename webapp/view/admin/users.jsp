<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    if(user == null){
        response.sendRedirect(JspURL.LOGIN_PAGE);
    }
%>


<div class="container p-3 my-3 bg-light text-dark" >
    <center><div class="card bg-dark text-white text-center" style="width:35%">
        <div class="card-body">
            <h1 class="">Users</h1>
        </div>
    </div>
    </center>
    <br>
    <table class = "table table-striped table-bordered" id="datatable">
        <thead class="thead-dark">
        <tr>
            <th>Name</th>
            <th>Surname</th>
            <th>Password</th>
            <th>Email</th>
            <th>Setting</th>
        </tr>
        </thead>
        <c:forEach items="${userList}" var="user">
            <tr>
                <td>${user.name}</td>
                <td>${user.surname}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>

                    <form action="${pageContext.request.contextPath}/deleteUser?id=${user.id}" method="post">

                        <a class="btn btn-link" href="${pageContext.request.contextPath}/dataUser?email=${user.email}">Edit</a>
                        <input type="submit" value="Delete" class="btn btn-link"/>
                    </form>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <div class="float-right">
        <a type="button" class="btn btn-light btn-lg" href="${pageContext.request.contextPath}/view/admin/database.jsp">Cancel</a>
    </div>
</div>


</body>
</html>