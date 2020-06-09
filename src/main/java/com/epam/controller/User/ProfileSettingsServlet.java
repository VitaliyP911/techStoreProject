package com.epam.controller.User;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.User;
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
import java.util.Optional;

@WebServlet(name = "ProfileSettingsServlet", urlPatterns = ServletURL.PROFILE_SETTINGS)
public class ProfileSettingsServlet extends HttpServlet {
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspURL.PROFILE_SETTINGS_PAGE).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User user = (User) request.getSession().getAttribute("user");

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String password = request.getParameter("password");

            if(name != ""){
                user.setName(name);
            }
            if(surname != ""){
                user.setSurname(surname);
            }
            if(password != ""){
                user.setPassword(password);
            }

            if (userService.update(user.getId(), user)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userService.getDataUser(user.getEmail()).get());
                request.getRequestDispatcher(JspURL.HOME_PAGE).forward(request, response);
            } else {
                throw new IncorrectDataException("Incorrect email or password");
            }
        }catch (RuntimeException e){
            request.setAttribute("status", "Incorrect email or password");
            doGet(request,response);
        }
    }
}
