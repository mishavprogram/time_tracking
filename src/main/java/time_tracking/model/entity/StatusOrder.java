package time_tracking.model.entity;

import com.sun.istack.internal.Nullable;

public enum StatusOrder {
    DELETED("deleted"),
    REJECTED("rejected"),
    PENDING("pending");

    private String orderName;

    StatusOrder(String orderName) {
        this.orderName = orderName;
    }

    @Nullable
    public String getStatusName(){
        return orderName;
    }

    @Nullable
    public static StatusOrder getStatusOrder(String statusName){
        for(StatusOrder statusOrder:values()) {
            if (statusOrder.getStatusName().equals(statusName))
                return statusOrder;
        }
        return null;
    }
}
