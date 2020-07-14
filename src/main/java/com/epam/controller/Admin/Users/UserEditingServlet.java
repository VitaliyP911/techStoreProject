package com.epam.controller.Admin.Users;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.User;
import com.epam.exception.IncorrectDataException;
import com.epam.exception.NotUpdateException;
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

@WebServlet(name = "UserEditingServlet", urlPatterns = ServletURL.USER_EDITING)
public class UserEditingServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserEditingServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ServletURL.USERS).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String email = request.getParameter("email");

            User user = userService.getDataUser(email).get();

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String password = request.getParameter("password");

            if (!name.isEmpty()) {
                user.setName(name);
            }
            if (!surname.isEmpty()) {
                user.setSurname(surname);
            }
            if (!password.isEmpty()) {
                user.setPassword(password);
            }
            if (!email.isEmpty()) {
                user.setEmail(email);
            }

            if (userService.update(user.getId(), user)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", user);
                LOGGER.info("Updated user information");
                request.getRequestDispatcher(ServletURL.USERS).forward(request, response);
            } else {
                throw new NotUpdateException("Failed to update user data");
            }
        } catch (RuntimeException e) {
            LOGGER.info("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(ServletURL.USER_EDITING).forward(request, response);
        }
    }
}
