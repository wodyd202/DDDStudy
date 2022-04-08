package com.ljy.dddstudy.presenation.order;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljy.dddstudy.presentation.order.OrderController;
import com.ljy.dddstudy.services.order.application.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(OrderController.class)
abstract public class OrderControllerTest {
    @Autowired
    protected MockMvc mockMvc;

    @MockBean
    protected OrderService orderService;

    @Autowired
    protected ObjectMapper objectMapper;
}
