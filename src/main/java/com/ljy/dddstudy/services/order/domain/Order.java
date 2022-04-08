
package com.ljy.dddstudy.services.order.domain;

import com.ljy.dddstudy.services.order.domain.exception.NoChangeableOrderStateException;
import com.ljy.dddstudy.services.order.domain.exception.NoUpdatePermitionException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

import static com.ljy.dddstudy.services.order.domain.OrderState.ORDER_COMPLETED;

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
    @Getter
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
        this.state = ORDER_COMPLETED;
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

    public void changeDelivery(Delivery delivery, String updater) {
        verifyHasUpdatePermition(updater);
        verifyChangeableDelivery();
        this.delivery = delivery;
    }

    private void verifyHasUpdatePermition(String updater) {
        if(hasUpdatePermition(updater) == false) {
            throw new NoUpdatePermitionException();
        }
    }

    private boolean hasUpdatePermition(String updater) {
        return this.orderer.equals(updater);
    }

    private void verifyChangeableDelivery() {
        if(state.isChangeable() == false) {
            throw new NoChangeableOrderStateException();
        }
    }
}
