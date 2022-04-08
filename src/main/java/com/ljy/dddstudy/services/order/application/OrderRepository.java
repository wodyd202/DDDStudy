package com.ljy.dddstudy.services.order.application;

import com.ljy.dddstudy.services.order.domain.Order;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findById(Long orderId);

    void save(Order order);
}
