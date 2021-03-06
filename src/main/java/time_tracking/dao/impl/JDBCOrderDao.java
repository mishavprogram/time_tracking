package time_tracking.dao.impl;

import time_tracking.dao.OrderDao;
import time_tracking.dao.exception.DaoException;
import time_tracking.dao.mapper.impl.OrderMapper;
import time_tracking.model.entity.Order;
import time_tracking.model.entity.StatusOrder;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCOrderDao implements OrderDao {
    private static final String INSERT_ORDER = "INSERT INTO `time_tracking_db`.`ordert` (`action`, `status`, `activityt_id`) VALUES (?, ?, ?)";
    private static final String SELECT_PENDING_ORDER_WITH_ACTIVITY = "SELECT * FROM ordert \n" +
            "left join activityt on activityt_id = activityt.id\n" +
            "left join usert on activityt.usert_id = usert.id\n" +
            "where ordert.status='pending'";
    private static final String SELECT_COUNT_PENDING_ORDER_WITH_ACTIVITY = "SELECT count(*) FROM ordert \n" +
            "left join activityt on activityt_id = activityt.id\n" +
            "left join usert on activityt.usert_id = usert.id\n" +
            "where ordert.status='pending'";
    private static final String SELECT_ORDER_WITH_ACTIVITY_FOR_USER = "SELECT * FROM ordert \n" +
            "join activityt on (activityt_id = activityt.id) \n" +
            "join usert on (activityt.usert_id = usert.id)\n" +
            "where ordert.status='pending' and activityt.usert_id=? ";
    private static final String CHANGE_STATUS = "UPDATE `time_tracking_db`.`ordert` SET `status`=? WHERE `id`=?;";
    private static final String FIND_ORDER_BY_ID = "SELECT * FROM ordert join activityt on (activityt_id = activityt.id) join usert on (activityt.usert_id = usert.id) where ordert.id = ?";
    private static final int ALL_USERS_ID = -1;
    private Connection connection;

    JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order order) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ORDER);
            preparedStatement.setString(1, order.getAction().toString());
            preparedStatement.setString(2, order.getStatus().toString());
            preparedStatement.setLong(3, order.getActivity().getId());

            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public void setOrderStatus(StatusOrder order, long orderId) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHANGE_STATUS);
            preparedStatement.setString(1, order.getStatusName().toString());
            preparedStatement.setLong(2, orderId);

            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<Order> findById(long id) {
        Optional<Order> order = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()) {
                set.next();

                OrderMapper orderMapper = new OrderMapper();
                order = Optional.of(orderMapper.extractFromResultSet(set));
            }

        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return order;
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
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Order> getAllOrdersForApproving(long numberOfPortion, long sizeOfPortion) {
        List<Order> orderList = getOrders(numberOfPortion, sizeOfPortion, SELECT_PENDING_ORDER_WITH_ACTIVITY, ALL_USERS_ID);
        return orderList;
    }

    @Override
    public List<Order> getAllOrdersForApprovingForUser(long numberOfPortion, long sizeOfPortion, long userId) {
        List<Order> orderList = getOrders(numberOfPortion, sizeOfPortion, SELECT_ORDER_WITH_ACTIVITY_FOR_USER, userId);
        return orderList;
    }

    private List<Order> getOrders(long numberOfPortion, long sizeOfPortion, String sqlStatement, long userId) {
        List<Order> orderList = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sqlStatement);
            if (sqlStatement.equals(SELECT_ORDER_WITH_ACTIVITY_FOR_USER) && userId != ALL_USERS_ID)
                preparedStatement.setLong(1, userId);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            extractOrdersFromSetToList(numberOfPortion, sizeOfPortion, orderList, set);
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return orderList;
    }

    @Override
    public long getCountOfPendingOrders() {
        long count = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_COUNT_PENDING_ORDER_WITH_ACTIVITY);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()) {
                set.next();
                count = set.getLong(1);
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return count;
    }

    private void extractOrdersFromSetToList(long numberOfPortion, long sizeOfPortion, List<Order> orders, ResultSet set) throws SQLException {
        long startPosition = sizeOfPortion * numberOfPortion - sizeOfPortion + 1;
        long n = 0;
        long count = 0;
        OrderMapper orderMapper = new OrderMapper();

        while (set.next()) {
            n++;
            if (n >= startPosition && count < sizeOfPortion) {
                count++;
                Order order = orderMapper.extractFromResultSet(set);
                orders.add(order);
            }
        }
    }

    private void extractOrdersFromSetToList(List<Order> orders, ResultSet set) throws SQLException {
        OrderMapper orderMapper = new OrderMapper();
        while (set.next()) {
            Order order = orderMapper.extractFromResultSet(set);
            orders.add(order);
        }
    }
}
