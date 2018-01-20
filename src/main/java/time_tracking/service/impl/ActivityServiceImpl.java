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

public class ActivityServiceImpl implements ActivityService{
    @Override
    public void setDayTimeForActivity(Activity activity, LocalDate localDate, int hours) {

    }

    @Override
    public List<Activity> getAllForCurrentUser(User user) {
        return null;
    }

    @Override
    public List<Activity> getCurrentDayActivitiesForUser(User user, LocalDate localDate) {
        /*if (user.getRole().equals(RoleType.USER))
            return listActivities.get()
                    .stream()
                    .filter(activity -> activity.getStartDate().compareTo(localDate)<=0
                    && activity.getEndDate().compareTo(localDate)>=0)
            .collect(Collectors.toList());
        else return new ArrayList<>();*/
        return null;
    }

    private ActivityServiceImpl(){

    }
}
