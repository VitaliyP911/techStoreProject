package com.epam.controller.Admin.Products;

import com.epam.constant.ServletURL;
import com.epam.exception.NotDeleteException;
import com.epam.service.ProductService;
import com.epam.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "DeleteProductServlet", urlPatterns = ServletURL.DELETE_PRODUCT)
public class DeleteProductServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteProductServlet.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(ServletURL.PRODUCTS).forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id =  Long.parseLong(request.getParameter("id"));

            if (productService.delete(id)) {
                LOGGER.info("Removed the product from the database");
                request.getRequestDispatcher(ServletURL.PRODUCTS).forward(request,response);
            }else {
                throw new NotDeleteException("Failed to delete product");
            }

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            doGet(request,response);
        }

    }
}
