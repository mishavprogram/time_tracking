package time_tracking.controller.commands.user;

import time_tracking.controller.commands.CommandExecutor;
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
        int maxCountOfElemOnPage = getLimitValueOrDefault(request);
        LocalDate dateToShow = getLocalDateOrDefault(request, LocalDate.now());
        long userId = (long) (request.getSession().getAttribute(Attributes.USER_ID));
        long totalCount = activityService.getCountOfActiveActivities(userId, dateToShow);
        int totalPages = calculateOverallPagesCount(maxCountOfElemOnPage, (int) totalCount);
        int numberOfPage = getNumberOfPageOrDefault(request);

        List<Activity> activities = activityService.getActiveActivities(numberOfPage, maxCountOfElemOnPage, userId, dateToShow);
        request.setAttribute(Attributes.ACTIVITIES, activities);
        request.setAttribute(Attributes.TOTAL_PAGES, totalPages);
        return PagesPath.USER_HOME_PAGE;
    }
}
