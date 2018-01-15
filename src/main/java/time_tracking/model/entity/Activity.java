package time_tracking.model.entity;

import time_tracking.model.Entity_With_Id;

import java.time.LocalDate;

public class Activity extends Entity_With_Id {
    private User user;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    private boolean isVerify;
    private boolean isDeleted;

    public Activity(long id, User user, String name, LocalDate startDate, LocalDate endDate, boolean isVerify, boolean isDeleted) {
        super(id);
        this.user = user;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isVerify = isVerify;
        this.isDeleted = isDeleted;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public boolean isVerify() {
        return isVerify;
    }

    public void setVerify(boolean verify) {
        isVerify = verify;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Activity)) return false;
        if (!super.equals(o)) return false;

        Activity activity = (Activity)o;

        if (user != null ? !user.equals(activity.user) : activity.user != null) return false;
        if (name != null ? !name.equals(activity.name) : activity.name != null) return false;
        if (startDate != null ? !startDate.equals(activity.startDate) : activity.startDate != null) return false;
        if (endDate != null ? !endDate.equals(activity.endDate) : activity.endDate != null) return false;
        if (isVerify != activity.isVerify) return false;
        if (isDeleted != activity.isDeleted) return false;
        return this.getId()==activity.getId();
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (user.hashCode());
        result = 31 * result + (name.hashCode());
        result = 31 * result + (startDate.hashCode());
        result = 31 * result + (endDate.hashCode());
        result = 31 * result + (Boolean.hashCode(isVerify));
        result = 31 * result + (Boolean.hashCode(isDeleted));
        return result;
    }

    //TODO toString()
}
