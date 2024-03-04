package com.test.shop.domain.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Map;

@Getter
@Builder
public class OrderDto {
    private final int customerId;
    private final Map<Integer, Integer> quantityByProduct;
}
