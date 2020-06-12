package com.epam.controller.Products;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Product;
import com.epam.service.ProductService;
import com.epam.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "SortedServlet", urlPatterns = ServletURL.SORTED)
public class SortedServlet extends HttpServlet {

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String company =  request.getParameter("company");
            String price =  request.getParameter("price");
            String category = request.getParameter("category");


            List<Product> products = productService.sort(company, price, category);

            request.setAttribute("productList", products);

            request.getRequestDispatcher(JspURL.CATALOG_PAGE).forward(request, response);

        }catch (RuntimeException e){
            request.setAttribute("status", "Incorrect data");
            request.getRequestDispatcher(JspURL.CATALOG_PAGE).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
