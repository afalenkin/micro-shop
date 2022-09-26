package com.afalenkin.ordersservice.controller;

import com.afalenkin.ordersservice.dto.OrderRequest;
import com.afalenkin.ordersservice.service.OrderService;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@RestController
@RequestMapping(path = "/api/v1/orders")
@RequiredArgsConstructor
public class OrderControllerImpl implements OrderController {

    private final OrderService orderService;

    /**
     * Аннотация @CircuitBreaker позволяет добавить к вызову методу дополнительную функциональность
     * конечного автомата.
     * Чтобы использовать конечный автомат в проекте нужно:
     * - добавить стартер нужного автоматав pom.xml
     * - в application.properties сконфигурировать конечный автомат
     * - Нужный метод пометить аннотацией
     * При использовании аннотации @TimeLimiter в качестве возвращаемого значения следует
     * использовать CompletableFuture
     */
    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @CircuitBreaker(name = "inventory", fallbackMethod = "fallback")
    @TimeLimiter(name = "inventory")
    @Retry(name = "inventory")
    public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));
    }

    /**
     * Если в процессе исполнения метода placeOrder возникнут какие-либо исключения - будет вызван
     * метод fallback, который предлагает альтернативную логику обработки запроса.
     */
    public CompletableFuture<String> fallback(OrderRequest orderRequest, RuntimeException exception) {
        return CompletableFuture
                .supplyAsync(() -> "Sorry, we have some problems today... Today is a bad day for orders");
    }
}
