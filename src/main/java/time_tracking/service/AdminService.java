package time_tracking.service;

import time_tracking.model.entity.Activity;
import time_tracking.service.entity.OrderInfoForAdmin;

import java.util.List;

public interface AdminService {
    long getActivityIdByOrder(long orderId);

    String getActionNameOfOrderByOrderId(long orderId);

    void confirmOrder(long orderId, long activityId);

    void rejectOrder(long orderId);

    List<OrderInfoForAdmin> getPendingOrders(long numberOfPortion, long sizeOfPortion);

    long getCountOfPendingOrders();
}
