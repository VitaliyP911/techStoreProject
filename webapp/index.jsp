<%@ page import="com.epam.constant.JspURL" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>TECHSTORE</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
</head>
<body>

<div class="jumbotron text-center border border-top-secondary " style="margin-top:10%">
    <h1>Welcome to the tech store!</h1>
    <p>Here you can buy any technique for your taste.</p>
    <a type="button" class="btn btn-dark" href="${pageContext.request.contextPath}/view/login.jsp">Sing in</a>
    <a type="button" class="btn btn-outline-dark" href="${pageContext.request.contextPath}/view/registration.jsp">Sing up</a>
</div>
</body>
</html>
