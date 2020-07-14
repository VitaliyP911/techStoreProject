package com.epam.controller.Basket;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.exception.NotDeleteException;
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

@WebServlet(name = "DeleteProductWithBasketServlet", urlPatterns = ServletURL.DELETE_PRODUCT_WITH_BASKET)
public class DeleteProductWithBasketServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteProductWithBasketServlet.class);

    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        basketService = new BasketServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        Long id = Long.parseLong(request.getParameter("id"));

        try {
            Product product = basketService.getProduct(id).get();

            if(basketService.deleteProductWithBasket(product, user)) {
                LOGGER.info("Removed the product from the basket");
                request.getRequestDispatcher(ServletURL.BASKET).forward(request, response);
            }else {
                throw new NotDeleteException("Not delete element with list");
            }
        }catch (RuntimeException e){
            LOGGER.info("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(ServletURL.BASKET).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
