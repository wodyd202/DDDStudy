package com.ljy.dddstudy.services.order.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "ORDER_LINES")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderLine {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "PRODUCT_ID")
    private long productId;

    @Column(name = "QUANTITY")
    private int quantity;

    @Setter(AccessLevel.PACKAGE)
    @ManyToOne
    @JoinColumn(name = "ORDERS_ID")
    private Order order;

    private OrderLine(long productId, int quantity) {
        this.productId = productId;
        this.quantity = quantity;
    }

    public static OrderLine of(long productId, int quantity) {
        return new OrderLine(productId, quantity);
    }
}
