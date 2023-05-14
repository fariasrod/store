package com.app.factory;

import com.app.core.domain.PriceDomain;
import com.app.core.domain.PriceDomainRequest;
import com.app.infrastructure.entity.PriceEntity;
import com.spec.model.PriceRequest;
import com.spec.model.PriceResponse;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class PriceFactory {

    private static final LocalDateTime START_DATE = LocalDateTime.of(2020, 6, 14, 15, 0, 0);
    private static final LocalDateTime END_DATE = LocalDateTime.of(2020, 6, 14, 18, 30, 0);

    public static PriceDomain priceDomain() {
        return PriceDomain.builder()
                .id(1L)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .priceList(1)
                .priority(1)
                .curr("EUR")
                .price(new BigDecimal("25.45"))
                .brandId(1L)
                .productId(35455L)
                .build();
    }

    public static PriceEntity priceEntity() {
        return PriceEntity.builder()
                .id(1L)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .priceList(1)
                .priority(1)
                .curr("EUR")
                .price(new BigDecimal("25.45"))
                .brandId(1L)
                .productId(35455L)
                .build();
    }

    public static PriceDomainRequest priceDomainRequest() {
        return PriceDomainRequest.builder()
                .date(LocalDateTime.of(2020, 6, 14, 16, 0, 0))
                .brandId(1L)
                .productId(35455L)
                .build();
    }

    public static PriceDomainRequest priceDomainRequestBrandNull() {
        return PriceDomainRequest.builder()
                .date(LocalDateTime.of(2020, 6, 14, 16, 0, 0))
                .brandId(null)
                .productId(35455L)
                .build();
    }

    public static PriceDomainRequest priceDomainRequestProductNull() {
        return PriceDomainRequest.builder()
                .date(LocalDateTime.of(2020, 6, 14, 16, 0, 0))
                .brandId(1L)
                .productId(null)
                .build();
    }

    public static List<PriceEntity> lPriceEntity() {
        return List.of(priceEntity());
    }

    public static PriceRequest priceRequest(final LocalDateTime date, final Integer brandId, final Integer productId) {
        PriceRequest request = new PriceRequest();
        request.setDate(date);
        request.setBrandId(brandId);
        request.setProductId(productId);
        return request;
    }

    public static PriceResponse priceResponse() {
        PriceResponse response = new PriceResponse();
        response.setId(2);
        response.setStartDate(START_DATE);
        response.setEndDate(END_DATE);
        response.setCurr("EUR");
        response.setPrice("25.45");
        response.setBrandId(1);
        response.setProductId(35455);
        return response;
    }
}
