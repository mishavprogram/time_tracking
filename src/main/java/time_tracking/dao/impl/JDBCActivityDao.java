package time_tracking.dao.impl;

import time_tracking.dao.ActivityDao;
import time_tracking.dao.DaoFactory;
import time_tracking.dao.exception.DaoException;
import time_tracking.model.entity.Action;
import time_tracking.model.entity.Activity;
import time_tracking.model.entity.StatusActivity;
import time_tracking.model.entity.User;

import javax.media.j3d.Locale;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JDBCActivityDao implements ActivityDao {
    public static final String INSERT_ACTIVITY = "INSERT INTO `time_tracking_db`.`activityt` ( `name`, `startDate`, `endDate`, `status`, `usert_id`) VALUES (?,?,?,?,?);";
    public static final String SELECT_LAST_INSERT_ID = "select last_insert_id();";

    public static final String GET_ACTIVITIES_WITHOUT_USER = "SELECT * FROM time_tracking_db.activityt ";
    public static final String GET_ACTIVITIES_WITH_USER = "SELECT * FROM time_tracking_db.activityt join usert on activityt.usert_id = usert.id and usert_id = ?";
    public static final String WITH_STATUS = " and status = ? ";
    public static final String WITH_DAYWORK = "and activityt.id in" +
            "(select activityt_id from activitylogt " +
            "where dateWork = ?)";

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

    /**
     * @param numberOfPortion - for pagination
     * @param sizeOfPortion - for pagination
     * @param status - aprroved / pending
     * @param date
     * @param user
     * @return portion of activities from some index
     * that will calculate how (numberOfPortion*(sizeOfPortion-1) + 1)
     */
    public List<Activity> findAll(long numberOfPortion, long sizeOfPortion, StatusActivity status, Optional<LocalDate> date, Optional<User> user){
        List<Activity> activities = new ArrayList<>();
        try {
            String statement = getStatement(date, user);

            PreparedStatement preparedStatement = getPreparedStatement(status, date, user, statement);

            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();

            long startPosition = numberOfPortion*(sizeOfPortion-1)+1;
            long n = 0;
            long count = 0;

            while (set.next()){
                n++;
                count++;
                if (n>=startPosition && count<=sizeOfPortion){
                    //extract in write in list
                }
            }
        }
        catch(SQLException ex){
            throw new DaoException(ex.getMessage());
        }
        return activities;
    }

    private PreparedStatement getPreparedStatement(StatusActivity status, Optional<LocalDate> date, Optional<User> user, String statement) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(statement);

        int paramIndex = 1;

        if (user.isPresent()) {
            preparedStatement.setLong(paramIndex,user.get().getId());
            paramIndex++;
        }

        preparedStatement.setString(paramIndex,status.getStatusName());

        if (date.isPresent()){
            paramIndex++;
            preparedStatement.setString(paramIndex, date.get().toString());
        }
        return preparedStatement;
    }

    private String getStatement(Optional<LocalDate> date, Optional<User> user) {
        String statement = "";

        if (user.isPresent()) statement+=GET_ACTIVITIES_WITH_USER;
                else statement+=GET_ACTIVITIES_WITHOUT_USER;
        statement+=WITH_STATUS;

        if (date.isPresent()) statement+=WITH_DAYWORK;
        return statement;
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

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        ActivityDao activityDao = daoFactory.createActivityDao();
        ((JDBCActivityDao)activityDao).findAll(StatusActivity.APPROVED,
                Optional.of(LocalDate.of(2018,1,1)),
                Optional.of(new User.Builder()
                .setId(5)
                .getInstance()));
    }
}
