package com.epam.controller.Products;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Category;
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
import java.util.stream.Collectors;

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

            List<Product> products = productService.getProductsList();

            if(!company.isEmpty()){
                products = products.stream()
                        .filter(x -> x.getNameCompany().equals(company))
                        .collect(Collectors.toList());
            }
            if (!price.isEmpty()){
                if (price.equals("larger")) {
                    products.sort((first, second) ->
                            first.getPrice() <= second.getPrice() ? 1 : -1);
                }
                if (price.equals("smaller")) {
                    products.sort((first, second) ->
                            first.getPrice() >= second.getPrice() ? 1 : -1);
                }
            }
            if(!category.isEmpty()){
                products = products.stream()
                        .filter(x -> x.getCategory().getNameCategory().equals(category))
                        .collect(Collectors.toList());
            }

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
