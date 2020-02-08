package com.demchenko.rabbitdemo.sender;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MessageSender {

    public void sendMessage(RabbitTemplate rabbitTemplate, String exchange, String routingKey, Object data) {
        log.info("Send message to queue using routingKey {}, Message= {}", routingKey, data);
        rabbitTemplate.convertAndSend(exchange, routingKey, data);
        log.info("The message has been sent to the queue");
    }
}
