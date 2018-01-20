package time_tracking.service.impl;

import time_tracking.model.entity.Activity;
import time_tracking.model.entity.RoleType;
import time_tracking.model.entity.User;
import time_tracking.service.ActivityService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

public class DefaultActivityService implements ActivityService{
    @Override
    public void setDayTimeForActivity(Activity activity, LocalDate localDate, int hours) {

    }

    @Override
    public List<Activity> getActiveActivities() {
        return null;
    }

    @Override
    public List<Activity> getActiveActivities(User user) {
        return null;
    }

    @Override
    public List<Activity> getActiveActivities(User user, LocalDate localDate) {
        return null;
    }

    @Override
    public List<Activity> getPendingActivities() {
        return null;
    }

    @Override
    public List<Activity> getPendingActivities(User user) {
        return null;
    }

    @Override
    public List<Activity> getPendingActivities(User user, LocalDate localDate) {
        return null;
    }
}
