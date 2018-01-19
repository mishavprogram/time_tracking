package time_tracking.model.entity;

import time_tracking.model.Entity_With_Id;

import java.time.LocalDate;

public class Activity extends Entity_With_Id {
    private User user;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;

    private StatusActivity status;

    public Activity(){

    }
    public Activity(long id, User user, String name, LocalDate startDate, LocalDate endDate, StatusActivity status) {
        super(id);
        this.user = user;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
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

    public StatusActivity getStatus() {
        return status;
    }

    public void setStatus(StatusActivity status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        Activity activity = (Activity) o;

        if (!user.equals(activity.user)) return false;
        if (!name.equals(activity.name)) return false;
        if (!startDate.equals(activity.startDate)) return false;
        if (!endDate.equals(activity.endDate)) return false;
        return status == activity.status;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + user.hashCode();
        result = 31 * result + name.hashCode();
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        result = 31 * result + status.hashCode();
        return result;
    }

    public static class Builder {
        Activity instance = new Activity();

        public Builder() {

        }

        public Builder setId(Long id) {
            instance.setId(id);
            return this;
        }

        public Builder setUser(User user) {
            instance.user = user;
            return this;
        }

        public Builder setName(String name){
            instance.name = name;
            return this;
        }

        public Builder setStartDate(LocalDate startDate){
            instance.startDate = startDate;
            return this;
        }

        public Builder setEndDate(LocalDate endDate){
            instance.endDate = endDate;
            return this;
        }

        public Builder setStatus(StatusActivity status){
            instance.status = status;
            return this;
        }


        public Activity build() {
            return instance;
        }
    }

    @Override
    public String toString() {
        return "Activity{" +
                "user=" + user +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", status=" + status +
                '}';
    }
}
