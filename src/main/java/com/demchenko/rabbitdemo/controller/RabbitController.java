package com.demchenko.rabbitdemo.controller;

import com.demchenko.rabbitdemo.config.ApplicationConfigReader;
import com.demchenko.rabbitdemo.model.UserDto;
import com.demchenko.rabbitdemo.sender.MessageSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@RestController
@RequestMapping(path = "/rabbit")
public class RabbitController {

    public static final String MESSAGE_IN_QUEUE = "Message in queue";

    @Autowired
    public RabbitController(RabbitTemplate rabbitTemplate, ApplicationConfigReader configReader, MessageSender messageSender) {
        this.rabbitTemplate = rabbitTemplate;
        this.configReader = configReader;
        this.messageSender = messageSender;
    }
    private final RabbitTemplate rabbitTemplate;
    private ApplicationConfigReader configReader;
    private MessageSender messageSender;

    // curl -X POST http://localhost:8080/rabbit/send_object -H "Content-Type: application/json" -d '{"name":"Mykola","lastName":"Demchenko"}'
    @PostMapping(path = "/send_object", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity sendObject(@RequestBody UserDto userDto) {
        String exchange = configReader.getApp1Exchange();
        String routingKey = configReader.getApp1RoutingKey();

        messageSender.sendMessage(rabbitTemplate, exchange, routingKey, userDto);

        return ResponseEntity.ok().body(MESSAGE_IN_QUEUE);
    }

    // curl -X POST http://localhost:8080/rabbit/send_string -d 'Hello world'
    @PostMapping(path = "/send_string", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity sendString(@RequestBody String message) {
        String exchange = configReader.getApp2Exchange();
        String routingKey = configReader.getApp2RoutingKey();

        messageSender.sendMessage(rabbitTemplate, exchange, routingKey, message);

        return ResponseEntity.ok().body(MESSAGE_IN_QUEUE);
    }
}
