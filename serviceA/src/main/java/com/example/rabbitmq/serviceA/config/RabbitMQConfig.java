package com.example.rabbitmq.serviceA.config;

import com.example.rabbitmq.serviceA.rabbitmq.RabbitConfirmBack;
import com.example.rabbitmq.serviceA.rabbitmq.RabbitReturnCallback;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 *
 */
@Configuration
@Slf4j
public class RabbitMQConfig {

    public static final String DIRECT_EXCHANGE = "direct_exchange";
    public static final String FANOUT_EXCHANGE = "fanout_exchange";
    public static final String TOPIC_EXCHANGE = "topic_exchange";
    public static final String HEADER_EXCHANGE = "header_exchange";

    public static final String DIRECT_QUEUE = "direct_queue";
    public static final String FANOUT_QUEUEA = "fanout_queueA";
    public static final String FANOUT_QUEUEB = "fanout_queueB";
    public static final String FANOUT_QUEUEC = "fanout_queueC";
    public static final String TOPIC_QUEUEA = "topic_queueA";
    public static final String TOPIC_QUEUEB = "topic_queueB";

    public static final String DIRECT_ROUTING_KEY = "direct_routing_key";


    @Autowired
    private RabbitConfirmBack rabbitConfirmBack;

    @Autowired
    private RabbitReturnCallback rabbitReturnCallback;

    /**
     *创建direct模式队列
     * @return
     */
    @Bean(name = "directQueue")
    public Queue directQueue() {
        return new Queue(DIRECT_QUEUE);
    }

    /**
     *创建topic模式队列
     * @return
     */
    @Bean(name = "topicQueueA")
    public Queue topicQueueA() {
        return new Queue(TOPIC_QUEUEA);
    }

    /**
     *创建topic模式队列
     * @return
     */
    @Bean(name = "topicQueueB")
    public Queue topicQueueB() {
        return new Queue(TOPIC_QUEUEB);
    }

    /**
     *创建fanout模式队列
     * @return
     */
    @Bean(name = "fanoutQueueA")
    public Queue fanoutQueueA() {
        return new Queue(FANOUT_QUEUEA);
    }

    /**
     *创建fanout模式队列
     * @return
     */
    @Bean(name = "fanoutQueueB")
    public Queue fanoutQueueB() {
        return new Queue(FANOUT_QUEUEB);
    }

    /**
     *创建fanout模式队列
     * @return
     */
    @Bean(name = "fanoutQueueC")
    public Queue fanoutQueueC() {
        return new Queue(FANOUT_QUEUEC);
    }

    /**
     * 创建direct交换机
     * @return
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(DIRECT_EXCHANGE);
    }

    /**
     * 创建topic交换机
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * fanout是路由广播的形式,将会把消息发给绑定它的全部队列,即便设置了key,也会被忽略.
     * 因此我们发送到交换机的消息会使得绑定到该交换机的每一个Queue接收到消息,这个时候就算指定了路由键（routingKey）,
     * 或者规则,也会被忽略!
     * @return
     */
    @Bean
    FanoutExchange fanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE);
    }

    /**
     *direct交换机和队列绑定
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding directBinding(@Qualifier(value = "directQueue") Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(DIRECT_ROUTING_KEY);
    }

    /**
     * 使用#
     * topic交换机和队列绑定， topic转发信息主要是依据通配符,队列和交换机的绑定主要是依据一种模式(通配符+字符串),而当发送消息的时候,
     * 只有指定的Key和该模式相匹配的时候,消息才会被发送到该消息队列中.通配符：* 表示一个词，# 表示零个或多个词
     * 通配符是针对交换机的，消息进入交换机时进行通配符匹配，匹配完了才进入固定的队列
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding topicBindingB(@Qualifier(value = "topicQueueB") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.service.#");
    }

    /**
     * 使用*
     * @param queue
     * @param exchange
     * @return
     */
    @Bean
    public Binding topicBindingA(@Qualifier(value = "topicQueueA") Queue queue, TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("topic.service.A");
    }

    @Bean
    public Binding fanoutBindingA(@Qualifier(value = "fanoutQueueA") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding fanoutBindingB(@Qualifier(value = "fanoutQueueB") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding fanoutBindingC(@Qualifier(value = "fanoutQueueC") Queue queue, FanoutExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public RabbitTemplate rabbitTemplate (CachingConnectionFactory factory) {
        logger.info("caching factory: {}",factory.getChannelCacheSize());
        RabbitTemplate rabbitTemplate = new RabbitTemplate(factory);
        rabbitTemplate.setConfirmCallback(rabbitConfirmBack);
        rabbitTemplate.setReturnCallback(rabbitReturnCallback);
         /*Mandatory设置为true时，如果exchange根据自身类型和消息routingKey无法找到合适的queue存储消息，那么broker会调用basic.return方法
        将消息返回给生产者，如果mandatory设置为false，出现上述情况broker会将消息丢弃*/
        rabbitTemplate.setMandatory(true);
        //使用单独的发送连接，避免生产者由于各种原因阻塞而导致消费者同样阻塞
        rabbitTemplate.setUsePublisherConnection(true);
        return rabbitTemplate;
    }
}
