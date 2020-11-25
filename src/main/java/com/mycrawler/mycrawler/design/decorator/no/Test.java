package com.mycrawler.mycrawler.design.decorator.no;

import com.mycrawler.mycrawler.design.decorator.yes.LoginSsoDecorator;

/**
 * @program: study-demo
 * @description:
 * @author: chenjiale
 * @create: 2020-10-26 14:16
 **/
public class Test {
    public void test_LoginSsoDecorator() {
        LoginSsoInterceptor ssoDecorator = new LoginSsoInterceptor();
        String request = "1successhuahua";
        boolean success = ssoDecorator.preHandle(request, "ewcdqwt40liuiu", "t");
        System.out.println("登录校验：" + request + (success ? " 放行" : " 拦截"));
    }

    public static void main(String[] args) {
       new Test().test_LoginSsoDecorator();
    }
}
