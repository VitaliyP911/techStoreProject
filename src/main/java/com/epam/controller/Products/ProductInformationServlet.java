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
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@WebServlet(name = "ProductInformationServlet", urlPatterns = ServletURL.PRODUCT_INFORMATION)
public class ProductInformationServlet extends HttpServlet {
    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id =  Long.parseLong(request.getParameter("id"));

            Optional<Product> product = productService.getProduct(id);

            request.setAttribute("product", product.get());

            request.getRequestDispatcher(JspURL.PRODUCT_INFORMATION_PAGE).forward(request, response);

        }catch (RuntimeException e){
            request.setAttribute("error", "Incorrect data");
            request.getRequestDispatcher(JspURL.PRODUCT_INFORMATION_PAGE).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
