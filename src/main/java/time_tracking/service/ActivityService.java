package time_tracking.service;

import time_tracking.model.entity.Activity;
import time_tracking.model.entity.User;

import java.time.LocalDate;
import java.util.List;

public interface ActivityService {
    void setDayTimeForActivity(Activity activity, LocalDate localDate, int hours);
    
    List<Activity> getActiveActivities(long numberOfPortion, long sizeOfPortion);
    List<Activity> getActiveActivities(long numberOfPortion, long sizeOfPortion, long userId);
    List<Activity> getActiveActivities(long numberOfPortion, long sizeOfPortion, long userId, LocalDate localDate);

    List<Activity> getPendingActivities(long numberOfPortion, long sizeOfPortion);
    List<Activity> getPendingActivities(long numberOfPortion, long sizeOfPortion, long userId);
    List<Activity> getPendingActivities(long numberOfPortion, long sizeOfPortion, long userId, LocalDate localDate);
}
