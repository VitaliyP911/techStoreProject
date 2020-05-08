<%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 07.05.2020
  Time: 16:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Сhange password system</title>
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
    String status = (String) request.getAttribute("status");

    if(status == "There is no such user"){
        request.setAttribute("statusText", "<div class=\"alert alert-danger\">\n" +
                "            <strong>Warning!</strong> There is no such user!\n" +
                "        </div>");
    }
    if(status == "Password changed"){
        request.setAttribute("statusText", "<div class=\"alert alert-success\">\n" +
                "            <strong>Success!</strong> Password changed!\n" +
                "        </div>");
    }
%>
<div class="container">
    <form action="${pageContext.request.contextPath}/changePassword" method="post">
        <p>${statusText}</p>
        <h2>Change password</h2>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
        <div class="form-group">
            <label for="new pwd">New password:</label>
            <input type="password" class="form-control" id="new pwd" placeholder="Enter new password" name="newPassword">
        </div>
        <div class="row">
            <div class="col-sm-10">
                <input type="submit" value="Change password" class="btn btn-dark"/>
            </div>
            <div class="col-sm-2">
                <input type="button" value="Back to login page" class="btn btn-link" onclick="window.location.href='${pageContext.request.contextPath}/view/login.jsp'" />
            </div>
        </div>
    </form>
</div>
</body>
</html>