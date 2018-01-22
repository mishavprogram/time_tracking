package time_tracking.model.entity;

public enum StatusOrder {
    APPROVED("approved"),
    REJECTED("rejected"),
    PENDING("pending");

    private String orderName;

    StatusOrder(String orderName) {
        this.orderName = orderName;
    }


    public String getStatusName() {
        return orderName;
    }


    public static StatusOrder getStatusOrder(String statusName) {
        for (StatusOrder statusOrder : values()) {
            if (statusOrder.getStatusName().equals(statusName))
                return statusOrder;
        }
        return null;
    }
}
