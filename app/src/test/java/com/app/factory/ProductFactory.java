package com.app.factory;

import com.app.infrastructure.entity.ProductEntity;

public class ProductFactory {

    public static ProductEntity productEntity() {
        return ProductEntity.builder()
                .id(35455L)
                .name("product")
                .build();
    }
}
