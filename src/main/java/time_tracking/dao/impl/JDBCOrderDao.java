package time_tracking.dao.impl;

import time_tracking.dao.DaoFactory;
import time_tracking.dao.OrderDao;
import time_tracking.dao.exception.DaoException;
import time_tracking.dao.mapper.impl.OrderMapper;
import time_tracking.model.entity.Order;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {
    public static final String INSERT_ORDER = "INSERT INTO `time_tracking_db`.`ordert` (`action`, `status`, `activityt_id`) VALUES (?, ?, ?)";
    public static final String SELECT_ORDER_WITH_ACTIVITY = "SELECT * FROM ordert left join activityt on activityt_id = activityt.id where ordert.status='pending'";
    public static final String SELECT_ORDER_WITH_ACTIVITY_FOR_USER = "SELECT * FROM ordert \n" +
            "join activityt on (activityt_id = activityt.id) \n" +
            "join usert on (activityt.usert_id = usert.id)\n" +
            "where ordert.status='pending' and activityt.usert_id=? ";
    public static final int ALL_USERS_ID = -1;
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setString(1,order.getAction().toString());
            preparedStatement.setString(2,order.getStatus().toString());
            preparedStatement.setLong(3,order.getActivity().getId());

            preparedStatement.execute();
        }
        catch(SQLException ex){
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<Order> findById(long id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }

    //TODO
    @Override
    public List<Order> getAllOrdersForApproving() {
        //List<Order> orderList = new ArrayList<>();
        //return getOrders(orderList, SELECT_ORDER_WITH_ACTIVITY, ALL_USERS_ID);
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Order> getAllOrdersForApprovingForUser(long userId) {
        List<Order> orderList = new ArrayList<>();
        return getOrders(orderList, SELECT_ORDER_WITH_ACTIVITY_FOR_USER, userId);
    }

    private List<Order> getOrders(List<Order> orderList, String sqlStatement, long userId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            if (userId!=-1)
                preparedStatement.setLong(1,userId);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            OrderMapper orderMapper = new OrderMapper();

            while (set.next()){
                Order order = orderMapper.extractFromResultSet(set);
                if (order!=null) orderList.add(order);
            }

            return orderList;
        }
        catch(SQLException ex){
            throw new DaoException(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        DaoFactory daoFactory = new JDBCDaoFactory();
        OrderDao orderDao = daoFactory.createOrderDao();
        List<Order> orderList = orderDao.getAllOrdersForApprovingForUser(5);
        for (Order o:
             orderList) {
            System.out.println(o);
        }
    }
}
