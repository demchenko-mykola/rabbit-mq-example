package com.demchenko.rabbitdemo.listener;

import com.demchenko.rabbitdemo.config.ApplicationConfigReader;
import com.demchenko.rabbitdemo.model.UserDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MessageListener {

    @Autowired
    ApplicationConfigReader applicationConfigReader;

    @RabbitListener(queues = "${app1.queue.name}")
    public void receiveMessageForApp1(final UserDto userDto) {
        log.info("Receiver message: {} from app1 queue", userDto);
    }

    @RabbitListener(queues = "${app2.queue.name}")
    public void receiveMessageForApp2(final String data) {
        log.info("Receiver message: {} from app1 queue", data);
    }
}
