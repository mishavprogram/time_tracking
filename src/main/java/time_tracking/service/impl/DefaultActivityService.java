package time_tracking.service.impl;

import time_tracking.dao.ActivityDao;
import time_tracking.dao.DaoFactory;
import time_tracking.model.entity.Activity;
import time_tracking.model.entity.StatusActivity;
import time_tracking.model.entity.User;
import time_tracking.service.ActivityService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * use danger thing - creating "empty" User with only ID (because dao method until use only id from User)
 */

public class DefaultActivityService implements ActivityService {
    private DaoFactory daoFactory = DaoFactory.getInstance();

    @Override
    public Optional<Activity> getActivity(long activityId) {
        Optional<Activity> activity = Optional.empty();

        ActivityDao activityDao = daoFactory.createActivityDao();
        activity = activityDao.findById(activityId);

        activityDao.close();
        return activity;
    }

    @Override
    public void setDayTimeForActivity(Activity activity, LocalDate localDate, int hours) {

    }

    @Override
    public long getCountOfActiveActivities(long userId, LocalDate localDate) {
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        long result = activityDao.getCountOfActivities(StatusActivity.APPROVED, Optional.of(localDate), Optional.of(user));
        activityDao.close();
        return result;
    }

    @Override
    public long getCountOfPendingActivities(long userId) {
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        return activityDao.getCountOfActivities(StatusActivity.PENDING, Optional.empty(), Optional.of(user));
    }

    @Override
    public long getCountOfPendingActivities(long userId, LocalDate localDate) {
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        long result = activityDao.getCountOfActivities(StatusActivity.PENDING, Optional.of(localDate), Optional.of(user));
        activityDao.close();
        return result;
    }

    @Override
    public List<Activity> getActiveActivities(long numberOfPortion, long sizeOfPortion) {
        List<Activity> activities;
        ActivityDao activityDao = daoFactory.createActivityDao();
        activities = activityDao.findAll(numberOfPortion, sizeOfPortion, StatusActivity.APPROVED, Optional.empty(), Optional.empty());
        return activities;
    }

    @Override
    public List<Activity> getActiveActivities(long numberOfPortion, long sizeOfPortion, long userId) {
        List<Activity> activities;
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        activities = activityDao.findAll(numberOfPortion, sizeOfPortion, StatusActivity.APPROVED, Optional.empty(), Optional.of(user));
        return activities;
    }

    @Override
    public List<Activity> getActiveActivities(long numberOfPortion, long sizeOfPortion, long userId, LocalDate localDate) {
        List<Activity> activities;
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        activities = activityDao.findAll(numberOfPortion, sizeOfPortion, StatusActivity.APPROVED, Optional.of(localDate), Optional.of(user));
        activityDao.close();
        return activities;
    }

    @Override
    public List<Activity> getPendingActivities(long numberOfPortion, long sizeOfPortion) {
        List<Activity> activities;
        ActivityDao activityDao = daoFactory.createActivityDao();
        activities = activityDao.findAll(numberOfPortion, sizeOfPortion, StatusActivity.PENDING, Optional.empty(), Optional.empty());
        return activities;
    }

    @Override
    public List<Activity> getPendingActivities(long numberOfPortion, long sizeOfPortion, long userId) {
        List<Activity> activities;
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        activities = activityDao.findAll(numberOfPortion, sizeOfPortion, StatusActivity.PENDING, Optional.empty(), Optional.of(user));
        return activities;
    }


    @Override
    public List<Activity> getPendingActivities(long numberOfPortion, long sizeOfPortion, long userId, LocalDate localDate) {
        List<Activity> activities;
        ActivityDao activityDao = daoFactory.createActivityDao();
        User user = new User.Builder().setId(userId).getInstance();
        activities = activityDao.findAll(numberOfPortion, sizeOfPortion, StatusActivity.PENDING, Optional.of(localDate), Optional.of(user));
        activityDao.close();
        return activities;
    }
}
