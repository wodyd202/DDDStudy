package com.ljy.dddstudy.services.order.domain;

public enum OrderState {
    ORDER_COMPLETED(true),
    SHIPPING(false),
    DELIVERY_COMPLETED(false),
    ORDER_CENCEL(false);

    private boolean changeable;

    OrderState(boolean changeable){
        this.changeable = changeable;
    }

    public boolean isChangeable() {
        return changeable;
    }
}
