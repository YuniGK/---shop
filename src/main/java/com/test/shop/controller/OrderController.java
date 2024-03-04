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
    public Response<Void> createNewOrder(@RequestParam int customerId){
        //TODO
        HashMap<Integer, Integer> orderMap = new HashMap<>();
        orderMap.put(1, 10);

        orderService.newOrder(
                CreateOrder.builder()
                        .customerId(customerId)
                        .quantityByProduct(orderMap)
                        .build()
        );

        return Response.success(null);
    }
}
