<%@ page import="com.epam.constant.JspURL" %>
<%@ page import="com.epam.entity.User" %><%--
  Created by IntelliJ IDEA.
  User: USER
  Date: 07.07.2020
  Time: 20:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Delete System</title>
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
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:25%">
    <form action="${pageContext.request.contextPath}/deleteAccount" method="post">
        <h4>You really want to delete this account?</h4>
        <center>
            <input type="submit" value="Yes, I want to delete" class="btn btn-danger btn-lg"/>
            <a type="button" class="btn btn-dark btn-lg"  href="${pageContext.request.contextPath}/view/profileSettings.jsp">No</a>
        </center>
    </form>
</div>
</body>
</html>
