package com.epam.controller.Admin.Users;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.controller.Admin.Products.ProductsServlet;
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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /dataUser url.
 */
@WebServlet(name = "DataUserServlet", urlPatterns = ServletURL.DATA_USER)
public class DataUserServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataUserServlet.class);
    /**
     * Method initializes required resources.
     */
    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    /**
     * Method processes GET request for /dataUser url
     * sets page attributes and returns filled userEditing.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String email = (String) request.getParameter("email");

            User user = userService.getDataUser(email).get();

            request.setAttribute("user", user);

            request.getRequestDispatcher(JspURL.USER_EDITING_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.USERS_PAGE).forward(request,response);
        }
    }
    /**
     * Method processes POST request for /dataUser url
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
