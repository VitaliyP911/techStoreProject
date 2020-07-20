package com.epam.controller.User;

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
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /profileSettings url.
 */
@WebServlet(name = "ProfileSettingsServlet", urlPatterns = ServletURL.PROFILE_SETTINGS)
public class ProfileSettingsServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProfileSettingsServlet.class);
    private UserService userService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    /**
     * Method processes GET request for /profileSettings url
     * redirects on profileSettings.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspURL.PROFILE_SETTINGS_PAGE).forward(request,response);
    }
    /**
     * Method processes POST request for /profileSettings url
     * takes parameters from the page, changes user information and
     * returns home.jsp
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User user = (User) request.getSession().getAttribute("user");

            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String password = request.getParameter("password");

            if(!name.isEmpty()){
                user.setName(name);
            }
            if(!surname.isEmpty()){
                user.setSurname(surname);
            }
            if(!password.isEmpty()){
                user.setPassword(password);
            }

            if (userService.update(user.getId(), user)) {
                HttpSession session = request.getSession();
                session.setAttribute("user", userService.getDataUser(user.getEmail()).get());
                LOGGER.info("updated user data");
                request.getRequestDispatcher(JspURL.HOME_PAGE).forward(request, response);
            } else {
                throw new NotUpdateException("Failed to update user account");
            }
        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.PROFILE_SETTINGS_PAGE).forward(request,response);
        }
    }
}
