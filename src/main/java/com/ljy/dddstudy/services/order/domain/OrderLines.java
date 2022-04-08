package com.ljy.dddstudy.services.order.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.OneToMany;
import java.util.Set;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLines {
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private Set<OrderLine> orderLines;

    private OrderLines(Set<OrderLine> orderLines) {
         if(orderLines.isEmpty()){
             throw new IllegalArgumentException("order line must not be empty");
         }
         this.orderLines = orderLines;
    }

    public static OrderLines setOf(Set<OrderLine> orderLines) {
        return new OrderLines(orderLines);
    }

    void setOrder(Order order) {
        for (OrderLine orderLine : this.orderLines) {
            orderLine.setOrder(order);
        }
    }
}
