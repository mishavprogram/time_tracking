package time_tracking.dao.impl;

import time_tracking.dao.UserDao;
import time_tracking.dao.exception.DaoException;
import time_tracking.model.entity.User;
import time_tracking.dao.mapper.impl.UserMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class JDBCUserDao implements UserDao {
    private static final String INSERT_USER = "INSERT INTO `time_tracking_db`.`usert` (`email`, `name`, `surname`, `passwordHash`, `role`) VALUES (?, ?, ?, ?, ?);";
    private static final String SELECT_USER_BY_EMAIL = "select * from usert where email = ?;";
    private static final String SELECT_USER_BY_ID = "select * from usert where id = ?;";
    private Connection connection;

    JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    //TODO check if exist
    @Override
    public void create(User user) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER);
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getPasswordHash());
            preparedStatement.setString(5, user.getRole().toString());

            preparedStatement.execute();
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
    }

    @Override
    public Optional<User> findById(long id) {
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setLong(1, id);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()) {
                set.next();
                UserMapper userMapper = new UserMapper();
                user = Optional.of(userMapper.extractFromResultSet(set));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return user;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {

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
    public Optional<User> getUserByEmail(String email) {
        Optional<User> user = Optional.empty();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_EMAIL);
            preparedStatement.setString(1, email);
            preparedStatement.execute();
            ResultSet set = preparedStatement.getResultSet();

            if (set.isBeforeFirst()) {
                set.next();
                UserMapper userMapper = new UserMapper();
                user = Optional.of(userMapper.extractFromResultSet(set));
            }
        } catch (SQLException ex) {
            throw new DaoException(ex.getMessage());
        }
        return user;
    }
}
