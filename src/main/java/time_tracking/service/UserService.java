package time_tracking.service;

import time_tracking.model.entity.Activity;
import time_tracking.model.entity.User;

import java.time.LocalDate;
import java.util.Optional;

public interface UserService {
    void addActivity(String name, LocalDate startDate, LocalDate endDate, long userId);
    void deleteActivity(long id);
    Optional<User> getUserByEmail(String email);
}
