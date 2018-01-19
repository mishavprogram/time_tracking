package time_tracking.controller.commands.user;

import time_tracking.controller.commands.Command;
import time_tracking.controller.commands.CommandExecutor;
import time_tracking.dao.DaoFactory;
import time_tracking.service.UserService;
import time_tracking.service.impl.UserServiceImpl;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Optional;

public class AddActivitySubmitCommand extends CommandExecutor {
    private UserService userService = new UserServiceImpl();

    public AddActivitySubmitCommand() {
        super(PagesPath.ADDING_ACTIVITY_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Perform execute in AddActivitySubmitCommand."+". Thread : "+Thread.currentThread().getName());
        String pageToGo = PagesPath.ADDING_ACTIVITY;

        Long userId = Long.parseLong(request.getParameter("userId"));
        String name = request.getParameter("actName");
        LocalDate startDate = LocalDate.parse(request.getParameter("startDate"));
        LocalDate endDate = LocalDate.parse(request.getParameter("endDate"));

        userService.addActivity(name, startDate, endDate, userId);

        return pageToGo;
    }
}
