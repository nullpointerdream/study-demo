package com.mycrawler.mycrawler.rabbitMq;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class HelloSender implements RabbitTemplate.ReturnCallback{

    @Autowired
    private RabbitTemplate template;
    
    public void send(String adcas) {
        this.template.setReturnCallback(this);
        this.template.setConfirmCallback((correlationData, ack, cause) -> {
            if (!ack) {
                System.out.println("HelloSender消息发送失败" + cause + correlationData.toString());
            } else {
                System.out.println("HelloSender 消息发送成功 ");
            }
        });
     
      template.convertAndSend("queue",adcas);
    }

    public void sendObject(CsdnBlog csdnBlog) {
        template.convertAndSend("queue", csdnBlog);
    }

    public void sendTopic(String msg) {
        template.convertAndSend("exchange", "topic.message",msg);
    }

    public void sendFaout(String msg) {
        template.convertAndSend("fanoutExchange", "",msg);
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        System.out.println("sender return success" + message.toString()+"==="+i+"==="+s1+"==="+s2);
    }
}