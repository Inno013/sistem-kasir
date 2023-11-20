package com.sistem.kasir.orderservice.service;

import com.sistem.kasir.orderservice.dto.OrderLineItemsDto;
import com.sistem.kasir.orderservice.dto.OrderRequest;
import com.sistem.kasir.orderservice.model.Order;
import com.sistem.kasir.orderservice.model.OrderLineItems;
import com.sistem.kasir.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final Order order;

    public void placeOrder(OrderRequest orderRequest){

        order.setOrderDate(LocalDate.now());
        order.setAdminId(002L);
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map((OrderLineItemsDto orderLineItemsDto) -> mapToDto(orderLineItemsDto, order))
                .toList();

        order.setOrderLineItemsList(orderLineItems);

        orderRepository.save(order);
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto, Order order) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setOrderId(order);
        orderLineItems.setSubTotal(orderLineItemsDto.getSubTotal());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setProductId(orderLineItemsDto.getProductId());
        return orderLineItems;
    }
}
