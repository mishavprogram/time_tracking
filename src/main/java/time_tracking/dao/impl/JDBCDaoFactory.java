package time_tracking.dao.impl;

import org.apache.commons.dbcp.BasicDataSource;
import time_tracking.dao.*;
import time_tracking.dao.exception.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private volatile DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public JDBCDaoFactory(){
        System.out.println("JDBC Dao Factory created. Datasource = "+dataSource);
    }

    private synchronized Connection getConnection() {
        try {
         System.out.println("try to get connection. Thread : "+Thread.currentThread().getName());

         System.out.println("ACTIVE connections : "+((BasicDataSource)dataSource).getNumActive());

         Connection connection = dataSource.getConnection();
        return connection;
        } catch (SQLException e) {
            System.out.println("Dao Exception. DataSource : " + dataSource + ". Thread : " + Thread.currentThread().getName());
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
