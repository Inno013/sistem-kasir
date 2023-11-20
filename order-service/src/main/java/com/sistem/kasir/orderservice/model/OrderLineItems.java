package com.sistem.kasir.orderservice.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "tb_orderItems")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "orderId", referencedColumnName = "orderId")
    private Order orderId;
    @ManyToOne
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product productId;
    private BigDecimal subTotal;
    private Integer quantity;
}
