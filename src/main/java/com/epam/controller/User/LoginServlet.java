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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@WebServlet(name = "LoginServlet", urlPatterns = ServletURL.LOGIN)
public class LoginServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(LoginServlet.class);
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspURL.LOGIN_PAGE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {

            String email = request.getParameter("email");
            String password = request.getParameter("password");

            if (userService.login(email, password)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userService.getDataUser(email).get());
                LOGGER.info("Login user");
                request.getRequestDispatcher(JspURL.HOME_PAGE).forward(request, response);
            } else {
                throw new IncorrectDataException("Incorrect email or password");
            }
        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(JspURL.LOGIN_PAGE).forward(request,response);
        }
    }
}
