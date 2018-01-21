package time_tracking.service;

import time_tracking.model.entity.Activity;
import time_tracking.service.entity.OrderInfoForAdmin;

import java.util.List;

public interface AdminService {
    void confirmOrder(long orderId);
    void rejectOrder(long orderId);
    List<OrderInfoForAdmin> getPendingOrders(long numberOfPortion, long sizeOfPortion);
    long getCountOfPendingOrders();
}
