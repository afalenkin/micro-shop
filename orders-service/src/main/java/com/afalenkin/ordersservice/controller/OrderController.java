package com.afalenkin.ordersservice.controller;

import com.afalenkin.ordersservice.dto.OrderRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.concurrent.CompletableFuture;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
public interface OrderController {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest);
}
