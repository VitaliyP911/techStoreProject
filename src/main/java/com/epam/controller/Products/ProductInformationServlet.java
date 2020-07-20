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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /productInformation url.
 */
@WebServlet(name = "ProductInformationServlet", urlPatterns = ServletURL.PRODUCT_INFORMATION)
public class ProductInformationServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(ProductInformationServlet.class);
    private ProductService productService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }
    /**
     * Method processes GET request for /productInformation url
     * takes the id parameter, searches for product information after it, and
     * returns filled productInformation.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id =  Long.parseLong(request.getParameter("id"));

            Optional<Product> product = productService.getProduct(id);

            request.setAttribute("product", product.get());

            LOGGER.info("Read the product data");

            request.getRequestDispatcher(JspURL.PRODUCT_INFORMATION_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.PRODUCT_INFORMATION_PAGE).forward(request,response);
        }
    }
    /**
     * Method processes POST request for /productInformation url
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
