package com.epam.controller.Admin.Products;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Category;
import com.epam.entity.Product;
import com.epam.entity.User;
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
/**
 * Class processes requests for /dataProduct url.
 */
@WebServlet(name = "DataProductServlet", urlPatterns = ServletURL.DATA_PRODUCT)
public class DataProductServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(DataProductServlet.class);

    private ProductService productService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }
    /**
     * Method processes GET request for /dataProduct url
     * sets page attributes and returns filled productEditing.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            Product product = productService.getProduct(id).get();

            request.setAttribute("product", product);

            request.setAttribute("message", "success");

            request.getRequestDispatcher(JspURL.PRODUCT_EDITING_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(JspURL.PRODUCTS_PAGE).forward(request,response);
        }
    }
    /**
     * Method processes POST request for /dataProduct url
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);

    }
}
