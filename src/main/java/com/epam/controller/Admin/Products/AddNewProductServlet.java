package com.epam.controller.Admin.Products;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.Category;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.exception.IncorrectDataException;
import com.epam.exception.NotSaveException;
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

@WebServlet(name = "AddNewProductServlet", urlPatterns = ServletURL.ADD_NEW_PRODUCT)
public class AddNewProductServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(AddNewProductServlet.class);

    private ProductService productService;

    @Override
    public void init() throws ServletException {
        productService = new ProductServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {

            Product product = new Product();

            String name = request.getParameter("name");
            String price = request.getParameter("price");
            String guarantee = request.getParameter("guarantee");
            String category = request.getParameter("category");
            String nameCompany = request.getParameter("nameCompany");

            if(!name.isEmpty() && !price.isEmpty() && !guarantee.isEmpty()
                    && !category.isEmpty() && !nameCompany.isEmpty()) {

                product.setName(name);
                product.setPrice(Integer.parseInt(price));
                product.setGuarantee(Integer.parseInt(guarantee));
                product.setCategory(Category.valueOf(category));
                product.setNameCompany(nameCompany);

                if (productService.addNewProduct(product)) {
                    LOGGER.info("Add new product in database");
                    request.setAttribute("message", "success");
                    request.getRequestDispatcher(ServletURL.PRODUCTS).forward(request, response);
                } else {
                    throw new NotSaveException("Failed to save product");
                }
            }else{
                throw new IncorrectDataException("Failed to save product");
            }
        }catch (RuntimeException e){
            LOGGER.error("RuntimeException: " + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(JspURL.HOME_PAGE).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
