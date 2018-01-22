package time_tracking.dao.impl;

import time_tracking.dao.*;
import time_tracking.dao.exception.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private volatile DataSource dataSource = ConnectionPoolHolder.getDataSource();

    private synchronized Connection getConnection() {
        try {
            Connection connection = dataSource.getConnection();
            return connection;
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public ActivityDao createActivityDao() {
        return new JDBCActivityDao(getConnection());
    }

    @Override
    public ActivityLogDao createActivityLogDao() {
        return new JDBCActivityLogDao(getConnection());
    }

}
