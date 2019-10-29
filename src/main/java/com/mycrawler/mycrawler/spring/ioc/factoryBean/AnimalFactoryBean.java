package com.mycrawler.mycrawler.spring.ioc.factoryBean;

import org.springframework.beans.factory.FactoryBean;

/**
 * @program: mycrawler
 * @description:
 * @author: 陈家乐
 * @create: 2019-09-06 13:44
 **/

public class AnimalFactoryBean implements FactoryBean<Animal> {

    private String   animal;

    @Override
    public Animal getObject() throws Exception {
        if ("Monkey".equals(animal))
        {
            return new Monkey();
        }
        else if ("Tiger".equals(animal))
        {
            return new Tiger();
        }
        else
        {
            return null;
        }
    }

    @Override
    public Class<?> getObjectType() {
        return Animal.class;
    }

    public void setAnimal(String animal) {
        this.animal = animal;
    }
}
