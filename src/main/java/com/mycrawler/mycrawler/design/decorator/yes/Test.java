package com.mycrawler.mycrawler.design.decorator.yes;

import com.mycrawler.mycrawler.design.decorator.no.SsoInterceptor;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-10-26 14:28
 **/
public class Test {
    public void test_LoginSsoDecorator() {
        LoginSsoDecorator ssoDecorator = new LoginSsoDecorator(new SsoInterceptor());
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放行" : " 拦截"));
    }

    public static void main(String[] args) {
        new Test().test_LoginSsoDecorator();
    }
}
