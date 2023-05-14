package com.app.infrastructure.repository;

import com.app.infrastructure.entity.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {

    List<PriceEntity> findByStartDateLessThanEqualAndEndDateGreaterThanEqualAndBrandIdAndProductId(
            final LocalDateTime date,
            final LocalDateTime date2,
            final Long brandId,
            final Long productId);
}
