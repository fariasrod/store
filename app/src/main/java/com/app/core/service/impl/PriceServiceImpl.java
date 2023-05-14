package com.app.core.service.impl;

import com.app.core.domain.PriceDomain;
import com.app.core.domain.PriceDomainRequest;
import com.app.core.mappers.PriceMapper;
import com.app.core.service.PriceService;
import com.app.infrastructure.entity.PriceEntity;
import com.app.infrastructure.repository.BrandRepository;
import com.app.infrastructure.repository.PriceRepository;
import com.app.infrastructure.repository.ProductRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;
    private final BrandRepository brandRepository;
    private final ProductRepository productRepository;

    @Autowired
    public PriceServiceImpl(final PriceRepository priceRepository,
                            final BrandRepository brandRepository,
                            final ProductRepository productRepository) {
        this.priceRepository = priceRepository;
        this.brandRepository = brandRepository;
        this.productRepository = productRepository;
    }

    @Override
    public PriceDomain getPricesByDateAndBrandIdAndProductId(final PriceDomainRequest request) {
        log.info(String.format("Trying to find all prices by date : { %s } brand : { %s } product { %s }",
                request.getDate(), request.getBrandId(), request.getProductId()));

        var brand = brandRepository.findById(request.getBrandId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Brand { %s } not found.", request.getBrandId())));

        var product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, String.format("Product { %s } not found.", request.getProductId())));

        var prices = priceRepository
                .findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(
                        request.getDate(),
                        request.getDate(),
                        brand.getId(),
                        product.getId());

        if (prices.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "There are no prices for the parameters given.");
        }

        return PriceMapper.INSTANCE.toPriceDomain(this.checksAndReturnsTheHighestPriorityPrice(prices));
    }

    private PriceEntity checksAndReturnsTheHighestPriorityPrice(final List<PriceEntity> entities) {
        return Collections.max(entities, Comparator.comparingInt(PriceEntity::getPriority));
    }
}
