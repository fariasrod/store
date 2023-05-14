package com.app.core.domain;

import lombok.*;

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
