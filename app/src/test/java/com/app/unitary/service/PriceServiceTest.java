package com.app.unitary.service;

import com.app.core.domain.PriceDomain;
import com.app.core.service.impl.PriceServiceImpl;
import com.app.infrastructure.repository.BrandRepository;
import com.app.infrastructure.repository.PriceRepository;
import com.app.infrastructure.repository.ProductRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Optional;

import static com.app.factory.BrandFactory.brandEntity;
import static com.app.factory.PriceFactory.*;
import static com.app.factory.ProductFactory.productEntity;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

@SpringBootTest
public class PriceServiceTest {

    @Mock
    private PriceRepository priceRepository;
    @Mock
    private BrandRepository brandRepository;
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private PriceServiceImpl priceService;

    @Test
    @DisplayName("WHEN trying to retrieve a price domain THEN return a price domain | unitary test")
    public void WHEN_trying_to_retrieve_a_price_domain_THEN_ok() {

        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(brandEntity()));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(productEntity()));
        when(priceRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        any(Long.class),
                        any(Long.class))).thenReturn(lPriceEntity());

        PriceDomain result = priceService.getPricesByDateAndBrandIdAndProductId(priceDomainRequest());

        assertNotNull(result);
        assertThat(result.getStartDate(), equalTo(priceDomain().getStartDate()));
        assertThat(result.getEndDate(), equalTo(priceDomain().getEndDate()));
        assertThat(result.getPriceList(), equalTo(priceDomain().getPriceList()));
        assertThat(result.getPriority(), equalTo(priceDomain().getPriority()));
        assertThat(result.getCurr(), equalTo(priceDomain().getCurr()));
        assertThat(result.getPrice(), equalTo(priceDomain().getPrice()));
        assertThat(result.getBrandId(), equalTo(priceDomain().getBrandId()));
        assertThat(result.getProductId(), equalTo(priceDomain().getProductId()));
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price domain when price is empty THEN ResponseStatusException | unitary test")
    public void WHEN_trying_to_retrieve_a_price_domain_when_price_is_empty_THEN_ResponseStatusException() {

        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(brandEntity()));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(productEntity()));
        when(priceRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(
                        any(LocalDateTime.class),
                        any(LocalDateTime.class),
                        any(Long.class),
                        any(Long.class))).thenReturn(Collections.emptyList());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            priceService.getPricesByDateAndBrandIdAndProductId(priceDomainRequest());
        });

        assertEquals(HttpStatus.NO_CONTENT, exception.getStatus());
        assertEquals("There are no prices for the parameters given.", exception.getReason());
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price domain when brand is empty THEN ResponseStatusException | unitary test")
    public void WHEN_trying_to_retrieve_a_price_domain_when_brand_is_empty_THEN_ResponseStatusException() {

        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            priceService.getPricesByDateAndBrandIdAndProductId(priceDomainRequest());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Brand { 1 } not found.", exception.getReason());
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price domain when product is empty THEN ResponseStatusException | unitary test")
    public void WHEN_trying_to_retrieve_a_price_domain_when_product_is_empty_THEN_ResponseStatusException() {

        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(brandEntity()));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            priceService.getPricesByDateAndBrandIdAndProductId(priceDomainRequest());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Product { 35455 } not found.", exception.getReason());
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price domain when brand is null THEN ResponseStatusException | unitary test")
    public void WHEN_trying_to_retrieve_a_price_domain_when_brand_is_null_THEN_ResponseStatusException() {

        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            priceService.getPricesByDateAndBrandIdAndProductId(priceDomainRequestBrandNull());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Brand { null } not found.", exception.getReason());
    }

    @Test
    @DisplayName("WHEN trying to retrieve a price domain when product is null THEN ResponseStatusException | unitary test")
    public void WHEN_trying_to_retrieve_a_price_domain_when_product_is_null_THEN_ResponseStatusException() {

        when(brandRepository.findById(any(Long.class))).thenReturn(Optional.ofNullable(brandEntity()));
        when(productRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        ResponseStatusException exception = assertThrows(ResponseStatusException.class, () -> {
            priceService.getPricesByDateAndBrandIdAndProductId(priceDomainRequestProductNull());
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
        assertEquals("Product { null } not found.", exception.getReason());
    }
}
