package com.mycrawler.mycrawler.rocketMq;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-10-20 13:39
 **/
public class Producer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer =new DefaultMQProducer("lalla");
        producer.setNamesrvAddr("60.205.169.175:9876");

        producer.start();

        for (int i = 0; i < 100; i++) {
            //创建一条消息对象，指定其主题、标签和消息内容
            Message msg = new Message(
                    "topic_example_java" /* 消息主题名 */,
                    "TagA" /* 消息标签 */,
                    ("Hello Java demo RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET) /* 消息内容 */
            );

            //发送消息并返回结果
            SendResult sendResult = producer.send(msg);

            System.out.printf("%s%n", sendResult);
        }

        // 一旦生产者实例不再被使用则将其关闭，包括清理资源，关闭网络连接等
        producer.shutdown();


    }
}
