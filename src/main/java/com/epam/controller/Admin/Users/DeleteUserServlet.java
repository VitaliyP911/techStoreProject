package com.epam.controller.Admin.Users;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.User;
import com.epam.exception.IncorrectDataException;
import com.epam.exception.NotDeleteException;
import com.epam.service.UserService;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /deleteUser url.
 */
@WebServlet(name = "DeleteUserServlet", urlPatterns = ServletURL.DELETE_USER)
public class DeleteUserServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteUserServlet.class);

    private UserService userService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    /**
     * Method processes GET request for /deleteUser url
     * redirects on users servlet.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ServletURL.USERS).forward(request,response);
    }
    /**
     * Method processes POST request for /deleteUser url
     * takes the id parameter and deletes the user with that id
     * and redirect to the users servlet
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id =  Long.parseLong(request.getParameter("id"));

            if (userService.delete(id)) {
                LOGGER.info("Removed the user from the database");
                request.getRequestDispatcher(ServletURL.USERS).forward(request,response);
            }else {
                throw new NotDeleteException("Failed to delete user");
            }
        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(ServletURL.USERS).forward(request,response);
        }

    }
}
