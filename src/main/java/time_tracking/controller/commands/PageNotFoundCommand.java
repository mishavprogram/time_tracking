package time_tracking.controller.commands;

import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.MessageKeys;
import time_tracking.utils.constants.PagesPath;
import time_tracking.controller.validators.Errors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PageNotFoundCommand implements Command{
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        Errors errors = new Errors();
        errors.addError(Attributes.ERROR, MessageKeys.URL_NOT_FOUND);
        request.setAttribute(Attributes.ERRORS, errors);
        return PagesPath.ERROR_PAGE;
    }
}