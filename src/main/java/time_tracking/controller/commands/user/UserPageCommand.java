package time_tracking.controller.commands.user;

import time_tracking.controller.commands.Command;
import time_tracking.controller.commands.CommandExecutor;
import time_tracking.dao.DaoFactory;
import time_tracking.model.entity.Activity;
import time_tracking.service.ActivityService;
import time_tracking.service.impl.DefaultActivityService;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class UserPageCommand extends CommandExecutor {
    private ActivityService activityService = new DefaultActivityService();

    public UserPageCommand() {
        super(PagesPath.USER_HOME_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int quantity = getLimitValueOrDefault(request);
        if (request.getParameter(Attributes.OFFSET)!=null){
            System.out.println("current page = "+request.getParameter(Attributes.OFFSET));
        }
        int offset = getOffsetValueOrDefault(request, quantity);//default - 0
        //int totalCount = activityService.getTotalCount();
        int totalCount = 10;
        int totalPages = calculateOverallPagesCount(quantity, totalCount);

        LocalDate dateToShow;
        if (request.getParameter("date")!=null){
            dateToShow = LocalDate.parse(request.getParameter("date"));
        }
        else dateToShow = LocalDate.now();
        long userId = (long)(request.getSession().getAttribute(Attributes.USER_ID));

        //номер порції
        int numberOfPage = 1;
        if (request.getParameter(Attributes.OFFSET)!=null){
            System.out.println("current page = "+request.getParameter(Attributes.OFFSET));
            numberOfPage = Integer.parseInt(request.getParameter(Attributes.OFFSET));
            if (numberOfPage!=1){
                System.out.println("ura!");
            }
        }

        List<Activity> activities = activityService.getPendingActivities(numberOfPage, quantity, userId, dateToShow);
        request.setAttribute(Attributes.ACTIVITIES, activities);
        request.setAttribute(Attributes.TOTAL_PAGES, totalPages);
        return PagesPath.USER_HOME_PAGE;
    }
}
