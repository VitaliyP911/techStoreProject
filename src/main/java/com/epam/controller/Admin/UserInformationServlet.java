package com.epam.controller.Admin;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.service.ProductService;
import com.epam.service.UserService;
import com.epam.service.impl.ProductServiceImpl;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "UserInformationServlet", urlPatterns = ServletURL.USER_INFORMATION)
public class UserInformationServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = (String) request.getParameter("email");

            Optional<User> user = userService.getDataUser(email);

            request.setAttribute("user", user.get());

            request.getRequestDispatcher(JspURL.USERS_PAGE).forward(request, response);

        }catch (RuntimeException e){
            request.setAttribute("error", "Incorrect data");
            request.getRequestDispatcher(JspURL.USERS_PAGE).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
