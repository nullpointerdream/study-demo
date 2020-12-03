package com.mycrawler.mycrawler.spi;

import java.util.ServiceLoader;

public class TestMyServiceLoader {

    public static void main(String[] argus) {
        ServiceLoader<IMyServiceLoader> serviceLoader = ServiceLoader.load(IMyServiceLoader.class, TestMyServiceLoader.class.getClassLoader());
        for (IMyServiceLoader myServiceLoader : serviceLoader) {
            System.out.println(myServiceLoader.getName() + myServiceLoader.sayHello());
        }
    }
}