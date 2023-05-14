package com.app.core.service;

import com.app.core.domain.PriceDomain;
import com.app.core.domain.PriceDomainRequest;

public interface PriceService {

    PriceDomain getPricesByDateAndBrandIdAndProductId(final PriceDomainRequest request);
}
