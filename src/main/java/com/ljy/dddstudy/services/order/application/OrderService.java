package com.ljy.dddstudy.services.order.application;

import com.ljy.dddstudy.services.order.application.dto.DeliveryDto;
import com.ljy.dddstudy.services.order.application.dto.PlaceOrderDto;
import com.ljy.dddstudy.services.order.application.exception.OrderNotFoundException;
import com.ljy.dddstudy.services.order.domain.Delivery;
import com.ljy.dddstudy.services.order.domain.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public Long place(PlaceOrderDto dto, String orderer) {
        Order order = orderMapper.mapOf(dto, orderer);
        orderRepository.save(order);
        return order.getId();
    }

    public Long changeDelivery(Long orderId, DeliveryDto dto, String updater) {
        Order order = orderRepository.findById(orderId).orElseThrow(OrderNotFoundException::new);
        Delivery delivery = orderMapper.toDelivery(dto);
        order.changeDelivery(delivery, updater);
        return orderId;
    }
}

