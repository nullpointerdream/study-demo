package com.mycrawler.mycrawler.design.decorator.yes;

import com.mycrawler.mycrawler.design.decorator.no.HandlerInterceptor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class LoginSsoDecorator extends SsoDecorator {


    private static Map<String, String> authMap = new ConcurrentHashMap<String, String>();

    static {
        authMap.put("huahua", "queryUserInfo");
        authMap.put("doudou", "queryUserInfo");
    }

    public LoginSsoDecorator(HandlerInterceptor handlerInterceptor) {
        super(handlerInterceptor);
    }

    @Override
    public boolean preHandle(String request, String response, Object handler) {
        boolean success = super.preHandle(request, response, handler);
        if (!success) return false;
        String userId = request.substring(8);
        String method = authMap.get(userId);
        System.out.println("模拟单点登录方法访问拦截校验："+userId+","+method);
        // 模拟方法校验
        return "queryUserInfo".equals(method);
    }
}