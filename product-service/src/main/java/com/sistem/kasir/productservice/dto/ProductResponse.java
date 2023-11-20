package com.sistem.kasir.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Component
public class ProductResponse {
    private Long productId;
    private String productName;
    private String skuCode;
    private BigDecimal price;
}
