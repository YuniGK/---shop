package com.test.shop.reopsitory;

import com.test.shop.domain.Order;
import com.test.shop.domain.StoreProduct;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

public interface StoreProductRepository extends CrudRepository<StoreProduct, Integer> {
    Optional<StoreProduct> findByStoreIdAndProductId(int storeId, int productId);
}
