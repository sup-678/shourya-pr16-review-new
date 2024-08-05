package com.ataccama.hiring.order;

import org.springframework.stereotype.Component;

@Component
public class RabbitMqServiceImpl implements RabbitMqService {
    @Override
    public void send(RabbitMessage message) {
        // NOTE: method impl irrelevant
    }
}
