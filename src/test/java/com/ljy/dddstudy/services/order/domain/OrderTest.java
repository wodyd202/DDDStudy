package com.ljy.dddstudy.services.order.domain;

import com.ljy.dddstudy.presenation.order.request.OrderRequestFixture;
import com.ljy.dddstudy.presentation.order.request.place.PlaceOrderRequest;
import com.ljy.dddstudy.services.order.application.OrderMapper;
import com.ljy.dddstudy.services.order.application.dto.PlaceOrderDto;
import com.ljy.dddstudy.services.order.domain.exception.NoUpdatePermitionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.ljy.dddstudy.presenation.order.request.OrderRequestFixture.aPlaceOrderRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class OrderTest {

    OrderMapper orderMapper = new OrderMapper();
    Order order;

    @BeforeEach
    void setUp() {
        PlaceOrderRequest requset = aPlaceOrderRequest().build();
        PlaceOrderDto dto = requset.toDto();
        order = orderMapper.mapOf(dto, "orderer");
    }

    @Test
    void 배송지_변경() {
        // when
        order.changeDelivery(Delivery.of("update zipcode", "update detail"), "orderer");

        // then
        assertEquals(Delivery.of("update zipcode", "update detail"), order.getDelivery());
    }

    @Test
    void 배송지_변경_권한이_존재하지_않을경우_에러() {
        // when
        assertThrows(NoUpdatePermitionException.class, () -> order.changeDelivery(Delivery.of("update zipcode", "update detail"), "no has permition"));
    }
}
