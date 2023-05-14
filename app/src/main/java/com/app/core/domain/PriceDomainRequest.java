package com.app.core.domain;

import lombok.*;

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
