package com.test.shop.service;

import com.test.shop.domain.CreateOrder;
import com.test.shop.domain.Order;
import com.test.shop.reopsitory.OrderRepository;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void newOrder(CreateOrder order){
        //dto -> entity
        Order entity = Order.newOrder(order);
        Order saved = orderRepository.save(entity);
    }
}
