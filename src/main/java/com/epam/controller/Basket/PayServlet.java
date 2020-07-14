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

@WebServlet(name = "PayServlet", urlPatterns = ServletURL.PAY)
public class PayServlet extends HttpServlet {
    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        basketService = new BasketServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            request.setAttribute("basket", user.getProductList());

            request.setAttribute("amountDue", basketService.countAmountDue(user));

            request.getRequestDispatcher(JspURL.PAY_PAGE).forward(request, response);

        }catch (RuntimeException e){
            request.setAttribute("status", "Incorrect email or password");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
