package com.ljy.dddstudy.presentation.order.request.place;

import com.ljy.dddstudy.services.order.application.dto.PlaceOrderDto;
import lombok.*;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PlaceOrderRequest {
    @NotNull
    @Size(min = 1)
    @Valid
    private Set<OrderLine> orderLines;

    @NotNull
    @Valid
    private Delivery delivery;

    private String message;

    public PlaceOrderDto toDto() {
        return PlaceOrderDto.of(orderLines.stream().map(OrderLine::toDto).collect(toSet()), delivery.toDto(), message);
    }
}
