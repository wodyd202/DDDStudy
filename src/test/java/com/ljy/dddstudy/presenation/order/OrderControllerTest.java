package com.ljy.dddstudy.presenation.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.dddstudy.presentation.order.request.place.Delivery;
import com.ljy.dddstudy.presentation.order.request.place.OrderLine;
import com.ljy.dddstudy.presentation.order.OrderController;
import com.ljy.dddstudy.presentation.order.request.place.PlaceOrderRequest;
import com.ljy.dddstudy.services.order.application.OrderService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toSet;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(OrderController.class)
class OrderControllerTest {

    @MockBean
    OrderService orderService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void 주문() throws Exception {
        // given
        when(orderService.place(any(), eq("orderer")))
        .thenReturn(1L);

        PlaceOrderRequest request = PlaceOrderRequest.builder()
                .orderLines(
                        asList(
                            OrderLine.builder()
                                    .productId(1L)
                                    .quantity(2)
                                    .build()
                        ).stream().collect(toSet())
                )
                .delivery(Delivery.builder()
                        .zipcode("zipcode")
                        .detail("상세 주소")
                        .build())
                .message("주문 매시지")
                .build();

        // when
        mockMvc.perform(post("/api/v1/orders")
                .header("userId", "orderer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

        // then
        .andExpect(status().isCreated())
        .andDo(print());
    }
}
