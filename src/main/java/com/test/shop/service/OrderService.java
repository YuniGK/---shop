package com.test.shop.service;

import com.test.shop.domain.CreateOrder;
import com.test.shop.domain.Order;
import com.test.shop.domain.StoreProduct;
import com.test.shop.reopsitory.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final StoreService storeService;

    public void newOrder(CreateOrder order){
        List<StoreProduct> storeProducts = new ArrayList<>();

        //상품의 재고가 있는지 확인
        for(Map.Entry<Integer, Integer> entry : order.getQuantityByProduct().entrySet()){
            Integer productId = entry.getKey();
            Integer buyQuantity = entry.getValue();

            StoreProduct storeProduct = storeService.getStoreProduct(order.getStoreId(), productId);

            int stockQuantity = storeProduct.getStockQuantity();

            //구매하고자 하는 갯수가 재고와 비교
            if(buyQuantity > stockQuantity){
                throw new RuntimeException("[OrderService] - newOrder 재고가 없습니다.");
            }

            //재고 감량
            storeProduct.abjustStockQuantity(buyQuantity);

            storeProducts.add(storeProduct);
        }

        //dto -> entity
        Order entity = Order.newOrder(order);
        Order saved = orderRepository.save(entity);

        storeService.saveAll(storeProducts);
    }
}
