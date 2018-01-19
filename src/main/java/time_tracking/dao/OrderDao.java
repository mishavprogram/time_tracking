package time_tracking.dao;

import time_tracking.model.entity.Order;

import java.util.List;

public interface OrderDao extends GenericDao<Order> {
    void create(Order order);
    List<Order> getAllOrdersForApproving();
    List<Order> getAllOrdersForApprovingForUser(long userId);
}
