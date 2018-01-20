package time_tracking.service;

import time_tracking.model.entity.Activity;
import time_tracking.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {
    void setDayTimeForActivity(Activity activity, LocalDate localDate, int hours);
    
    List<Activity> getActiveActivities();
    List<Activity> getActiveActivities(User user);
    List<Activity> getActiveActivities(User user, LocalDate localDate);

    List<Activity> getPendingActivities();
    List<Activity> getPendingActivities(User user);
    List<Activity> getPendingActivities(User user, LocalDate localDate);
}
