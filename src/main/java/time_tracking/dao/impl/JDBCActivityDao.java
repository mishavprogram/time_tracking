package time_tracking.dao.impl;

import time_tracking.dao.ActivityDao;
import time_tracking.dao.DaoFactory;
import time_tracking.dao.exception.DaoException;
import time_tracking.dao.mapper.impl.ActivityMapper;
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

    private enum Action{GET_ACTIVITIES, GET_COUNT};
    public static final String GET_ACTIVITIES = "SELECT * ";
    public static final String GET_COUNT_OF_ACTIVITIES = "SELECT count(*) ";
    public static final String FROM_ACT_TABLE = "FROM time_tracking_db.activityt ";
    //public static final String FROM_ACT_TABLE_WITHOUT_USER = "SELECT * FROM time_tracking_db.activityt ";
    public static final String WITH_USER = " join usert on activityt.usert_id = usert.id and usert_id = ?";
    public static final String WITH_STATUS = " and status = ? ";
    public static final String WITH_WORK_IN_CURR_DAY = "and activityt.startDate<=date(?) and activityt.endDate>=date(?)";

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
    public long getCountOfActivities(StatusActivity status, Optional<LocalDate> date, Optional<User> user) {
        long result = 0;

        try {
            String statement = getStatement(Action.GET_COUNT, date, user);
            PreparedStatement preparedStatement = getPreparedStatement(status, date, user, statement);
            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();
            if (set.isBeforeFirst()){
                set.next();
                result = set.getLong(1);
            }
        }
        catch(SQLException ex){
            throw new DaoException(ex.getMessage());
        }

        return result;
    }

    /**
     * @param numberOfPortion - for pagination
     * @param sizeOfPortion - for pagination
     * @param status - aprroved / pending
     * @param date
     * @param user - need only ID, that's why can get empty User but with ID
     * @return portion of activities from some index
     * that will calculate how (numberOfPortion*(sizeOfPortion-1) + 1)
     */
    public List<Activity> findAll(long numberOfPortion, long sizeOfPortion, StatusActivity status, Optional<LocalDate> date, Optional<User> user){
        List<Activity> activities = new ArrayList<>();
        try {
            String statement = getStatement(Action.GET_ACTIVITIES, date, user);
            PreparedStatement preparedStatement = getPreparedStatement(status, date, user, statement);
            preparedStatement.execute();

            ResultSet set = preparedStatement.getResultSet();
            extractActivitiesFromSetToList(numberOfPortion, sizeOfPortion, activities, set);
        }
        catch(SQLException ex){
            throw new DaoException(ex.getMessage());
        }
        return activities;
    }

    private void extractActivitiesFromSetToList(long numberOfPortion, long sizeOfPortion, List<Activity> activities, ResultSet set) throws SQLException {
        long startPosition = sizeOfPortion*numberOfPortion - sizeOfPortion+1;
        long n = 0;
        long count = 0;
        ActivityMapper activityMapper = new ActivityMapper();

        while (set.next()){
            n++;
            if (n>=startPosition && count<sizeOfPortion){
                count++;
                Activity activity = activityMapper.extractFromResultSet(set);
                activities.add(activity);
            }
        }
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
            paramIndex++;
            preparedStatement.setString(paramIndex, date.get().toString());
        }
        return preparedStatement;
    }

    private String getStatement(Action action, Optional<LocalDate> date, Optional<User> user) {
        String statement = "";
        if (action == Action.GET_ACTIVITIES)
            statement+=GET_ACTIVITIES;
        else statement+=GET_COUNT_OF_ACTIVITIES;

        statement+=FROM_ACT_TABLE;

        if (user.isPresent()) statement+=WITH_USER;
        statement+=WITH_STATUS;

        if (date.isPresent()) statement+=WITH_WORK_IN_CURR_DAY;
        return statement;
    }

    @Override
    public void update(Activity entity) {

    }

    @Override
    public void delete(int id) {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DaoFactory daoFactory = DaoFactory.getInstance();
        ActivityDao activityDao = daoFactory.createActivityDao();
        List<Activity> activities = ((JDBCActivityDao)activityDao).findAll(2, 3, StatusActivity.PENDING,
                Optional.of(LocalDate.of(2018,1,4)),
                Optional.of(new User.Builder()
                .setId(5)
                .getInstance()));
        for (Activity a:
             activities) {
            System.out.println(a);
        }
    }
}
