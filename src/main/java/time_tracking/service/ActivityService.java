package time_tracking.service;

import time_tracking.model.entity.Activity;
import time_tracking.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {
    void setDayTimeForActivity(Activity activity, LocalDate localDate, int hours);
    List<Activity> getAllForCurrentUser(User user);
    List<Activity> getCurrentDayActivitiesForUser(User user, LocalDate localDate);
}
