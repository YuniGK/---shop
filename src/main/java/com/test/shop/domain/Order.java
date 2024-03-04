package com.test.shop.domain;

import org.springframework.data.annotation.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.*;

@Getter
@Table(name = "orders")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Order {
    @Id
    private int orderId;

    @Column
    private int customerId;

    @Column
    private Timestamp orderedAt;

    @MappedCollection(idColumn="order_id", keyColumn = "order_item_id")
    private Set<OrderItem> orderItems = new HashSet<>();

    public Order(int customerId, Set<OrderItem> orderItems){
        this.customerId = customerId;
        this.orderedAt = Timestamp.valueOf(LocalDateTime.now());
        this.orderItems = orderItems;
    }

    public static Order newOrder(CreateOrder order){
        Set<OrderItem> items = new HashSet<>();

        for (Map.Entry<Integer, Integer> entry : order.getQuantityByProduct().entrySet()){
            items.add(new OrderItem(entry.getKey(), entry.getValue()));
        }

        return new Order(order.getCustomerId(), items);
    }
}
