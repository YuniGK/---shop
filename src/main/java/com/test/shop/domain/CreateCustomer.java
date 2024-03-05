package com.test.shop.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Builder
@Accessors(chain=true)
public class CreateCustomer {
    private String name;

    private String address;

    private String phneNumber;
}
