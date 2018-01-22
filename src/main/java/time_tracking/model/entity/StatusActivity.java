package time_tracking.model.entity;

public enum StatusActivity {
    DELETED("deleted"),
    APPROVED("approved"),
    PENDING("pending");

    private String statusName;

    StatusActivity(String statusName) {
        this.statusName = statusName;
    }

    public String getStatusName() {
        return statusName;
    }

    public static StatusActivity getStatusActivity(String statusName) {
        for (StatusActivity statusActivity : values()) {
            if (statusActivity.getStatusName().equals(statusName))
                return statusActivity;
        }
        return null;
    }
}
