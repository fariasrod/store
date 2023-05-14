package com.app.core.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.Builder;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceDomainRequest {

    @NotNull
    private LocalDateTime date;

    @NotNull
    private Long brandId;

    @NotNull
    private Long productId;
}
