
package com.ljy.dddstudy.services.order.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ljy.dddstudy.services.order.domain.OrderState.COMPLETED;

@Entity
@Table(name = "ORDERS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    @Getter
    private Long id;

    @Embedded
    private OrderLines orderLines;

    @Embedded
    private Delivery delivery;

    @Column(name = "ORDERER")
    private String orderer;

    @Column(name = "MESSAGE")
    private String message;

    @Column(name = "STATE")
    private OrderState state;

    @Column(name = "ORDER_DATE_TIME")
    private LocalDateTime orderDateTime;

    private Order(OrderLines orderLines,
                  Delivery delivery,
                  String orderer,
                  String message) {
        setOrderLines(orderLines);
        this.delivery = delivery;
        this.orderer = orderer;
        this.message = message;
        this.orderDateTime = LocalDateTime.now();
        this.state = COMPLETED;
    }

    private void setOrderLines(OrderLines orderLines) {
        orderLines.setOrder(this);
        this.orderLines = orderLines;
    }

    public static Order of(OrderLines orderLines,
                           Delivery delivery,
                           String orderer,
                           String message) {
        return new Order(orderLines, delivery, orderer, message);
    }

}
