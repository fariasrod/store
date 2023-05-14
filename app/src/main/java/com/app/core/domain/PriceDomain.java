package com.app.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDomain {

    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private Integer priority;

    private String curr;

    private BigDecimal price = BigDecimal.ZERO;

    private Long brandId;

    private Long productId;
}
