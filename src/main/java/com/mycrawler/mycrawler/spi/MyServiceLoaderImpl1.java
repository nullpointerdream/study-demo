package com.mycrawler.mycrawler.spi;

public class MyServiceLoaderImpl1 implements IMyServiceLoader {
    @Override
    public String sayHello() {
        return "hello1";
    }

    @Override
    public String getName() {
        return "name1";
    }
}
