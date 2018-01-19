package time_tracking.dao.impl;

import time_tracking.dao.ActivityLogDao;
import time_tracking.model.entity.ActivityLog;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class JDBCActivityLogDao implements ActivityLogDao {
    private Connection connection;

    public JDBCActivityLogDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(ActivityLog entity) {

    }

    @Override
    public Optional<ActivityLog> findById(long id) {
        return null;
    }

    @Override
    public List<ActivityLog> findAll() {
        return null;
    }

    @Override
    public void update(ActivityLog entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}
