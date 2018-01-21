package time_tracking.controller.commands;

import time_tracking.model.entity.RoleType;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class HomeCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RoleType roleType = RoleType.valueOf(request.getSession().getAttribute(Attributes.USER_ROLE).toString());

        if (roleType.equals(RoleType.USER))
            return PagesPath.USER_HOME_PAGE;
        else if (roleType.equals(RoleType.ADMIN))
            return PagesPath.ADMIN_HOME_PAGE;
        else return PagesPath.ERROR_PAGE;
    }
}
