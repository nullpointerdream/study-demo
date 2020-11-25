package com.mycrawler.mycrawler.design.decorator.no;

public interface HandlerInterceptor {

    boolean preHandle(String request, String response, Object handler);

}