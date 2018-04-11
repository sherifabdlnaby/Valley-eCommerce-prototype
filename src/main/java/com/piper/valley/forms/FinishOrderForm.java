package com.piper.valley.forms;

import javax.validation.constraints.NotNull;

public class FinishOrderForm {

    @NotNull
    private Long orderId;

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
