package time_tracking.controller.commands.login;

import time_tracking.controller.commands.Command;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class LoginCommand implements Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        return PagesPath.LOGIN_PAGE;
    }
}
