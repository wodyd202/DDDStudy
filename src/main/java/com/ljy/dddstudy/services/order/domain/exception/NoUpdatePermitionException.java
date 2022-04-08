package com.ljy.dddstudy.services.order.domain.exception;

public class NoUpdatePermitionException extends RuntimeException {
    public NoUpdatePermitionException() {
        super("not has permition");
    }
}
