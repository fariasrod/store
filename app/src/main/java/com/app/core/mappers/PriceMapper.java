package com.app.core.mappers;

import com.app.core.domain.PriceDomain;
import com.app.core.domain.PriceDomainRequest;
import com.app.infrastructure.entity.PriceEntity;
import com.spec.model.PriceRequest;
import com.spec.model.PriceResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PriceMapper {

    PriceMapper INSTANCE = Mappers.getMapper(PriceMapper.class);

    PriceResponse toPriceResponse(PriceDomain domain);

    PriceDomain toPriceDomain(PriceEntity entity);

    PriceDomainRequest toPriceDomainRequest(PriceRequest request);
}
