package com.epam.controller.Products;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.controller.History.HistoryServlet;
import com.epam.dao.CrudDao;
import com.epam.dao.impl.ProductDaoImpl;
import com.epam.entity.Category;
import com.epam.entity.Product;
import com.epam.service.ProductService;
import com.epam.service.impl.ProductServiceImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /catalog url.
 */
@WebServlet(name = "CatalogServlet", urlPatterns = ServletURL.CATALOG)
public class CatalogServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(CatalogServlet.class);

    private ProductService productService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }
    /**
     * Method processes GET request for /catalog url
     * fills the catalog table and returns filled catalog.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> products = productService.getProductsList();

            request.setAttribute("productList", products);

            LOGGER.info("Read the catalog data");

            request.getRequestDispatcher(JspURL.CATALOG_PAGE).forward(request, response);

        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.CATALOG_PAGE).forward(request,response);
        }
    }
    /**
     * Method processes POST request for /catalog url
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
