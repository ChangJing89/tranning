package com.example.rabbitmq.serviceA.rabbitmq;

import com.example.rabbitmq.serviceA.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Slf4j
public class MessageSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessageByDirectExchange() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.DIRECT_EXCHANGE, RabbitMQConfig.DIRECT_ROUTING_KEY,
                "serviceA sends a message to serviceB with directExchange", correlationId);
    }

    public void sendMessageByTopicExchangeA() {
        //在做补偿性机制的时候通过ID来获取到这条消息进行重发
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.service.A",
                "serviceA sends a message to serviceB with topicExchange1", correlationId);
    }

    public void sendMessageByTopicExchangeB() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.service.B",
                "serviceA sends a message to serviceB with topicExchange2", correlationId);
    }

    public void sendMessageByFanoutExchangeB() {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        this.rabbitTemplate.convertAndSend(RabbitMQConfig.FANOUT_EXCHANGE, "",
                "serviceA sends a message to serviceB with fanoutExchange", correlationId);
    }
}
