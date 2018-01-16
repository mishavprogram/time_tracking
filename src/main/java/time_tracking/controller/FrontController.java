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
public class FrontController extends HttpServlet{
    private AtomicInteger countOfEnter = new AtomicInteger(0);

    private static final Logger logger = Logger.getLogger(FrontController.class);
    /**
     * class which contains all commands
     */
    private static transient CommandHolder commandHolder = new CommandHolder();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Front doGet"+". Thread : "+Thread.currentThread().getName()+" .Session ="+request.getSession().toString());
        int n = countOfEnter.incrementAndGet();
        System.out.println("countOfEnter : "+countOfEnter+". Thread : "+Thread.currentThread().getName()+"\n\n");
        if (n>3){
            System.out.println("Ми на місці для дебагу"+". Thread : "+Thread.currentThread().getName());
        }


        String path = processRequest(request, response);
        if(!path.equals(PagesPath.REDIRECT)) {
            System.out.println("Цей поток буде СКРИТО перенаправлено на "+path+". Thread : "+Thread.currentThread().getName());
             request.getRequestDispatcher(path).forward(request, response);
        }
    }

    /**
     * after all post requests it is necessary to perform redirect
     * accordingly with Post-Redirect-Get pattern
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("\nFRONT DO_POST"+". Thread : "+Thread.currentThread().getName()+"\n\n");
        String path = processRequest(request, response);
        if(!path.equals(PagesPath.FORWARD)){
            System.out.println("Цей поток буде ЯВНО перенаправлено на "+path+" . Thread : "+Thread.currentThread().getName()+"\n\n");
            response.sendRedirect(path);
        }
    }

    /**
     * this method search necessary command and perform it
     * @param request
     * @param response
     * @return page url
     * @throws ServletException
     * @throws IOException
     */
    public String processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        System.out.println("Process Request"+". Thread : "+Thread.currentThread().getName());
        String method = request.getMethod().toUpperCase();
        String path = request.getRequestURI();
        String key = method+":"+path;
        logger.debug(method + " Uri: "+ path);
        Command command = commandHolder.findCommand(key);
        System.out.println(command.getClass().getName()+". Thread : "+Thread.currentThread().getName());
        return command.execute(request, response);
    }

    public static void setCommandHolder(CommandHolder commandHolder) {
        FrontController.commandHolder=commandHolder;
    }
}