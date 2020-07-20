package com.epam.controller.Basket;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.controller.Admin.CheckAdminServlet;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.exception.NotAddElementToListException;
import com.epam.service.BasketService;
import com.epam.service.ProductService;
import com.epam.service.UserService;
import com.epam.service.impl.BasketServiceImpl;
import com.epam.service.impl.ProductServiceImpl;
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
 * Class processes requests for /addProductToBasket url.
 */
@WebServlet(name = "AddProductToBasketServlet", urlPatterns = ServletURL.ADD_PRODUCT_TO_BASKET)
public class AddProductToBasketServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddProductToBasketServlet.class);

    private BasketService basketService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        basketService = new BasketServiceImpl();
    }
    /**
     * Method processes GET request for /addProductToBasket url
     * takes user data from the session, adds a new product to his basket
     * and returns productInformation.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id = Long.parseLong(request.getParameter("id"));

        try {

            User user = (User) request.getSession().getAttribute("user");

            Integer count =  Integer.parseInt(request.getParameter("count"));

            Product product = basketService.getProduct(id).get();

            product.setCount(count);

            if(basketService.addNewProductToBasket(product, user)){
                LOGGER.info("Added product to basket");
                request.getRequestDispatcher(ServletURL.PRODUCT_INFORMATION + "?id=" + id).forward(request, response);
            }else{
                throw new NotAddElementToListException("Not add element to list");
            }

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(ServletURL.PRODUCT_INFORMATION + "?id=" + id).forward(request, response);
        }
    }
    /**
     * Method processes POST request for /addProductToBasket url
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
