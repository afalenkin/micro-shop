package com.afalenkin.ordersservice.service;

import com.afalenkin.ordersservice.dto.OrderItemDto;
import com.afalenkin.ordersservice.dto.OrderRequest;
import com.afalenkin.ordersservice.model.Order;
import com.afalenkin.ordersservice.model.OrderItem;
import com.afalenkin.ordersservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository repository;

    public void placeOrder(OrderRequest orderRequest) {
        List<OrderItem> items = orderRequest.getOrderItems().stream()
                .map(this::toOrderItem)
                .collect(Collectors.toList());

        Order order = Order.builder()
                .items(items)
                .orderNumber(UUID.randomUUID().toString())
                .build();

        repository.save(order);

    }

    private OrderItem toOrderItem(OrderItemDto dto) {
        return OrderItem.builder()
                .code(dto.getCode())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}
