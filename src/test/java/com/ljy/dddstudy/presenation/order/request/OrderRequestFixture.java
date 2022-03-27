package com.ljy.dddstudy.presenation.order.request;

import com.ljy.dddstudy.presentation.order.request.place.Delivery;
import com.ljy.dddstudy.presentation.order.request.place.OrderLine;
import com.ljy.dddstudy.presentation.order.request.place.PlaceOrderRequest;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;

public class OrderRequestFixture {
    public static PlaceOrderRequest.PlaceOrderRequestBuilder aPlaceOrderRequest() {
        return PlaceOrderRequest.builder()
                .orderLines(asList(aOrderLine().build()).stream().collect(toSet()))
                .delivery(aDelivery().build())
                .message("주문 매시지");
    }

    public static OrderLine.OrderLineBuilder aOrderLine() {
        return OrderLine.builder()
                .productId(1L)
                .quantity(2);
    }

    public static Delivery.DeliveryBuilder aDelivery() {
        return Delivery.builder()
                .zipcode("zipcode")
                .detail("상세 주소");
    }
}
