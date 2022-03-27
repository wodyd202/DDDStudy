package com.ljy.dddstudy.services.order.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class PlaceOrderDto {
    private final Set<OrderLineDto> orderLines;
    private final DeliveryDto delivery;
    private final String message;

    public static PlaceOrderDto of(Set<OrderLineDto> orderLines, DeliveryDto delivery, String message) {
        return new PlaceOrderDto(orderLines, delivery, message);
    }
}
