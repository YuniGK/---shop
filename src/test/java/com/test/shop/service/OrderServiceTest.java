package com.test.shop.service;

import com.test.shop.domain.CreateOrder;
import com.test.shop.domain.StoreProduct;
import com.test.shop.reopsitory.OrderRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    //의존성 주입
    @Mock
    OrderRepository orderRepository;
    @Mock
    StoreService storeService;

    @InjectMocks
    OrderService orderService;

    @Test
    @DisplayName("구매 수량 < 재고 수량 / 정상 주문 가능")
    public void stockQuantityTest_success(){
        //given
        int buyQuantity = 5;
        int stockQuantity = 50;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, buyQuantity);

        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();

        //storeId, customerId 1이 들어갔을 때, stock을 반환한다.
        when(storeService.getStoreProduct(1, 1)).thenReturn(stock);

        //when
        orderService.newOrder(createOrder);

        //then
        assertThat(stock.getStockQuantity()).isEqualTo(stockQuantity - buyQuantity);
    }

    @Test
    @DisplayName("구매 수량 > 재고 수량 / 주문 실패")
    public void stockQuantityTest_fail(){
        //given
        int buyQuantity = 50;
        int stockQuantity = 1;

        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, buyQuantity);

        CreateOrder createOrder = CreateOrder.builder()
                .storeId(1)
                .customerId(1)
                .quantityByProduct(map)
                .build();

        StoreProduct stock = StoreProduct.builder()
                .stockQuantity(stockQuantity)
                .build();

        //storeId, customerId 1이 들어갔을 때, stock을 반환한다.
        when(storeService.getStoreProduct(1, 1)).thenReturn(stock);

        //when
        RuntimeException runtimeException = assertThrows(RuntimeException.class, () -> {
            orderService.newOrder(createOrder);
        });

        //then
        //assertThat(runtimeException.getMessage()).isEqualTo("[StoreService] - getStoreProduct 존재하지 않습니다.");
        assertThat(runtimeException.getMessage()).isEqualTo("[OrderService] - newOrder 재고가 없습니다.");
    }

}