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
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /registration url.
 */
@WebServlet(name = "RegistrationServlet", urlPatterns = ServletURL.REGISTRATION)
public class RegistrationServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrationServlet.class);

    private UserService userService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    /**
     * Method processes GET request for /registration url
     * redirects on registration.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspURL.REGISTRATION_PAGE).forward(request,response);
    }
    /**
     * Method processes POST request for /registration url
     * takes parameters from the page, registers a new user and
     * returns registration.jsp
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            String name = request.getParameter("name");
            String surname = request.getParameter("surname");
            String email = request.getParameter("email");
            String password = request.getParameter("password");

        if(!name.isEmpty() && !surname.isEmpty() && !email.isEmpty() && !password.isEmpty()) {
            if (!userService.checkForSimilarityOfEmails(email) && userService.addNewUser(new User(name, surname, email, password))) {
                LOGGER.info("Registered user");
                request.setAttribute("message", "success");
                request.getRequestDispatcher(JspURL.REGISTRATION_PAGE).forward(request,response);
            } else {
                throw new IncorrectDataException("Incorrect data");
            }
        }else{
            throw new IncorrectDataException("Incorrect data");
        }
        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(JspURL.REGISTRATION_PAGE).forward(request,response);
        }
    }
}
