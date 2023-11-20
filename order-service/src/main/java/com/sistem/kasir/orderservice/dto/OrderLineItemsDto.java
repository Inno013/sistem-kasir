package com.sistem.kasir.orderservice.dto;

import com.sistem.kasir.orderservice.model.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItemsDto {
    private Product productId;
    private BigDecimal subTotal;
    private Integer quantity;
}
