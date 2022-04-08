package com.ljy.dddstudy.presenation.order;

import com.ljy.dddstudy.presentation.order.request.place.ChangeDeliveryRequest;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;

import static com.ljy.dddstudy.presenation.order.request.OrderRequestFixture.aDelivery;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ChangeDeliveryTest extends OrderControllerTest{

    @Test
    void 배송지_변경() throws Exception {
        // given
        ChangeDeliveryRequest request = aDelivery().build();

        // when
        mockMvc.perform(patch("/api/v1/orders/{orderId}/delivery", 1)
                .header("userId", "orderer")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))

        // then
        .andExpect(status().isOk());
    }

    @Test
    void 배송지_변경이_불가할_경우_400() throws Exception {
        // given
        when(orderService.changeDelivery(eq(2L), any(), eq("배송지 변경 실패")))
        .thenThrow(IllegalStateException.class);

        ChangeDeliveryRequest request = aDelivery().build();

        // when
        mockMvc.perform(patch("/api/v1/orders/{orderId}/delivery", 2)
                        .header("userId", "배송지 변경 실패")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))

        // then
        .andExpect(status().isBadRequest());
    }
}
