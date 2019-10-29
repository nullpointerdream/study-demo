package com.mycrawler.mycrawler.rabbitMq;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;

import java.io.IOException;

//@Component
public class HelloReceive {

    @RabbitListener(queues="queue")    //监听器监听指定的Queue
    public void processC(String hello, Channel channel, Message message) {
        System.out.println("Receive:"+hello);
        try {
           // int a=1/0;
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
            System.out.println("receiver success");
        } catch (Exception e) {
            e.printStackTrace();
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
            System.out.println("receiver fail");
        }


    }

   /* @RabbitListener(queues="queue")    //监听器监听指定的Queue
    public void process(CsdnBlog str) {
        System.out.println("Receive:"+ JSONObject.toJSONString(str));
    }

    @RabbitListener(queues="topic.message")    //监听器监听指定的Queue
    public void process1(String str) {
        System.out.println("message:"+str);
    }
    @RabbitListener(queues="topic.messages")    //监听器监听指定的Queue
    public void process2(String str) {
        System.out.println("messages:"+str);
    }

    @RabbitListener(queues="fanout.A")
    public void processA(String str1) {
        System.out.println("ReceiveA:"+str1);
    }
    @RabbitListener(queues="fanout.B")
    public void processB(String str) {
        System.out.println("ReceiveB:"+str);
    }
    @RabbitListener(queues="fanout.C")
    public void processD(String str) {
        System.out.println("ReceiveC:"+str);
    }*/

}