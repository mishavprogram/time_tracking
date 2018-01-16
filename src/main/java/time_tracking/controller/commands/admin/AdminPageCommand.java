package time_tracking.controller.commands.admin;

import time_tracking.controller.commands.Command;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminPageCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesPath.ADMIN_HOME_PAGE;
    }
}