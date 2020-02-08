package com.demchenko.rabbitdemo.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

@Getter
@Setter
@Primary
@Configuration
@PropertySource("classpath:application.properties")
public class ApplicationConfigReader {
    @Value("${app1.exchange.name}")
    private String app1Exchange;
    @Value("${app1.queue.name}")
    private String app1Queue;
    @Value("${app1.routing.key}")
    private String app1RoutingKey;
    @Value("${app2.exchange.name}")
    private String app2Exchange;
    @Value("${app2.queue.name}")
    private String app2Queue;
    @Value("${app2.routing.key}")
    private String app2RoutingKey;
}
