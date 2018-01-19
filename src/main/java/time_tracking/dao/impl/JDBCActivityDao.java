package time_tracking.dao.impl;

import time_tracking.dao.ActivityDao;
import time_tracking.dao.exception.DaoException;
import time_tracking.model.entity.Activity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCActivityDao implements ActivityDao {
    public static final String INSERT_ACTIVITY = "INSERT INTO `time_tracking_db`.`activityt` ( `name`, `startDate`, `endDate`, `status`, `usert_id`) VALUES (?,?,?,?,?);";
    public static final String SELECT_LAST_INSERT_ID = "select last_insert_id();";

    private Connection connection;

    public JDBCActivityDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Activity activity) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ACTIVITY);
            preparedStatement.setString(1,activity.getName());
            preparedStatement.setString(2,activity.getStartDate().toString());
            preparedStatement.setString(3,activity.getEndDate().toString());
            preparedStatement.setString(4, activity.getStatus().toString());
            preparedStatement.setLong(5,activity.getUser().getId());

            preparedStatement.execute();
        }
        catch(SQLException ex){
            throw new DaoException(ex.getMessage());
        }
    }

    public long getLastInsertId(){
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_LAST_INSERT_ID);
            preparedStatement.execute();

            ResultSet resultSet = preparedStatement.getResultSet();
            resultSet.next();

            long id = resultSet.getLong(1);
            return id;
        }
        catch(Exception ex){
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<Activity> findById(long id) {
        return null;
    }

    @Override
    public List<Activity> findAll() {
        return null;
    }

    @Override
    public void update(Activity entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
