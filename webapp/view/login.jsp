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
    if (user != null) {
        response.sendRedirect(JspURL.HOME_PAGE);
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
        <strong>Warning!</strong> Incorrect email or password!
    </div>
</center>
<div class="container p-3 my-3 bg-light text-dark  border border-top-secondary" style="width:25%">
    <form action="${pageContext.request.contextPath}/login" method="post">

        <h2>Login</h2>
        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Email</span>
            </div>
            <input type="email" class="form-control" id="email" name="email">
        </div>

        <div class="input-group mb-3">
            <div class="input-group-prepend">
                <span class="input-group-text">Password</span>
            </div>
            <input type="password" class="form-control" id="pwd" name="password">
        </div>
        <input type="submit" value="Sing in" class="btn btn-dark"/>
        <div class="float-right">
            <a type="button" class="btn btn-light" href="${pageContext.request.contextPath}/view/changePassword.jsp">Forgot
                your password?</a>
        </div>
    </form>
</div>
</body>
</html>