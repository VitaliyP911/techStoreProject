package com.epam.controller.User;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.exception.IncorrectDataException;
import com.epam.service.UserService;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ChangePasswordServlet", urlPatterns = ServletURL.CHANGE_PASSWORD)
public class ChangePasswordServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspURL.CHANGE_PASSWORD_PAGE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String email = request.getParameter("email");
            String newPassword = request.getParameter("newPassword");

            if (userService.changePassword(newPassword, email)) {
                request.setAttribute("status", "success");
            }else {
                throw new IncorrectDataException("Incorrect email");
            }

        }catch (RuntimeException e){
            request.setAttribute("status", "warning");
        }
        doGet(request,response);
    }
}
