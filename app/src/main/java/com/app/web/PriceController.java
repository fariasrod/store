package com.app.web;

import com.app.core.mappers.PriceMapper;
import com.app.core.service.PriceService;
import com.spec.api.PricesApi;
import com.spec.model.PriceRequest;
import com.spec.model.PriceResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@CrossOrigin(origins = "*")
@RestController
public class PriceController implements PricesApi {

    private final PriceService priceService;

    @Autowired
    public PriceController(final PriceService priceService) {
        this.priceService = priceService;
    }

    @Override
    public ResponseEntity<PriceResponse> retrievePrices(final PriceRequest request) {
        return Optional
                .ofNullable(priceService.getPricesByDateAndBrandIdAndProductId(PriceMapper.INSTANCE.toPriceDomainRequest(request)))
                .map(m -> ResponseEntity.ok().body(PriceMapper.INSTANCE.toPriceResponse(m)))
                .orElse(ResponseEntity.notFound().build());
    }
}
