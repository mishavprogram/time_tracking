package time_tracking.controller.commands.admin;

import time_tracking.controller.commands.Command;
import time_tracking.controller.commands.CommandExecutor;
import time_tracking.model.entity.Activity;
import time_tracking.model.entity.Order;
import time_tracking.service.ActivityService;
import time_tracking.service.AdminService;
import time_tracking.service.impl.DefaultActivityService;
import time_tracking.service.impl.DefaultAdminService;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;
import time_tracking.utils.extractors.RequestParamExtractor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.file.OpenOption;
import java.util.Optional;

public class MakeDecisionCommand extends CommandExecutor {
    ActivityService activityService = new DefaultActivityService();
    AdminService adminService = new DefaultAdminService();

    private RequestParamExtractor requestExtractor = new RequestParamExtractor();

    public MakeDecisionCommand() {
        super(PagesPath.MAKE_DECISION_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long orderId = requestExtractor.extractSingleLongPathParam(request);
        request.getSession().setAttribute(Attributes.CURRENT_ORDER_ID, orderId);

        long activityId = adminService.getActivityIdByOrder(orderId);
        request.getSession().setAttribute(Attributes.CURRENT_ACTIVITY_ID, activityId);

        Optional<Activity> activity = activityService.getActivity(activityId);

        if (activity.isPresent()) {
            request.setAttribute(Attributes.ACTIVITY_USER_EMAIL, activity.get().getUser().getEmail());
            request.setAttribute(Attributes.ACTIVITY_NAME, activity.get().getName());
            request.setAttribute(Attributes.ACTIVITY_START_DATE, activity.get().getStartDate().toString());
            request.setAttribute(Attributes.ACTIVITY_END_DATE, activity.get().getEndDate().toString());
            String actionName = adminService.getActionNameOfOrderByOrderId(orderId);
            request.setAttribute(Attributes.ACTIVITY_ACTION, actionName);
            request.setAttribute(Attributes.ACTIVITY_ID, activity.get().getId());

            return PagesPath.MAKE_DECISION_PAGE;
        }
        response.sendRedirect(PagesPath.ADMIN_HOME_PAGE);
        return PagesPath.ERROR_PAGE;
    }
}
