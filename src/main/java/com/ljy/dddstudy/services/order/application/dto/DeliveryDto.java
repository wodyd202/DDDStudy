package com.ljy.dddstudy.services.order.application.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class DeliveryDto {
    private final String zipcode;
    private final String detail;

    public static DeliveryDto of(String zipcode, String detail) {
        return new DeliveryDto(zipcode, detail);
    }
}
