package com.ljy.dddstudy.services.order.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class OrderLineDto {
    private final long productId;
    private final int quantity;

    public static OrderLineDto of(long productId, int quantity) {
        return new OrderLineDto(productId, quantity);
    }
}