package com.mycrawler.mycrawler.spring.ioc.factoryBean;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath*:applicationContext.xml",
})*/
public class BaseTest
{
    //@Resource
   // private Animal    animal;
    
    @Test
    public void aa()
    {
        ApplicationContext applicationContext =new ClassPathXmlApplicationContext("applicationContext.xml");
        Animal animal = (Animal) applicationContext.getBean("animal");
        animal.move();
    }
}