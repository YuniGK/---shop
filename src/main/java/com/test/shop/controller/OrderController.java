package com.test.shop.controller;

import com.test.shop.domain.CreateOrder;
import com.test.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping("/orders")
@RequiredArgsConstructor
@RestController
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/new")
    public Response<Void> createNewOrder(@RequestBody NewOrderRequest request){
        orderService.newOrder(
                CreateOrder.builder()
                        .customerId(request.getCustomerId())
                        .storeId(request.getStoreId())
                        .quantityByProduct(request.getProducts())
                        .build()
        );

        return Response.success(null);
    }
}
