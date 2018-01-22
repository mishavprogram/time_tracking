package time_tracking.service.entity;

public class OrderInfoForAdmin {
    private long id;
    private String userEmail;
    private String userName;
    private String userSurname;
    private String activityName;
    private String action;
    private long activityId;

    public OrderInfoForAdmin(){

    }

    public OrderInfoForAdmin(long id, String userEmail, String userName, String userSurname, String activityName, String action, long activityId) {
        this.id = id;
        this.userEmail = userEmail;
        this.userName = userName;
        this.userSurname = userSurname;
        this.activityName = activityName;
        this.action = action;
        this.activityId = activityId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
    }

    public String getActivityName() {
        return activityName;
    }

    public void setActivityName(String activityName) {
        this.activityName = activityName;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public long getActivityId() {
        return activityId;
    }

    public void setActivityId(long activityId) {
        this.activityId = activityId;
    }
}
