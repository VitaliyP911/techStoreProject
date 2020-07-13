package com.epam.controller.Basket;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
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

@WebServlet(name = "AddProductToBasketServlet", urlPatterns = ServletURL.ADD_PRODUCT_TO_BASKET)
public class AddProductToBasketServlet extends HttpServlet {
    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        basketService = new BasketServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");

        Long id = Long.parseLong(request.getParameter("id"));

        Integer count =  Integer.parseInt(request.getParameter("count"));

        try {
            Product product = basketService.getProduct(id).get();

            product.setCount(count);

            if(basketService.addNewProductToBasket(product, user)){
                request.getRequestDispatcher(ServletURL.PRODUCT_INFORMATION + "?id=" + id).forward(request, response);
            }else{
                throw new NotAddElementToListException("Not add element to list");
            }

        }catch (RuntimeException e){
            e.printStackTrace();
            request.setAttribute("status", "warning");
            request.getRequestDispatcher(ServletURL.PRODUCT_INFORMATION + "?id=" + id).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
