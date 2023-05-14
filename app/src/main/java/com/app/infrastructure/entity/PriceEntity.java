package com.app.infrastructure.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl_price")
public class PriceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Integer priceList;

    private Integer priority;

    private String curr;

    @Column(precision = 10, scale = 2)
    private BigDecimal price = BigDecimal.ZERO;

    @ManyToOne
    @JoinColumn(name = "brand_id", updatable = false, insertable = false)
    private BrandEntity brand;

    @Column(name = "brand_id")
    private Long brandId;

    @ManyToOne
    @JoinColumn(name = "product_id", updatable = false, insertable = false)
    private ProductEntity product;

    @Column(name = "product_id")
    private Long productId;
}
