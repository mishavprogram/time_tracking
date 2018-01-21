package time_tracking.controller.commands.user;

import time_tracking.controller.commands.CommandExecutor;
import time_tracking.service.ActivityService;
import time_tracking.service.UserService;
import time_tracking.service.impl.DefaultActivityService;
import time_tracking.service.impl.DefaultUserService;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SetTimeSubmitCommand extends CommandExecutor {
    ActivityService activityService = new DefaultActivityService();

    public SetTimeSubmitCommand() {
        super(PagesPath.SET_TIME_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       return null;
    }
}
