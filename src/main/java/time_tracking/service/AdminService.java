package time_tracking.service;

import time_tracking.model.entity.Activity;

public interface AdminService {
    void confirmAddActivity(Activity activity);
    void confirmDeleteActivity(Activity activity);
}
