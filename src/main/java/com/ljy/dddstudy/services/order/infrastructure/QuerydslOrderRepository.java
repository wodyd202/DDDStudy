package com.ljy.dddstudy.services.order.infrastructure;

import com.ljy.dddstudy.core.querydsl.QuerydslRepository;
import com.ljy.dddstudy.core.querydsl.SimpleQuerydslRepositorySupport;
import com.ljy.dddstudy.services.order.application.OrderRepository;
import com.ljy.dddstudy.services.order.domain.Order;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.ljy.dddstudy.services.order.domain.QOrder.order;

@QuerydslRepository
@Transactional
public class QuerydslOrderRepository extends SimpleQuerydslRepositorySupport<Order> implements OrderRepository {
    protected QuerydslOrderRepository() {super(Order.class);}

    @Override
    public Optional<Order> findById(Long orderId) {
        return Optional.ofNullable(
                from(order)
                .select(order)
                .where(
                    eqId(orderId)
                )
                .fetchFirst()
        );
    }

    private BooleanExpression eqId(Long orderId) {
        return order.id.eq(orderId);
    }
}

