package com.afalenkin.ordersservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@Configuration
public class WebClientConfiguration {

    /**
     * Со Spring 5.0 RestTemplate находится в состоянии поддержки и не рекомендуется к применению.
     * Вместо него следует использовать WebClient - это часть проекта WebFlux(нужно подключать зависимость от Webflux)
     * и он позволяет совершать синхронные и асинхронные вызовы REST-эндпоинтов.
     */
    @Bean
    public WebClient webClient() {
        return WebClient.builder().build();
    }
}
