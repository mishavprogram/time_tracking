package time_tracking.controller;

import org.apache.log4j.Logger;
import time_tracking.controller.commands.Command;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * main servlet which intercept all user requests
 */
public class FrontController extends HttpServlet {
    private AtomicInteger countOfEnter = new AtomicInteger(0);

    private static final Logger logger = Logger.getLogger(FrontController.class);
    /**
     * class which contains all commands
     */
    private static transient CommandHolder commandHolder = new CommandHolder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = processRequest(request, response);
        if (!path.equals(PagesPath.REDIRECT)) {
            request.getRequestDispatcher(path).forward(request, response);
        }
    }

    /**
     * after all post requests it is necessary to perform redirect
     * accordingly with Post-Redirect-Get pattern
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String path = processRequest(request, response);
        if (!path.equals(PagesPath.FORWARD)) {
            response.sendRedirect(path);
        }
    }

    /**
     * this method search necessary command and perform it
     *
     * @param request
     * @param response
     * @return page url
     * @throws ServletException
     * @throws IOException
     */
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        String key = method + ":" + path;
        logger.debug(method + " Uri: " + path);
        Command command = commandHolder.findCommand(key);
        return command.execute(request, response);
    }

    public static void setCommandHolder(CommandHolder commandHolder) {
        FrontController.commandHolder = commandHolder;
    }
}