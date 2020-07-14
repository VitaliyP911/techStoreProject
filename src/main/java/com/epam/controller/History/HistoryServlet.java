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

@WebServlet(name = "HistoryServlet", urlPatterns = ServletURL.HISTORY)
public class HistoryServlet extends HttpServlet {
    private HistoryService historyService;
    private BasketService basketService;

    @Override
    public void init() throws ServletException {
        historyService = new HistoryServiceImpl();
        basketService = new BasketServiceImpl();
    }

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
                request.getRequestDispatcher(ServletURL.CHECK_ADMIN).forward(request, response);
            } else {
                throw new NotSaveException("");
            }
        } catch (RuntimeException e) {
            request.setAttribute("status", "warning");
            request.getRequestDispatcher(ServletURL.PAY).forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
