package time_tracking.dao;

import time_tracking.model.entity.User;

import java.util.Optional;

public interface UserDao extends GenericDao<User> {
    Optional<User> getUserByEmail(String email);
}
