package com.example.rabbitmq.serviceA.rabbitmq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

/**
 * 实现消息发送到rabbitmq交换器后接收到ack回调
 */
@Component
@Slf4j
public class RabbitConfirmBack implements RabbitTemplate.ConfirmCallback {

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        logger.info("回调id: {}", correlationData);
        if(ack) {
            logger.info("消息接收成功");
        }else {
            logger.info("消息接收失败： {}",cause);
        }
    }
}
