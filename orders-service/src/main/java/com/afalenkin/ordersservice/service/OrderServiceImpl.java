package com.afalenkin.ordersservice.service;

import com.afalenkin.ordersservice.dto.InventoryResponse;
import com.afalenkin.ordersservice.dto.OrderItemDto;
import com.afalenkin.ordersservice.dto.OrderRequest;
import com.afalenkin.ordersservice.events.OrderPlacedEvent;
import com.afalenkin.ordersservice.model.Order;
import com.afalenkin.ordersservice.model.OrderItem;
import com.afalenkin.ordersservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.UUID;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Аннотация RefreshScope позволяет обновлять свойства в классе, которые внедряются из файла
 * с пропертями(application-properties). Чтобы обновить эти свойства в запущенном приложении - нужно
 * обновить их в гит-репозитории в котором они хранятся и вызвать эндпоинт actuator/refresh этого сервиса.
 * Чтобы был доступ к этому эндпоинту прометеуса нужно в конфигурациях приложения добавить свойство
 * management.endpoints.web.exposure.include=*
 *
 * При этом значения свойств не будут обновляться автоматически при изменении их в репозитории,
 * они обновятся только при рестарте приложения или при вызове эндпоинта актуатора
 */
@Service
@RequiredArgsConstructor
@Transactional
@RefreshScope
public class OrderServiceImpl implements OrderService {

    @Value("${orderservice.inventory.api.uri}")
    public String inventory_api;

    @Value("${orderservice.kafka.topic.name}")
    public String kafka_topic;

    private final OrderRepository repository;
    private final WebClient.Builder webClientBuilder;
    private final Tracer tracer;
    private final KafkaTemplate<String, OrderPlacedEvent> kafkaTemplate;

    @Override
    public String placeOrder(OrderRequest orderRequest) {
        List<String> itemCodes = orderRequest.getOrderItems().stream()
                .map(OrderItemDto::getCode)
                .toList();

        // Метод placeOrder запускается асинхронно в отдельном потоке, поэтому трейсинг вызовов сторонних
        // сервисов из этого метода не будет производиться. Чтобы сохранить трейсинг - необходимо
        // назначить спан вручную.
        Span inventoryServiceSpan = tracer.nextSpan().name("inventoryServiceSpan");
        try (Tracer.SpanInScope spanInScope = tracer.withSpan(inventoryServiceSpan.start())) {
            List<InventoryResponse> inventoryResponses =
                    webClientBuilder.build()
                            .put()
                            .uri(inventory_api)
                            .body(BodyInserters.fromValue(itemCodes))
                            .retrieve()
                            .bodyToMono(new ParameterizedTypeReference<List<InventoryResponse>>() {
                            })
                            .block();

            boolean itemsExists = (inventoryResponses != null &&
                    itemCodes.size() == inventoryResponses.size()) &&
                    (inventoryResponses.stream().allMatch(InventoryResponse::isInStock));
            if (!itemsExists) {
                throw new IllegalArgumentException("One item from order is absent, please try to make order later");
            }
        } finally {
            inventoryServiceSpan.end();
        }

        List<OrderItem> items = orderRequest.getOrderItems().stream()
                .map(this::toOrderItem)
                .toList();

        Order order = Order.builder()
                .items(items)
                .orderNumber(UUID.randomUUID().toString())
                .build();

        repository.save(order);
        kafkaTemplate.send(kafka_topic, new OrderPlacedEvent(order.getOrderNumber()));

        return "Order placed successfully";
    }

    private OrderItem toOrderItem(OrderItemDto dto) {
        return OrderItem.builder()
                .code(dto.getCode())
                .price(dto.getPrice())
                .quantity(dto.getQuantity())
                .build();
    }
}
