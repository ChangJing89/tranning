package com.example.rabbitmq.serviceB.rabbitmq;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@Slf4j
public class MessageReceiver {

    @RabbitHandler
    @RabbitListener(queues = "direct_queue")
    public void receiveMessageByDirectExchange (String content, Channel channel, Message message) throws IOException {
        logger.info("receive a message: {}",content);
        try {
            //通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                // 拒绝接收消息， 一次只能拒绝一条消息
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                logger.error("消息即将再次返回队列处理...");
                // 消息接收失败，可以批量拒绝多条消息
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "topic_queueA")
    public void receiveMessageByTopicExchangeA (String content, Channel channel, Message message) throws IOException {
        logger.info("receive a message: {}",content);
        try {
            //通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                logger.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "topic_queueB")
    public void receiveMessageByTopicExchangeB (String content, Channel channel, Message message) throws IOException {
        logger.info("receive a message: {}",content);
        try {
            //通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                logger.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

   /* @RabbitHandler
    @RabbitListener(queues = "fanout_queueA")
    public void receiveMessageByFanoutExchangeA (String content, Channel channel, Message message) throws IOException {
        logger.info("receive a message: queueA {}",content);
        try {
            //通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                logger.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout_queueB")
    public void receiveMessageByFanoutExchangeB (String content, Channel channel, Message message) throws IOException {
        logger.info("receive a message: queueB {}",content);
        try {
            //通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                logger.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }

    @RabbitHandler
    @RabbitListener(queues = "fanout_queueC")
    public void receiveMessageByFanoutExchangeC (String content, Channel channel, Message message) throws IOException {
        logger.info("receive a message: queueC {}",content);
        try {
            //通知服务器此消息已经被消费，可从队列删掉， 这样以后就不会重发，否则后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (Exception e) {
            if (message.getMessageProperties().getRedelivered()) {
                logger.error("消息已重复处理失败,拒绝再次接收...");
                channel.basicReject(message.getMessageProperties().getDeliveryTag(), false); // 拒绝消息
            } else {
                logger.error("消息即将再次返回队列处理...");
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }
        }
    }*/
}

