package com.epam.controller.User;

import com.epam.constant.JspURL;
import com.epam.constant.ServletURL;
import com.epam.entity.User;
import com.epam.exception.IncorrectDataException;
import com.epam.exception.NotDeleteException;
import com.epam.service.UserService;
import com.epam.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * Class processes requests for /deleteAccount url.
 */
@WebServlet(name = "DeleteAccountServlet", urlPatterns = ServletURL.DELETE_ACCOUNT)
public class DeleteAccountServlet extends HttpServlet {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeleteAccountServlet.class);

    private UserService userService;
    /**
     * Method initializes required resources.
     */
    @Override
    public void init() throws ServletException {
        userService = new UserServiceImpl();
    }
    /**
     * Method processes GET request for /deleteAccount url
     * redirects on profile.jsp.
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher(JspURL.PROFILE_PAGE).forward(request,response);
    }
    /**
     * Method processes POST request for /deleteAccount url
     * takes user data from the session, deletes the user
     * and returns logOut.jsp
     *
     * @param request  HTTP request object
     * @param response HTTP response object
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            User user = (User) request.getSession().getAttribute("user");
            if (userService.delete(user.getId())) {
                LOGGER.info("Deleted user account");
                request.getRequestDispatcher(JspURL.LOG_OUT_PAGE).forward(request,response);

            } else {
                throw new NotDeleteException("Failed to delete user account");
            }
        }catch (RuntimeException e){
            LOGGER.error("RuntimeException" + e.getMessage());
            request.getRequestDispatcher(JspURL.HOME_PAGE).forward(request,response);
        }
    }
}
