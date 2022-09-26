package com.afalenkin.ordersservice.service;

import com.afalenkin.ordersservice.dto.OrderRequest;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface OrderService {
    String placeOrder(OrderRequest orderRequest);
}
