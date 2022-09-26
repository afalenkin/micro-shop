package com.afalenkin.discoveryserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author Alenkin Andrew
 * oxqq@ya.ru
 *
 * Аннотация EnableEurekaServer позволяет сделать из текущего приложения Server Discovery.
 * При этом сервисы, которые получают информацию с Server Discovery должны иметь аннотацию EnableEurekaClient.
 */
@SpringBootApplication
@EnableEurekaServer
public class DiscoveryServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DiscoveryServerApplication.class, args);
    }

}
