package com.test.shop.domain;


import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Getter
@Table(name = "store_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class StoreProduct {
    @Id
    private int storeProductId;

    @Column
    private int storeId;

    @Column
    private int productId;

    @Column
    private int stockQuantity;
}
