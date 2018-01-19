package time_tracking.dao.mapper.impl;

import com.sun.istack.internal.Nullable;
import time_tracking.dao.mapper.ObjectMapper;
import time_tracking.model.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class OrderMapper implements ObjectMapper<Order> {
    @Nullable
    @Override
    public Order extractFromResultSet(ResultSet rs) throws SQLException {
        long id = rs.getLong(1);
        Action action = Action.getAction(rs.getString(2));
        StatusOrder status = StatusOrder.getStatusOrder(rs.getString(3));

        Activity activity = new Activity.Builder()
                .setId(rs.getLong(5))
                .setName(rs.getString(6))
                .setStartDate(rs.getDate(7).toLocalDate())
                .setEndDate(rs.getDate(8).toLocalDate())
                .setStatus(StatusActivity.getStatusActivity(rs.getString(9)))
                //.setUser()
                .build();
        User user = new User.Builder()
                .setId(rs.getLong(10))
                .setEmail(rs.getString(12))
                .setName(rs.getString(13))
                .setSurname(rs.getString(14))
                .setPasswordHash(rs.getString(15))
                .setRole(RoleType.getRole(rs.getString(16)))
                .getInstance();

        activity.setUser(user);

        return new Order(id, status, action, activity);
    }
}
