package time_tracking.model.entity;

public enum Action {
    ADD("add"),
    DELETE("delete");

    private String actionName;

    Action(String roleName) {
        this.actionName = roleName;
    }

    public String getActionName() {
        return actionName;
    }

    public static Action getAction(String roleName) {
        for (Action roleType : values()) {
            if (roleType.getActionName().equals(roleName))
                return roleType;
        }
        return null;
    }
}
