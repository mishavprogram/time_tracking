package time_tracking.dao;

import time_tracking.model.entity.Order;
import time_tracking.model.entity.StatusOrder;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    public void setOrderStatus(StatusOrder order, long orderId);
    void create(Order order);
    List<Order> getAllOrdersForApproving(long numberOfPortion, long sizeOfPortion);
    List<Order> getAllOrdersForApprovingForUser(long numberOfPortion, long sizeOfPortion, long userId);
    long getCountOfPendingOrders();
}
