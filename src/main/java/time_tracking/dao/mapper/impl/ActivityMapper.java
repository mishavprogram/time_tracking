package time_tracking.dao.mapper.impl;

import time_tracking.dao.mapper.ObjectMapper;
import time_tracking.model.entity.Activity;
import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.StatusActivity;
import time_tracking.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ActivityMapper implements ObjectMapper<Activity> {
    @Override
    public Activity extractFromResultSet(ResultSet rs) throws SQLException {
        Activity activity = new Activity.Builder()
                .setId(rs.getLong(1))
                .setName(rs.getString(2))
                .setStartDate(rs.getDate(3).toLocalDate())
                .setEndDate(rs.getDate(4).toLocalDate())
                .setStatus(StatusActivity.getStatusActivity(rs.getString(5)))
                .build();
        User user = new User.Builder()
                .setId(rs.getLong(6))
                .setEmail(rs.getString(8))
                .setName(rs.getString(9))
                .setSurname(rs.getString(10))
                .setPasswordHash(rs.getString(11))
                .setRole(RoleType.getRole(rs.getString(12)))
                .getInstance();
        activity.setUser(user);
        return activity;
    }
}
