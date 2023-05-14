package com.app.factory;

import com.app.infrastructure.entity.BrandEntity;

public class BrandFactory {

    public static BrandEntity brandEntity() {
        return BrandEntity.builder()
                .id(1L)
                .name("brand")
                .build();
    }
}
