package com.ljy.dddstudy.services.order.infrastructure;

import com.ljy.dddstudy.services.order.domain.Order;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class QuerydslOrderRepository {
    @PersistenceContext
    private EntityManager entityManager;

    void save(Order order) {
        if(entityManager.contains(order)){
            entityManager.merge(order);
            return;
        }
        entityManager.persist(order);
    }
}
