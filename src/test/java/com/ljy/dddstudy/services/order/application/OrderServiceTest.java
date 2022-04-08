package com.ljy.dddstudy.services.order.application;

import com.ljy.dddstudy.DataQuerydslTest;
import com.ljy.dddstudy.presentation.order.request.place.PlaceOrderRequest;
import com.ljy.dddstudy.services.order.application.dto.PlaceOrderDto;
import com.ljy.dddstudy.services.order.infrastructure.QuerydslOrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;

import static com.ljy.dddstudy.presenation.order.request.OrderRequestFixture.aPlaceOrderRequest;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataQuerydslTest
@Import({OrderServiceTest.OrderServiceTestConfig.class})
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    void 주문() {
        // given
        PlaceOrderRequest request = aPlaceOrderRequest().build();
        PlaceOrderDto dto = request.toDto();

        // when
        Long orderId = orderService.place(dto, "orderer");

        // then
        assertNotNull(orderId);
    }

    public static class OrderServiceTestConfig {

        @Autowired
        private OrderRepository orderRepository;

        @Bean
        OrderService orderService() {
            return new OrderService(orderRepository, new OrderMapper());
        }
    }
}
