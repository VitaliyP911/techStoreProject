package com.epam.controller.History;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.controller.Basket.PayServlet;
import com.epam.entity.User;
import com.epam.exception.NotDeleteException;
import com.epam.service.HistoryService;
import com.epam.service.impl.HistoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /clearHistory url.
 */
@WebServlet(name = "ClearHistoryServlet", urlPatterns = ServletURL.CLEAR_HISTORY)
public class ClearHistoryServlet extends HttpServlet {

    private static final Logger LOGGER = LoggerFactory.getLogger(ClearHistoryServlet.class);


    private HistoryService historyService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        historyService = new HistoryServiceImpl();
    }
    /**
     * Method processes GET request for /clearHistory url
     * takes user data from the session, clear your story
     * and redirects on getHistory servlet.
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

            if(historyService.clearHistory(user)){
                LOGGER.info("Cleared user history");
                request.getRequestDispatcher(ServletURL.GET_HISTORY).forward(request,response);
            }else {
                throw new NotDeleteException("Failed to clear order history");
            }
        }catch (RuntimeException e){
            LOGGER.info("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(ServletURL.CHECK_ADMIN).forward(request,response);
        }
    }
    /**
     * Method processes POST request for /clearHistory url
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
