package com.afalenkin.notificationservice;

import com.afalenkin.notificationservice.events.OrderPlacedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@SpringBootApplication
@Slf4j
public class NotificationServiceApplication {

    public static final String KAFKA_TOPIC = "notifications";

    public static void main(String[] args) {
        SpringApplication.run(NotificationServiceApplication.class, args);
    }

    @KafkaListener(topics=KAFKA_TOPIC)
    public void handleNotification(OrderPlacedEvent event) {
        log.warn("Order number {} was placed", event.getOrderNumber());
    }

}
