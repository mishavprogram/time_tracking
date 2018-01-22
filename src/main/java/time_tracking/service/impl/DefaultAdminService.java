package time_tracking.service.impl;

import time_tracking.dao.ActivityDao;
import time_tracking.dao.DaoFactory;
import time_tracking.dao.OrderDao;
import time_tracking.model.entity.*;
import time_tracking.service.AdminService;
import time_tracking.service.entity.OrderInfoForAdmin;
import time_tracking.service.exception.ServiceException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DefaultAdminService implements AdminService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public long getCountOfPendingOrders() {
        long count;
        OrderDao orderDao = daoFactory.createOrderDao();
        count = orderDao.getCountOfPendingOrders();
        orderDao.close();

        return count;
    }

    @Override
    public String getActionNameOfOrderByOrderId(long orderId) {
        String result = "";

        OrderDao orderDao = daoFactory.createOrderDao();
        Optional<Order> order = orderDao.findById(orderId);
        if (order.isPresent()) {
            result = order.get().getAction().getActionName();
        }
        orderDao.close();
        return result;
    }

    @Override
    public List<OrderInfoForAdmin> getPendingOrders(long numberOfPortion, long sizeOfPortion) {
        List<OrderInfoForAdmin> list = new ArrayList<>();

        OrderDao orderDao = daoFactory.createOrderDao();
        List<Order> orders = orderDao.getAllOrdersForApproving(numberOfPortion, sizeOfPortion);

        for (Order o :
                orders) {
            OrderInfoForAdmin infoForAdmin = new OrderInfoForAdmin();

            infoForAdmin.setId(o.getId());
            infoForAdmin.setUserEmail(o.getActivity().getUser().getEmail());
            infoForAdmin.setUserName(o.getActivity().getUser().getName());
            infoForAdmin.setUserSurname(o.getActivity().getUser().getSurname());
            infoForAdmin.setActivityName(o.getActivity().getName());
            infoForAdmin.setAction(o.getAction().getActionName());
            infoForAdmin.setActivityId(o.getActivity().getId());

            list.add(infoForAdmin);
        }
        orderDao.close();

        return list;
    }

    @Override
    public void confirmOrder(long orderId, long activityId) {
        OrderDao orderDao = daoFactory.createOrderDao();
        Optional<Order> order = orderDao.findById(orderId);

        if (order.isPresent()) {
            ActivityDao activityDao = daoFactory.createActivityDao();

            if (order.get().getAction().equals(Action.ADD))
                activityDao.setActivityStatus(StatusActivity.APPROVED, activityId);
            else {
                activityDao.setActivityStatus(StatusActivity.DELETED, activityId);
            }

            activityDao.close();
            orderDao.setOrderStatus(StatusOrder.APPROVED, orderId);
        }
        orderDao.close();
    }

    @Override
    public void rejectOrder(long orderId) {
        OrderDao orderDao = daoFactory.createOrderDao();
        orderDao.setOrderStatus(StatusOrder.REJECTED, orderId);

        Optional<Order> order = orderDao.findById(orderId);
        if (order.isPresent()) {
            ActivityDao activityDao = daoFactory.createActivityDao();

            if (order.get().getAction().equals(Action.ADD))
                activityDao.setActivityStatus(StatusActivity.DELETED, order.get().getActivity().getId());

            activityDao.close();
        }

        orderDao.close();
    }

    @Override
    public long getActivityIdByOrder(long orderId) {
        OrderDao orderDao = daoFactory.createOrderDao();
        Optional<Order> order = orderDao.findById(orderId);
        orderDao.close();
        if (order.isPresent()) {
            ActivityDao activityDao = daoFactory.createActivityDao();
            Optional<Activity> activity = activityDao.findById(order.get().getActivity().getId());
            if (activity.isPresent()) {
                activityDao.close();
                return activity.get().getId();
            }
            activityDao.close();
        }
        throw new ServiceException("");
    }
}
