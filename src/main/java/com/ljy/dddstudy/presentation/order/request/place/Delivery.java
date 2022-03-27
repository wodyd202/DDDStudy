package com.ljy.dddstudy.presentation.order.request.place;

import com.ljy.dddstudy.services.order.application.dto.DeliveryDto;
import lombok.*;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Delivery {
    private String zipcode;
    private String detail;

    public DeliveryDto toDto() {
        return DeliveryDto.of(zipcode, detail);
    }
}
