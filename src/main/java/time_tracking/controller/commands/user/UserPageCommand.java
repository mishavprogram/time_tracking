package time_tracking.controller.commands.user;

import time_tracking.controller.commands.Command;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserPageCommand implements Command {
    //TODO настроїти достатні властивості сесії для роботи юзера
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PagesPath.USER_HOME_PAGE;
    }
}
