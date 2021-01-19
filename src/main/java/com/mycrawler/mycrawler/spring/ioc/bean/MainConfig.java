package com.mycrawler.mycrawler.spring.ioc.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-12-05 11:59
 **/
@Configuration
@Import({Color.class,MyImportSelector.class,MyImportBeanDefinitionRegistrar.class})
//@Conditional({LinuxCondtion.class})
//@ComponentScan(value = "com.mycrawler.mycrawler", includeFilters={@ComponentScan.Filter(type = FilterType.CUSTOM,classes = MyAutoFilter.class)},useDefaultFilters = false)
public class MainConfig {
    @Bean
    public Person person(){
        return new Person("config",12);
    }

    @Bean//默认ID=方法名
   // @Conditional({LinuxCondtion.class})
    public Person person2(){
        return new Person("config2",12);
    }

    @Bean
    public BlueFactoryBean blueFactoryBean(){
        return new BlueFactoryBean();
    }

}
