package time_tracking.model.entity;

import time_tracking.model.Entity_With_Id;

public class Order extends Entity_With_Id {
    private StatusOrder status;
    private Action action;
    private Activity activity;

    public Order(long id, StatusOrder status, Action action, Activity activity) {
        super(id);
        this.status = status;
        this.action = action;
        this.activity = activity;
    }

    public StatusOrder getStatus() {
        return status;
    }

    public void setStatus(StatusOrder status) {
        this.status = status;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
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

        Order order = (Order) o;

        if (status != order.status) return false;
        if (action != order.action) return false;
        return activity.equals(order.activity);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + status.hashCode();
        result = 31 * result + action.hashCode();
        result = 31 * result + activity.hashCode();
        return result;
    }
}
