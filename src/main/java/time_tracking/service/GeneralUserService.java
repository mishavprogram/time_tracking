package time_tracking.service;

import time_tracking.model.entity.User;

import java.util.List;

public interface GeneralUserService {
    User login(String email, String password);
    void create(User user);
    List<User> findAll();
}
