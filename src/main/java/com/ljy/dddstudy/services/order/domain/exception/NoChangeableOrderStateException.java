package com.ljy.dddstudy.services.order.domain.exception;

public class NoChangeableOrderStateException extends RuntimeException {
    public NoChangeableOrderStateException() {
        super("no changeable order state");
    }
}
