package com.epam.controller.Admin.Products;

import com.epam.constant.ServletURL;
import com.epam.entity.Category;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.exception.NotUpdateException;
import com.epam.service.ProductService;
import com.epam.service.impl.ProductServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(name = "ProductEditingServlet", urlPatterns = ServletURL.PRODUCT_EDITING)
public class ProductEditingServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductEditingServlet.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Long id = Long.parseLong(request.getParameter("id"));

            Product product = productService.getProduct(id).get();

            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String guarantee = request.getParameter("guarantee");
            String category = request.getParameter("category");
            String nameCompany = request.getParameter("nameCompany");

            if (!name.isEmpty()) {
                product.setName(name);
            }
            if (!price.isEmpty()) {
                product.setPrice(Integer.parseInt(price));
            }
            if (!guarantee.isEmpty()) {
                product.setGuarantee(Integer.parseInt(guarantee));
            }
            if (!category.isEmpty() ) {
                product.setCategory(Category.valueOf(category));
            }
            if (!nameCompany.isEmpty()) {
                product.setNameCompany(nameCompany);
            }

            if (productService.update(product.getId(), product)) {
                HttpSession session = request.getSession();
                session.setAttribute("product", product);
                LOGGER.info("Updated product information");
                request.getRequestDispatcher(ServletURL.PRODUCTS).forward(request, response);
            } else {
                throw new NotUpdateException("Failed to update product data");
            }

        } catch (RuntimeException e) {
            LOGGER.info("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(ServletURL.PRODUCT_EDITING).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

}
