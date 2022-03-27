package com.ljy.dddstudy.presentation.order.request.place;

import com.ljy.dddstudy.services.order.application.dto.OrderLineDto;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {
    @NotNull
    private Long productId;

    @NotNull
    @Min(1)
    private Integer quantity;

    public OrderLineDto toDto() {
        return OrderLineDto.of(productId, quantity);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderLine orderLine = (OrderLine) o;
        return Objects.equals(productId, orderLine.productId) && Objects.equals(quantity, orderLine.quantity);
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId, quantity);
    }
}
