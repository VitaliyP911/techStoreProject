package com.epam.controller.Basket;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.User;
import com.epam.service.BasketService;
import com.epam.service.UserService;
import com.epam.service.impl.BasketServiceImpl;
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
 * Class processes requests for /pay url.
 */
@WebServlet(name = "PayServlet", urlPatterns = ServletURL.PAY)
public class PayServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(PayServlet.class);

    private BasketService basketService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        basketService = new BasketServiceImpl();
    }
    /**
     * Method processes GET request for /pay url
     * sets page attributes and returns pay.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            request.setAttribute("basket", user.getProductList());

            request.setAttribute("amountDue", basketService.calcAmountDue(user));

            LOGGER.info("Subtracted data for payment");

            request.getRequestDispatcher(JspURL.PAY_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.info("RuntimeException: Failed to subtract payment data");
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(JspURL.PAY_PAGE).forward(request, response);
        }
    }
    /**
     * Method processes POST request for /pay url
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
