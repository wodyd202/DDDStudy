package com.ljy.dddstudy.services.order.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    @Column(name = "ZIPCODE")
    private String zipcode;

    @Column(name = "DETAIL")
    private String detail;

    public static Delivery of(String zipcode, String detail) {
        return new Delivery(zipcode, detail);
    }
}
