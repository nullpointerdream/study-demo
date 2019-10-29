package com.mycrawler.mycrawler.rabbitMq;

import com.mycrawler.mycrawler.MycrawlerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@SpringBootTest(classes= MycrawlerApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class TestRabbitMQ {
    
    @Autowired
    private HelloSender helloSender;

    @Test
    public void testRabbit() {
        helloSender.send("adcas");
    }

/*
    @Test
    public void testObject() {
        CsdnBlog csdnBlog =new CsdnBlog();
        csdnBlog.setCategory("123");
        csdnBlog.setTitle("标题");
        helloSender.sendObject(csdnBlog);
    }

    @Test
    public void testTopic() {
        CsdnBlog csdnBlog =new CsdnBlog();
        csdnBlog.setCategory("123");
        csdnBlog.setTitle("标题");
        helloSender.sendTopic("你好");
    }

    @Test
    public void testFaount() {
        CsdnBlog csdnBlog =new CsdnBlog();
        csdnBlog.setCategory("123");
        csdnBlog.setTitle("标题");
        helloSender.sendFaout("你好");
    }*/

}