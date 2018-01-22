package time_tracking.controller.commands.admin;

import time_tracking.controller.commands.CommandExecutor;
import time_tracking.service.AdminService;
import time_tracking.service.impl.DefaultAdminService;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MakeDecisionSubmitCommand extends CommandExecutor {
    private AdminService adminService = new DefaultAdminService();

    public MakeDecisionSubmitCommand() {
        super(PagesPath.MAKE_DECISION_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long orderId = Long.parseLong(request.getSession().getAttribute(Attributes.CURRENT_ORDER_ID).toString());
        long activityId = Long.parseLong(request.getSession().getAttribute(Attributes.CURRENT_ACTIVITY_ID).toString());

        if (request.getParameter(Attributes.CONFIRM) != null) {
            adminService.confirmOrder(orderId, activityId);

            return PagesPath.ADMIN_HOME;
        } else if (request.getParameter(Attributes.REJECT) != null) {
            adminService.rejectOrder(orderId);

            return PagesPath.ADMIN_HOME;
        }

        response.sendRedirect(PagesPath.ERROR_PAGE);
        return PagesPath.REDIRECT;
    }
}
