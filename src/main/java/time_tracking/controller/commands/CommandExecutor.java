package time_tracking.controller.commands;

import time_tracking.controller.validators.Errors;
import time_tracking.exception.ApplicationException;
import time_tracking.service.exception.ServiceException;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.MessageKeys;
import time_tracking.utils.constants.PagesPath;
import time_tracking.utils.extractors.RequestParamExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * wrapper class for specific commands which can throw exception
 */
public abstract class CommandExecutor implements Command {
    private final String nextPage;
    private RequestParamExtractor paramExtractor = new RequestParamExtractor();
    private static final int DEFAULT_QUANTITY_VALUE=3;
    private static final int DEFAULT_OFFSET_VALUE = 0;

    protected CommandExecutor(String nextPage) {
        this.nextPage = nextPage;
    }

    /**
     * main method which wrap all actions, which could throw some exception
     * @param request request from client
     * @param response response to client
     * @return
     * @throws ServletException
     * @throws IOException
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("CommandExecutor execute."+". Thread : "+Thread.currentThread().getName());
        try {
            return performExecute(request, response);
        }
        catch (ServiceException exception){
            putErrorMessageInRequest(request, exception.getMessageKey());
            request.getRequestDispatcher(nextPage).forward(request, response);
        }
        catch (ApplicationException exception){
            putErrorMessageInRequest(request, exception.getMessageKey());
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request,response);
        }
        catch (Exception exception){
            putErrorMessageInRequest(request, MessageKeys.UNKNOWN_ERROR_OCCURED);
            request.getRequestDispatcher(PagesPath.ERROR_PAGE).forward(request,response);

        }
        return PagesPath.FORWARD;
    }

    public void putErrorMessageInRequest(HttpServletRequest request, String messageKey){
        Errors errors = (Errors) request.getAttribute(Attributes.ERRORS);
        if(errors==null){
            errors = new Errors();
        }
        errors.addError(Attributes.ERROR, messageKey);
        request.setAttribute(Attributes.ERRORS, errors);
    }

    public abstract String performExecute(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException;

    protected int getLimitValueOrDefault(HttpServletRequest request){
        return Optional.ofNullable(paramExtractor.extractPaginParam(request, Attributes.LIMIT))
                .orElse(DEFAULT_QUANTITY_VALUE);
    }

    protected int getOffsetValueOrDefault(HttpServletRequest request, int quantity){
        return Optional.ofNullable(paramExtractor.extractPaginParam(request, Attributes.OFFSET))
                .map(page->(page-1)*quantity)
                .orElse(DEFAULT_OFFSET_VALUE);
    }

    protected int calculateOverallPagesCount(int limit, int totalCount){
        return (int)Math.ceil((totalCount+0.0)/limit);
    }
}