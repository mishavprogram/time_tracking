package time_tracking.controller.commands.admin;

import time_tracking.controller.commands.Command;
import time_tracking.controller.commands.CommandExecutor;
import time_tracking.service.AdminService;
import time_tracking.service.entity.OrderInfoForAdmin;
import time_tracking.service.impl.DefaultAdminService;
import time_tracking.utils.constants.Attributes;
import time_tracking.utils.constants.PagesPath;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class AdminPageCommand extends CommandExecutor {
    AdminService adminService = new DefaultAdminService();

    public AdminPageCommand() {
        super(PagesPath.ADMIN_HOME_PAGE);
    }

    @Override
    public String performExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int maxCountOfElemOnPage = getLimitValueOrDefault(request);
        long totalCount = adminService.getCountOfPendingOrders();
        int totalPages = calculateOverallPagesCount(maxCountOfElemOnPage, (int)totalCount);//danger
        int numberOfPage = getNumberOfPageOrDefault(request);

        List<OrderInfoForAdmin> orders = adminService.getPendingOrders(numberOfPage, maxCountOfElemOnPage);
        request.setAttribute(Attributes.ORDERS, orders);
        request.setAttribute(Attributes.TOTAL_PAGES, totalPages);
        return PagesPath.ADMIN_HOME_PAGE;
    }
}
