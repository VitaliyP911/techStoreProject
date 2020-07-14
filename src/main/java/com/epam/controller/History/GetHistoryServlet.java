package com.epam.controller.History;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.History;
import com.epam.entity.User;
import com.epam.exception.NotSaveException;
import com.epam.service.BasketService;
import com.epam.service.HistoryService;
import com.epam.service.impl.BasketServiceImpl;
import com.epam.service.impl.HistoryServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

@WebServlet(name = "GetHistoryServlet", urlPatterns = ServletURL.GET_HISTORY)
public class GetHistoryServlet extends HttpServlet {
    private HistoryService historyService;

    @Override
    public void init() throws ServletException {
        historyService = new HistoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            request.setAttribute("historyList", historyService.getHistory(user));

            request.getRequestDispatcher(JspURL.HISTORY_PAGE).forward(request,response);
        }catch (RuntimeException e){
            request.setAttribute("status", "warning");
            request.getRequestDispatcher(JspURL.PAY_PAGE).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
