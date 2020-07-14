package com.epam.controller.Admin;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.controller.Admin.Users.UsersServlet;
import com.epam.entity.User;
import com.epam.service.UserService;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "CheckAdminServlet", urlPatterns = ServletURL.CHECK_ADMIN)
public class CheckAdminServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(CheckAdminServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            if(userService.checkAdmin(user.getEmail())){
                LOGGER.info("Checked if the user is an administrator");
                request.setAttribute("status", "admin");
            }

            request.getRequestDispatcher(JspURL.PROFILE_PAGE).forward(request, response);
        }catch (RuntimeException e) {
            LOGGER.error("RuntimeException: User check on admin did not work");
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(JspURL.PROFILE_PAGE).forward(request, response);
        }
    }
}
