
<%@ page import="com.epam.constant.JspURL" %><%
    session.invalidate();
    response.sendRedirect(JspURL.LOGIN_PAGE);
%>