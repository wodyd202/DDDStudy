package com.ljy.dddstudy.services.order.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
@EqualsAndHashCode
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
