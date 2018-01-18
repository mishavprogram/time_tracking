package time_tracking.model.entity;

import time_tracking.model.Entity_With_Id;

import java.time.LocalDate;

public class ActivityLog extends Entity_With_Id {
    private LocalDate dateWork;
    private int hours;
    private Activity activity;

    public ActivityLog(long id, LocalDate dateWork, int hours, Activity activity) {
        super(id);
        this.dateWork = dateWork;
        this.hours = hours;
        this.activity = activity;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        this.hours = hours;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        ActivityLog that = (ActivityLog) o;

        if (hours != that.hours) return false;
        if (!dateWork.equals(that.dateWork)) return false;
        return activity.equals(that.activity);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + dateWork.hashCode();
        result = 31 * result + hours;
        result = 31 * result + activity.hashCode();
        return result;
    }
}
