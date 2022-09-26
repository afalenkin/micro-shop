package com.afalenkin.ordersservice.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
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
     * Этот бин не может работать с приложениями, для которых одновременно запущено несколько инстансов.
     */
//    @Bean
//    public WebClient webClient() {
//        return WebClient.builder().build();
//    }

    /**
     * Чтобы активировать client-side LoadBalancing вместо бина WebClient нужносоздавать бин WebClient.Builder
     * и пометить метод аннотацией @LoadBalanced - в таком случае спринг с помощью билдера сам
     * сконфигурирует и создаст бин WebClient, который будет отвечать за распределение нагрузки на
     * стороне клиента и сможет работать с приложениями, для которых одновременно запущено несколько инстансов.
     */
    @Bean
    @LoadBalanced
    public WebClient.Builder webClientBuilder() {
        return WebClient.builder();
    }
}
