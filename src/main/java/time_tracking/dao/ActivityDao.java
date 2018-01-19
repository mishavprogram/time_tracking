package time_tracking.dao;

import time_tracking.model.entity.Activity;

public interface ActivityDao extends GenericDao<Activity> {
    public long getLastInsertId();
}
