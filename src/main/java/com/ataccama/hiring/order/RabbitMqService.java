package com.ataccama.hiring.order;

public interface RabbitMqService {
    void send(RabbitMessage message);
}
