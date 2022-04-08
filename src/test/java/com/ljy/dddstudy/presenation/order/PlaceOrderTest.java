package com.ljy.dddstudy.presenation.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.dddstudy.presentation.order.OrderController;
import com.ljy.dddstudy.presentation.order.request.place.PlaceOrderRequest;
import com.ljy.dddstudy.services.order.application.OrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import java.util.HashSet;

import static com.ljy.dddstudy.presenation.order.request.OrderRequestFixture.*;
import static java.util.Arrays.asList;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class PlaceOrderTest extends OrderControllerTest {

    @Test
    void 주문_정보를_입력하지_않았을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                .orderLines(null),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 주문_정보가_존재하지_않을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .orderLines(new HashSet<>()),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 주문_정보중_상품_고유번호를_입력하지_않았을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .orderLines(
                                new HashSet<>(asList(aOrderLine()
                                        .productId(null)
                                        .build()))
                        ),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 주문_정보중_수량을_입력하지_않았을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .orderLines(
                                new HashSet<>(asList(aOrderLine()
                                        .quantity(null)
                                        .build()))
                        ),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 주문_정보중_수량을_0이하로_입력했을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .orderLines(
                                new HashSet<>(asList(aOrderLine()
                                        .quantity(0)
                                        .build()))
                        ),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 배송지_정보를_입력하지_않았을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .delivery(null),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 배송지_정보중_우편번호를_입력하지_않았을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .delivery(aDelivery()
                                .zipcode("")
                                .build()),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 배송지_정보중_상세정보를_입력하지_않았을_경우_400() throws Exception {
        // when
        assertPlaceOrder(aPlaceOrderRequest()
                        .delivery(aDelivery()
                                .detail("")
                                .build()),
                "orderer")

        // then
        .andExpect(status().isBadRequest());
    }

    @Test
    void 주문() throws Exception {
        // given
        when(orderService.place(any(), eq("orderer")))
        .thenReturn(1L);

        // when
        assertPlaceOrder(aPlaceOrderRequest(), "orderer")

        // then
        .andExpect(status().isCreated());
    }

    private ResultActions assertPlaceOrder(PlaceOrderRequest.PlaceOrderRequestBuilder builder,
                                           String orderer) throws Exception {
        return mockMvc.perform(post("/api/v1/orders")
                .header("userId", orderer)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(builder.build())));
    }
}
