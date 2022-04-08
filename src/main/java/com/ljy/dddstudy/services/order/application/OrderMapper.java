package com.ljy.dddstudy.services.order.application;

import com.ljy.dddstudy.services.order.application.dto.DeliveryDto;
import com.ljy.dddstudy.services.order.application.dto.PlaceOrderDto;
import com.ljy.dddstudy.services.order.domain.Delivery;
import com.ljy.dddstudy.services.order.domain.Order;
import com.ljy.dddstudy.services.order.domain.OrderLine;
import com.ljy.dddstudy.services.order.domain.OrderLines;
import org.springframework.stereotype.Component;

import static java.util.stream.Collectors.toSet;

@Component
public class OrderMapper {

    public Order mapOf(PlaceOrderDto dto, String orderer) {
        return Order.of(OrderLines.setOf(dto.getOrderLines()
                .stream().map(orderLine -> OrderLine.of(orderLine.getProductId(), orderLine.getQuantity()))
                .collect(toSet())),
                toDelivery(dto.getDelivery()),
                orderer,
                dto.getMessage());
    }

    public Delivery toDelivery(DeliveryDto dto) {
        return Delivery.of(dto.getZipcode(), dto.getDetail());
    }
}
