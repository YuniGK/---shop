package com.test.shop.domain;


import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.annotation.Id;

@Getter
@Table(name = "store_products")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Accessors(chain=true)
public class StoreProduct {
    @Id
    private int storeProductId;

    @Column
    private int storeId;

    @Column
    private int productId;

    @Column
    private int stockQuantity;

    //재고 감량
    public void abjustStockQuantity(int buyQuantity){
        if(stockQuantity < buyQuantity){
            throw new RuntimeException("[StoreProduct] - abjustStockQuantity 재고보다 많을 수 없습니다.");
        }

        this.stockQuantity = this.stockQuantity - buyQuantity;
    }
}
