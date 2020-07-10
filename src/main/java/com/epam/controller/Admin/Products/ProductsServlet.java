package com.epam.controller.Admin.Products;

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

@WebServlet(name = "ProductsServlet", urlPatterns = ServletURL.PRODUCTS)
public class ProductsServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productService.getProductsList();

            request.setAttribute("productList", products);

            request.getRequestDispatcher(JspURL.PRODUCTS_PAGE).forward(request, response);

        }catch (RuntimeException e){
            request.setAttribute("status", "Incorrect data");
            request.getRequestDispatcher(JspURL.PRODUCTS_PAGE).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
