package time_tracking.dao.impl;

import time_tracking.dao.*;
import time_tracking.dao.exception.DaoException;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {

    private DataSource dataSource = ConnectionPoolHolder.getDataSource();

    public JDBCDaoFactory(){
        System.out.println("JDBC Dao Factory created. Datasource = "+dataSource);
    }
    private Connection dangerConnection;

    private Connection getConnection(){
        try {
            System.out.println("try to get connection. Thread : "+Thread.currentThread().getName());
            return dataSource.getConnection();
        } catch (SQLException e) {
            System.out.println("Dao Exception. DataSource : "+dataSource +". Thread : "+Thread.currentThread().getName());
            //throw new DaoException(e.getMessage());
            if (dangerConnection==null) dangerConnection = getDangerConnection();
            return dangerConnection;
        }
    }

    public void closeDangerConnection(){
        try {
            dangerConnection.close();
        } catch (SQLException e) {
            e.printStackTrace();
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

    private Connection getDangerConnection(){
        try {
            try {
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/time_tracking_db",
                    "root" ,
                    "1111" );
        } catch (SQLException e) {
            throw new DaoException(e.getMessage());
        }
    }
}
