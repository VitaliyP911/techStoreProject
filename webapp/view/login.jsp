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

<div class="container">
    <form action="${pageContext.request.contextPath}/login" method="post">
        <p>${status}</p>
        <h2>Login</h2>
        <div class="form-group">
            <label for="email">Email:</label>
            <input type="email" class="form-control" id="email" placeholder="Enter email" name="email">
        </div>
        <div class="form-group">
            <label for="pwd">Password:</label>
            <input type="password" class="form-control" id="pwd" placeholder="Enter password" name="password">
        </div>
        <div class="row">
            <div class="col-sm-9">
                <input type="submit" value="Log in" class="btn btn-dark"/>
            </div>
            <div class="col-sm-3">
                <input type="button" value="Forgot your password?" class="btn btn-link"/>
            </div>
        </div>
    </form>
</div>
</body>
</html>