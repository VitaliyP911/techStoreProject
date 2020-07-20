package com.epam.controller.Admin.Users;

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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /users url.
 */
@WebServlet(name = "UsersServlet", urlPatterns = ServletURL.USERS)
public class UsersServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsersServlet.class);

    private UserService userService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    /**
     * Method processes GET request for /users url
     * sets page attributes and returns users.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<User> users = userService.getUserList();

            request.setAttribute("userList", users);

            request.getRequestDispatcher(JspURL.USERS_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.USERS_PAGE).forward(request,response);
        }
    }
    /**
     * Method processes POST request for /users url
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
