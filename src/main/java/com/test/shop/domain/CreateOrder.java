package com.test.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.Map;

@Getter
@Builder
@Accessors(chain=true)
public class CreateOrder {
    private final int customerId;
    private final Map<Integer, Integer> quantityByProduct;//["아이스 아에리카노", 3]
}
