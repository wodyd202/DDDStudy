package com.ljy.dddstudy.presentation.order.request.place;

import com.ljy.dddstudy.services.order.application.dto.DeliveryDto;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangeDeliveryRequest {
    @NotBlank
    private String zipcode;

    @NotBlank
    private String detail;

    public DeliveryDto toDto() {
        return DeliveryDto.of(zipcode, detail);
    }
}
