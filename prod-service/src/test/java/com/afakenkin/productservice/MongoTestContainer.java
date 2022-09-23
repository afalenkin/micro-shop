package com.afakenkin.productservice;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 */
@SpringBootTest
@Testcontainers
public abstract class MongoTestContainer {

    /**
     * После запуска тестов будет создан тестконтейнер для mongoDb
     */
    @Container
    static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:latest");

    /**
     * После того, как будет запущен тестконтейнер - в контекст будут помещены свойства для подключения к нему.
     * Этими свойствами сможет воспользоваться SpringData чтобы работать с монго.
     */
    @DynamicPropertySource
    static void setProperties(DynamicPropertyRegistry properties) {
        properties.add("spring.data.mongodb.uri", mongoDBContainer::getReplicaSetUrl);
    }
}
