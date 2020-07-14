package com.epam.controller.History;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
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

@WebServlet(name = "ClearHistoryServlet", urlPatterns = ServletURL.CLEAR_HISTORY)
public class ClearHistoryServlet extends HttpServlet {
    private HistoryService historyService;

    @Override
    public void init() throws ServletException {
        historyService = new HistoryServiceImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            User user = (User) request.getSession().getAttribute("user");

            if(historyService.clearHistory(user)){
                request.setAttribute("status", "success");
                request.getRequestDispatcher(ServletURL.GET_HISTORY).forward(request,response);
            }else {
                throw new NotDeleteException("");
            }
        }catch (RuntimeException e){
            request.setAttribute("status", "warning");
            request.getRequestDispatcher(ServletURL.CHECK_ADMIN).forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request,response);
    }
}
