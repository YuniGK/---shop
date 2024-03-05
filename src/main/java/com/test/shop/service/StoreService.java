package com.test.shop.service;

import com.test.shop.domain.CreateOrder;
import com.test.shop.domain.Order;
import com.test.shop.domain.StoreProduct;
import com.test.shop.reopsitory.OrderRepository;
import com.test.shop.reopsitory.StoreProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Transactional
@Service
public class StoreService {
    private final StoreProductRepository storeProductRepository;

    public StoreProduct getStoreProduct(int storeId, int productId){
        Optional<StoreProduct> storeProduct = storeProductRepository.findByStoreIdAndProductId(storeId, productId);

        if(storeProduct.isEmpty()){
            throw new RuntimeException("[StoreService] - getStoreProduct 존재하지 않습니다.");
        }

        return storeProduct.get();
    }

    public void saveAll(List<StoreProduct> storeProducts){
        storeProductRepository.saveAll(storeProducts);
    }
}
