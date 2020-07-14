package com.epam.controller.Basket;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Product;
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
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "BasketServlet", urlPatterns = ServletURL.BASKET)
public class BasketServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(BasketServlet.class);

    private UserService userService;

    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            request.setAttribute("basket", user.getProductList());

            LOGGER.info("Subtracted data from the user's basket");

            request.getRequestDispatcher(JspURL.BASKET_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.BASKET_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
