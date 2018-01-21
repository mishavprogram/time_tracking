package time_tracking.service.impl;

import time_tracking.dao.DaoFactory;
import time_tracking.dao.OrderDao;
import time_tracking.model.entity.Order;
import time_tracking.service.AdminService;
import time_tracking.service.entity.OrderInfoForAdmin;

import java.util.ArrayList;
import java.util.List;

public class DefaultAdminService implements AdminService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public long getCountOfPendingOrders() {
        long count = 0;
        OrderDao orderDao = daoFactory.createOrderDao();
        count = orderDao.getCountOfPendingOrders();

        return count;
    }

    @Override
    public List<OrderInfoForAdmin> getPendingOrders(long numberOfPortion, long sizeOfPortion) {
        List<OrderInfoForAdmin> list = new ArrayList<>();

        OrderDao orderDao = daoFactory.createOrderDao();
        List<Order> orders = orderDao.getAllOrdersForApproving(numberOfPortion, sizeOfPortion);

        for (Order o:
             orders) {
            OrderInfoForAdmin infoForAdmin = new OrderInfoForAdmin();

            infoForAdmin.setId(o.getId());
            infoForAdmin.setUserEmail(o.getActivity().getUser().getEmail());
            infoForAdmin.setUserName(o.getActivity().getUser().getName());
            infoForAdmin.setUserSurname(o.getActivity().getUser().getSurname());
            infoForAdmin.setActivityName(o.getActivity().getName());
            infoForAdmin.setAction(o.getAction().getActionName());

            list.add(infoForAdmin);
        }

        return list;
    }

    @Override
    public void confirmOrder(long orderId) {

    }

    @Override
    public void rejectOrder(long orderId) {

    }
}
