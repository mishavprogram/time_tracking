package time_tracking.dao.mapper.impl;

import time_tracking.dao.mapper.ObjectMapper;
import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        User user = new User.Builder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setSurname(rs.getString("surname"))
                .setEmail(rs.getString("email"))
                .setPasswordHash(rs.getString("passwordHash"))
                .setRole((RoleType.getRole(rs.getString("role"))))
                .getInstance();
        return user;
    }
}
