package com.app.unitary.mapper;

import com.app.core.domain.PriceDomain;
import com.app.core.domain.PriceDomainRequest;
import com.app.core.mappers.PriceMapper;
import com.app.infrastructure.entity.PriceEntity;
import com.spec.model.PriceRequest;
import com.spec.model.PriceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static com.app.factory.PriceFactory.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PriceMapperTest {

    @Test
    @DisplayName("Given a PriceDomain returns a PriceResponse")
    void toPriceResponse() {
        PriceResponse priceResponse = PriceMapper.INSTANCE.toPriceResponse(priceDomain());
        this.compare(priceResponse, priceDomain());
    }

    @Test
    @DisplayName("Given a PriceEntity returns a PriceDomain")
    void toPriceDomain() {
        PriceDomain priceDomain = PriceMapper.INSTANCE.toPriceDomain(priceEntity());
        this.compare(priceDomain, priceEntity());
    }

    @Test
    @DisplayName("Given a PriceRequest returns a PriceRequest")
    void toPriceDomainRequest() {
        var priceRequest = priceRequest(LocalDateTime.of(2020, 6, 14, 16, 0, 0), 100, 35455);
        PriceDomainRequest priceDomainRequest = PriceMapper.INSTANCE.toPriceDomainRequest(priceRequest);
        this.compare(priceDomainRequest, priceRequest);
    }

    protected void compare(final PriceResponse priceResponse, final PriceDomain priceDomain) {
        assertNotNull(priceResponse);
        assertNotNull(priceDomain);
        assertEquals(priceResponse.getStartDate(), priceDomain.getStartDate());
        assertEquals(priceResponse.getEndDate(), priceDomain.getEndDate());
        assertEquals(priceResponse.getCurr(), priceDomain.getCurr());
        assertEquals(priceResponse.getPrice(), priceDomain.getPrice().toString());
        assertEquals(priceResponse.getBrandId().toString(), priceDomain.getBrandId().toString());
        assertEquals(priceResponse.getProductId().toString(), priceDomain.getProductId().toString());
    }

    protected void compare(final PriceDomain priceDomain, final PriceEntity priceEntity) {
        assertNotNull(priceDomain);
        assertNotNull(priceEntity);
        assertEquals(priceDomain.getStartDate(), priceEntity.getStartDate());
        assertEquals(priceDomain.getEndDate(), priceEntity.getEndDate());
        assertEquals(priceDomain.getPriceList(), priceEntity.getPriceList());
        assertEquals(priceDomain.getPriority(), priceEntity.getPriority());
        assertEquals(priceDomain.getCurr(), priceEntity.getCurr());
        assertEquals(priceDomain.getPrice().toString(), priceEntity.getPrice().toString());
        assertEquals(priceDomain.getBrandId().toString(), priceEntity.getBrandId().toString());
        assertEquals(priceDomain.getProductId().toString(), priceEntity.getProductId().toString());
    }

    protected void compare(final PriceDomainRequest priceDomainRequest, final PriceRequest priceRequest) {
        assertNotNull(priceDomainRequest);
        assertNotNull(priceRequest);
        assertEquals(priceDomainRequest.getDate(), priceRequest.getDate());
        assertEquals(priceDomainRequest.getBrandId().toString(), priceRequest.getBrandId().toString());
        assertEquals(priceDomainRequest.getProductId().toString(), priceRequest.getProductId().toString());
    }
}
