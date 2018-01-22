package time_tracking.dao;

import time_tracking.model.entity.Activity;
import time_tracking.model.entity.StatusActivity;
import time_tracking.model.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ActivityDao extends GenericDao<Activity> {
    void setActivityStatus(StatusActivity status, long activityId);

    long getLastInsertId();

    List<Activity> findAll(long numberOfPortion, long sizeOfPortion, StatusActivity status, Optional<LocalDate> date, Optional<User> user);

    long getCountOfActivities(StatusActivity status, Optional<LocalDate> date, Optional<User> user);
}
