package time_tracking.service;

import time_tracking.model.entity.User;

import java.util.List;
import java.util.Optional;

public interface GeneralUserService {
    Optional<User> login(String email, String password);
    void create(User user);
    List<User> findAll();
}
