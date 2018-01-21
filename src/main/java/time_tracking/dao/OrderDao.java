package time_tracking.dao;

import time_tracking.model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    void create(Order order);
    List<Order> getAllOrdersForApproving(long numberOfPortion, long sizeOfPortion);
    List<Order> getAllOrdersForApprovingForUser(long numberOfPortion, long sizeOfPortion, long userId);
    long getCountOfPendingOrders();
}
