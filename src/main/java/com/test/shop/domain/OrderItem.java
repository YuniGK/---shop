package com.test.shop.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Table(name = "order_items")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class OrderItem {
    @Id
    private int orderItemId;

    @Column
    private int orderId;

    @Column
    private int orderQuantity;

    public OrderItem(int orderId, int orderQuantity) {
        this.orderId = orderId;
        this.orderQuantity = orderQuantity;
    }
}
