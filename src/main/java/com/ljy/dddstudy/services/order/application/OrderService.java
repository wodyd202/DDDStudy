package com.ljy.dddstudy.services.order.application;

import com.ljy.dddstudy.services.order.application.dto.PlaceOrderDto;
import com.ljy.dddstudy.services.order.domain.Order;
import com.ljy.dddstudy.services.order.domain.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Long place(PlaceOrderDto dto, String orderer) {
        Order order = orderMapper.mapOf(dto, orderer);
        orderRepository.save(order);
        return order.getId();
    }
}
