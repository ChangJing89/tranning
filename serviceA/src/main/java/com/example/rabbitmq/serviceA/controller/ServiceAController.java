package com.example.rabbitmq.serviceA.controller;

import com.example.rabbitmq.serviceA.rabbitmq.MessageSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ServiceAController {

    @Autowired
    private MessageSender messageSender;

    @RequestMapping(value = "directMessage" , method = RequestMethod.GET)
    public void sendMessageByDirectExchange () {
        this.messageSender.sendMessageByDirectExchange();
    }

    @RequestMapping(value = "topicMessageA" , method = RequestMethod.GET)
    public void sendMessageByTopicExchangeA () {
        this.messageSender.sendMessageByTopicExchangeA();
    }

    @RequestMapping(value = "topicMessageB" , method = RequestMethod.GET)
    public void sendMessageByTopicExchangeB () {
        this.messageSender.sendMessageByTopicExchangeB();
    }

    @RequestMapping(value = "fanoutMessage" , method = RequestMethod.GET)
    public void sendMessageByFanoutExchange () {
        this.messageSender.sendMessageByFanoutExchangeB();
    }
}
