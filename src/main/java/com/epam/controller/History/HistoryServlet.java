package com.epam.controller.History;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.History;
import com.epam.entity.Product;
import com.epam.entity.User;
import com.epam.exception.IncorrectDataException;
import com.epam.exception.NotSaveException;
import com.epam.service.BasketService;
import com.epam.service.HistoryService;
import com.epam.service.UserService;
import com.epam.service.impl.BasketServiceImpl;
import com.epam.service.impl.HistoryServiceImpl;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /history url.
 */
@WebServlet(name = "HistoryServlet", urlPatterns = ServletURL.HISTORY)
public class HistoryServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(HistoryServlet.class);

    private HistoryService historyService;
    private BasketService basketService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        historyService = new HistoryServiceImpl();
        basketService = new BasketServiceImpl();
    }
    /**
     * Method processes GET request for /history url
     * takes user data from the session, adds
     * the purchase to the story, clears the cart
     * and redirect to the getHistory servlet.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            List<History> historyList = new LinkedList<>();

            user.getProductList().stream()
                    .forEach(i -> {
                        historyList.add(new History(i.getName(),
                                i.getPrice(),
                                i.getNameCompany(),
                                i.getCount(),
                                user.getId(),
                                historyService.currentTimeGeneration(),
                                i.getPrice() * i.getCount()));
                    });

            if (historyService.addPurchaseToHistory(historyList)) {
                basketService.clearBasket(user);
                LOGGER.info("They made a purchase and cleaned the basket" );
                request.getRequestDispatcher(ServletURL.CHECK_ADMIN).forward(request, response);
            } else {
                throw new NotSaveException("Failed to save history");
            }
        } catch (RuntimeException e) {
            LOGGER.info("RuntimeException" + e.getMessage());
            request.setAttribute("message", "warning");
            request.getRequestDispatcher(ServletURL.PAY).forward(request, response);
        }
    }
    /**
     * Method processes POST request for /history url
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
