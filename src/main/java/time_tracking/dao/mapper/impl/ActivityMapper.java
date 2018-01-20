package time_tracking.dao.mapper.impl;

import time_tracking.dao.mapper.ObjectMapper;
import time_tracking.model.entity.Activity;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements ObjectMapper<Activity> {
    @Override
    public Activity extractFromResultSet(ResultSet rs) throws SQLException {
        Activity activity = new Activity.Builder().build();
        return activity;
    }
}
